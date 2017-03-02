package teamplaceholder.com.placeholderapp.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import teamplaceholder.com.placeholderapp.R;

/**
 * Created by Jack on 2/12/2017.
 * This activity will be the main home page of the app
 */

public class HomeActivity extends AppCompatActivity{

    private SharedPreferences loginInfo;
    private SharedPreferences.Editor loginInfoEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loginInfo = getSharedPreferences("login_info", 0);
        loginInfoEditor = loginInfo.edit();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    /**
     * Called when the logout button is pressed
     * @param view is the view in which the logout button is pressed
     */
    protected void onLogoutPress(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Confirm Logout");
        alert.setMessage("Do you really wish to log out?");


        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                loginInfoEditor.putString("logged_user", null);
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final AlertDialog dialog = alert.create();
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    /**
     * Called when the edit info button is pressed
     * @param view is the view in which the logout button is pressed
     */
    protected void onEditPress(View view) {
        Intent intent = new Intent(HomeActivity.this, EditActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the add
     * @param view is the view in which the logout button is pressed
     */
    protected void onAddPress(View view) {
        Intent intent = new Intent(HomeActivity.this, SubWaterRepoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_report_toolbar_item) {
            this.onAddPress(findViewById(R.id.add_report_toolbar_item));
        } else if (id == R.id.edit_profile_toolbar_item) {
            this.onEditPress(findViewById(R.id.edit_profile_toolbar_item));
        } else if (id == R.id.logout_toolbar_item) {
            this.onLogoutPress(findViewById(R.id.logout_toolbar_item));
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when back button is pressed
     *      asks user if they want to discard changes and exit app if yes is selected
     */
    public void onBackPressed() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Confirm Logout");
        alert.setMessage("Do you really wish to log out?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final AlertDialog dialog = alert.create();
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.dismiss();
            }
        });
        alert.show();

    }
}
