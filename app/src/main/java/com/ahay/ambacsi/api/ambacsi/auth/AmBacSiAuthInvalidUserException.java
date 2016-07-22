package com.ahay.ambacsi.api.ambacsi.auth;

import android.support.annotation.NonNull;

/**
 * Created by SONY on 22-Jul-16.
 */
public class AmBacSiAuthInvalidUserException extends AmBacSiAuthException {
    public static final String ERROR_CODE_SIGNIN_USERNAME_NOT_EXIST = "The username does not exist";
    public static final String ERROR_CODE_SIGNIN_USERNAME_DISABLED = "This account is disabled";

    public AmBacSiAuthInvalidUserException(@NonNull String errorCode, @NonNull String messageDetail) {
        super(errorCode, messageDetail);
    }
}
