package vn.ahaay.ambacsi.api.ambacsi;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by SONY on 28-Jul-16.
 */
public final class Constant {
    public static final class ServerApiErrorConstant {
        public static final String USERNAME_INVALID = "Username is invalid.";
        public static final String USERNAME_EXISTED = "A user with that username already exists.";
        public static final String PASSWORD_INVALID = "Password is invalid.";
        public static final String EMAIL_EXISTED = "Email existed.";
        public static final String EMAIL_INVALID = "Enter a valid email address.";
    }

    public static final class UserGroupConstant {
        public static final int GROUP_USER = 0;
        public static final int GROUP_DOCTOR = 1;
        public static final int GROUP_CLINICAL_CENTER = 2;
    }

    public static final class FormatConstant {
        public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
    }

    public static final class LoginUserTypeConstant {
        public static final String LOGIN_USER_TYPE_PASSWORD = "PASSWORD";
        public static final String LOGIN_USER_TYPE_ANONYMOUS = "ANONYMOUS";
        public static final String LOGIN_USER_TYPE_GOOGLE = "GOOGLE";
        public static final String LOGIN_USER_TYPE_FACEBOOK = "FACEBOOK";
    }

    public static final class UserDataConstant {
        public static final String USER_DATA_AVATAR_FILE_NAME = "my_avatar.png";
    }

    public static final class SharedPreferencesConstant {
        public static final String PREFS_LOGIN_USER = "prefs_login_user";
        public static final String PREFS_LOGIN_USER_TYPE = "prefs_login_user_type";
        public static final String PREFS_LOGIN_USER_ID = "prefs_login_user_id";
        public static final String PREFS_LOGIN_USER_USERNAME = "prefs_login_user_username";
        public static final String PREFS_LOGIN_USER_EMAIL = "prefs_login_user_email";
        public static final String PREFS_LOGIN_USER_DISPLAY_NAME = "prefs_login_user_display_name";
        public static final String PREFS_LOGIN_USER_PHOTO_URI = "prefs_login_user_photo_uri";
        public static final String PREFS_LOGIN_USER_TOKEN = "prefs_login_user_token";
        public static final String PREFS_LOGIN_USER_ROLE = "prefs_login_user_role";

        public static final String PREFS_SETTINGS = "prefs_settings";
    }
}