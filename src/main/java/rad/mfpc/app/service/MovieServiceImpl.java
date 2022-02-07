package rad.mfpc.app.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rad.mfpc.app.model.Movie;
import rad.mfpc.app.repository.MovieRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepo;
    private final RestTemplate restTemplate;

    public MovieServiceImpl(MovieRepository movieRepo, RestTemplateBuilder restTemplateBuilder) {
        this.movieRepo = movieRepo;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepo.findById(id).get();
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Movie addMovie(Movie movie){
        String url = "http://www.omdbapi.com/?t="+ movie.getTitle() + "&apikey=ecd6b70c";
        String result =  this.restTemplate.getForObject(url, String.class);
        JsonElement root = JsonParser.parseString(result);
        JsonObject rootObject = root.getAsJsonObject();
        String poster = rootObject.get("Poster").getAsString();
        String title = rootObject.get("Title").getAsString();
        String genre = rootObject.get("Genre").getAsString();
        movie.setPosterPath(poster);
        movie.setTitle(title);
        movie.setGenre(genre);

        return movieRepo.save(movie);
    }

    @Override
    public void delete(Long id) {
        movieRepo.deleteById(id);
    }

    @Override
    public Movie getRandomMovie() {
        List<Movie> allMovies = movieRepo.findAll();
        Random rand = new Random();
        return allMovies.get(rand.nextInt(allMovies.size()));
    }

    @Override
    public List<Movie> getMovieByGenre(String genre) {
        return movieRepo.findAll().stream().filter(movie -> movie.getGenre().contains(genre)).collect(Collectors.toList());
    }
}
