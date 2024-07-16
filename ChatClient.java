import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT =666;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Connected to server. Type 'bye' to disconnect.");
            System.out.println("Type 'bye' to disconnect.\n");

            // Create input and output streams for the socket
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("You: ");
                String userInput = scanner.nextLine();

                // Send message to server
                output.println(userInput);

                // Exit loop if user types "bye"
                if (userInput.equalsIgnoreCase("bye")) {
                	System.out.println("Server is disconnected");
                    break;
                }

                // Display server response
                String serverResponse = input.readLine();
                System.out.println(serverResponse);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}





