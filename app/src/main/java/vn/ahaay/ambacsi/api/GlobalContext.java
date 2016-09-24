package vn.ahaay.ambacsi.api;

import android.content.Context;

/**
 * Created by SONY on 06-Aug-16.
 */
public class GlobalContext {
    // TODO refactor to do not put Android context static
    // find new solution
    private static Context mContext;

    public static void setContext(Context _context) {
        mContext = _context;
    }

    public static Context getContext() {
        return mContext;
    }
}
