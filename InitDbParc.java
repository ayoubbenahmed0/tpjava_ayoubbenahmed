import java.sql.*;
public class InitDbParc {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cnn = DriverManager.getConnection(url, "root", "");
            Statement st = cnn.createStatement();

            st.executeUpdate("CREATE DATABASE IF NOT EXISTS db_parc");
            st.executeUpdate("USE db_parc");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Table_Vehicules (" +
                    "matricule VARCHAR(50) PRIMARY KEY, " +  "marque VARCHAR(50), " +
                    "prixBase DOUBLE, " + "climatisee TINYINT(1))");
            // Ajout de 5 enregistrements 
st.executeUpdate("INSERT IGNORE INTO Table_Vehicules VALUES ('123TN01', 'Toyota', 45000, 1)"); 
st.executeUpdate("INSERT IGNORE INTO Table_Vehicules VALUES ('456TN02', 'Volvo', 120000, 0)"); 
st.executeUpdate("INSERT IGNORE INTO Table_Vehicules VALUES ('789TN03', 'Peugeot', 35000, 1)"); 
st.executeUpdate("INSERT IGNORE INTO Table_Vehicules VALUES ('101TN04', 'Mercedes', 85000, 1)"); 
st.executeUpdate("INSERT IGNORE INTO Table_Vehicules VALUES ('202TN05', 'Isuzu', 95000, 0)"); 
            System.out.println("Base db_parc et table Table_Vehicules prêtes.");
            st.close(); cnn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
