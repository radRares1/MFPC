package rad.mfpc.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import rad.mfpc.app.model.Movie;
import rad.mfpc.app.service.MovieService;

import java.util.List;

@RestController
@RequestMapping(MovieController.base)
public class MovieController {

    public static final String base = "/mfpc/movie";

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    @GetMapping("/random")
    public Movie getRandomMovie(){return movieService.getRandomMovie();}

    @GetMapping("/genre")
    List<Movie> getMoviesByGenre(@RequestParam String genre){ return movieService.getMovieByGenre(genre);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie addMovie(@RequestBody Movie movie){

        System.out.println(movie);
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
