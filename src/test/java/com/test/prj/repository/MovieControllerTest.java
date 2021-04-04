package com.test.prj.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.prj.TexotestApplication;
import com.test.prj.model.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TexotestApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void setupTest() {
	}	
	
	@Test
	public void testIncludeAMovie() {
		Movie newMovie = new Movie(System.currentTimeMillis(), "title_test1", "studio_test1", "producer_test1", "winner_test1");
		
		HttpEntity<Movie> entity = new HttpEntity<Movie>(newMovie, headers);
		
		ResponseEntity<Movie> response = restTemplate.exchange(
				createURLWithPort("/api/v1/create-movie/"), 
				HttpMethod.POST, entity, Movie.class);
		
		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}


	@Test
	public void testGetMovies() {
		HttpEntity<Movie[]> entity = new HttpEntity<Movie[]>(null, headers);
		
		ResponseEntity<Movie[]> response = restTemplate.exchange(
				createURLWithPort("/api/v1/movies"), 
				HttpMethod.GET, entity, Movie[].class);
		
		Movie[] movies = null;
		if (response.getStatusCode() == HttpStatus.OK) {
			movies = response.getBody();
		}
		
		assertTrue(movies != null && movies.length > 0);
	}

}