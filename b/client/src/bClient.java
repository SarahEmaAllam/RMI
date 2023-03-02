package b.client.src;

import b.server.src.Compute;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class bClient {
    public static void main(String args[]) {
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost");
            Compute comp = (Compute) registry.lookup(name);

            Message task = new Message();
            int count = comp.executeTask(task);
            System.out.println(count);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}