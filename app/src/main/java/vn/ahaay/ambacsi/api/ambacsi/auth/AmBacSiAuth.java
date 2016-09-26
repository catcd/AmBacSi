package vn.ahaay.ambacsi.api.ambacsi.auth;

import android.content.Context;
import android.net.Credentials;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import vn.ahaay.ambacsi.api.GlobalContext;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.constant.ApiUrl;
import vn.ahaay.ambacsi.api.ambacsi.constant.ServerApiError;
import vn.ahaay.ambacsi.api.ambacsi.helper.JSONParse;
import vn.ahaay.ambacsi.api.device.UserDataManager;
import vn.ahaay.ambacsi.api.sharedpreference.constant.LoginUserPreference;
import vn.ahaay.ambacsi.api.sharedpreference.constant.SettingPreference;

/**
 * Created by SONY on 21-Jul-16.
 */
public final class AmBacSiAuth {
    private static final String TAG = "AmBacSiAuth";

    private static AmBacSiAuth mAuth;
    private static AmBacSiUser mUser;

    // require private constructor
    private AmBacSiAuth() {

    }

    // singleton design pattern
    public static AmBacSiAuth getInstance() {
        if (mAuth == null) {
            mAuth = new AmBacSiAuth();
        }
        return mAuth;
    }

    public static AmBacSiUser getLoginUser() throws AmBacSiAuthException {
        if (mUser != null) {
            return mUser;
        } else {
            throw new AmBacSiAuthException(
                    AmBacSiAuthException.ERROR_CODE_NOT_LOGIN,
                    AmBacSiAuthException.ERROR_CODE_NOT_LOGIN
            );
        }
    }

    @Nullable
    public static AmBacSiUser loadOfflineLoginUser() {
        // Restore preferences
//        SharedPreferences __preferences = GlobalContext.getContext()
//                .getSharedPreferences(LoginUserPreference.PREFS_NAME, Context.MODE_PRIVATE);
//
//        String __loginType = __preferences.getString(LoginUserPreference.PREFS_LOGIN_USER_TYPE, "");
//        AmBacSiUser __user;
//        switch (__loginType) {
//            case LoginUserTypeConstant.LOGIN_USER_TYPE_ANONYMOUS:
//                __user = new AmBacSiUser(
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_ID, ""),
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_TOKEN, "")
//                );
//                mUser = __user;
//                return __user;
//            case LoginUserTypeConstant.LOGIN_USER_TYPE_PASSWORD:
//            case LoginUserTypeConstant.LOGIN_USER_TYPE_GOOGLE:
//            case LoginUserTypeConstant.LOGIN_USER_TYPE_FACEBOOK:
//                __user = new AmBacSiUser(
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_ID, ""),
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_USERNAME, ""),
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_EMAIL, ""),
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_DISPLAY_NAME, ""),
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_PHOTO_URI, ""),
//                        __preferences.getString(LoginUserPreference.LOGIN_USER_TOKEN, ""),
//                        __preferences.getInt(LoginUserPreference.LOGIN_USER_ROLE, 0),
//                        __loginType
//                );
//                mUser = __user;
//                return __user;
//            default:
//                return null;
//        }
        return null;
    }

    @NonNull
    public static Task<Void> logout() {
        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // TODO delete all shared preference if add more
                GlobalContext.getContext()
                        .getSharedPreferences(
                                LoginUserPreference.PREFS_NAME,
                                Context.MODE_PRIVATE
                        ).edit().clear().apply();
                GlobalContext.getContext()
                        .getSharedPreferences(
                                SettingPreference.PREFS_SETTINGS,
                                Context.MODE_PRIVATE
                        ).edit().clear().apply();

                // TODO delete SQLite

                // TODO delete caches

                // Delete avatar
                UserDataManager.deleteUserAvatar();

                this.isComplete = true;
                return null;
            }
        };
    }

    @NonNull
    public Task<AmBacSiUser> authWithUsernameAndPassword(final String _username, final String _password) {
        return new Task<AmBacSiUser>() {
            @Override
            protected AmBacSiUser doInBackground(Void... voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_LOGIN;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Building post parameters, key and value pair
                JSONObject __data = new JSONObject();
                try {
                    __data.put("username", _username)
                            .put("password", _password);
                } catch (JSONException e) {
                    Log.e(TAG + ":login", e.getMessage());
                }

                // Url Encoding the POST parameters
                try {
                    __httpPost.setEntity(new StringEntity(__data.toString()));
                    __httpPost.addHeader("Content-Type", "application/json");

                    // Making HTTP Request
                    try {
                        HttpResponse __httpResponse = __httpClient.execute(__httpPost);

                        if (__httpResponse.getStatusLine().getStatusCode() == 200) {
                            JSONObject __result = JSONParse.parse(__httpResponse);

                            String __uid            = __result.getString("uid");
                            String __username       = __result.getString("username");
                            String __email          = __result.getString("email");
                            String __displayName    = __result.getString("display_name");
                            String __uri            = __result.getString("photo_uri");
                            String __token          = __result.getString("token");
                            int __role           = __result.getInt("role");

                            this.isComplete = true;
                            mUser = new AmBacSiUser(__uid, __username, __email, __displayName, __uri, __token, __role);

                            GlobalContext.getContext()
                                    .getSharedPreferences(
                                            LoginUserPreference.PREFS_NAME,
                                            Context.MODE_PRIVATE
                                    )
                                    .edit()
                                    .putString(LoginUserPreference.LOGIN_USER_ID, __uid)
                                    .putString(LoginUserPreference.LOGIN_USER_USERNAME, __username)
                                    .putString(LoginUserPreference.LOGIN_USER_EMAIL, __email)
                                    .putString(LoginUserPreference.LOGIN_USER_DISPLAY_NAME, __displayName)
                                    .putString(LoginUserPreference.LOGIN_USER_PHOTO_URI, __uri)
                                    .putString(LoginUserPreference.LOGIN_USER_TOKEN, __token)
                                    .putInt(LoginUserPreference.LOGIN_USER_ROLE, __role)
                                    .apply();
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthInvalidCredentialsException(
                                    AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGN_IN_FAILED,
                                    AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGN_IN_FAILED
                            );
                        } else {
                            this.isComplete = false;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                            );
                        }
                    } catch (IOException | JSONException e) {
                        // writing error to Log
                        Log.e(TAG + ":login", e.getMessage());

                        this.isComplete = false;
                        this.mException = e;
                    }
                }
                catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    Log.e(TAG + ":login", e.getMessage());

                    this.isComplete = false;
                    this.mException = e;
                }
                return mUser;
            }
        };
    }

    @NonNull
    public Task<AmBacSiUser> authWithCredential(Credentials mCredentials) {
        return new Task<AmBacSiUser>() {
            @Override
            protected AmBacSiUser doInBackground(Void... voids) {
                // URL
                String url = ApiUrl.PREFIX_URL + ApiUrl.URL_LOGIN_WITH_CREDENTIAL;

                // Creating HTTP client
                HttpClient httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost httpPost = new HttpPost(url);

                // TODO auth with credential
                return mUser;
            }
        };
    }

    @NonNull
    public Task<AmBacSiUser> createUserWithUsernameAndPassword(final String _username, final String _password, final String _email) {
        return new Task<AmBacSiUser>() {
            @Override
            protected AmBacSiUser doInBackground(Void... voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_REGISTER;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Building post parameters, key and value pair
                JSONObject __data = new JSONObject();
                try {
                    __data.put("username", _username)
                            .put("password", _password)
                            .put("email", _email);
                } catch (JSONException e) {
                    Log.e(TAG + ":login", e.getMessage());
                }

                // Url Encoding the POST parameters
                try {
                    __httpPost.setEntity(new StringEntity(__data.toString()));
                    __httpPost.addHeader("Content-Type", "application/json");

                    // Making HTTP Request
                    try {
                        HttpResponse __httpResponse = __httpClient.execute(__httpPost);

                        if (__httpResponse.getStatusLine().getStatusCode() == 201) {
                            JSONObject __result = JSONParse.parse(__httpResponse);

                            String __uid        = __result.getString("uid");
                            String __username   = __result.getString("username");
                            String __email      = __result.getString("email");
                            String __token      = __result.getString("token");

                            this.isComplete = true;
                            mUser = new AmBacSiUser(__uid, __username, __email, __token);

                            GlobalContext.getContext()
                                    .getSharedPreferences(
                                            LoginUserPreference.PREFS_NAME,
                                            Context.MODE_PRIVATE
                                    )
                                    .edit()
                                    .putString(LoginUserPreference.LOGIN_USER_ID, __uid)
                                    .putString(LoginUserPreference.LOGIN_USER_USERNAME, __username)
                                    .putString(LoginUserPreference.LOGIN_USER_EMAIL, __email)
                                    .putString(LoginUserPreference.LOGIN_USER_TOKEN, __token)
                                    .apply();
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            JSONObject __result = JSONParse.parse(__httpResponse);

                            this.isComplete = true;
                            if (__result.has("email")) {
                                JSONArray error = __result.getJSONArray("email");

                                if (error.getString(0).equals(ServerApiError.EMAIL_INVALID)) {
                                    this.mException = new AmBacSiAuthInvalidCredentialsException(
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGN_UP_INVALID_EMAIL,
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGN_UP_INVALID_EMAIL
                                    );
                                } else if (error.getString(0).equals(ServerApiError.EMAIL_EXISTED)) {
                                    this.mException = new AmBacSiAuthUserCollisionException(
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_EMAIL_EXISTED,
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_EMAIL_EXISTED
                                    );
                                }
                            } else if (__result.has("username")) {
                                JSONArray error = __result.getJSONArray("username");

                                if (error.getString(0).equals(ServerApiError.USERNAME_INVALID)) {
                                    this.mException = new AmBacSiAuthInvalidCredentialsException(
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGN_UP_INVALID_USERNAME,
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGN_UP_INVALID_USERNAME
                                    );
                                } else if (error.getString(0).equals(ServerApiError.USERNAME_EXISTED)) {
                                    this.mException = new AmBacSiAuthUserCollisionException(
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_USERNAME_EXISTED,
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_USERNAME_EXISTED
                                    );
                                }
                            } else if (__result.has("password")) {
                                JSONArray error = __result.getJSONArray("password");

                                if (error.getString(0).equals(ServerApiError.PASSWORD_INVALID)) {
                                    this.mException = new AmBacSiAuthWeakPasswordException(
                                            AmBacSiAuthWeakPasswordException.ERROR_CODE_PASSWORD_LESS_THAN_SIX,
                                            AmBacSiAuthWeakPasswordException.ERROR_CODE_PASSWORD_LESS_THAN_SIX
                                    );
                                }
                            } else {
                                this.mException = new AmBacSiAuthException(
                                        AmBacSiAuthException.ERROR_CODE_UNKNOWN,
                                        AmBacSiAuthException.ERROR_CODE_UNKNOWN
                                );
                            }
                        } else {
                            this.isComplete = false;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                            );
                        }
                    } catch (IOException | JSONException e) {
                        // writing error to Log
                        Log.e(TAG + ":register", e.getMessage());

                        this.isComplete = false;
                        this.mException = e;
                    }
                }
                catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    Log.e(TAG + ":register", e.getMessage());

                    this.isComplete = false;
                    this.mException = e;
                }
                return mUser;
            }
        };
    }
}
