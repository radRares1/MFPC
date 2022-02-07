package rad.mfpc.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


public class DataInitializer implements CommandLineRunner {

    @Autowired
    public final JdbcTemplate jdbc;

    public DataInitializer(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataInitializer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String sql = "INSERT INTO movie (title, id, genre, grade) VALUES ('movie3', 3 , 'genre3', 6.7)";
        System.out.println(jdbc);
        int rows = jdbc.update(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }

    }
}
