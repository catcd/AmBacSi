package com.ahay.ambacsi.api.ambacsi;

/**
 * Created by SONY on 28-Jul-16.
 */
public class Constant {
    public static class ApiUrlConstant{
        public static final String DOMAIN = "http://63.142.253.167";
        public static final String API_URL = "/wecare/api";

        public static final String URL_REGISTER = "/account/register";
        public static final String URL_LOGIN = "/account/login";
        public static final String URL_LOGIN_WITH_CREDENTIAL = "/account/login_with_credential";
        public static final String URL_PROFILE = "/account/profile";
        public static final String URL_CHANGE_EMAIL = "/account/change_email";
        public static final String URL_CHANGE_PASSWORD = "/account/change_password";
    }
    public static class ApiErrorConstant{
        public static final String USERNAME_INVALID = "Username is invalid.";
        public static final String USERNAME_EXISTED = "A user with that username already exists.";
        public static final String PASSWORD_INVALID = "Password is invalid.";
        public static final String EMAIL_EXISTED = "Email existed.";
        public static final String EMAIL_INVALID = "Enter a valid email address.";

    }
    public static class UserGroupConstant {
        public static final int GROUP_USER = 0;
        public static final int GROUP_DOCTOR = 1;
        public static final int GROUP_CLINICAL_CENTER = 2;
    }
}
