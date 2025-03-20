package edu.kirkwood.wackerproject.controller;


import edu.kirkwood.wackerproject.model.Litter;
import edu.kirkwood.wackerproject.model.LitterDAO;
import edu.kirkwood.wackerproject.model.User;
import edu.kirkwood.wackerproject.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-litter")
public class DeleteLitter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String litterID = req.getParameter("litterID");

        if (litterID == null || litterID.isEmpty()) {
            session.setAttribute("flashMessageWarning", "Invalid litter ID.");
            resp.sendRedirect(req.getContextPath() + "/litter-list");  // Redirect to the list of puppies
            return;
        }

        Litter litterFromDatabase = LitterDAO.getLitter(litterID);
        if (litterFromDatabase == null) {
            session.setAttribute("flashMessageWarning", "Litter not found.");
            resp.sendRedirect(req.getContextPath() + "/litter-list");  // Redirect to the list of puppies
            return;
        }

        req.setAttribute("litter", litterFromDatabase);
        req.setAttribute("pageTitle", "Delete Litter");
        req.getRequestDispatcher("WEB-INF/delete-litter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String litterID = req.getParameter("litterID");
        req.setAttribute("litterID", litterID);
        HttpSession session = req.getSession();

        boolean errorFound = false;
        Litter litterFromDatabase = LitterDAO.getLitter(litterID);

        if(litterFromDatabase == null) {
            errorFound = true;
            session.setAttribute("flashMessageWarning", "You entered an invalid litterID.");
        }

        if (!errorFound) {
            boolean deleted = LitterDAO.deleteLitter(litterFromDatabase);
            if (deleted) {
                session.setAttribute("flashMessageSuccess", "Litter has been successfully deleted.");
                resp.sendRedirect(req.getContextPath() + "/litters");  // Redirect to the list of litters
                return;
            } else {
                session.setAttribute("flashMessageDanger", "An error occurred while deleting litter.");
            }
        }

        req.setAttribute("pageTitle", "Delete Litter");
        req.getRequestDispatcher("WEB-INF/delete-litter.jsp").forward(req, resp);
    }

}