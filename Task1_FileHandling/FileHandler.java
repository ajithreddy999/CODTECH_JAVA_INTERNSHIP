import java.io.*;

public class FileHandler {
    public static void main(String[] args) {
        String fileName = "sample.txt";
        try {
            // Write to a file
            FileWriter writer = new FileWriter(fileName);
            writer.write("Hello, this is a sample file created using Java File Handling.\n");
            writer.write("This demonstrates writing and reading file content.\n");
            writer.close();
            System.out.println("File written successfully.");

            // Read from the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println("File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
