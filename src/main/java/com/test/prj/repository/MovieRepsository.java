package com.test.prj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.prj.model.Movie;

@Repository
public interface MovieRepsository extends JpaRepository<Movie, Long> {

}
