package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.Puppy;
import edu.kirkwood.wackerproject.model.PuppyDAO;
import edu.kirkwood.wackerproject.model.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/cart")
public class Cart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Cart");
        req.getRequestDispatcher("WEB-INF/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String puppyID = req.getParameter("puppyID");
        Puppy puppy = PuppyDAO.getPuppy(puppyID);
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        if(cart != null && puppy != null) {
            if (action.equals("delete")) {
                cart.deletePuppy(puppy);
            }
            session.setAttribute("cart", cart);
        }
        req.setAttribute("pageTitle", "Cart");
        req.getRequestDispatcher("WEB-INF/cart.jsp").forward(req, resp);
    }
}
