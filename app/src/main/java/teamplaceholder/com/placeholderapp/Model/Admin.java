package teamplaceholder.com.placeholderapp.Model;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class Admin extends AccountHolder {

    public Admin(String username, String password) {
        super(username, password);
        typeOfAccount = "Admin";
    }

}
