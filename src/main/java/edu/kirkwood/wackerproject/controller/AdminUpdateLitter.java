package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(value="/update-litter")
public class AdminUpdateLitter extends HttpServlet {
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
            req.setAttribute("errorMessage", "litterID is missing.");
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
            return;
        }

        List<FatherDog> fatherDogs = FatherDogDAO.getAllFatherDogs();
        if (fatherDogs == null || fatherDogs.isEmpty()) {
            System.out.println("No father dogs found");
        } else {
            req.setAttribute("fatherDogs", fatherDogs);
        }

        List<MotherDog> motherDogs = MotherDogDAO.getAllMotherDogs();
        if (motherDogs == null || motherDogs.isEmpty()) {
            System.out.println("No mother dogs found");
        } else {
            req.setAttribute("motherDogs", motherDogs);
        }

        try {
            Litter litter = LitterDAO.getLitter(litterID);

            if (litter == null) {
                req.setAttribute("errorMessage", "No litter found with ID: " + litterID);
                req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("litter", litter);
            req.setAttribute("litterID", litterID);
            req.getRequestDispatcher("WEB-INF/update-litter.jsp").forward(req, resp);

        }catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An unexpected error occurred.");
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String litterID = req.getParameter("litterID");
        String fatherDogID = req.getParameter("fatherDogID");
        String motherDogID = req.getParameter("motherDogID");
        String image = req.getParameter("image");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = null;
        String dateOfBirthString = req.getParameter("dateOfBirth");
        if (dateOfBirthString != null && !dateOfBirthString.isEmpty()) {
            try {
                java.util.Date utilDate = formatter.parse(dateOfBirthString);
                dateOfBirth = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date goHomeDate = null;
        String goHomeDateString = req.getParameter("goHomeDate");
        if (goHomeDateString != null && !goHomeDateString.isEmpty()) {
            try {
                java.util.Date utilDate = formatter.parse(goHomeDateString);
                goHomeDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int numberPuppies = Integer.parseInt(req.getParameter("numberPuppies"));

        List<FatherDog> fatherDogs = FatherDogDAO.getAllFatherDogs();
        if (fatherDogs == null || fatherDogs.isEmpty()) {
            System.out.println("No father dogs found");
        } else {
            req.setAttribute("fatherDogs", fatherDogs);
        }

        List<MotherDog> motherDogs = MotherDogDAO.getAllMotherDogs();
        if (motherDogs == null || motherDogs.isEmpty()) {
            System.out.println("No mother dogs found");
        } else {
            req.setAttribute("motherDogs", motherDogs);
        }

        Litter newLitter = new Litter();
        boolean validationError = false;

        Litter originalLitter = LitterDAO.getLitter(litterID);

        newLitter.setLitterID(litterID);

        // fatherDog ID validation
        try {
            newLitter.setFatherDogID(fatherDogID);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("fatherDogIDError", e.getMessage());
        }

        // motherDogID validation
        try {
            newLitter.setMotherDogID(motherDogID);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("motherDogIDError", e.getMessage());
        }

        // Image validation
        try {
            newLitter.setImage(image);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("imageError",  e.getMessage());
        }

        // Date of Birth validation
        try {
            newLitter.setDateOfBirth(dateOfBirth);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("dateOfBirthError", e.getMessage());
        }


        // Go Home Date validation
        if(goHomeDate.before(dateOfBirth)) {
            validationError = true;
            req.setAttribute("goHomeDateError", "Go home date must be after the date of birth.");
        }
        try {
            newLitter.setGoHomeDate(goHomeDate);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("goHomeDateError", e.getMessage());
        }


        // Number of Puppies validation
        try {
            if (numberPuppies <= 0) {
                throw new IllegalArgumentException("Number of puppies cannot be negative.");
            }
            newLitter.setNumberPuppies(numberPuppies);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("numberPuppiesError", e.getMessage());
        }
        
        if (!validationError) {
            boolean litterUpdated = LitterDAO.updateLitter(originalLitter, newLitter);
            req.setAttribute("litterUpdated", litterUpdated);
            if (litterUpdated) {
                req.setAttribute("litterUpdatedMessage", "Successfully updated litter!");
            } else {
                req.setAttribute("litterUpdatedMessage", "Error updating litter.");
            }
        }

        req.setAttribute("litter", newLitter);
        req.setAttribute("litterID", litterID);
        req.getRequestDispatcher("WEB-INF/update-litter.jsp").forward(req, resp);
    }
}
