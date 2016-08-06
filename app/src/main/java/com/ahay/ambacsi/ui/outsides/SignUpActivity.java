package com.ahay.ambacsi.ui.outsides;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.ahay.ambacsi.api.ambacsi.auth.AmBacSiAuthUserCollisionException;
import com.ahay.ambacsi.api.ambacsi.auth.AmBacSiAuthWeakPasswordException;
import com.ahay.ambacsi.api.ambacsi.auth.AmBacSiUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.signUpAppName) TextView signUpAppName;
    @BindView(R.id.signUpEmail) EditText signUpEmail;
    @BindView(R.id.signUpUsername) EditText signUpUsername;
    @BindView(R.id.signUpPassword) EditText signUpPassword;
    @BindView(R.id.signUpRetypePassword) EditText signUpRetypePassword;
    @BindView(R.id.signUpProgressBar) ProgressBar signUpProgressBar;
    @BindView(R.id.signUpForm) RelativeLayout signUpForm;

    private AmBacSiAuth amBacSiAuth;

    private boolean onLoading = false;

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setAppNameFont();

        specifyInputMethodAction();

        amBacSiAuth = AmBacSiAuth.getInstance();
    }

    private void setAppNameFont() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/hemi-head-426.bold-italic.ttf");
        signUpAppName.setTypeface(font);
    }

    private void specifyInputMethodAction() {
        signUpRetypePassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    register();
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

    @OnClick(R.id.signUpSubmit) void register() {
        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }

        // get EditText
        String mEmail = signUpEmail.getText().toString().trim();
        final String mUsername = signUpUsername.getText().toString().trim();
        String mPassword = signUpPassword.getText().toString().trim();
        String mRetypePassword = signUpRetypePassword.getText().toString().trim();

        // Check valid
        if (TextUtils.isEmpty(mEmail)) {
            signUpEmail.requestFocus();
            signUpEmail.setError(getResources().getString(R.string.sign_up_error_field_required));
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            signUpEmail.requestFocus();
            signUpEmail.setError(getResources().getString(R.string.sign_up_error_invalid_email));
            return;
        } else if (TextUtils.isEmpty(mUsername)) {
            signUpUsername.requestFocus();
            signUpUsername.setError(getResources().getString(R.string.sign_up_error_field_required));
            return;
        } else if (!mUsername.matches("^[a-z0-9\\._\\-]{6,20}$")) {
            signUpUsername.requestFocus();
            signUpUsername.setError(getResources().getString(R.string.sign_up_error_invalid_username));
            return;
        } else if (TextUtils.isEmpty(mPassword)) {
            signUpPassword.requestFocus();
            signUpPassword.setError(getResources().getString(R.string.sign_up_error_field_required));
            return;
        } else if (mPassword.length() < 6) {
            signUpPassword.requestFocus();
            signUpPassword.setError(getResources().getString(R.string.sign_up_error_too_short_password));
            return;
        } else if (TextUtils.isEmpty(mRetypePassword)) {
            signUpRetypePassword.requestFocus();
            signUpRetypePassword.setError(getResources().getString(R.string.sign_up_error_field_required));
            return;
        } else if (!mPassword.equals(mRetypePassword)) {
            signUpRetypePassword.requestFocus();
            signUpRetypePassword.setError(getResources().getString(R.string.sign_up_error_not_matched_password));
            return;
        }

        startLoading();
        //create user
        amBacSiAuth.createUserWithUsernameAndPassword(mUsername, mPassword, mEmail)
                .addOnCompleteListener(new OnCompleteListener<AmBacSiUser>() {
                    @Override
                    public void onComplete(@NonNull Task<AmBacSiUser> task) {
                        stopLoading();
                    }
                })
                .addOnFailureListener(new OnFailureListener<AmBacSiUser>() {
                    @Override
                    public void onFailure(@NonNull Task<AmBacSiUser> task) {
                        if (task.getException() instanceof AmBacSiAuthWeakPasswordException) {
                            signUpPassword.requestFocus();
                            signUpPassword.setError(getResources().getString(R.string.sign_up_error_week_password));
                        } else if (task.getException() instanceof AmBacSiAuthInvalidCredentialsException) {
                            if (((AmBacSiAuthInvalidCredentialsException) task.getException()).getErrorCode().equals(AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNUP_INVALID_EMAIL)) {
                                signUpEmail.requestFocus();
                                signUpEmail.setError(getResources().getString(R.string.sign_up_error_invalid_email));
                            } else if (((AmBacSiAuthInvalidCredentialsException) task.getException()).getErrorCode().equals(AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNUP_INVALID_USERNAME)) {
                                signUpUsername.requestFocus();
                                signUpUsername.setError(getResources().getString(R.string.sign_up_error_invalid_username));
                            }
                        } else if (task.getException() instanceof AmBacSiAuthUserCollisionException) {
                            if (((AmBacSiAuthUserCollisionException) task.getException()).getErrorCode().equals(AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_EMAIL_EXISTED)) {
                                signUpEmail.requestFocus();
                                signUpEmail.setError(getResources().getString(R.string.sign_up_error_existed_email));
                            } else if (((AmBacSiAuthUserCollisionException) task.getException()).getErrorCode().equals(AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_USERNAME_EXISTED)) {
                                signUpUsername.requestFocus();
                                signUpUsername.setError(getResources().getString(R.string.sign_up_error_existed_username));
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this,
                                    getResources().getString(R.string.sign_up_error_unknown),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<AmBacSiUser>() {
                    @Override
                    public void onSuccess(@NonNull Task<AmBacSiUser> task) {
                        // get ID, token
                        AmBacSiUser user = task.getResult();

                        Intent i = new Intent(SignUpActivity.this, WelcomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })
                .execute();
    }

    @OnClick(R.id.signUpLogin) void login() {
        finish();
    }

    private void startLoading() {
        onLoading = true;
        signUpForm.setVisibility(View.GONE);
        signUpProgressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoading() {
        onLoading = false;
        signUpForm.setVisibility(View.VISIBLE);
        signUpProgressBar.setVisibility(View.GONE);
    }
}
