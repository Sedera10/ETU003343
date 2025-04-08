import java.sql.Connection;

import connexion.Connexion;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = Connexion.getConnection();
            System.out.println(con);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
