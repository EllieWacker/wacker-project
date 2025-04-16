package edu.kirkwood.wackerproject.controller;
import edu.kirkwood.wackerproject.model.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) req.getAttribute("cart");
        double salesTaxRate = 0.07;
        double shippingCost = 0;
        double discount = 0;
        req.setAttribute("pageTitle", "Checkout");
        req.getRequestDispatcher("WEB-INF/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] billingInfo = new String[8];
        billingInfo[0] = "Ellie";
        billingInfo[1] = "Wacker";
        billingInfo[2] = "Ellie@gmail.com";
        billingInfo[3] = "1234 West Street";
        billingInfo[4]= "Wacker Town";
        billingInfo[5] = "IA";
        billingInfo[6] = "55555";
        billingInfo[7] = "319-231-1243";

        String[] sameAddressCheck = req.getParameterValues("same_address");
        boolean sameAddress = false;
        if (sameAddressCheck != null) {
            sameAddress = true;
        }
        String[] ccInfo = new String[4];
        ccInfo[0] = "Ellie Wacker";
        ccInfo[1] = "424242424242";
        ccInfo[2] = "1234";
        ccInfo[3] = "123";
    }
}
