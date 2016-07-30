package com.ahay.ambacsi.ui.outsides;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.ahay.ambacsi.R;
import com.ahay.ambacsi.api.ambacsi.OnCompleteListener;
import com.ahay.ambacsi.api.ambacsi.OnFailureListener;
import com.ahay.ambacsi.api.ambacsi.OnSuccessListener;
import com.ahay.ambacsi.api.ambacsi.Task;
import com.ahay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import com.ahay.ambacsi.api.ambacsi.auth.AmBacSiAuthInvalidCredentialsException;
import com.ahay.ambacsi.api.ambacsi.auth.AmBacSiUser;
import com.ahay.ambacsi.ui.medicals.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ahay.ambacsi.constant.LoginConstant.LOGIN_USER_TYPE_PASSWORD;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_EMAIL;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_FULL_NAME;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_ID;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_TOKEN;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_TYPE;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_USERNAME;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.loginAppName) TextView loginAppName;
    @BindView(R.id.loginUsername) EditText loginUsername;
    @BindView(R.id.loginPassword) EditText loginPassword;
    @BindView(R.id.loginProgressBar) ProgressBar loginProgressBar;
    @BindView(R.id.loginForm) RelativeLayout loginForm;

    private AmBacSiAuth amBacSiAuth;
    private SharedPreferences sharedPreferencesLoginUser;

    private boolean onLoading = false;

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setAppNameFont();

        specifyInputMethodAction();

        amBacSiAuth = AmBacSiAuth.getInstance();

        // get shared preference editor
        sharedPreferencesLoginUser = getSharedPreferences(PREFS_LOGIN_USER, MODE_PRIVATE);
    }

    private void setAppNameFont() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/hemi-head-426.bold-italic.ttf");
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

    @OnClick(R.id.loginSubmit) void login() {
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
            loginUsername.setError(getResources().getString(R.string.login_error_field_required));
            return;
        } else if (!mUsername.matches("^[a-z0-9\\._\\-]{6,20}$")) {
            loginUsername.requestFocus();
            loginUsername.setError(getResources().getString(R.string.login_error_invalid_username));
            return;
        } else if (TextUtils.isEmpty(mPassword)) {
            loginPassword.requestFocus();
            loginPassword.setError(getResources().getString(R.string.login_error_field_required));
            return;
        } else if (mPassword.length() < 6) {
            loginPassword.requestFocus();
            loginPassword.setError(getResources().getString(R.string.login_error_too_short_password));
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
                                    && ((AmBacSiAuthInvalidCredentialsException) task.getException()).getErrorCode().equals(AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNIN_FAILED)) {
                                loginUsername.requestFocus();
                                loginUsername.setError(getResources().getString(R.string.login_error_failed));
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        getResources().getString(R.string.login_error_unknown),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                })
                .addOnSuccessListener(new OnSuccessListener<AmBacSiUser>() {
                    @Override
                    public void onSuccess(@NonNull Task<AmBacSiUser> task) {
                        // get ID, mail, full name
                        AmBacSiUser user = task.getResult();

                        sharedPreferencesLoginUser.edit()
                                .putString(PREFS_LOGIN_USER_ID, user.getUid())
                                .putString(PREFS_LOGIN_USER_USERNAME, user.getUsername())
                                .putString(PREFS_LOGIN_USER_EMAIL, user.getEmail())
                                .putString(PREFS_LOGIN_USER_FULL_NAME, user.getDisplayName())
                                .putString(PREFS_LOGIN_USER_TOKEN, user.getToken())
                                .putString(PREFS_LOGIN_USER_TYPE, LOGIN_USER_TYPE_PASSWORD)
                                .apply();

                        // TODO login with password complete download all data save to SQLite table

                        Toast.makeText(LoginActivity.this,
                                getResources().getString(R.string.login_success),
                                Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }
                })
                .execute();
    }

    @OnClick(R.id.loginAnonymous) void loginAnonymous() {
        // TODO login Anonymous
        Toast.makeText(LoginActivity.this, "Login Anonymous, coming soon!", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.loginGoogle) void loginWithGoogle() {
        // TODO login Google
        Toast.makeText(LoginActivity.this, "Login with Google, coming soon!", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.loginFacebook) void loginWithFacebook() {
        // TODO login Facebook
        Toast.makeText(LoginActivity.this, "Login with Facebook, coming soon!", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.loginSignUp) void signUp() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    @OnClick(R.id.loginForgotPassword) void forgotPassword() {
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
