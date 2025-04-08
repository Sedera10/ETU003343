package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

import java.sql.Date;
import java.sql.PreparedStatement;

public class Depense{
    private int id;
    private int idCredit;
    private double montant;
    private Date date;

    public Depense(){}
    public Depense(int id , int idCredit, double montant, Date date){
        this.id = id;
        this.idCredit = idCredit;
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public double getMontant() {
        return montant;
    }
    public Date getDate() {
        return date;
    }
    public int getIdCredit() {
        return idCredit;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    //les fonctions
    public void save() throws SQLException {
        Connection connection = Connexion.getConnection();
        String query = "INSERT INTO depenses (idCredit,montant,date) VALUES (?,?,?)";
        try{
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, this.getIdCredit());
            statement.setDouble(2, this.getMontant());
            statement.setDate(3, this.getDate());
            statement.executeUpdate();
            
            statement.close();
            System.out.println("Credits inseré");
    
        } catch (Exception e) {
            throw e;

        } finally {
            connection.close();
        }
    }

    public static List<Depense> findAll(Connection connection) throws Exception{
        List<Depense> Depenses = new ArrayList<>();
        String query = "SELECT * FROM depenses";
    
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Depense d = new Depense(rs.getInt("id"),rs.getInt("idCredit"),rs.getDouble("montant"),rs.getDate("date"));
                Depenses.add(d); 
                System.out.println("Depense trouvée : " + d.getMontant());
            }
            rs.close();
            statement.close();
        }catch (Exception e) {
            throw e;
        } 
        return Depenses;
    }


    //Notions de totals et de restes :
    public static List<Depense> getDepensesParCredit(Connection conn, int idCredit) throws SQLException {
        List<Depense> liste = new ArrayList<>();
        String sql = "SELECT * FROM depenses WHERE idCredit = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCredit);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Depense d = new Depense();
            d.setId(rs.getInt("id"));
            d.setIdCredit(rs.getInt("idCredit"));
            d.setMontant(rs.getDouble("montant"));
            d.setDate(rs.getDate("date"));  // peut être ignoré si non utilisé maintenant
            liste.add(d);
        }

        return liste;
    }

    // Fonction 2 : Calculer le montant total des dépenses pour un crédit
    public static double getTotalDepenses(Connection conn, int idCredit) throws SQLException {
        String sql = "SELECT SUM(montant) FROM depenses WHERE idCredit = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCredit);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getDouble(1);
        }
        return 0.0;
    }

    public static double getResteCredit(Connection conn, int idCredit) throws SQLException {
        String sql = "SELECT montant FROM credits WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCredit);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            double montantCredit = rs.getDouble("montant");
            double depenses = getTotalDepenses(conn, idCredit);
            return montantCredit - depenses;
        }

        return 0.0;
    }
}
