package teamplaceholder.com.placeholderapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ashwiniiyer on 2/19/17.
 */

public class AccountHolder implements Parcelable {
    protected String username;
    protected String password;
    protected String typeOfAccount;

    public AccountHolder(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private AccountHolder(Parcel in) {
        username = in.readString();
        password = in.readString();
        typeOfAccount = in.readString();
    }

    public void setPassword(String s) {
        password = s;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getWorkerType() {
        return typeOfAccount;
    }

    public void setWorkerType(String type) {
        typeOfAccount = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(typeOfAccount);
    }

    public static final Parcelable.Creator<AccountHolder> CREATOR
            = new Parcelable.Creator<AccountHolder>() {
        public AccountHolder createFromParcel(Parcel in) {return new AccountHolder(in);}

        public AccountHolder[] newArray(int size) {return new AccountHolder[size];}
    };
}
