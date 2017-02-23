package teamplaceholder.com.placeholderapp.Model;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class Admin extends AccountHolder {

    public Admin(String username, String password, String email, String address, String title) {
        super(username, password, email, address, title);
        accountType = "Admin";
    }
    public Admin(String username, String password) {
        this(username, password, null, null, null);
    }
}
