package teamplaceholder.com.placeholderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the login button is pressed*
     * @param view is the where the button was clicked
     */
    protected void onLoginPress(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the login button is pressed*
     * @param view is the where the button was clicked
     */
    protected void onRegisterPress(View view) {
        Intent intent = new Intent(this, RegisterNewUserActivity.class);
        startActivity(intent);
    }
    /**
     * Override functionality
     */
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
