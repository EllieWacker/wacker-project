package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.*;
import jakarta.ejb.ApplicationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/add-litter")

public class AdminAddLitter extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            User userFromSession = (User)session.getAttribute("activeUser");
            if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
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
            req.getRequestDispatcher("WEB-INF/add-litter.jsp").forward(req, resp);
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
                    java.util.Date utilDate = formatter.parse(dateOfBirthString); // parse to util.Date
                    dateOfBirth = new java.sql.Date(utilDate.getTime()); // convert to sql.Date
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            Date goHomeDate = null;
            String goHomeDateString = req.getParameter("goHomeDate");
            if (goHomeDateString != null && !goHomeDateString.isEmpty()) {
                try {
                    java.util.Date utilDate = formatter.parse(goHomeDateString); // parse to util.Date
                    goHomeDate = new java.sql.Date(utilDate.getTime()); // convert to sql.Date
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

            req.setAttribute("litterID", litterID);
            req.setAttribute("fatherDogID", fatherDogID);
            req.setAttribute("motherDogID", motherDogID);
            req.setAttribute("image", image);
            req.setAttribute("dateOfBirth", dateOfBirth);
            req.setAttribute("goHomeDate", goHomeDate);
            req.setAttribute("numberPuppies", numberPuppies);


            Litter litter = new Litter();
            boolean validationError = false;

            if(litterID == null || litterID.equals("")) {
                validationError = true;
                req.setAttribute("litterIDError", "You must enter a litter ID");
            }
            else{
                try {
                    litter.setLitterID(litterID);
                } catch (IllegalArgumentException e) {
                    validationError = true;
                    req.setAttribute("litterIDError", e.getMessage());
                }
                if(!validationError && LitterDAO.getLitter(litterID) != null) {
                    validationError = true;
                    req.setAttribute("puppyIDError", "A puppy with that id already exists.");
                }
            }

            if(fatherDogID == null || fatherDogID.equals("")) {
                validationError = true;
                req.setAttribute("fatherDogIDError", "You must pick a father dog ID");
            }
            else{
                try {
                    litter.setFatherDogID(fatherDogID);
                } catch (IllegalArgumentException e) {
                    validationError = true;
                    req.setAttribute("fatherDogIDError", e.getMessage());
                }
            }

            if(motherDogID == null || motherDogID.equals("")) {
                validationError = true;
                req.setAttribute("motherDogIDError", "You must pick a mother dog ID");
            }
            else{
                try {
                    litter.setMotherDogID(motherDogID);
                } catch (IllegalArgumentException e) {
                    validationError = true;
                    req.setAttribute("motherDogIDError", e.getMessage());
                }
            }

            if(image == null || image.equals("")) {
                validationError = true;
                req.setAttribute("imageError", "You must enter an image");
            }
            else{
                try {
                    litter.setImage(image);
                } catch (IllegalArgumentException e) {
                    validationError = true;
                    req.setAttribute("imageError", e.getMessage());
                }
            }

            if(dateOfBirth == null) {
                validationError = true;
                req.setAttribute("dateOfBirthError", "You must enter a date of birth");
            }
            if (dateOfBirth.compareTo(new Date(System.currentTimeMillis())) > 0) { // compares the sql date to the current date and checks if the current date is later
                validationError = true;
                req.setAttribute("dateOfBirthError", "The date of birth must be in the past.");
            }
            else{
                try {
                    litter.setDateOfBirth(dateOfBirth);
                } catch (IllegalArgumentException e) {
                    validationError = true;
                    req.setAttribute("dateOfBirthError", e.getMessage());
                }
            }

            if(goHomeDate == null) {
                validationError = true;
                req.setAttribute("goHomeDateError", "You must enter a go home date");
            }
            else{
                try {
                    if(goHomeDate.before(dateOfBirth)) {
                        throw new IllegalArgumentException("Go home date must be after the date of birth.");
                    }
                    litter.setGoHomeDate(goHomeDate);
                } catch (IllegalArgumentException e) {
                    validationError = true;
                    req.setAttribute("goHomeDateError", e.getMessage());
                }
            }


            try {
                if (numberPuppies <= 0) {
                    validationError = true;
                    req.setAttribute("numberPuppiesError", "Number of puppies cannot be 0 or less.");
                } else {
                    litter.setNumberPuppies(numberPuppies);
                }

            } catch (IllegalArgumentException e) {
                validationError = true;
                req.setAttribute("numberPuppiesError", e.getMessage());
            }

            if(!validationError) {
                boolean litterAdded = false;
                try{
                    litterAdded = LitterDAO.addLitter(litter);
                }catch(RuntimeException e){
                    req.setAttribute("puppyAddedMessage", "Error adding litter." + litterAdded);
                }
                req.setAttribute("litterAdded", litterAdded);
                if(litterAdded) {
                    req.setAttribute("litterAddedMessage", "Successfully added litter!");
                } else {
                    req.setAttribute("litterAddedMessage", "Error adding litter." + litterAdded);
                }
            }
            req.setAttribute("pageTitle", "Add Litter");
            req.getRequestDispatcher("WEB-INF/add-litter.jsp").forward(req, resp);
        }
    }