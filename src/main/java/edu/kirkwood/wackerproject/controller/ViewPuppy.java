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
        int limit = 10;
        try{
            limit = Integer.parseInt(limitStr);
        }catch(NumberFormatException e){
            if(limit < 0){
                limit = 10;
            }
        }
        req.setAttribute("limit", limit);


        // breed filter stuff
        String[] breedsArr = req.getParameterValues("breedFilter");
        String breedFilter = "";
        if(breedsArr != null && breedsArr.length > 0){
            breedFilter = String.join(",", breedsArr);
        }
        req.setAttribute("breedFilter", breedFilter);

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
        req.setAttribute("adopted", adopted);

        // Get the total product count
        int totalPuppies = PuppyDAO.getPuppyCount(breedFilter);
        int totalPages = totalPuppies / limit;
        if(totalPuppies % limit != 0) {
            totalPages++;
        }
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalPuppies", totalPuppies);

        // Get the page number
        String pageStr = req.getParameter("page");
        int page = 1;
        try{
            page = Integer.parseInt(pageStr);
        }catch(NumberFormatException e){

        }
        if(page > totalPages){
            page = totalPages;
        }else if(page < 1){
            page = 1;
        }
        req.setAttribute("page", page);
        int offset = (page - 1) * limit;

        // Calculate begin and end page links
        int pageLinks = 5;
        int beginPage = page / pageLinks * pageLinks > 0 ? page / pageLinks * pageLinks : 1;
        int endPage = beginPage + pageLinks - 1 > totalPages ? totalPages : beginPage + pageLinks - 1;
        req.setAttribute("beginPage", beginPage);
        req.setAttribute("endPage", endPage);


        //Determine the first and last puppies shown
        int firstPuppyShown = 1 + (page - 1) * limit;
        req.setAttribute("firstPuppyShown", firstPuppyShown);

        int lastPuppyShown = limit + (page - 1) * limit;
        if(lastPuppyShown > totalPuppies) {
            lastPuppyShown = totalPuppies;
        }
        req.setAttribute("lastPuppyShown", lastPuppyShown);

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