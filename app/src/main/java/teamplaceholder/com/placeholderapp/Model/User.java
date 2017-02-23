package teamplaceholder.com.placeholderapp.Model;

/**
 * Created by ashwiniiyer on 2/19/17.
 * Class which has basic privileges to the system. May submit report on water availability, and may
 * view available water sources.
 */

public class User extends AccountHolder {

    public User(String username, String password, String email, String address, String title) {
        super(username, password, email, address, title);
        accountType = "User";
    }
    public User(String username, String password) {
        this(username, password, null, null, null);
    }
}
