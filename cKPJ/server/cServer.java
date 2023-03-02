package cKPJ.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class cServer implements Compute {
    /**
     * Tracker for RPCs to make sure at-least-once is respected
     * */
    private int executionCount;

    public cServer() {
        super();
        this.executionCount = 0;
    }

    public static void main(String[] args) {
        try {
            String name = "Compute";
            Compute engine = new cServer();

            // register remote procedure to be discovered by clients
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

    /**
     * Implementation of remote procedure: goes through a string and counts
     * how many (Latin) letters are present
     * */
    @Override
    public int printAndCountLetters(String message) throws RemoteException {
        executionCount++;

        System.out.printf("[TASK %d]: Input: %s", executionCount, message);
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                count = count + 1;
            }
        }
        System.out.println("\tNumber of letters: " + count);
        return count;
    }
}