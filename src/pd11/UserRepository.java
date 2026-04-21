package pd11;

import java.util.HashMap;

public class UserRepository {
    HashMap<String, User> database = new HashMap<>();

    public boolean containsEmail(String email){
        return database.containsKey(email);
    }

    public void add(User user){
        database.put(user.getEmail(), user);
    }

    public void printDatabase(){
        database.forEach((email, user) -> System.out.println(email + " " + user));
    }
}
