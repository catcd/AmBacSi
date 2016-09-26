package vn.ahaay.ambacsi.ui.outsides;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.ahaay.ambacsi.api.ambacsi.OnCompleteListener;
import vn.ahaay.ambacsi.api.ambacsi.OnFailureListener;
import vn.ahaay.ambacsi.api.ambacsi.OnSuccessListener;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthInvalidCredentialsException;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiUser;
import vn.ahaay.ambacsi.ui.medicals.HomeActivity;
import vn.ahaay.ambacsi.ui.profiles.CreateProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Cat Can.
 * Last update on 26-Sep-2016
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(vn.ahaay.ambacsi.R.id.loginAppName) TextView loginAppName;
    @BindView(vn.ahaay.ambacsi.R.id.loginUsername) EditText loginUsername;
    @BindView(vn.ahaay.ambacsi.R.id.loginPassword) EditText loginPassword;
    @BindView(vn.ahaay.ambacsi.R.id.loginProgressBar) ProgressBar loginProgressBar;
    @BindView(vn.ahaay.ambacsi.R.id.loginForm) RelativeLayout loginForm;

    private AmBacSiAuth amBacSiAuth;

    private boolean onLoading = false;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_login);
        ButterKnife.bind(this);
        setAppNameFont();

        specifyInputMethodAction();

        amBacSiAuth = AmBacSiAuth.getInstance();
    }

    private void setAppNameFont() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/rotulona-hand.ffp.otf");
        loginAppName.setTypeface(font);
    }

    private void specifyInputMethodAction() {
        loginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                    handled = true;
                }
                return handled;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!onLoading) {
            stopLoading();
        } else {
            startLoading();
        }
    }

    @OnClick(vn.ahaay.ambacsi.R.id.loginSubmit) void login() {
        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }

        // get EditText
        String mUsername = loginUsername.getText().toString().trim();
        String mPassword = loginPassword.getText().toString().trim();

        // Check valid
        if (TextUtils.isEmpty(mUsername)) {
            loginUsername.requestFocus();
            loginUsername.setError(getResources().getString(vn.ahaay.ambacsi.R.string.login_error_field_required));
            return;
        } else if (!mUsername.matches("^[a-z0-9\\._\\-]{6,20}$")) {
            loginUsername.requestFocus();
            loginUsername.setError(getResources().getString(vn.ahaay.ambacsi.R.string.login_error_invalid_username));
            return;
        } else if (TextUtils.isEmpty(mPassword)) {
            loginPassword.requestFocus();
            loginPassword.setError(getResources().getString(vn.ahaay.ambacsi.R.string.login_error_field_required));
            return;
        } else if (mPassword.length() < 6) {
            loginPassword.requestFocus();
            loginPassword.setError(getResources().getString(vn.ahaay.ambacsi.R.string.login_error_too_short_password));
            return;
        }

        startLoading();
        // login with username and password
        // authenticate user
        // login with password and email
        // authenticate user
        amBacSiAuth.authWithUsernameAndPassword(mUsername, mPassword)
                .addOnCompleteListener(new OnCompleteListener<AmBacSiUser>() {
                    @Override
                    public void onComplete(@NonNull Task<AmBacSiUser> task) {
                        stopLoading();
                    }
                })
                .addOnFailureListener(new OnFailureListener<AmBacSiUser>() {
                    @Override
                    public void onFailure(@NonNull Task<AmBacSiUser> task) {
                            Log.w(TAG, "signInWithEmailAndPassword", task.getException());
                            // there was an error
                            if (task.getException() instanceof AmBacSiAuthInvalidCredentialsException
                                    && ((AmBacSiAuthInvalidCredentialsException) task.getException()).getErrorCode().equals(AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGN_IN_FAILED)) {
                                loginUsername.requestFocus();
                                loginUsername.setError(getResources().getString(vn.ahaay.ambacsi.R.string.login_error_failed));
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        getResources().getString(vn.ahaay.ambacsi.R.string.login_error_unknown),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                })
                .addOnSuccessListener(new OnSuccessListener<AmBacSiUser>() {
                    @Override
                    public void onSuccess(@NonNull Task<AmBacSiUser> task) {
                        AmBacSiUser __user = task.getResult();
                        try {
                            __user.downloadData();
                        } catch (AmBacSiAuthException _e) {
                            _e.printStackTrace();
                        }

                        Toast.makeText(LoginActivity.this,
                                getResources().getString(vn.ahaay.ambacsi.R.string.login_success),
                                Toast.LENGTH_SHORT).show();

                        String __name = __user.getDisplayName();
                        if (__name == null || __name.equals("")) {
                            startActivity(new Intent(LoginActivity.this, CreateProfileActivity.class));
                        } else {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                        finish();
                    }
                })
                .execute();
    }

    private void loginSuccess() {
        AmBacSiUser __user = null;
        try {
            __user = AmBacSiAuth.getLoginUser();
        } catch (AmBacSiAuthException _e) {
            _e.printStackTrace();
        }
        if (__user != null) {
            Toast.makeText(LoginActivity.this,
                    getResources().getString(vn.ahaay.ambacsi.R.string.login_success),
                    Toast.LENGTH_SHORT).show();

            String __name = __user.getDisplayName();
            if (__name == null || __name.equals("")) {
                startActivity(new Intent(LoginActivity.this, CreateProfileActivity.class));
            } else {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
            finish();
        }
    }

    @OnClick(vn.ahaay.ambacsi.R.id.loginAnonymous) void loginAnonymous() {
        // TODO login Anonymous
        Toast.makeText(LoginActivity.this, "Login Anonymous, coming soon!", Toast.LENGTH_LONG).show();
    }

    @OnClick(vn.ahaay.ambacsi.R.id.loginGoogle) void loginWithGoogle() {
        // TODO login Google
        Toast.makeText(LoginActivity.this, "Login with Google, coming soon!", Toast.LENGTH_LONG).show();
    }

    @OnClick(vn.ahaay.ambacsi.R.id.loginFacebook) void loginWithFacebook() {
        // TODO login Facebook
        Toast.makeText(LoginActivity.this, "Login with Facebook, coming soon!", Toast.LENGTH_LONG).show();
    }

    @OnClick(vn.ahaay.ambacsi.R.id.loginSignUp) void signUp() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    @OnClick(vn.ahaay.ambacsi.R.id.loginForgotPassword) void forgotPassword() {
        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
    }

    private void startLoading() {
        onLoading = true;
        loginForm.setVisibility(View.GONE);
        loginProgressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoading() {
        onLoading = false;
        loginForm.setVisibility(View.VISIBLE);
        loginProgressBar.setVisibility(View.GONE);
    }
}
