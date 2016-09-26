package vn.ahaay.ambacsi.ui.outsides;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(vn.ahaay.ambacsi.R.id.forgotPasswordAppName) TextView forgotPasswordAppName;
    @BindView(vn.ahaay.ambacsi.R.id.forgotPasswordEmail) EditText forgotPasswordEmail;
    @BindView(vn.ahaay.ambacsi.R.id.forgotPasswordProgressBar) ProgressBar forgotPasswordProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        setAppNameFont();

        specifyInputMethodAction();
    }

    private void setAppNameFont() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/rotulona-hand.ffp.otf");
        forgotPasswordAppName.setTypeface(font);
    }

    private void specifyInputMethodAction() {
        forgotPasswordEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    resetPassword();
                    handled = true;
                }
                return handled;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        forgotPasswordProgressBar.setVisibility(View.GONE);
    }

    @OnClick(vn.ahaay.ambacsi.R.id.forgotPasswordSubmit) void resetPassword() {
        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }

        // get EditText
        String mEmail = forgotPasswordEmail.getText().toString().trim();

        // Check valid
        if (TextUtils.isEmpty(mEmail)) {
            forgotPasswordEmail.requestFocus();
            forgotPasswordEmail.setError(getResources().getString(vn.ahaay.ambacsi.R.string.forgot_password_error_field_required));
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            forgotPasswordEmail.requestFocus();
            forgotPasswordEmail.setError(getResources().getString(vn.ahaay.ambacsi.R.string.forgot_password_error_invalid_email));
            return;
        }

        forgotPasswordProgressBar.setVisibility(View.VISIBLE);
        // send password reset instruction email

        forgotPasswordProgressBar.setVisibility(View.GONE);
        Toast.makeText(ForgotPasswordActivity.this,
                getResources().getString(vn.ahaay.ambacsi.R.string.forgot_password_success),
                Toast.LENGTH_LONG).show();

        finish();
    }

    @OnClick(vn.ahaay.ambacsi.R.id.forgotPasswordLogin) void login() {
        finish();
    }
}
