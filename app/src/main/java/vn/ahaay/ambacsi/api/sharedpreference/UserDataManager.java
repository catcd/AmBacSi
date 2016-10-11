package vn.ahaay.ambacsi.api.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public void setLoggedInAccount(String _username, String _uId, String _email, String _token) {
        pref.edit()
                .putString(UserDataPreference.LOGGED_IN_USERNAME, _username)
                .putString(UserDataPreference.LOGGED_IN_UID, _uId)
                .putString(UserDataPreference.LOGGED_IN_EMAIL, _email)
                .putString(UserDataPreference.LOGGED_IN_TOKEN, _token)
                .apply();
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

    public void setPrimaryProfile(String _uId, String _profileId, int _role, String _name, String _thumbPath) {
        pref.edit()
                .putString(UserDataPreference.LOGGED_IN_UID, _uId)
                .putString(UserDataPreference.PRIMARY_PROFILE_ID, _profileId)
                .putInt(UserDataPreference.PRIMARY_PROFILE_ROLE, _role)
                .putString(UserDataPreference.PRIMARY_PROFILE_DISPLAY_NAME, _name)
                .putString(UserDataPreference.PRIMARY_PROFILE_THUMB_PATH, _thumbPath)
                .apply();
    }

    public void setPrimaryProfile(CacheProfile _profile) {
        pref.edit()
                .putString(UserDataPreference.LOGGED_IN_UID, _profile.getAccountId())
                .putString(UserDataPreference.PRIMARY_PROFILE_ID, _profile.getProfileId())
                .putInt(UserDataPreference.PRIMARY_PROFILE_ROLE, _profile.getRole())
                .putString(UserDataPreference.PRIMARY_PROFILE_DISPLAY_NAME, _profile.getDisplayName())
                .putString(UserDataPreference.PRIMARY_PROFILE_THUMB_PATH, _profile.getThumbPath())
                .apply();
    }

    public boolean isHaveProfile() {
        return !getProfileList().isEmpty();
    }

    public List<CacheProfile> getProfileList() {
        ArrayList<CacheProfile> __profiles = new ArrayList<>();
        try {
            JSONArray __rawProfiles = new JSONArray(
                    pref.getString(UserDataPreference.LOGGED_IN_PROFILE_LIST, "[]")
            );

            for (int i = 0; i < __rawProfiles.length(); i++) {
                CacheProfile __profile;
                try {
                    JSONObject __item = __rawProfiles.getJSONObject(i);
                    __profile = new CacheProfile(__item);
                } catch (JSONException _e) {
                    __profile = null;
                }
                if (__profile != null) {
                    __profiles.add(__profile);
                }
            }
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return __profiles;
    }

    public void setProfileList(List<CacheProfile> _profiles) {
        String a = _profiles.toString();
        pref.edit()
                .putString(UserDataPreference.LOGGED_IN_PROFILE_LIST, _profiles.toString())
                .apply();
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

        public String getUId() {
            return uId;
        }

        public void setUId(String _uId) {
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
