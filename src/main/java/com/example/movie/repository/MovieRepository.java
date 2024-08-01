// Write your code here
package com.example.movie.repository;

import java.util.*;
import com.example.movie.model.Movie;

public interface MovieRepository {
    ArrayList<Movie> getallmovie();

    Movie addmovie(Movie addmoviebody);

    Movie getparticularmovie(int movieId);

    Movie updatemovie(int movieId, Movie updatebody);

    void deletemovie(int movieId);
}