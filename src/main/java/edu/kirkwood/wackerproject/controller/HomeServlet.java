package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Testimonial> testimonials = TestimonialDAO.getAllTestimonials();
        req.setAttribute("testimonials", testimonials);
        Testimonial meyerTestimonial = TestimonialDAO.getTestimonial("Meyer Family Testimonial");
        req.setAttribute("meyerTestimonial", meyerTestimonial);

        Testimonial sannerTestimonial = TestimonialDAO.getTestimonial("Sanner Family Testimonial");
        req.setAttribute("sannerTestimonial", sannerTestimonial);

        Testimonial wackerTestimonial = TestimonialDAO.getTestimonial("Wacker Family Testimonial");
        req.setAttribute("wackerTestimonial", wackerTestimonial);

        Puppy puppy1 = PuppyDAO.getPuppy("ALit2Four");
        req.setAttribute("puppy1", puppy1);

        Puppy puppy2 = PuppyDAO.getPuppy("CLit2Five");
        req.setAttribute("puppy2", puppy2);

        Puppy puppy3 = PuppyDAO.getPuppy("GLit2Four");
        req.setAttribute("puppy3", puppy3);

        String adminEmail = System.getenv("ADMIN_EMAIL");
        if (adminEmail == null || adminEmail.isEmpty()) {
            adminEmail = System.getProperty("ADMIN_EMAIL");
        }


        req.setAttribute("pageTitle", "Home");
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
