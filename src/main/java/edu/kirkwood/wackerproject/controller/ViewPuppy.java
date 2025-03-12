package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/view-puppies")
public class ViewPuppy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String breed = req.getParameter("breed");

        // Log the breed parameter to confirm it's being received
        System.out.println("Received breed: " + breed);

        if (breed == null || breed.isEmpty()) {
            resp.sendRedirect("index.jsp");
            return;
        }

        try {
            // Fetch puppies by breed from the DAO
            List<Puppy> puppies = PuppyDAO.getPuppiesByBreed(breed);
            System.out.println("Number of puppies found: " + puppies.size());  // Log the number of puppies found

            req.setAttribute("pageTitle", "View Puppies");

            req.setAttribute("puppies", puppies);
            req.getRequestDispatcher("WEB-INF/view-puppies.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();  // Log the error stack trace
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching puppies.");
        }
    }
}