package b.server.src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
    <T> int executeTask(Task<T> t) throws RemoteException;
}
