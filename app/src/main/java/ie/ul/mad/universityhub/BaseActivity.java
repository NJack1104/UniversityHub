package ie.ul.mad.universityhub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

abstract class BaseActivity extends AppCompatActivity {
    protected FirebaseAuth mAuth;
    protected FirebaseUser mUser;
    protected static final String TAG = "UniversityHub";

    public void checkLogin()
    {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if (mUser == null)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
    public void signOutUserAccount()
    {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        showPopupMessage("Sign-out completed.");
                    }
                });
        checkLogin();
    }

    public void deleteUserAccount()
    {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        showPopupMessage("User account deleted.");
                    }
                });
        signOutUserAccount();
    }

    protected void showPopupMessage(String message)
    {
        Log.e(BaseActivity.TAG, message);

        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}
