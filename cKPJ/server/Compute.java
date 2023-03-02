package cKPJ.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
    int printAndCountLetters(String string) throws RemoteException;
}
