package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

public class Credit {
    private int id;
    private String nom;
    private double montant;

    public Credit(){}
    public Credit(int id , String nom, double montant){
        this.id = id;
        this.nom = nom;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public double getMontant() {
        return montant;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

    //les fonctions

    public void save() throws SQLException {
        Connection connection = Connexion.getConnection();
        String query = "INSERT INTO credits (id,nom,montant) VALUES (?,?,?)";
        try{
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, this.getId());
            statement.setString(2, this.getNom());
            statement.setDouble(3, this.getMontant());
            statement.executeUpdate();
            
            statement.close();
            System.out.println("Credits inseré");
    
        } catch (Exception e) {
            throw e;

        } finally {
            connection.close();
        }
    }

    public static List<Credit> findAll(Connection connection) throws Exception{
        List<Credit> credits = new ArrayList<>();
        String query = "SELECT * FROM credits";
    
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Credit d = new Credit(rs.getInt("id"),rs.getString("nom"),rs.getDouble("montant"));
                d.setId(rs.getInt("id"));
                credits.add(d); 
                System.out.println("Credit trouvé : " + d.getNom());
            }
            rs.close();
            statement.close();
        }catch (Exception e) {
            throw e;
        } 
        return credits;
    }
}
