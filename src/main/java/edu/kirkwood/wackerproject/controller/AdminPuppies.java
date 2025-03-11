package edu.kirkwood.wackerproject.controller;
import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/puppies")
public class AdminPuppies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Puppy> puppies = PuppyDAO.getAllPuppies();
        System.out.println("Number of puppies in request: " + puppies.size());
        req.setAttribute("puppies", puppies);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin-puppies.jsp");
        dispatcher.forward(req, resp);

    }

}