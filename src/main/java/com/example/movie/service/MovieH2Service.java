/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.movie.service;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.model.MovieRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class MovieH2Service implements MovieRepository {
  @Autowired
  private JdbcTemplate db;

  @Override
  public ArrayList<Movie> getallmovie() {
    List<Movie> one = db.query("select * from MOVIELIST", new MovieRowMapper());
    ArrayList<Movie> two = new ArrayList<>(one);
    return two;
  }

  @Override
  public Movie addmovie(Movie addmoviebody) {
    db.update("insert into MOVIELIST(movieName,leadActor) values(?,?) ", addmoviebody.getMovieName(),
        addmoviebody.getLeadActor());
    return db.queryForObject("select * from MOVIELIST where movieName=? and  leadActor=? ", new MovieRowMapper(),
        addmoviebody.getMovieName(), addmoviebody.getLeadActor());
  }

  @Override
  public Movie getparticularmovie(int movieId) {
    try {
    return  db.queryForObject("select * from MOVIELIST where movieId=?", new MovieRowMapper(), movieId);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public Movie updatemovie(int movieId, Movie updatebody) {
    if (updatebody.getMovieName() != null) {
      db.update("update MOVIELIST set movieName=? where movieId=? ", updatebody.getMovieName(), movieId);
    }
    if (updatebody.getLeadActor() != null) {
      db.update("update MOVIELIST set leadActor=? where movieId=? ", updatebody.getLeadActor(), movieId);
    }
    return getparticularmovie(movieId);
  }

  @Override
  public void deletemovie(int movieId) {
    db.update("delete from MOVIELIST where  movieId=? ", movieId);
  }
}