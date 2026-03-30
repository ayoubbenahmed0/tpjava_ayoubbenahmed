import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatriceImpl extends UnicastRemoteObject implements ICalculatrice {
    protected CalculatriceImpl() throws RemoteException {
        super();
    }

    @Override
    public double addition(double a, double b) { return a + b; }
    
    @Override
    public double soustraction(double a, double b) { return a - b; }
    
    @Override
    public double multiplication(double a, double b) { return a * b; }
    
    @Override
    public double division(double a, double b) throws RemoteException {
        if (b == 0) throw new RemoteException("Division par zéro impossible.");
        return a / b;
    }

    @Override
    public double puissance(double a, double b) {
        return Math.pow(a, b);
    }
}
