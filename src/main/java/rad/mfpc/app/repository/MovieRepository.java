package rad.mfpc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rad.mfpc.app.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
