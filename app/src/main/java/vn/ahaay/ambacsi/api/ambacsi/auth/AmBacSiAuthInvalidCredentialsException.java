package vn.ahaay.ambacsi.api.ambacsi.auth;

import android.support.annotation.NonNull;

/**
 * Created by SONY on 22-Jul-16.
 */
public class AmBacSiAuthInvalidCredentialsException extends AmBacSiAuthException {
    public static final String ERROR_CODE_SIGNUP_INVALID_EMAIL = "The email address is malformed";
    public static final String ERROR_CODE_SIGNUP_INVALID_USERNAME = "The username is invalid";
    public static final String ERROR_CODE_SIGNIN_FAILED = "Sign in failed";

    public AmBacSiAuthInvalidCredentialsException(@NonNull String errorCode, @NonNull String messageDetail) {
        super(errorCode, messageDetail);
    }
}
