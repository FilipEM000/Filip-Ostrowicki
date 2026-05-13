package pd20.model;

import lombok.Data;

@Data
public class Todo {
    private Long userId;
    private Long id;
    private String title;
    private boolean completed;
}
