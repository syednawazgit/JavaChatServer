

import java.io.*;
import java.net.*;

public class ChatServer {
    private static final int PORT = 666;

    public static void main(String[] args) {
        System.out.println("Server starting...");
        System.out.println("Server is listening on port " + PORT + "\n");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                System.out.println("Type 'bye' to disconnect.\n");

                // Handle client communication
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),        
                  true);

                String clientMessage;
                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Client: " + clientMessage);

                    // Process client message
                    if (clientMessage.equalsIgnoreCase("bye")) {
                        output.println("Server: Goodbye!");
                        break;
                    } else {
                        output.println("Server: Message received - " + clientMessage);
                    }
                }

                // Clean up resources
                input.close();
                output.close();
                clientSocket.close();
                System.out.println("Client disconnected.\n");
            }
            }
             catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


