import java.sql.*;
class VolManager {
    private String url = "jdbc:mysql://localhost:3306/db_vols";
    private String user = "root";
    private String password = "";
    private Connection cnn;
    private Statement st;

    public void connecter() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du pilote 
        cnn = DriverManager.getConnection(url, user, password); // Établissement de la connexion 
        st = cnn.createStatement();
    }
   // Listing des vols
    public void listerVols() throws Exception {
        connecter();
        ResultSet res = st.executeQuery("SELECT * FROM Table_Vols");
        System.out.println("\nListe des vols : \n N° VOL \t DESTINATION \t NB PLACES DISPO.");
        while (res.next()) {
            System.out.println(res.getString(1) + "\t\t" + res.getString(2) + "\t\t" + res.getInt(3));
        }
        fermer();
    }

    public void reserverVol(String numVol) throws Exception {
        connecter();
        // Vérification des places (SELECT) 
        String str="SELECT placesDisponibles FROM Table_Vols WHERE numeroVol='" + numVol + "'";
        ResultSet res = st.executeQuery(str);
        

        if (res.next() && res.getInt(1) > 0) {
            // Mise à jour (UPDATE) 
           str = "UPDATE Table_Vols SET placesDisponibles = placesDisponibles - 1 WHERE numeroVol='" + numVol + "'";

            st.executeUpdate(str);
            System.out.println("Réservation : Place réservée avec succès sur le vol " + numVol);
            listerVols();
        } else {
            System.out.println("Réservation : Action Impossible ! Complet !");
        }
        fermer();
    }

    private void fermer() throws Exception {
        if (st != null) st.close();
        if (cnn != null) cnn.close(); // Libération des ressources
    }
}
public class TestVols {
    public static void main(String[] args) {
        VolManager gestionnaire = new VolManager();
        try {
            System.out.println("--- Test de Réservation de Vols ---");
            // Tentative de réservation pour le vol AF123 (créé dans InitDbVols)
            gestionnaire.reserverVol("EK202");
            
            // Tentative pour un vol qui n'a plus de place (ex: LH890)
            gestionnaire.reserverVol("LH890");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
