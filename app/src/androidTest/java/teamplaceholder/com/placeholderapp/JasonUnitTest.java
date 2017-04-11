package teamplaceholder.com.placeholderapp;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import teamplaceholder.com.placeholderapp.data.DBAccountHandler;
import teamplaceholder.com.placeholderapp.model.AccountHolder;
import teamplaceholder.com.placeholderapp.model.Admin;
import teamplaceholder.com.placeholderapp.model.Manager;
import teamplaceholder.com.placeholderapp.model.User;
import teamplaceholder.com.placeholderapp.model.Worker;

import static org.junit.Assert.*;

/**
 * Created by Jason Ngor on 4/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class JasonUnitTest {
    private DBAccountHandler db;

    @Before
    public void setup() {
        db = new DBAccountHandler(InstrumentationRegistry.getTargetContext());
        db.deleteAccount("quhwfruiqhefuianefiuean fasdjasod");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNonExistentAccount() {
        db.getAccount("quhwfruiqhefuianefiuean fasdjasod");
    }

    @Test
    public void testAdmin() {
        Admin acc = new Admin("quhwfruiqhefuianefiuean fasdjasod", "passwordtest", "emailtest", "addresstest", "Mr.");
        db.addAccount(acc);
        db.setProfile("quhwfruiqhefuianefiuean fasdjasod", "emailtest", "addresstest", "Mr.");
        AccountHolder accResult = db.getAccount("quhwfruiqhefuianefiuean fasdjasod");
        int accountCompare = compare(acc, accResult);
        assertEquals(0, accountCompare);

        db.deleteAccount("quhwfruiqhefuianefiuean fasdjasod");
    }

    @Test
    public void testManager() {
        Manager acc = new Manager("quhwfruiqhefuianefiuean fasdjasod", "passwordtest", "emailtest", "addresstest", "Mr.");
        db.addAccount(acc);
        db.setProfile("quhwfruiqhefuianefiuean fasdjasod", "emailtest", "addresstest", "Mr.");
        AccountHolder accResult = db.getAccount("quhwfruiqhefuianefiuean fasdjasod");
        int accountCompare = compare(acc, accResult);
        assertEquals(0, accountCompare);

        db.deleteAccount("quhwfruiqhefuianefiuean fasdjasod");
    }

    @Test
    public void testWorker() {
        Worker acc = new Worker("quhwfruiqhefuianefiuean fasdjasod", "passwordtest", "emailtest", "addresstest", "Mr.");
        db.addAccount(acc);
        db.setProfile("quhwfruiqhefuianefiuean fasdjasod", "emailtest", "addresstest", "Mr.");
        AccountHolder accResult = db.getAccount("quhwfruiqhefuianefiuean fasdjasod");
        int accountCompare = compare(acc, accResult);
        assertEquals(0, accountCompare);

        db.deleteAccount("quhwfruiqhefuianefiuean fasdjasod");
    }

    @Test
    public void testUser() {
        User acc = new User("quhwfruiqhefuianefiuean fasdjasod", "passwordtest", "emailtest", "addresstest", "Mr.");
        db.addAccount(acc);
        db.setProfile("quhwfruiqhefuianefiuean fasdjasod", "emailtest", "addresstest", "Mr.");
        AccountHolder accResult = db.getAccount("quhwfruiqhefuianefiuean fasdjasod");
        int accountCompare = compare(acc, accResult);
        assertEquals(0, accountCompare);

        db.deleteAccount("quhwfruiqhefuianefiuean fasdjasod");
    }

    public int compare(AccountHolder a, AccountHolder b) {
        if (!a.getAccountType().equals(b.getAccountType())) {
            return -1;
        } else if (!a.getUsername().equals(b.getUsername())) {
            return -2;
        } else if (!a.getPassword().equals(b.getPassword())) {
            return -3;
        } else if (!a.getEmail().equals(b.getEmail())) {
            return -4;
        } else if (!a.getAddress().equals(b.getAddress())) {
            return -5;
        } else if (!a.getTitle().equals(b.getTitle())) {
            return -6;
        } else {
            return 0;
        }
    }

}
