package teamplaceholder.com.placeholderapp.model;

/**
 * Created by ashwiniiyer on 2/19/17.
 *      ACCOUNT HOLDER OBJECT
 */

/**
 * Base class for an generic account holder of the app
 */
public class AccountHolder {
    private String username;
    private String password;
    String accountType;
    private String email;
    private String address;
    private String title;

    public AccountHolder(String username, String password, String email, String address, String title) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.title = title;
    }

    /**
     * Constructor for Account Holder
     * @param username the username of the account holder
     * @param password the password of the account holder
     */
    public AccountHolder(String username, String password) {
        this(username, password, null, null, null);
    }

    /**
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username for this user
     * @param username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password for this user
     * @param password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * sets account type for this user
     * @param accountType of user
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email for this user
     * @param email of user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets address for this user
     * @param address of user
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets tile for this user
     * @param title of user
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
