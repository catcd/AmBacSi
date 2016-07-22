package com.ahay.ambacsi.api.ambacsi.auth;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ahay.ambacsi.api.ambacsi.AmBacSiException;

/**
 * Created by SONY on 22-Jul-16.
 */
public class AmBacSiAuthInvalidCredentialsException extends AmBacSiAuthException {
    public static final String ERROR_CODE_SIGNUP_INVALID_EMAIL = "The email address is malformed";
    public static final String ERROR_CODE_SIGNIN_WRONG_PASSWORD = "The password is wrong";


    public AmBacSiAuthInvalidCredentialsException(@NonNull String errorCode, @NonNull String messageDetail) {
        super(errorCode, messageDetail);
    }
}
