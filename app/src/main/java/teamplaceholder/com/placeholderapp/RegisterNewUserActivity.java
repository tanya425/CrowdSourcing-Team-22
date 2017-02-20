package teamplaceholder.com.placeholderapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class RegisterNewUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("REGISTERACTIVITY","ENTERED ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //setContentView(R.layout.register);
    }
}
