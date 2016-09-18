package vn.ahaay.ambacsi.api.ambacsi.auth;

import android.support.annotation.NonNull;

/**
 * Created by SONY on 22-Jul-16.
 */
public class AmBacSiAuthUserCollisionException extends AmBacSiAuthException {
    public static final String ERROR_CODE_SIGNUP_EMAIL_EXISTED = "There already exists an account with the given email address";
    public static final String ERROR_CODE_SIGNUP_USERNAME_EXISTED = "There already exists an account with the given username";

    public AmBacSiAuthUserCollisionException(@NonNull String errorCode, @NonNull String messageDetail) {
        super(errorCode, messageDetail);
    }
}
