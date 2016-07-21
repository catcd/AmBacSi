package com.ahay.ambacsi.api.ambacsi.auth;

import android.support.annotation.NonNull;

/**
 * Created by SONY on 21-Jul-16.
 */
public interface OnSuccessListener<TResult> {
    void onSuccess(@NonNull Task<TResult> var1);
}
