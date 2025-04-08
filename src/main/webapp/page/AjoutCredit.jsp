<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, models.Credit" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de credit</title>
</head>
<body>
    <h1>Nouveau credit</h1>
    <form action="FormCredit" method="post">
        <div>
            <div>
                <label for="nom">Libélé : </label>
                <input type="text" id="nom" name="nom" placeholder="EX : frais" required>
            </div>
        </div>
        <div>
            <div>
                <label for="montant">Montant : </label>
                <input type="number" id="montant" name="montant" placeholder="saisir le montant" required>
            </div>
        </div>

        <button type="submit" >Ajouter</button>
    </form>
    <div class="container">
        <ul>
            <li><a href="Dashboard">Dashboard</a></li>
        </ul>
    </div>
</body>
</html>
