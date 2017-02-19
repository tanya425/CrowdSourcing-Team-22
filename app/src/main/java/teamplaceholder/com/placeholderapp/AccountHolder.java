package teamplaceholder.com.placeholderapp;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public abstract class AccountHolder {
    String username;
    String password;

    public  AccountHolder() {

    }
    public AccountHolder(String username, String password) {
        username = this.username;
        password = this.password;
    }
    abstract void setUsername(String user);
    abstract String getUsername();
    abstract void setPassword(String pass);
    abstract String getPassword();
}
