package com.revature.servlet;

import com.revature.orm.ormDriver;
import com.revature.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {
    private String url;
    private String user;
    private String pass;
    private Properties properties;
    private static InputStream input;
    MovieService service;

    public MovieServlet(){

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
        service = new MovieService(url,user,pass);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.getAllMovies(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.insertMovies(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.updateMovie(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.deleteMovie(req, resp);
    }

}
