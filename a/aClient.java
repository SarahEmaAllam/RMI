import java.net.*;
import java.io.*;
import java.util.Scanner;

public class aClient {
    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter input string: ");
            String message = scanner.nextLine();
            Socket socket = null;
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                socket = new Socket("localhost", 5000);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
                int count = Integer.parseInt(in.readLine());
                System.out.println("Number of letters: " + count);
            } catch (IOException e) {
                e.printStackTrace();
            } 
            in.close();
            out.close();
            socket.close();
        }         
        
    }
}
