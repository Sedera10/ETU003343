package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import connexion.Connexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Credit;

public class FormCreditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false); 

            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("Login"); 
                return; 
            }
            request.getRequestDispatcher("/page/AjoutCredit.jsp").forward(request, response);            

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String montantParam = request.getParameter("montant");
        double montant = Double.parseDouble(montantParam);

        try {

            Credit newC = new Credit();
            newC.setNom(nom);
            newC.setMontant(montant);
            newC.save();
            // Rediriger vers la page JSP
            if(newC.getNom() != null){
                request.setAttribute("message", "Nouveau credit ajouté");
            }
            response.sendRedirect("FormCredit");

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}