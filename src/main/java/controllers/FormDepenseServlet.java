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

public class FormDepenseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false); 

            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("Login"); 
                return; 
            }

            Connection conn = Connexion.getConnection();
            List<Credit> credits = Credit.findAll(conn);
            String message = "Choisir un type";

            if(credits == null || credits.isEmpty()){
                message = "Aucuns credits trouvés";
            }

            request.setAttribute("message", message);
            request.setAttribute("credits", credits);

            request.getRequestDispatcher("/page/AjoutDepense.jsp").forward(request, response);            

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String creditParam = request.getParameter("credit");
        String montantParam = request.getParameter("montant");
        String datyParam = request.getParameter("daty");

        int credit = Integer.parseInt(creditParam);
        System.out.println("Id de credit = " + credit);
        double montant = Double.parseDouble(montantParam);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(datyParam, formatter);

        Date daty = Date.valueOf(localDate);

        try {
            Connection conn = Connexion.getConnection();
            if(montant > Depense.getResteCredit(conn, credit)){
                String retour = "Montant trop grand";
                request.setAttribute("retour", retour);
                request.getRequestDispatcher("/page/AjoutDepense.jsp").forward(request, response);
                return;
            }

            Depense newC = new Depense();
            newC.setIdCredit(credit);
            newC.setMontant(montant);
            newC.setDate(daty);
            newC.save();
            // Rediriger vers la page JSP
           
            response.sendRedirect("FormDepense");

        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}