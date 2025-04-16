package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.ShoppingCart;
import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/adopt")
public class Adopt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String puppyID = req.getParameter("puppyID");

        boolean errorFound = false;
        String errorMsg = "";
        Puppy puppy = PuppyDAO.getPuppyByID(puppyID);
        if(puppy == null) {
            errorFound = true;
            errorMsg = "Puppy not found";
        }

        HttpSession session = req.getSession();
        if(errorFound){
            session.setAttribute("flashMessageDanger", errorMsg);
        }else{
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            if(cart == null) {
                cart = new ShoppingCart();
            }
            cart.addPuppy(puppy);
            session.setAttribute("cart", cart);
            session.setAttribute("flashMessageSuccess", "Adopted Successfully");
        }
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/view-puppies"));
    }
}
