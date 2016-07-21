package com.ahay.ambacsi.ui.outsides;

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
import android.widget.TextView;
import android.widget.Toast;

import com.ahay.ambacsi.R;
import com.ahay.ambacsi.ui.medicals.MainActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setAppNameFont();

        specifyInputMethodAction();
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
        signUpProgressBar.setVisibility(View.GONE);
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

        signUpProgressBar.setVisibility(View.VISIBLE);
        //create user

        signUpProgressBar.setVisibility(View.GONE);
        // TODO login complete download all data save to SQLite table
        // Set local status to logged in
        Toast.makeText(SignUpActivity.this,
                getResources().getString(R.string.login_success),
                Toast.LENGTH_SHORT).show();

        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.signUpLogin) void login() {
        finish();
    }
}
