package teamplaceholder.com.placeholderapp;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class User extends AccountHolder {
    private String username;
    private String password;
    private String typeOfWorker;

    public User(){

    }
    public User(String username, String password) {
        super(username, password);
        typeOfWorker = "User";
    }

    public String getWorkerType() {
        return typeOfWorker;
    }

    public void setWorkerType(String type) {
        typeOfWorker = type;
    }

    public void setPassword(String s) {
        password = s;
    }

    @Override
    public void setUsername(String user) {
        this.username = user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
