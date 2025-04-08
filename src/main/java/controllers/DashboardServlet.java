package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import connexion.Connexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Credit;
import models.Depense;

public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            Connection conn = Connexion.getConnection();
            List<Credit> credits = Credit.findAll(conn);
            
            request.setAttribute("connexion", conn);
            request.setAttribute("credits", credits);

            request.getRequestDispatcher("/page/Liste.jsp").forward(request, response);            

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}