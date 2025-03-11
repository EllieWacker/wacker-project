package edu.kirkwood.wackerproject.controller;
import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import edu.kirkwood.wackerproject.model.User;
import edu.kirkwood.wackerproject.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-puppy")
public class AdminAddDog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/add-puppy.jsp").forward(req, resp);
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

        req.setAttribute("puppyID", puppyID);
        req.setAttribute("breedID", breedID);
        req.setAttribute("litterID", litterID);
        req.setAttribute("medicalRecordID", medicalRecordID);
        req.setAttribute("image", image);
        req.setAttribute("gender", gender);
        req.setAttribute("adopted", adopted);
        req.setAttribute("microchip", microchip);
        req.setAttribute("price", price);
        req.setAttribute("breedDescription", breedDescription);

        Puppy puppy = new Puppy();
        boolean validationError = false;

        if(puppyID == null || puppyID.equals("")) {
            validationError = true;
            req.setAttribute("puppyIDError", "You must enter a puppy ID");
        }
        else{
            try {
                puppy.setPuppyID(puppyID);
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("puppyIDError", e.getMessage());
            }
            if(!validationError && PuppyDAO.getPuppy(puppyID) != null) {
                validationError = true;
                req.setAttribute("puppyIDError", "A puppy with that id already exists.");
            }
        }

        if(breedID == null || breedID.equals("")) {
            validationError = true;
            req.setAttribute("breedIDError", "You must enter a breed ID");
        }
        else{
            try {
                puppy.setBreedID(breedID);
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("breedIDError", e.getMessage());
            }
        }

        if(litterID == null || litterID.equals("")) {
            validationError = true;
            req.setAttribute("litterIDError", "You must enter a litter ID");
        }
        else{
            try {
                puppy.setLitterID(litterID);
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("litterIDError", e.getMessage());
            }
        }

        if(medicalRecordID == null || medicalRecordID.equals("")) {
            validationError = true;
            req.setAttribute("medicalRecordIDError", "You must enter a medicalRecord ID");
        }
        else{
            try {
                puppy.setMedicalRecordID(medicalRecordID);
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("medicalRecordIDError", e.getMessage());
            }
        }

        if(image == null || image.equals("")) {
            validationError = true;
            req.setAttribute("imageError", "You must enter a image");
        }
        else{
            try {
                puppy.setImage(image);
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("imageError", e.getMessage());
            }
        }

        if(gender == null || gender.equals("")) {
            validationError = true;
            req.setAttribute("genderError", "You must enter a gender");
        }
        else{
            try {
                puppy.setGender(gender);
            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("genderError", e.getMessage());
            }
        }

        try {
            puppy.setAdopted(adopted);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("adoptedError", e.getMessage());
        }


        try {
            puppy.setMicrochip(microchip);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("microchipError", e.getMessage());
        }

        price = 0;
        try {
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative.");
            }
            puppy.setPrice(price);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("priceError", e.getMessage());
        }

        try {
            puppy.setBreedDescription(breedDescription);
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("breedDescriptionError", e.getMessage());
        }

        if(!validationError) {
            boolean puppyAdded = false;
            try{
                puppyAdded = PuppyDAO.addPuppy(puppy);
            }catch(RuntimeException e){
                req.setAttribute("puppyAddedMessage", "Error adding puppy." + puppyAdded);
            }
            req.setAttribute("puppyAdded", puppyAdded);
            if(puppyAdded) {
                req.setAttribute("puppyAddedMessage", "Successfully added puppy!");
            } else {
                req.setAttribute("puppyAddedMessage", "Error adding puppy." + puppyAdded);
            }
        }
        req.setAttribute("pageTitle", "Add Puppy");
        req.getRequestDispatcher("WEB-INF/add-puppy.jsp").forward(req, resp);
    }
}
