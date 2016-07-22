package com.ahay.ambacsi.api.ambacsi.auth;

import android.support.annotation.NonNull;

/**
 * Created by SONY on 22-Jul-16.
 */
public class AmBacSiAuthWeakPasswordException extends AmBacSiAuthException {
    public static final String ERROR_CODE_PASSWORD_LESS_THAN_SIX = "Password has less than 6 characters";

    public AmBacSiAuthWeakPasswordException(@NonNull String errorCode, @NonNull String messageDetail) {
        super(errorCode, messageDetail);
    }
}
