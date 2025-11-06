import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to server.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String userMsg, serverMsg;
            while (true) {
                System.out.print("You: ");
                userMsg = console.readLine();
                output.println(userMsg);
                if (userMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                serverMsg = input.readLine();
                if (serverMsg == null || serverMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Server closed the connection.");
                    break;
                }
                System.out.println("Server: " + serverMsg);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
