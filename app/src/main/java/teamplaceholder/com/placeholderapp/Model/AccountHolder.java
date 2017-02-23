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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
