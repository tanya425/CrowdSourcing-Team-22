package teamplaceholder.com.placeholderapp.Model;

/**
 * Created by ashwiniiyer on 2/19/17.
 * Class with special privileges for maintenance of the system. Can delete accounts, ban users from
 * submitting reports, and unblock an account that's been locked for incorrect login attempts.
 * Can also view security log.
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
