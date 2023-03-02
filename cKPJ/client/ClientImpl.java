package cKPJ.client;

import cKPJ.server.Compute;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientImpl {

    private final Compute comp;

    public ClientImpl(Compute comp) {
        this.comp = comp;
    }

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


    private volatile boolean finished = false;
    private volatile int count = 0;
    /**
     * At-least-once implementation
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


    private void countAndSet(String message) throws RemoteException {
        count = comp.printAndCountLetters(message);
        finished = true;
    }



}
