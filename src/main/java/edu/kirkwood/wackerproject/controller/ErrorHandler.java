package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.shared.email.EmailThread;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.servlet.RequestDispatcher.*;
import java.io.IOException;

@WebServlet("/errorHandler")
public class ErrorHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorMsg = "<strong>Error code:</strong> " + req.getAttribute(ERROR_STATUS_CODE) + "<br>";
        errorMsg += "<strong>Exception:</strong> " + req.getAttribute(ERROR_EXCEPTION_TYPE) + "<br>";
        errorMsg += "<strong>Message:</strong> " + req.getAttribute(ERROR_MESSAGE) + "<br>";
        errorMsg += "<strong>Servlet:</strong> " + req.getAttribute(ERROR_SERVLET_NAME) + "<br>";
        errorMsg += "<strong>Request URI:</strong> " + req.getAttribute(ERROR_REQUEST_URI) + "<br>";
        req.setAttribute("errorMsg", errorMsg);
        req.setAttribute("pageTitle", "Error");

        String requestedUrl = req.getRequestURL().toString();
        String servletPath = req.getServletPath();
        String subject = "An error occurred";
        String bodyHTML = "<p><strong>" + errorMsg + "</strong></p>";
        bodyHTML += "<p>The following URL could not be found:</p>";
        bodyHTML += "<p><strong>" + requestedUrl + "</strong></p>";
        bodyHTML += "<p><strong>Servlet Name:</strong> " + servletPath + "</p>";

        String adminEmail = System.getenv("ADMIN_EMAIL");

       // try {
         //   EmailThread emailThread = new EmailThread(adminEmail, subject, bodyHTML, "no-reply@yourdomain.com");
           // emailThread.start();
          //  emailThread.join();
       // } catch (InterruptedException e) {
            // Handle email sending failure (optional logging)
           // e.printStackTrace();
       // }



        req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
    }
}