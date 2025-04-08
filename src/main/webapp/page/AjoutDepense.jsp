<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, models.Credit, models.Depense" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de depense</title>
</head>
<body>
    <h1>Nouvelle depense</h1>
    <% String mess = request.getParameter("message"); %>
    <form action="FormDepense" method="post">
        <div>
            <div>
                <%
                List<Credit> credits = (List<Credit>) request.getAttribute("credits");
                    if (credits != null && !credits.isEmpty()) {
                %>
                    <label for="credit">Credit :</label>
                    <select name="credit" id="credit">
                        <option value="">-- Sélectionnez un credit --</option>
                        <% for (Credit d : credits) { %>
                            <option value="<%= d.getId() %>"><%= d.getNom() %></option>
                        <% } %>
                    </select>
                <%
                    } else {
                %>
                    <p><%= mess %></p>
                <%
                    }
                %>
            </div>
        </div>
        <div>
            <div>
                <label for="montant">Montant : </label>
                <input type="number" id="montant" name="montant" placeholder="saisir le montant" required>
            </div>
        </div>
        <div>
            <div>
                <label for="daty">Date : </label>
                <input type="date" id="daty" name="daty" placeholder="Date d'ajout" required>
            </div>
        </div>

        <button type="submit" >Ajouter</button>
    </form>

    <div id="error">
        <%
            String erreur = (String) request.getAttribute("retour");
            if (erreur != null) {
        %>
            <p style="color:red;"><%= erreur %></p>
        <%
            }
        %>

    </div>
    <div class="container">
        <ul>
            <li><a href="Dashboard">Dashboard</a></li>
        </ul>
    </div>
</body>
</html>
