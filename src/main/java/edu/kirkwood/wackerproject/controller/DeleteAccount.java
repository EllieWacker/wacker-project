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
        User user = (User) session.getAttribute("activeUser");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (!user.getStatus().equals("active")) {
            req.setAttribute("errorMessage", "Your account is locked or inactive.");
            req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("pageTitle", "Delete Account");
        req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null) {
            req.setAttribute("errorMessage", "You must be logged in to delete your account.");
            req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
            return;
        }

        String email = req.getParameter("email");
        String confirmed = req.getParameter("confirmed");  // Check if confirmation already happened


        if (email == null || email.isEmpty()) {
            req.setAttribute("errorMessage", "You must enter your email.");
            req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
            return;
        }

        if (!email.equals(user.getEmail())) {
            req.setAttribute("errorMessage", "The email you entered does not match your account.");
            req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
            return;
        }

        if (confirmed == null || !confirmed.equals("true")) { // only show the confirmation after the errors have been checked
            req.setAttribute("showConfirmation", true);
            req.setAttribute("email", email);
            req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
            return;
        }

        boolean deleted = UserDAO.delete(user);
        if (deleted) {
            session.invalidate();
            session = req.getSession();
            session.setAttribute("flashMessageSuccess", "Your account has been successfully deleted.");
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("errorMessage", "An error occurred while deleting your account.");
            req.getRequestDispatcher("WEB-INF/delete-account.jsp").forward(req, resp);
        }
    }
}
