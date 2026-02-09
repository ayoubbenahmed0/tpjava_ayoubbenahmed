

import java.sql.*;
class ParcManager {
    private String url = "jdbc:mysql://localhost:3306/db_parc";
    private String user = "root";
    private String password = "";
    private Connection cnn;
    private Statement st;

    public void connecter() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du pilote 
        cnn = DriverManager.getConnection(url, user, password); // Établissement de la connexion 
        st = cnn.createStatement();
    }

    // Insertion d'un véhicule (UPDATE) 
    public void ajouterVoiture(String matricule, String marque, double prix, boolean clim) throws Exception {
        connecter();
        String sql = "INSERT INTO Table_Vehicules VALUES ('" + matricule + "', '" + marque + "', " + prix + ", " + (clim ? 1 : 0) + ")";
        st.executeUpdate(sql);
        System.out.println("Véhicule ajouté avec succès.");
        fermer();
    }

    // Lecture du parc (SELECT) 
    public void listerParc() throws Exception {
        connecter();
        ResultSet res = st.executeQuery("SELECT * FROM Table_Vehicules");
        System.out.println("MATRICULE \t MARQUE \t PRIX");
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getDouble(3));
        }
        fermer();
    }
public double prixmoyy() throws Exception {
    connecter();

    ResultSet res = st.executeQuery("SELECT * FROM Table_Vehicules");

    int nbr = 0;
    double somme = 0;

    while (res.next()) {
        double prix = res.getDouble("prixbase");
        somme += prix;
        nbr++;
    }

    fermer();
    return somme / nbr;
}
public void lest_Parc_Filtre_Marque(String nom) throws Exception {
    connecter();
    /*ResultSet res = st.executeQuery("SELECT * FROM Table_Vehicules");
    System.out.println("test");
        while (res.next()) {
            if (res.getString("marque") == nom) {
                          int id = res.getInt("id");           // get the 'id' column
            String marque = res.getString("marque"); // get the 'marque' column
            String modele = res.getString("modele"); // get the 'modele' column

            System.out.println("ID: " + id + ", Marque: " + marque + ", Modèle: " + modele);  
            }else{
                System.out.println("model introuvable");
            }

        }*/
       try {
        String sql = "SELECT * FROM Table_Vehicules WHERE marque = '" + nom + "'";
        ResultSet res = st.executeQuery(sql);

        while (res.next()) {
            int id = res.getInt("matricule");           
            String marque = res.getString("marque"); 
            String modele = res.getString("modele"); 

            System.out.println("found -------ID: " + id + ", Marque: " + marque + ", Modèle: " + modele);
        }

        res.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
 }
private void fermer() throws Exception {
        if (st != null) st.close();
        if (cnn != null) cnn.close(); 
    }


}
public class TestParc {
   public static void main(String[] args) {
       ParcManager gestionnaire = new ParcManager();
       try {
           System.out.println("--- Test du Parc Automobile ---");
           // Ajout d'un nouveau véhicule
           gestionnaire.ajouterVoiture("980TN99", "BMW", 150000, true);
          
           // Affichage de la liste
           gestionnaire.listerParc();
           gestionnaire.lest_Parc_Filtre_Marque("bmw");
       } catch (Exception e) {
           System.out.println("Erreur : " + e.getMessage());
       }
       try {
        System.out.print(gestionnaire.prixmoyy());
    } catch (Exception e) {
        e.printStackTrace();
    }
   }
}