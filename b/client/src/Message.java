package client;

import server.Task;
import java.io.Serializable;
import java.util.Scanner;

public class Message implements Task<String>, Serializable {

    private static final long serialVersionUID = 227L;

    /** digits of precision after the decimal point */
    private final String message;
    
    /**
     * Construct a task to calculate pi to the specified
     * precision.
     */
    public Message() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input string: ");
        String message = scanner.nextLine();
        this.message = message;
    }

    public int execute() {
        // this.count =  Integer.parseInt(this.message);
        return printAndCountLetters(message);
    }

    private static int printAndCountLetters(String message) {
    	System.out.println("Input: " + message);
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                count = count + 1;
            }
        }
        System.out.println("Number of letters: " + count);
        return count;
    }

}