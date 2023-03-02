import java.net.*;
import java.io.*;

public class aServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
        	System.out.println("Server is ready for requests");
			while (true) {
			    Socket socket = serverSocket.accept();
			    try {
			        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			        String message = in.readLine();
			        int count = printAndCountLetters(message);
			        out.println(count);
			        in.close();
			        out.close();
			        socket.close();
			    } 
			    catch (IOException e) {
			        e.printStackTrace();
			    }    
			}
		}
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
