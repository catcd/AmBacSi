package vn.ahaay.ambacsi.api.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import vn.ahaay.ambacsi.api.model.profile.CacheProfile;
import vn.ahaay.ambacsi.api.sharedpreference.constant.UserDataPreference;

/**
 * Created by Can on 24-Sep-16.
 * Last update on 25-Sep-2016
 */

public class UserDataManager {
    private SharedPreferences pref;

    public UserDataManager(Context context) {
        pref = context.getSharedPreferences(UserDataPreference.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        pref.edit()
                .putBoolean(UserDataPreference.IS_LOGGED_IN, isLoggedIn)
                .apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(UserDataPreference.IS_LOGGED_IN, false);
    }

    @Nullable
    public LoggedInAccount getLoggedInAccount() {
        return isLoggedIn() ? new LoggedInAccount(
                pref.getString(UserDataPreference.LOGGED_IN_USERNAME, ""),
                pref.getString(UserDataPreference.LOGGED_IN_UID, ""),
                pref.getString(UserDataPreference.LOGGED_IN_EMAIL, ""),
                pref.getString(UserDataPreference.LOGGED_IN_TOKEN, "")
        ) : null;
    }

    public void setPrimaryProfile(boolean isSetPrimaryProfile) {
        pref.edit()
                .putBoolean(UserDataPreference.IS_SET_PRIMARY_PROFILE, isSetPrimaryProfile)
                .apply();
    }

    public boolean isSetPrimaryProfile() {
        return pref.getBoolean(UserDataPreference.IS_SET_PRIMARY_PROFILE, false);
    }

    @Nullable
    public CacheProfile getPrimaryProfile() {
        return isSetPrimaryProfile() ? new CacheProfile(
                pref.getString(UserDataPreference.LOGGED_IN_UID, ""),
                pref.getString(UserDataPreference.PRIMARY_PROFILE_ID, ""),
                pref.getInt(UserDataPreference.PRIMARY_PROFILE_ROLE, -1),
                pref.getString(UserDataPreference.PRIMARY_PROFILE_DISPLAY_NAME, ""),
                pref.getString(UserDataPreference.PRIMARY_PROFILE_THUMB_PATH, "")
        ) : null;
    }

    public static class LoggedInAccount {
        private String userName;
        private String uId;
        private String email;
        private String token;

        public LoggedInAccount(String _userName, String _uId, String _email, String _token) {
            userName = _userName;
            uId = _uId;
            email = _email;
            token = _token;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String _userName) {
            userName = _userName;
        }

        public String getuId() {
            return uId;
        }

        public void setuId(String _uId) {
            uId = _uId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String _email) {
            email = _email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String _token) {
            token = _token;
        }
    }

}
