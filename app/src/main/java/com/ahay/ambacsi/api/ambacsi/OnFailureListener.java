package com.ahay.ambacsi.api.ambacsi;

import android.support.annotation.NonNull;

import com.ahay.ambacsi.api.ambacsi.Task;

/**
 * Created by SONY on 21-Jul-16.
 */
public interface OnFailureListener<TResult> {
    void onFailure(@NonNull Task<TResult> var1);
}
