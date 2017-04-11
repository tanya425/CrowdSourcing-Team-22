package teamplaceholder.com.placeholderapp.model;

/**
 * Created by ashwiniiyer on 2/19/17.
 * Class with special privileges for maintenance of the system. Can delete accounts, ban users from
 * submitting reports, and unblock an account that's been locked for incorrect login attempts.
 * Can also view security log.
 */

public class Admin extends AccountHolder {

    /**
     * Constructor for Admin
     * @param username Admin username
     * @param password Admin password
     * @param email Admin email
     * @param address Admin address
     * @param title Admin title
     */
    public Admin(String username, String password, String email, String address, String title) {
        super(username, password, email, address, title);
        accountType = "Admin";
    }

    /**
     * Chained constructor
     * @param username Admin username
     * @param password Admin password
     */
    public Admin(String username, String password) {
        this(username, password, null, null, null);
    }
}
