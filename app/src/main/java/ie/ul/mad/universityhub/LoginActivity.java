package ie.ul.mad.universityhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity" ;
    private static final int RC_SIGN_IN = 1007;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handleLogin();
    }

    private void handleLogin()
    {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if (mUser != null)
        {
            showPopupMessage("Already signed in, \n" +
                    "displayName=" + mUser.getDisplayName() + ", \n" +
                    "email=" + mUser.getEmail() + ", \n" +
                    "uuid=" + mUser.getUid());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
            );
            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK)
            {
                mUser = FirebaseAuth.getInstance().getCurrentUser();
                showPopupMessage("Sign in successful, " +
                        "displayName=" + mUser.getDisplayName() + ", \n" +
                        "email=" + mUser.getEmail() + ", \n" +
                        "uuid=" + mUser.getUid());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
            else
            {
                if (response == null)
                {
                    showPopupMessage("sign in cancelled");
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK)
                {
                    showPopupMessage("no internet connection");
                    return;
                }
                // handle all other errors
                showPopupMessage("Sign-in error: " + response.getError());
            }
        }
    }

}
