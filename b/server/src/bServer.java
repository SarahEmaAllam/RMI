package b.server.src;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class bServer implements Compute {

    public bServer() {
        super();
    }

    public <T> int executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) {
        try {
            
            String name = "Compute";
            Compute engine = new bServer();
            Compute stub =
                (Compute) UnicastRemoteObject.exportObject(engine, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind(name, stub);
            System.out.println("bServer bound");
        } catch (Exception e) {
            System.err.println("bServer exception:");
            e.printStackTrace();
        }
    }
}