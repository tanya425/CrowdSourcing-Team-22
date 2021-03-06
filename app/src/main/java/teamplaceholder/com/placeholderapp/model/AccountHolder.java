package teamplaceholder.com.placeholderapp.model;

/**
 * Created by ashwiniiyer on 2/19/17.
 *      ACCOUNT HOLDER OBJECT
 *
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

    public AccountHolder(String username, String password) {
        this(username, password, null, null, null);
    }

    /**
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String account type
     */
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
