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

        // No errors, proceed to send the email
//        try {
//            // Create an email thread to send the email asynchronously
//            EmailThread emailThread = new EmailThread(toEmailAddress, subject, bodyHTML, replyTo);
//            emailThread.start();
//            emailThread.join(); // Wait for the email to be sent
//
//            String emailErrorMessage = emailThread.getErrorMessage();
//            if (emailErrorMessage.isEmpty()) {
//                // Success: Set success message and clear form values
//                req.setAttribute("messages", "<ul><li>Message sent successfully!</li></ul>");
//                req.setAttribute("email", replyTo);  // Retain the email in case of redirection
//                req.setAttribute("subject", subject); // Retain the subject
//                req.setAttribute("body", bodyHTML);  // Retain the body
//            } else {
//                // Failure: Set the error message
//                req.setAttribute("errorText", "<ul><li>" + emailErrorMessage + "</li></ul>");
//            }
//        } catch (InterruptedException e) {
//            // Thread interruption handling
//            req.setAttribute("errorText", "<ul><li>There was an error sending the email.</li></ul>");
//        }

        // Forward the request back to the JSP page for display
        req.getRequestDispatcher("contact.jsp").forward(req, resp);
    }

}

