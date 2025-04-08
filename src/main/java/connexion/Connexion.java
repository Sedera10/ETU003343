package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static final String URL = "jdbc:mysql://localhost:3306/examen"; // Remplace 'bank' par ton nom de base
    private static final String USER = "root"; // Remplace si ton user MySQL est différent
    private static final String PASSWORD = ""; // Idem pour le mot de passe

    private static Connection connection;

    /**
     * Cette méthode retourne une connexion à la base de données MySQL.
     * @return une instance de Connection
     * @throws SQLException si la connexion échoue
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Pas toujours nécessaire depuis JDBC 4.0, mais pour forcer explicitement :
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion MySQL établie avec succès.");
            } catch (ClassNotFoundException e) {
                System.err.println("Le driver MySQL n'a pas été trouvé : " + e.getMessage());
                throw new SQLException("Impossible de charger le driver MySQL.", e);
            }
        }
        return connection;
    }
}
