package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import edu.kirkwood.wackerproject.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/view-puppies")
public class ViewPuppy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String breed = req.getParameter("breed");
        List<Puppy> puppies;

        if (breed == null || breed.isEmpty()) {
            puppies = PuppyDAO.getAllPuppies();
        }else{
            puppies = PuppyDAO.getPuppiesByBreed(breed);
        }

        req.setAttribute("pageTitle", "View Puppies");

        req.setAttribute("puppies", puppies);
        req.getRequestDispatcher("WEB-INF/view-puppies.jsp").forward(req, resp);

    }
}