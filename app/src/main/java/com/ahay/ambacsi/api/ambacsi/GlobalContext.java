package com.ahay.ambacsi.api.ambacsi;

import android.content.Context;

/**
 * Created by SONY on 06-Aug-16.
 */
public class GlobalContext {
    private static Context mContext;

    public static void setContext(Context _context) {
        mContext = _context;
    }

    public static Context getContext() {
        return mContext;
    }
}
