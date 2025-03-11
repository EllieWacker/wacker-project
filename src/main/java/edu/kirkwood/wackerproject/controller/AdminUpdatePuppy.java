package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value="/update-puppy")
public class AdminUpdatePuppy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String puppyID = req.getParameter("puppyID");  // Get puppyID from request
        if (puppyID == null || puppyID.isEmpty()) {
            req.setAttribute("errorMessage", "puppyID is missing.");
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
            return;  // Stop execution if puppyID is missing
        }
        try {
            // Attempt to retrieve the puppy from the database
            Puppy puppy = PuppyDAO.getPuppy(puppyID);

            if (puppy == null) {
                // Log and display message if no puppy is found
                System.out.println("No puppy found with ID: " + puppyID);
                req.setAttribute("errorMessage", "No puppy found with ID: " + puppyID);
                req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
                return;
            }

            // Set puppy in request if found
            req.setAttribute("puppy", puppy);
            req.setAttribute("puppyID", puppyID);
            req.getRequestDispatcher("WEB-INF/update-puppy.jsp").forward(req, resp);

        }catch (Exception e) {
            // Catch any unexpected exceptions
            e.printStackTrace();
            req.setAttribute("errorMessage", "An unexpected error occurred.");
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String puppyID = req.getParameter("puppyID");
        String breedID = req.getParameter("breedID");
        String litterID = req.getParameter("litterID");
        String medicalRecordID = req.getParameter("medicalRecordID");
        String image = req.getParameter("image");
        String gender = req.getParameter("gender");
        boolean adopted = Boolean.parseBoolean(req.getParameter("adopted"));
        boolean microchip = Boolean.parseBoolean(req.getParameter("microchip"));
        double price = Double.parseDouble(req.getParameter("price"));
        String breedDescription = req.getParameter("breedDescription");

        Puppy newPuppy = new Puppy();
        boolean validationError = false;

        Puppy originalPuppy = PuppyDAO.getPuppy(puppyID);

        newPuppy.setPuppyID(puppyID);

        // Breed ID validation
        try {
            newPuppy.setBreedID(breedID);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("breedIDError", e.getMessage());
        }

        // Litter ID validation
        try {
            newPuppy.setLitterID(litterID);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("litterIDError", e.getMessage());
        }

        // Medical Record ID validation
        try {
            newPuppy.setMedicalRecordID(medicalRecordID);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("medicalRecordIDError", e.getMessage());
        }

        // Image validation
        try {
            newPuppy.setImage(image);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("imageError",  e.getMessage());
        }

        // Gender validation
        try {
            newPuppy.setGender(gender);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("genderError", e.getMessage());
        }

        // Adopted validation
        String adoptedStr = req.getParameter("adopted");
        adopted = (adoptedStr != null && !adoptedStr.isEmpty()) ? Boolean.parseBoolean(adoptedStr) : false;  // Default to false if invalid
        try {
            newPuppy.setAdopted(adopted);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("adoptedError", e.getMessage());
        }

        // Microchip validation
        String microchipStr = req.getParameter("microchip");
        microchip = (microchipStr != null && !microchipStr.isEmpty()) ? Boolean.parseBoolean(microchipStr) : false;  // Default to false if invalid
        try {
            newPuppy.setMicrochip(microchip);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("microchipError", e.getMessage());
        }


        // Price validation
        try {
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative.");
            }
            newPuppy.setPrice(price);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("priceError", e.getMessage());
        }

        // Breed Description validation
        try {
            newPuppy.setBreedDescription(breedDescription);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("breedDescriptionError", e.getMessage());
        }

        if (!validationError) {
            boolean puppyUpdated = PuppyDAO.updatePuppy(originalPuppy, newPuppy);
            req.setAttribute("puppyUpdated", puppyUpdated);
            if (puppyUpdated) {
                req.setAttribute("puppyUpdatedMessage", "Successfully updated puppy!");
            } else {
                req.setAttribute("puppyUpdatedMessage", "Error updating puppy.");
            }
        }

        // Set the updated puppy to the request and forward to the JSP
        req.setAttribute("puppy", newPuppy);
        req.setAttribute("puppyID", puppyID);
        req.getRequestDispatcher("WEB-INF/update-puppy.jsp").forward(req, resp);
    }
}
