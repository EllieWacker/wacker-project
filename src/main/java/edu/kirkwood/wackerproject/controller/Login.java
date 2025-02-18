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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@WebServlet("/login")
public class Login extends HttpServlet {
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

        User user = new User();
        try {
            user = UserDAO.get(email);
        } catch (RuntimeException e) {
            req.setAttribute("loginFail", "An error occurred."); // Use e.getMessage() to see the SQLException
        }

        if (user == null) {
            req.setAttribute("loginFail", "No user found with that email address. <a href=\"signup\">Sign-up</a>"); // For security, it might be better to just say "No user found".
        } else {
            boolean passwordMatches = false;
            try {
                passwordMatches = BCrypt.checkpw(password, new String(user.getPassword()));            } catch (Exception e) {
                req.setAttribute("loginFail", "An error occurred."); // Use e.getMessage() to see the NoSuchAlgorithmException or InvalidKeySpecException
            }
            // No user found that matches the password
            if (!passwordMatches) {
                req.setAttribute("loginFail",  "The password you entered is incorrect."); // For security, it might be better to just say "No user found".
            } else {
                // Successful login
                user.setPassword(null); // Remove the password before setting the User object as a session attribute

                HttpSession session = req.getSession(); // Get existing HttSession object
                session.invalidate(); // Remove any existing session attributes
                session = req.getSession(); // Create new HttpSession
                session.setAttribute("activeUser", user);
                session.setAttribute("flashMessageSuccess", String.format("Welcome back%s!", (user.getFirstName() != null && !user.getFirstName().equals("") ? " " + user.getFirstName() : "")));

                resp.sendRedirect(req.getContextPath()); // Redirects to the home page
                return;
            }
        }


        String token = req.getParameter("cf-turnstile-response");

        URL url = new URL("https://challenges.cloudflare.com/turnstile/v0/siteverify");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        String secretKey = System.getenv("SECRET_KEY");
        String postData = "secret=" + URLEncoder.encode(secretKey, "UTF-8") +
                "&response=" + URLEncoder.encode(token, "UTF-8");
        try (OutputStream os = conn.getOutputStream()) {
            os.write(postData.getBytes(StandardCharsets.UTF_8));
        }

        Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name());
        String verificationResponse = scanner.useDelimiter("\\A").next();


        req.setAttribute("pageTitle", "Login");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }
}











