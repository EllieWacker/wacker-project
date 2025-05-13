package edu.kirkwood.wackerproject.controller;

import edu.kirkwood.wackerproject.model.Order;
import edu.kirkwood.wackerproject.model.OrderItem;
import edu.kirkwood.wackerproject.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import edu.kirkwood.wackerproject.model.OrderDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/order-details")
public class OrderDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String orderIdStr = req.getParameter("id");
        int orderId = 0;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {

        }
        Order order = OrderDAO.getOrder(orderId);
        ArrayList<OrderItem> orderItems = OrderDAO.getOrderItems(orderId);
        req.setAttribute("order", order);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("pageTitle", "Details of Order #" + orderId);
        req.getRequestDispatcher("WEB-INF/admin-order-details.jsp").forward(req, resp);
    }
}

