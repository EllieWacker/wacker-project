package edu.kirkwood.wackerproject.controller;
import edu.kirkwood.wackerproject.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/litters")
public class AdminLitters extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        List<Litter> litters = LitterDAO.getAllLitters();
        System.out.println("Number of Litters in request: " + litters.size());
        req.setAttribute("litters", litters);

        List<FatherDog> fatherDogs = FatherDogDAO.getAllFatherDogs();
        req.setAttribute("fatherDogs", fatherDogs);
        List<MotherDog> motherDogs = MotherDogDAO.getAllMotherDogs();
        req.setAttribute("motherDogs", motherDogs);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin-litters.jsp");
        dispatcher.forward(req, resp);

    }

}