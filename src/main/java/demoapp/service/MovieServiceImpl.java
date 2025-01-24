package demoapp.service;

import demoapp.model.entities.Customer;
import demoapp.model.entities.Movie;
import demoapp.model.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        ArrayList<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }
}
