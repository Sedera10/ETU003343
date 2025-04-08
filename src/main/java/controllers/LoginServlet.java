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


public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getParameter("dec") != null){
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                    response.sendRedirect("Dashboard");
                    return;
                }
            }
            request.getRequestDispatcher("/page/Login.jsp").forward(request, response);            
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
            String username = request.getParameter("user");
            String password = request.getParameter("password");

            if ("Sedera".equals(username) && "success".equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", username); 
                response.sendRedirect("Dashboard");
                return;
            } else {
                String retour = "Login ou mot de passe incorrect";
                request.setAttribute("retour", retour);
                request.getRequestDispatcher("/page/Login.jsp").forward(request, response);
            }

            // request.getRequestDispatcher("/page/Liste.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
