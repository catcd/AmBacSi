package com.ahay.ambacsi.api.ambacsi.auth;

import android.support.annotation.Nullable;

/**
 * Created by SONY on 21-Jul-16.
 */
public class GetTokenResult {
    private String zzabf;

    public GetTokenResult(String var1) {
        this.zzabf = var1;
    }

    @Nullable
    public String getToken() {
        return this.zzabf;
    }
}
