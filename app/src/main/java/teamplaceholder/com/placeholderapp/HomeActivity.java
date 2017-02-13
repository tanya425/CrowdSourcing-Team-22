package teamplaceholder.com.placeholderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jack on 2/12/2017.
 * This activity will be the main home page of the app
 */

public class HomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    /* Called when the logout button is pressed*/
    protected void onLogoutPress(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
