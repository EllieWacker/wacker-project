package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.shared.email.EmailThread;
import edu.kirkwood.shared.Validators;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value="/contact")
public class Email extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("contact.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Load the admin email from the .env file
        String toEmailAddress = Dotenv.load().get("ADMIN_EMAIL"); // Use your own email address

        // Get the form parameters
        String subject = req.getParameter("subject");
        String bodyHTML = req.getParameter("body");
        String replyTo = req.getParameter("email");


        // Validate inputs
        boolean error = false;
        String errorText = "";

        // Validate email address
        if (toEmailAddress.isEmpty() || !Validators.isValidEmail(toEmailAddress)) {
            errorText += "<li>Invalid recipient email address </li>";
            error = true;
        }

        // Validate email address
        if (replyTo.isEmpty() || !Validators.isValidEmail(replyTo)) {
            errorText += "<ul><li>Invalid sender email address: " + replyTo + "</li><ul>";
            error = true;
        }

        // Validate subject
        if (subject == null || subject.isEmpty()) {
            errorText += "<li>Subject is required</li>";
            error = true;
        }

        // Validate message body
        if (bodyHTML == null || bodyHTML.isEmpty()) {
            errorText += "<ul><li>Body is required</li></ul>";
            error = true;
        }

        // If there are errors, set the error messages and forward to the JSP
        if (error) {
            req.setAttribute("errorText", "<ul>" + errorText + "</ul>");
            req.setAttribute("email", replyTo);
            req.setAttribute("subject", subject);
            req.setAttribute("body", bodyHTML);
            req.getRequestDispatcher("contact.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("contact.jsp").forward(req, resp);
    }

}

