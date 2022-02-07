package rad.mfpc.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import rad.mfpc.app.model.Movie;

import java.util.List;

public interface MovieService {

    Movie getMovieById(Long id);

    List<Movie> getAllMovies();

    Movie addMovie(Movie movie);

    void delete(Long id);

    Movie getRandomMovie();

    List<Movie> getMovieByGenre(String genre);
}
