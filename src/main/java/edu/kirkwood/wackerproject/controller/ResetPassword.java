package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("pageTitle", "Reset your password");
        req.getRequestDispatcher("WEB-INF/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("inputEmail");
        req.setAttribute("email", email);
        String message = UserDAO.passwordReset(email, req);
        req.setAttribute("passwordResetMsg", message);
        req.setAttribute("pageTitle", "Reset your password");
        req.getRequestDispatcher("WEB-INF/reset-password.jsp").forward(req, resp);
    }
}