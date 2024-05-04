package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPaso extends Remote {
    public void mirar() throws RemoteException;
}
