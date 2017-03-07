package teamplaceholder.com.placeholderapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

/**
 * Base class for an generic account holder of the app
 */
public class AccountHolder {
    protected String username;
    protected String password;
    protected String accountType;
    protected String email;
    protected String address;
    protected String title;

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
