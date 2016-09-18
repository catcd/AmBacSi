package vn.ahaay.ambacsi.api.ambacsi.constant;

/**
 * Created by Can on 10-Sep-16.
 */
public class ApiUrlConstant {
    public static final String DOMAIN = "http://63.142.253.167";
    public static final String API_URL = "/wecare/api";
    public static final String PREFIX_URL = DOMAIN + API_URL;

    public static final String URL_REGISTER = "/account/register";
    public static final String URL_LOGIN = "/account/login";
    public static final String URL_LOGIN_WITH_CREDENTIAL = "/account/login_with_credential";
    public static final String URL_UPDATE_PROFILE = "/account/profile/%1$s/update";
    public static final String URL_GET_PROFILE = "/account/profile";
    public static final String URL_GET_PROFILE_ID = "/account/profile/%1$s";
    public static final String URL_PROFILE = "/account/profile";
    public static final String URL_CHANGE_EMAIL = "/account/change_email";
    public static final String URL_CHANGE_PASSWORD = "/account/change_password";

    public static final String URL_CREATE_SCHEDULE = "/schedule/create";
    public static final String URL_DELETE_SCHEDULE = "/schedule/%1$s/delete";
    public static final String URL_UPDATE_SCHEDULE = "/schedule/%1$s/update";
    public static final String URL_GET_SCHEDULE_ID = "/schedule/%1$s";
    public static final String URL_GET_SCHEDULE = "/schedule";

    public static final String URL_CREATE_APPOINTMENT = "/appointment/create";
    public static final String URL_DELETE_APPOINTMENT = "/appointment/%1$s/delete";
    public static final String URL_GET_APPOINTMENT_ID = "/appointment/%1$s";
    public static final String URL_GET_APPOINTMENT = "/appointment";

    public static final String URL_REGISTER_FCM_TOKEN = "/fcm_token/create";

    public static final String URL_CREATE_FRIEND_REQUEST = "/friend_request/create";
    public static final String URL_DELETE_FRIEND_REQUEST = "/friend_request/%1$s/delete";
    public static final String URL_GET_FRIEND_REQUEST = "/friend_request";

    public static final String URL_CREATE_RELATIONSHIP = "/relationship/create";
    public static final String URL_DELETE_RELATIONSHIP = "/relationship/%1$s/delete";
    public static final String URL_GET_RELATIONSHIP_ID = "/relationship/%1$s";
    public static final String URL_GET_RELATIONSHIP = "/relationship";

    public static final String URL_CREATE_FRIEND_GROUP = "/friend_group/create";
    public static final String URL_DELETE_FRIEND_GROUP = "/friend_group/%1$s/delete";
    public static final String URL_UPDATE_FRIEND_GROUP = "/friend_group/%1$s/update";
    public static final String URL_FRIEND_GROUP_ADD = "/friend_group/%1$s/update?option=add";
    public static final String URL_FRIEND_GROUP_REMOVE = "/friend_group/%1$s/update?option=remove";
    public static final String URL_GET_FRIEND_GROUP_ID = "/friend_group/%1$s";
    public static final String URL_GET_FRIEND_GROUP = "/friend_group";
}
