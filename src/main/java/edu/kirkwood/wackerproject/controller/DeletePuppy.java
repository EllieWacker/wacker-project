package edu.kirkwood.wackerproject.controller;


import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import edu.kirkwood.wackerproject.model.User;
import edu.kirkwood.wackerproject.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-puppy")
public class DeletePuppy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String puppyID = req.getParameter("puppyID");
        HttpSession session = req.getSession();

        if (puppyID == null || puppyID.isEmpty()) {
            session.setAttribute("flashMessageWarning", "Invalid puppy ID.");
            resp.sendRedirect(req.getContextPath() + "/puppy-list");  // Redirect to the list of puppies
            return;
        }

        Puppy puppyFromDatabase = PuppyDAO.getPuppy(puppyID);
        if (puppyFromDatabase == null) {
            session.setAttribute("flashMessageWarning", "Puppy not found.");
            resp.sendRedirect(req.getContextPath() + "/puppy-list");  // Redirect to the list of puppies
            return;
        }

        req.setAttribute("puppy", puppyFromDatabase);
        req.setAttribute("pageTitle", "Delete Puppy");
        req.getRequestDispatcher("WEB-INF/delete-puppy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String puppyID = req.getParameter("puppyID");
        req.setAttribute("puppyID", puppyID);
        HttpSession session = req.getSession();

        boolean errorFound = false;
        Puppy puppyFromDatabase = PuppyDAO.getPuppy(puppyID);

        if(puppyFromDatabase == null) {
            errorFound = true;
            session.setAttribute("flashMessageWarning", "You entered an invalid puppyID.");
        }

        if (!errorFound) {
            boolean deleted = PuppyDAO.deletePuppy(puppyFromDatabase);
            if (deleted) {
                session.setAttribute("flashMessageSuccess", "Puppy has been successfully deleted.");
                resp.sendRedirect(req.getContextPath() + "/puppies");  // Redirect to the list of puppies
                return;
            } else {
                session.setAttribute("flashMessageDanger", "An error occurred while deleting puppy.");
            }
        }

        req.setAttribute("pageTitle", "Delete Puppy");
        req.getRequestDispatcher("WEB-INF/delete-puppy.jsp").forward(req, resp);
    }

}