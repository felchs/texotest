package com.test.prj.controller;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.test.prj.GlobalConfigs;
import com.test.prj.model.Movie;
import com.test.prj.model.Producer;
import com.test.prj.model.ProducerResult;
import com.test.prj.repository.MovieRepsository;

@RestController
@RequestMapping("/api/v1/")
public class MovieController {

	@Autowired
	private MovieRepsository movieRepository;

	@GetMapping("/movies")
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	@PostMapping("/create-movie")
	public Movie createMovie(@RequestBody Movie movie) {
		return movieRepository.save(movie);
	}
	
	private int getYear(long epochMilli) {
		Instant ofEpochMilli = Instant.ofEpochMilli(epochMilli);
		ZonedDateTime ofInstant = ZonedDateTime.ofInstant(ofEpochMilli, GlobalConfigs.GLOBAL_ZONE);
		return ofInstant.getYear();
	}
	
	@GetMapping("/productor-with-interval")
	public ProducerResult getProductorWithHighestIntervalBetweenTwoWins()
	{
		ProducerResult producerResult = new ProducerResult();
		
		String winnerStatus = "yes";
		Collection<Movie> winnerMovies = movieRepository.findgProductorWithWinnerStatus(winnerStatus);
		Map<String, List<Movie>> moviesByProducer = winnerMovies.stream().collect(Collectors.groupingBy(Movie::getProducer));
		
		// Obter o produtor com maior intervalo entre dois prêmios consecutivos,
		// e o que obteve dois prêmios mais rápido;
		
		
		Producer maxProducer = null;
		int mostDiffTimestamp = Integer.MIN_VALUE;
		Producer minProducer = null;
		int minDiffTimestamp = Integer.MAX_VALUE;
		

		Set<String> producerKeys = moviesByProducer.keySet();
		for (String producer : producerKeys) {
			List<Movie> moviesFromAProducer = moviesByProducer.get(producer);
			if (moviesFromAProducer.size() > 1) {
				moviesFromAProducer.sort(new Comparator<Movie>() {
					@Override
					public int compare(Movie movie0, Movie movie1) {
						return movie0.getYear() > movie1.getYear() ? 1 : -1;
					}
				});
				
				Movie firstMovie = moviesFromAProducer.get(0);
				Movie lastMovie = moviesFromAProducer.get(moviesFromAProducer.size() -1);
				
				int previousWin = getYear(firstMovie.getYear());
				int followingWin = getYear(lastMovie.getYear());
				
				int diff = Math.abs(followingWin - previousWin);
				if (diff > mostDiffTimestamp) {
					mostDiffTimestamp = diff;
					maxProducer = new Producer(producer, diff, previousWin, followingWin);
				}
				
				if (diff < minDiffTimestamp) {
					minDiffTimestamp = diff;
					minProducer = new Producer(producer, diff, previousWin, followingWin);
				}
			}
		}
		
		if (maxProducer == null) {
			throw new ResourceAccessException("The producer was not found");
		}
		
		producerResult.setMax(new Producer[] { maxProducer });
		producerResult.setMin(new Producer[] { minProducer });
		
		return producerResult;
	}
}
