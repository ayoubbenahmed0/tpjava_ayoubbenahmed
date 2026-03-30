import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServeurCalculatrice {
    public static void main(String[] args)  throws Exception {
        ICalculatrice calculatrice = new CalculatriceImpl();
        
        Registry registry = LocateRegistry.createRegistry(1099);
        
        registry.rebind("CalculatriceService", calculatrice);
        
        System.out.println("Serveur de Calculatrice prêt...");
    }
}
