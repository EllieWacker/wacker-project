package edu.kirkwood.wackerproject.controller;


import edu.kirkwood.wackerproject.model.User;
import edu.kirkwood.wackerproject.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-account")
public class DeleteAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser");
        if(user == null) {
            session.setAttribute("flashMessageWarning", "You must be logged in to delete your account.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login?redirect=delete-account"));
            return;
        } else if(user != null && !user.getStatus().equals("active")) {
            session.setAttribute("flashMessageDanger", "Your account is locked or inactive.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
            return;
        }
        req.setAttribute("pageTitle", "Delete Account");
        req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        req.setAttribute("email", email);

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser");

        boolean errorFound = false;
        User userFromDatabase = UserDAO.get(email);
        if(userFromDatabase == null) {
            errorFound = true;
            session.setAttribute("flashMessageWarning", "You entered the wrong email.");
        }

        if(!errorFound) {
            boolean deleted = UserDAO.delete(user);
            if (deleted) {
                session.invalidate(); // After deleting the user is logged out
                session = req.getSession(); // Makes a new session to show the message
                session.setAttribute("flashMessageSuccess", "Your account has been successfully deleted.");
                resp.sendRedirect(req.getContextPath() + "/"); // brings user back the main page
                return;
            } else {
                session.setAttribute("flashMessageDanger", "An error occurred while deleting your account.");
            }
        }

        req.setAttribute("pageTitle", "Delete Account");
        req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
    }
}