package c.client.src;

import c.server.src.Compute;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class cClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        // find the RPC from the server
        String name = "Compute";
        Registry registry = LocateRegistry.getRegistry("localhost");
        Compute comp = (Compute) registry.lookup(name);

        new ClientImpl(comp).run();
    }

}