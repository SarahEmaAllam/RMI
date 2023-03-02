package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import server.Compute;
import server.Task;

public class bServer implements Compute {

    public bServer() {
        super();
    }

    public <T> int executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) {
        // if (System.getSecurityManager() == null) {
        //     System.setSecurityManager(new SecurityManager());
        // }
        try {
            String name = "Compute";
            Compute engine = new bServer();
            Compute stub =
                (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("bServer bound");
        } catch (Exception e) {
            System.err.println("bServer exception:");
            e.printStackTrace();
        }
    }
}