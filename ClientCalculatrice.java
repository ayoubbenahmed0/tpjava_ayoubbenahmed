import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientCalculatrice {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ICalculatrice calc = (ICalculatrice) registry.lookup("CalculatriceService");

            Scanner sc = new Scanner(System.in);
            System.out.print("Entrez le nombre a : ");
            double a = sc.nextDouble();
            System.out.print("Entrez le nombre b : ");
            double b = sc.nextDouble();

            System.out.println("Résultat Addition : " + calc.addition(a, b));
            System.out.println("Résultat Division : " + calc.division(a, b));
            System.out.println("Résultat Puissance : " + calc.puissance(a, b));
        } catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
