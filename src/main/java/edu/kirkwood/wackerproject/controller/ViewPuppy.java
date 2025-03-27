package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.Breed;
import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import edu.kirkwood.wackerproject.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/view-puppies")
public class ViewPuppy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String limitStr = req.getParameter("limit");
        int limit = 20;
        try{
            limit = Integer.parseInt(limitStr);
        }catch(NumberFormatException e){
            if(limit < 0){
                limit = 20;
            }
        }
        int offset = 0;

        // breed filter stuff
        String[] breedsArr = req.getParameterValues("breedFilter");
        String breedFilter = "";
        if(breedsArr != null && breedsArr.length > 0){
            breedFilter = String.join(",", breedsArr);
        }

//        //adopted filter stuff
        String adoptedStr = req.getParameter("adopted");
        Boolean adopted = null;
        if (adoptedStr != null) {
            if (adoptedStr.equals("true")) {
                adopted = true;
            } else if (adoptedStr.equals("false")) {
                adopted = false;
            }
        }

        // send it back
        req.setAttribute("breedFilter", breedFilter);
        req.setAttribute("limit", limit);
        req.setAttribute("adopted", adopted);


        List<Breed> breeds = PuppyDAO.getAllBreeds();
        req.setAttribute("breeds", breeds);

        String breed = req.getParameter("breed");
        List<Puppy> puppies;

        if (breed == null || breed.isEmpty()) {
            puppies = PuppyDAO.getAllPuppies(limit, offset, breedFilter, adopted);

        }else{
            puppies = PuppyDAO.getPuppiesByBreed(breed);
        }

        req.setAttribute("pageTitle", "View Puppies");

        req.setAttribute("selectedBreed", breed);
        req.setAttribute("puppies", puppies);
        req.getRequestDispatcher("WEB-INF/view-puppies.jsp").forward(req, resp);

    }
}