package teamplaceholder.com.placeholderapp.Model;

/**
 * Created by Jason Ngor on 2/21/2017.
 * Manager class which can do everything a worker can do and view historical reports and trends of water purity.
 * Can also delete individual reports.
 */

public class Manager extends Worker {

    public Manager(String username, String password, String email, String address, String title) {
        super(username, password, email, address, title);
        accountType = "Manager";
    }
    public Manager(String username, String password) {
        this(username, password, null, null, null);
    }
}
