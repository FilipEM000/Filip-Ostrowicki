package pd20.model;

import java.util.List;

public record UserReport(User user, List<Todo> todos, List<Post> posts, Weather weather) {
}
