package vn.ahaay.ambacsi.api.sharedpreference.constant;

/**
 * Created by Can on 24-Sep-16.
 */

public class UserDataPreference {
    public static final String PREFS_NAME = "prefs_user_data";

    // to save account brief detail
    public static final String IS_LOGGED_IN = "prefs_is_logged_in";
    public static final String LOGGED_IN_USERNAME = "prefs_logged_in_username";
    public static final String LOGGED_IN_UID = "prefs_logged_in_uid";
    public static final String LOGGED_IN_EMAIL = "prefs_logged_in_email";
    public static final String LOGGED_IN_TOKEN = "prefs_logged_in_token";
    public static final String LOGGED_IN_PROFILE_LIST = "prefs_logged_in_profile_list";

    // to save primary profile brief detail
    public static final String IS_SET_PRIMARY_PROFILE = "prefs_is_set_primary_profile";
    public static final String PRIMARY_PROFILE_ID = "prefs_primary_profile_id";
    public static final String PRIMARY_PROFILE_ROLE = "prefs_primary_profile_role";
    public static final String PRIMARY_PROFILE_DISPLAY_NAME = "prefs_primary_profile_display_name";
    public static final String PRIMARY_PROFILE_THUMB_PATH = "prefs_primary_profile_thumb_path";
}
