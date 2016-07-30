package com.ahay.ambacsi.api.ambacsi.auth;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ahay.ambacsi.api.ambacsi.AmBacSiException;

/**
 * Created by SONY on 22-Jul-16.
 */
public class AmBacSiAuthException extends AmBacSiException {
    public static final String ERROR_CODE_SERVER_STATUS_CODE = "Server status code is not defined in API";
    public static final String ERROR_CODE_UNKNOWN = "Unknown error";

    private final String errorCode;

    public AmBacSiAuthException(@NonNull String mErrorCode, @NonNull String mMessage) {
        super(mMessage);
        this.errorCode = this.checkArg(mErrorCode);
    }

    @NonNull
    public String getErrorCode() {
        return this.errorCode;
    }

    private String checkArg(String mErrorCode) {
        if(TextUtils.isEmpty(mErrorCode)) {
            throw new IllegalArgumentException("Given String is empty or null");
        } else {
            return mErrorCode;
        }
    }
}
