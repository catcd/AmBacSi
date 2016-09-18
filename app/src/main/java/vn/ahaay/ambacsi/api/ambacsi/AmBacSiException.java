package vn.ahaay.ambacsi.api.ambacsi;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by SONY on 22-Jul-16.
 */
public class AmBacSiException extends Exception {

    public AmBacSiException(@NonNull String mMessage) {
        super(checkArg(mMessage, "Detail message must not be empty"));
    }

    public AmBacSiException(@NonNull String mMessage, Throwable mThrowable) {
        super(checkArg(mMessage, "Detail message must not be empty"), mThrowable);
    }

    private static String checkArg(String mMessage, Object errorMessage) {
        if(TextUtils.isEmpty(mMessage)) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        } else {
            return mMessage;
        }
    }
}
