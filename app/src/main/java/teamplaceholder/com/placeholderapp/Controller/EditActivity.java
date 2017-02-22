package teamplaceholder.com.placeholderapp.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import teamplaceholder.com.placeholderapp.R;

public class EditActivity extends AppCompatActivity {
    public static List<String> legalAccountTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    private EditText editText;
    private EditText editText2;
    private Spinner accountTypeSpinner;

    private String _email;
    private String _address;
    private String _accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        accountTypeSpinner = (Spinner) findViewById(R.id.spinner2);
        /*
          Set up the adapter to display the allowable accounts in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, legalAccountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);
    }

    protected void onSubmitPressed(View view) {
        this.finish();
    }

    /**
     * makes sure you want to leave the edit page
     * @param view
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
