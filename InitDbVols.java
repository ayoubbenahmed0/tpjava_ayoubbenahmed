import java.sql.*;

public class InitDbVols {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cnn = DriverManager.getConnection(url, "root", "");
            Statement st = cnn.createStatement();

            st.executeUpdate("CREATE DATABASE IF NOT EXISTS db_vols");
            st.executeUpdate("USE db_vols");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Table_Vols (" +
                    "numeroVol VARCHAR(20) PRIMARY KEY, " +
                    "destination VARCHAR(100), " +
                    "placesDisponibles INT)");

            // Ajout de 5 enregistrements
            st.executeUpdate("INSERT IGNORE INTO Table_Vols VALUES ('AF123', 'Paris', 2)");
            st.executeUpdate("INSERT IGNORE INTO Table_Vols VALUES ('TU756', 'Tunis', 15)");
            st.executeUpdate("INSERT IGNORE INTO Table_Vols VALUES ('LH890', 'Francfort', 0)");
            st.executeUpdate("INSERT IGNORE INTO Table_Vols VALUES ('QR444', 'Doha', 50)");
            st.executeUpdate("INSERT IGNORE INTO Table_Vols VALUES ('EK202', 'Dubai', 12)");
            
            System.out.println("Base db_vols initialisée avec 5 vols.");
            st.close(); cnn.close();
        } catch (Exception e) { e.printStackTrace(); }
    } }
