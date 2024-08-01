/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here
package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.movie.model.Movie;
import com.example.movie.service.MovieH2Service;

@RestController
public class MovieController {
    @Autowired
    public MovieH2Service a;

    @GetMapping("/movies")
    public ArrayList<Movie> getallmovie() {
        return a.getallmovie();
    }

    @PostMapping("/movies")
    public Movie addmovie(@RequestBody Movie addmoviebody) {
        return a.addmovie(addmoviebody);
    }

    @GetMapping("/movies/{movieId}")
    public Movie getparticularmovie(@PathVariable("movieId") int movieId) {
        return a.getparticularmovie(movieId);
    }

    @PutMapping("/movies/{movieId}")
    public Movie updatemovie(@PathVariable("movieId") int movieId, @RequestBody Movie updatebody) {
        return a.updatemovie(movieId, updatebody);
    }

    @DeleteMapping("/movies/{movieId}")
    public void deletemovie(@PathVariable("movieId") int movieId) {
        a.deletemovie(movieId);
    }
}
