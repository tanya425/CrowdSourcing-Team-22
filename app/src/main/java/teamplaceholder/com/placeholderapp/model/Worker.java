package teamplaceholder.com.placeholderapp.model;

/**
 * Created by ashwiniiyer on 2/19/17.
 * Worker class that can do everything a user can, and can also report on water purity levels.
 */

public class Worker extends User {

    public Worker(String username, String password, String email, String address, String title) {
        super(username, password, email, address, title);
        accountType = "Worker";
    }

    /**
     *
     * @param username the identification id of the user for logging in
     * @param password the value that gives the user access to his/her account
     */
    public Worker(String username, String password) {
        this(username, password, null, null, null);
    }
}