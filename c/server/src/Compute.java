package c.server.src;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Compute holds the computational tasks that can be run remotely
 * */
public interface Compute extends Remote {
    int printAndCountLetters(String string) throws RemoteException;
}
