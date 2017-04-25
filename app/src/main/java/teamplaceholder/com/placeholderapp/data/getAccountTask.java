package teamplaceholder.com.placeholderapp.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import teamplaceholder.com.placeholderapp.model.AccountHolder;

/**
 * Created by Jason Ngor on 4/24/2017.
 */

public class getAccountTask extends AsyncTask<String, Void, AccountHolder> {
    private static final List<String> titles = Arrays.asList("Mr.", "Mrs.", "Ms.", "Dr.");
    private DBAccountHandler db;
    private EditText emailText;
    private EditText addressText;
    private Spinner titleSpinner;

    public getAccountTask(Context context, EditText emailText, EditText addressText, Spinner titleSpinner) {
        db = new DBAccountHandler(context);
        this.emailText = emailText;
        this.addressText = addressText;
        this.titleSpinner = titleSpinner;
    }

    @Override
    protected AccountHolder doInBackground(String... params) {
        return db.getAccount(params[0]);
    }

    @Override
    protected void onPostExecute(AccountHolder acc) {
        emailText.setText(acc.getEmail());
        addressText.setText(acc.getAddress());
        titleSpinner.setSelection(titles.indexOf(acc.getTitle()));
    }
}
