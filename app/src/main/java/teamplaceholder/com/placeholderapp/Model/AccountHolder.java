package teamplaceholder.com.placeholderapp.Model;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class AccountHolder {
    protected String username;
    protected String password;
    protected String typeOfAccount;

    public  AccountHolder() {

    }
    public AccountHolder(String username, String password) {
        this.username = username;
        this.password = password;
    }
   /* abstract void setUsername(String user);
    abstract String getUsername();
    abstract void setPassword(String pass);
    abstract String getPassword();
*/
    public void setPassword(String s) {
        password = s;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getWorkerType() {
        return typeOfAccount;
    }

    public void setWorkerType(String type) {
        typeOfAccount = type;
    }
}
