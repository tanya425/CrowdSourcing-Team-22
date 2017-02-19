package teamplaceholder.com.placeholderapp;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class Worker extends User {
    public Worker(String username, String password) {
        super(username,password);
        typeOfAccount = "Worker";
    }
}
