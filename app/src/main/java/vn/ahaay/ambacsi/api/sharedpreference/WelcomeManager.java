package vn.ahaay.ambacsi.api.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import vn.ahaay.ambacsi.api.sharedpreference.constant.WelcomePreference;

/**
 * Created by Can on 24-Sep-16.
 */

public class WelcomeManager {
    private SharedPreferences pref;

    public WelcomeManager(Context context) {
        pref = context.getSharedPreferences(WelcomePreference.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        pref.edit()
                .putBoolean(WelcomePreference.IS_FIRST_TIME_LAUNCH, isFirstTime)
                .apply();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(WelcomePreference.IS_FIRST_TIME_LAUNCH, true);
    }
}
