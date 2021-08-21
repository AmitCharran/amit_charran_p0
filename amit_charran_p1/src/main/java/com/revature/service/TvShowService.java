package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.TvShows;
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

public class TvShowService {
    private static final Logger logger = LoggerFactory.getLogger(TvShowService.class);

    private Configuration cfg;
    private String url;
    private String user;
    private String pass;
    private ObjectMapper mapper;

    public TvShowService(String url, String user, String pass){
        cfg = new Configuration(url,user,pass);
        mapper = new ObjectMapper();
    }

    public void getAllTvShows(HttpServletRequest req, HttpServletResponse res){
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getTvShows());
            res.getOutputStream().print(json);

        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public void insertTvShows(HttpServletRequest req, HttpServletResponse resp) {
        try {

            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            TvShows TvShow = mapper.readValue(builder.toString(), TvShows.class);
            int result = insert(TvShow);

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

    public void updateTvShow(HttpServletRequest req, HttpServletResponse resp) {
        StringBuilder builder = new StringBuilder();
        try {

            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            TvShows TvShow = mapper.readValue(builder.toString(), TvShows.class);

            if(TvShow.getTvId() != 0){
                boolean result = update(TvShow);

                if(result){
                    resp.setStatus(HttpServletResponse.SC_OK);

                    String JSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
                    resp.getWriter().print(JSON);
                }

            } else{
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteTvShow(HttpServletRequest req, HttpServletResponse resp) {
        boolean result = delete(Integer.parseInt(req.getParameter("userId")));

        if(result){
            resp.setStatus(HttpServletResponse.SC_OK);
        } else{
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    private boolean delete(int id){
        List<TvShows> TvShows = getTvShows();
        for(TvShows TvShow: TvShows){
            if(TvShow.getTvId() == id){
                cfg.deleteByID(TvShows.class, id);
                return true;
            }
        }
        return false;
    }

    private boolean update(TvShows TvShow){
        cfg.update(TvShow.getClass(), TvShow.getTvId(), TvShow.getTvShowName(), TvShow.getGenre(), TvShow.getLength());
        List<TvShows> allTvShows = getTvShows();

        for(TvShows m: allTvShows){
            if(m.compareWithoutTvShowId(TvShow)){
                return true;
            }
        }
        return false;
    }

    private List<TvShows> getTvShows(){
        List<TvShows> answer = new ArrayList<>();
        List<Object> result = cfg.getAll(TvShows.class);

        for(int i =0; i < result.size(); i++){
            Object o = result.get(i);
            if(o instanceof TvShows){
                TvShows toAdd = new TvShows(((TvShows) o).getTvId(),
                        ((TvShows) o).getTvShowName(),
                        ((TvShows) o).getGenre(),
                        ((TvShows) o).getLength());
                answer.add(toAdd);
            }

        }

        return answer;
    }

    private int insert(TvShows TvShow){
        cfg.insertIntoTable(TvShow.getClass(), TvShow.getTvShowName(), TvShow.getGenre(), TvShow.getLength());

        List<TvShows> allTvShows = getTvShows();

        for(TvShows m: allTvShows){
            if(m.compareWithoutTvShowId(TvShow)){
                return m.getTvId();
            }
        }

        return 0;
    }


}
