package pd20;

import pd20.Client.JsonPlaceholderClient;
import pd20.Client.OpenMeteoClient;
import pd20.model.*;

import java.io.IOException;
import java.util.List;

public class ApiGateway {
    JsonPlaceholderClient jsonPlaceholderClient = new JsonPlaceholderClient();
    OpenMeteoClient openMeteoClient = new OpenMeteoClient();

    public UserReport getUserReport(Long userId) throws IOException, InterruptedException {
        User user = jsonPlaceholderClient.getUser(userId);
        List<Todo> todos = List.of();
        List<Post> posts = List.of();
        Weather weather = null;

        try {
            todos = jsonPlaceholderClient.getIncompleteTodoList(userId);
        } catch (Exception e) {
            System.err.println("Nie udało się pobrać zadań dla usera " + userId);
        }

        try {
            posts = jsonPlaceholderClient.getLast3Posts(userId);
        } catch (Exception e) {
            System.err.println("Nie udało się pobrać postów dla usera " + userId);
        }

        try {
            weather = openMeteoClient.getWeather(user.getAddress().getGeo().getLat(), user.getAddress().getGeo().getLng());
        } catch (Exception e) {
            System.err.println("Nie udało się pobrać pogody dla usera " + userId);
        }

        return new UserReport(user, todos, posts, weather);
    }
}
