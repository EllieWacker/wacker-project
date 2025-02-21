package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.User;
import edu.kirkwood.wackerproject.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final int maxTries = 5;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Login");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String[] rememberMe = req.getParameterValues("rememberMe");
        req.setAttribute("email", email);
        req.setAttribute("password", password);
        req.setAttribute("rememberMe", (rememberMe != null && rememberMe[0].equals("true")) ? "true" : "");

        HttpSession session = req.getSession();
        Integer attempts = (Integer) session.getAttribute("loginAttempts");
        if(attempts == null){
            attempts = 0;
        }

        User user = null;
        try {
            user = UserDAO.get(email);
        } catch (RuntimeException e) {
            req.setAttribute("loginFail", "An error occurred."); // Use e.getMessage() to see the SQLException
        }

        if (user == null) {
            // No user found that matches the email
            req.setAttribute("loginFail", "No user found with that email address. <a href=\"signup\">Sign-up</a>"); // For security, it might be better to just say "No user found".
        } else {
            boolean passwordMatches = false;
            try {
                passwordMatches = BCrypt.checkpw(password, String.valueOf(user.getPassword()));
            } catch (Exception e) {
                req.setAttribute("loginFail", "An error occurred."); // Use e.getMessage() to see the NoSuchAlgorithmException or InvalidKeySpecException
            }
            // No user found that matches the password
            if (!passwordMatches) {
                attempts++;
                session.setAttribute("loginAttempts", attempts);
                int attemptsRemaining = maxTries - attempts;

                if(attempts >= maxTries){
                    user.setStatus("locked");
                    UserDAO.updateStatus(user.getEmail(), "locked");
                    req.setAttribute("loginFail", "Account is locked. Please reset your password to unlock.");
                }
                else{
                    req.setAttribute("loginFail",  "Incorrect password. You have " + attemptsRemaining + " attempts remaining."); // For security, it might be better to just say "No user found".
                }
            } else {
                if(!user.getStatus().equals("active")) {
                    // the users account is not active or locked
                    req.setAttribute("loginFail",  "Your account is locked or inactive. Please reset your password.");
                    req.setAttribute("pageTitle", "Login");
                    req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
                    return;
                };

                // Successful login
                user.setPassword(null); // Remove the password before setting the User object as a session attribute

                session = req.getSession(); // Get existing HttSession object
                session.invalidate(); // Remove any existing session attributes
                session = req.getSession(); // Create new HttpSession
                // session.removeAttribute("activeUser"); // Instead of destroying all attributes, remove only the ones necessary
                if(rememberMe != null && rememberMe[0].equals("true")) {
                    session.setMaxInactiveInterval(30 * 24 * 60 * 60); // represented in seconds
                }
                session.setAttribute("activeUser", user);
                session.setAttribute("flashMessageSuccess", String.format("Welcome back%s!", (user.getFirstName() != null && !user.getFirstName().equals("") ? " " + user.getFirstName() : "")));

                resp.sendRedirect(req.getContextPath()); // Redirects to the home page
                return;
            }
        }

        req.setAttribute("pageTitle", "Login");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }
}











