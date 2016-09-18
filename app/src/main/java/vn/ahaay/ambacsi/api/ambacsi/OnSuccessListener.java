package vn.ahaay.ambacsi.api.ambacsi;

import android.support.annotation.NonNull;

/**
 * Created by SONY on 21-Jul-16.
 */
public interface OnSuccessListener<TResult> {
    void onSuccess(@NonNull Task<TResult> var1);
}
