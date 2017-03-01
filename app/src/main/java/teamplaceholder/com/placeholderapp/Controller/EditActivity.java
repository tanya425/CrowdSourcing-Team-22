package teamplaceholder.com.placeholderapp.Controller;

import android.content.SharedPreferences;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import teamplaceholder.com.placeholderapp.Data.DBHandler;
import teamplaceholder.com.placeholderapp.Model.AccountHolder;
import teamplaceholder.com.placeholderapp.R;

public class EditActivity extends AppCompatActivity {

    private SharedPreferences loginInfo;
    private String username;

    public static List<String> titles = Arrays.asList("Mr.", "Mrs.", "Ms.", "Dr.");
    private EditText emailText;
    private EditText addressText;
    private Spinner titleSpinner;

    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        loginInfo = getSharedPreferences("login_info", 0);
        username = loginInfo.getString("logged_user", null);

        db = new DBHandler(this);

        emailText = (EditText) findViewById(R.id.email_et);
        addressText = (EditText) findViewById(R.id.address_et);
        titleSpinner = (Spinner) findViewById(R.id.title_spinner);

        AccountHolder acc = db.getAccount(username);
        emailText.setText(acc.getEmail());
        addressText.setText(acc.getAddress());
        titleSpinner.setSelection(titles.indexOf(acc.getTitle()));

        /*
          Set up the adapter to display the allowable accounts in the spinner
         */
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, titles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        titleSpinner.setAdapter(adapter);


    }
    /**
     * onSubmitPressed is the button handler for the submit button
     * @param view is the current view being passed in
     */
    protected void onSubmitPressed(View view) {
        db.setProfile(username,
                emailText.getText().toString(),
                addressText.getText().toString(),
                titleSpinner.getSelectedItem().toString());
        this.finish();
    }

    /**
     * makes sure you want to leave the edit page
     * @param view is the current view being passed in
     */
    protected void onCancelPress(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(EditActivity.this);
        alert.setTitle("Discard Changes");
        alert.setMessage("Do you really wish to discard your changes?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                EditActivity.this.finish();
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
     * makes sure you want to leave the edit page
     */
    public void onBackPressed() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(EditActivity.this);
        alert.setTitle("Discard Changes");
        alert.setMessage("Do you really wish to discard your changes?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                EditActivity.this.finish();
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
