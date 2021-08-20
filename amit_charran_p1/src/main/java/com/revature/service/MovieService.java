package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Movies;
import com.revature.orm.util.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    private Configuration cfg;
    private String url;
    private String user;
    private String pass;
    private ObjectMapper mapper;

    public MovieService(String url, String user, String pass){
        cfg = new Configuration(url,user,pass);
        mapper = new ObjectMapper();
    }

    public void getAllMovies(HttpServletRequest req, HttpServletResponse res){
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getMovies());
            res.getOutputStream().print(json);

        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public void insertMovies(HttpServletRequest req, HttpServletResponse resp) {
        try {
//            Map<String, String[]> map = req.getParameterMap();
//            User user = mapper.convertValue(map, User.class);


            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            Movies movie = mapper.readValue(builder.toString(), Movies.class);
            int result = insert(movie);

            if(result != 0){
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else{

                // TODO: refactor with exception propagation to set better status codes
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.warn(e.getMessage());
        }
    }



    private List<Movies> getMovies(){
        List<Movies> answer = new ArrayList<>();
        List<Object> result = cfg.getAll(Movies.class);

        for(int i =0; i < result.size(); i++){
            Object o = result.get(i);
            if(o instanceof Movies){
                Movies toAdd = new Movies(((Movies) o).getMovieId(),
                        ((Movies) o).getMovieName(),
                        ((Movies) o).getGenre(),
                        ((Movies) o).getMovieLength(),
                        ((Movies) o).getMovieRating());

                answer.add(toAdd);
            }

        }

        return answer;
    }

    private int insert(Movies movie){
        return cfg.insertIntoTable(movie);
    }


}
