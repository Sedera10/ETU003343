<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, models.Credit, models.Depense, connexion.Connexion,java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de depense</title>
</head>
<body>
    <h1>DASHBOARD</h1>
    <%
        Connection conn = (Connection) request.getAttribute("connexion");
        List<Credit> credits = (List<Credit>) request.getAttribute("credits");
    %>
    <table border="1" width="600">
        <thead>
            <tr>
                <td>Type de crédit</td>
                <td>Montant (total dépensé)</td>
                <td>Reste</td>
            </tr>
        </thead>
        <tbody>
            <%
                if (credits != null && !credits.isEmpty()) {
                    for (Credit credit : credits) {
                        double total = Depense.getTotalDepenses(conn, credit.getId());
                        double reste = Depense.getResteCredit(conn, credit.getId());
            %>
            <tr>
                <td><%= credit.getNom() %></td>
                <td><%= total %></td>
                <td><%= reste %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3">Aucun crédit trouvé.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <div class="container">
        <ul>
            <li><a href="FormCredit">➕ Ajouter un crédit</a></li>
            <li><a href="FormDepense">💰 Ajouter une dépense</a></li>
        </ul>
    </div>

    <div style="float: right;">
        <a href="Login?dec=1"><button>Se deconnecter</button></a>  
    </div>
</body>
</html>
