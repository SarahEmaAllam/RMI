package cKPJ.client.src;

import cKPJ.server.src.Compute;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main implementation of the client
 * */
public class ClientImpl {

    private final Compute comp;

    /**
     * @param comp computation task to execute remotely
     * */
    public ClientImpl(Compute comp) {
        this.comp = comp;
    }

    /**
     * Ask for input and run the RPC
     * */
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter input string: ");
            String message = scanner.nextLine();
            countAtLeastOnce(message);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }

    /**
     * Shared variable between threads that tracks whether one of the executions finished
     * */
    private volatile boolean finished = false;
    /**
     * Shared variable between threads that holds the execution result
     * */
    private volatile int count = 0;

    /**
     * At-least-once implementation. Call RPC every second until one call received the result.
     * */
    public void countAtLeastOnce(String message) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (finished) {
                System.out.println(count);
                executor.shutdownNow();
            }
            try {
                countAndSet(message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Execute RPC and, after finishing, set the volatile tracker to "true"
     * */
    private void countAndSet(String message) throws RemoteException {
        count = comp.printAndCountLetters(message);
        finished = true;
    }



}
