<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, models.Credit, models.Depense, connexion.Connexion,java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de depense</title>
</head>
<body>
    <p>Il faut s'authentifier pour acceder aux ajouts</p>
    <h2>Connexion</h2>
    <form method="post" action="Login">
        <table>
            <tr>
                <td><label for="user">Nom d'utilisateur :</label></td>
                <td><input type="text" id="user" name="user" value="Sedera" required></td>
            </tr>
            <tr>
                <td><label for="password">Mot de passe :</label></td>
                <td><input type="password" id="password" name="password" value="success" required></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <button type="submit">Se connecter</button>
                </td>
            </tr>
        </table>
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
</body>
</html>
