package com.test.prj.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.prj.model.Movie;

@Repository
public interface MovieRepsository extends JpaRepository<Movie, Long> {
	
	@Query("SELECT m FROM Movie m WHERE m.winner = ?1 ORDER BY year")
	Collection<Movie> findgProductorWithWinnerStatus(String winner);
}
