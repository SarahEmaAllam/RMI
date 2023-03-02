package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.Compute;
import client.Message;

public class bClient {
    public static void main(String args[]) {
        // if (System.getSecurityManager() == null) {
        //     System.setSecurityManager(new SecurityManager());
        // }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);

            // Scanner scanner = new Scanner(System.in);
            // System.out.print("Enter input string: ");
            // String task = scanner.nextLine();
            // Message task = new Message();
            Message task = new Message();
            int count = comp.executeTask(task);
            System.out.println(count);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }    
}