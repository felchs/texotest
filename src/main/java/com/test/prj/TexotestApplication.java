package com.test.prj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import com.test.prj.model.Movie;
import com.test.prj.repository.MovieRepsository;

import antlr.Utils;

@SpringBootApplication
public class TexotestApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class.getName()); 
	
	@Autowired
	private MovieRepsository movieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TexotestApplication.class, args);
	}
	
	public static List<String[]> fetchMoviesCSVLines(){
		ArrayList<String[]> lines = new ArrayList<String[]>();
        try {
            File file = ResourceUtils.getFile("classpath:movielist.csv");
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row = null;
            while ((row = csvReader.readLine()) != null) {
            	String[] data = row.split(";");
            	if (row.endsWith("yes")) {
            		lines.add(data);
            	} else {
            		// workaround the csv file is missing the yes/no info
            		String[] copyOf = Arrays.copyOf(data, data.length + 1);
            		copyOf[copyOf.length -1] = "no";
            		lines.add(copyOf);
            	}
            }
            csvReader.close();
            
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return lines;
    }

	@Override
	public void run(String... args) throws Exception {
		List<String[]> csvMovieCSVLines = fetchMoviesCSVLines();

		int debugNumInserts = 0;
		int debugNumErros = 0;
		for (String[] line : csvMovieCSVLines) {
			System.out.println(Arrays.toString(line));
			
			// if the first line is a header it skips
			if (line[0].equals("year")) {
				continue;
			}
			
			try {
				int yearStrToInt = Integer.parseInt(line[0]);
				ZonedDateTime yearZonedDate = ZonedDateTime.of(LocalDate.of(yearStrToInt, Month.JANUARY, 1), LocalTime.of(0, 0), GlobalConfigs.GLOBAL_ZONE);
				long yearTimeStamp = yearZonedDate.toInstant().toEpochMilli();
				
				String title = line[1];
				String studio = line[2];
				String producer = line[3];
				String winner = line[4];
				Movie newMovie = new Movie(yearTimeStamp, title, studio, producer, winner);
				movieRepository.save(newMovie);
				
				debugNumInserts++;
			} catch (Exception e) {
				debugNumErros++;
				LOGGER.error("Not possible to insert a new movie from csv line from file because of exception", e);
			}
		}
		
		LOGGER.debug("Num inserts: " + debugNumInserts + ", Num errors: " + debugNumErros);
	}

}
