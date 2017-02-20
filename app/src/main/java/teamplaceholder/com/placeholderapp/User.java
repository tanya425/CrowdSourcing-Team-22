package teamplaceholder.com.placeholderapp;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class User extends AccountHolder {


    public User(){

    }
    public User(String username, String password) {
        super(username, password);
        typeOfAccount = "User";
    }

}
