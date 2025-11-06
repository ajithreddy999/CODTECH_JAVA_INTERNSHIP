import java.net.http.*;
import java.net.URI;

public class RestApiClient {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // GET request
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .GET()
                    .build();

            HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("GET Response Status: " + getResponse.statusCode());
            System.out.println("GET Response Body:\n" + getResponse.body());

            // POST request
            String jsonData = "{\"title\":\"Ajith's API Test\",\"body\":\"Learning REST API with Java HttpClient\",\"userId\":101}";
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("\nPOST Response Status: " + postResponse.statusCode());
            System.out.println("POST Response Body:\n" + postResponse.body());

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
