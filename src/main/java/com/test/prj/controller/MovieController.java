package com.test.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.prj.model.Movie;
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
}
