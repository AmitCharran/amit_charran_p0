package com.revature.servlet;

import com.revature.orm.ormDriver;
import com.revature.service.TvShowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(urlPatterns = "/tvshows")
public class TvShowServlet extends HttpServlet {
    private String url;
    private String user;
    private String pass;
    private Properties properties;
    private static InputStream input;
    TvShowService service;

    public TvShowServlet(){

        properties = new Properties();

        try {
            Class.forName("org.postgresql.Driver");
            input = ormDriver.class.getResourceAsStream("/credentials.properties");
            properties.load(input);

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        this.url = properties.getProperty("endpoint");
        this.user = properties.getProperty("username");
        this.pass = properties.getProperty("password");
        service = new TvShowService(url,user,pass);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.getAllTvShows(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.insertTvShows(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.updateTvShow(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.deleteTvShow(req, resp);
    }

}
