package demoapp.service;

import demoapp.model.entities.Customer;
import demoapp.model.entities.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie movie);
    Movie update(Movie movie);
    void deleteById(Long id);
    void delete(Movie movie);
}
