package teamplaceholder.com.placeholderapp.Model;

/**
 * Created by Jason Ngor on 2/21/2017.
 */

public class Manager extends Worker {
    public Manager(String username, String password) {
        super(username, password);
        typeOfAccount = "Manager";
    }
}
