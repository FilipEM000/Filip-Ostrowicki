package pd20.Client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import pd20.model.Post;
import pd20.model.Todo;
import pd20.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public final class JsonPlaceholderClient {
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();

    public User getUser(Long userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), new TypeReference<>() {});
    }

    public List<Todo> getIncompleteTodoList(Long userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/todos"))
                .header("Accept", "application/json")
                .header("User-Agent", "JavaHttpClient")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        List<Todo> todos = objectMapper.readValue(response.body(), new TypeReference<>() {});

        return todos.stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }

    public List<Post> getLast3Posts(Long userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + userId + "/posts"))
                .header("Accept", "application/json")
                .header("User-Agent", "JavaHttpClient")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        List<Post> posts = objectMapper.readValue(response.body(), new TypeReference<>() {});

        return posts.stream()
                .sorted(Comparator.comparing(Post::getId, Comparator.reverseOrder()))
                .limit(3)
                .toList();
    }
}
