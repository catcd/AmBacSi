package com.ahay.ambacsi.api.ambacsi.auth;

import android.net.Credentials;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ahay.ambacsi.api.ambacsi.JSONParse;
import com.ahay.ambacsi.api.ambacsi.Task;

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

import static com.ahay.ambacsi.api.ambacsi.Constant.ApiErrorConstant.EMAIL_EXISTED;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiErrorConstant.EMAIL_INVALID;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiErrorConstant.PASSWORD_INVALID;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiErrorConstant.USERNAME_EXISTED;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiErrorConstant.USERNAME_INVALID;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiUrlConstant.API_URL;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiUrlConstant.DOMAIN;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiUrlConstant.URL_LOGIN;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiUrlConstant.URL_LOGIN_WITH_CREDENTIAL;
import static com.ahay.ambacsi.api.ambacsi.Constant.ApiUrlConstant.URL_REGISTER;

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

    @Nullable
    public static AmBacSiUser getLoginUser() {
        return mUser;
    }

    @NonNull
    public Task<AmBacSiUser> authWithUsernameAndPassword(final String _username, final String _password) {
        return new Task<AmBacSiUser>() {
            @Override
            protected AmBacSiUser doInBackground(Void... voids) {
                // URL
                String __url = DOMAIN + API_URL + URL_LOGIN;

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
                            Uri __uri               = new Uri.Builder().path(__result.getString("photo_uri")).build();
                            String __token          = __result.getString("token");

                            this.isComplete = true;
                            mUser = new AmBacSiUser(__uid, __username, __email, __displayName, __uri, __token);
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthInvalidCredentialsException(
                                    AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNIN_FAILED,
                                    AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNIN_FAILED
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
                String url = DOMAIN + API_URL + URL_LOGIN_WITH_CREDENTIAL;

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
                String __url = DOMAIN + API_URL + URL_REGISTER;

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
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            JSONObject __result = JSONParse.parse(__httpResponse);

                            this.isComplete = true;
                            if (__result.has("email")) {
                                JSONArray error = __result.getJSONArray("email");

                                if (error.getString(0).equals(EMAIL_INVALID)) {
                                    this.mException = new AmBacSiAuthInvalidCredentialsException(
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNUP_INVALID_EMAIL,
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNUP_INVALID_EMAIL
                                    );
                                } else if (error.getString(0).equals(EMAIL_EXISTED)) {
                                    this.mException = new AmBacSiAuthUserCollisionException(
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_EMAIL_EXISTED,
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_EMAIL_EXISTED
                                    );
                                }
                            } else if (__result.has("username")) {
                                JSONArray error = __result.getJSONArray("username");

                                if (error.getString(0).equals(USERNAME_INVALID)) {
                                    this.mException = new AmBacSiAuthInvalidCredentialsException(
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNUP_INVALID_USERNAME,
                                            AmBacSiAuthInvalidCredentialsException.ERROR_CODE_SIGNUP_INVALID_USERNAME
                                    );
                                } else if (error.getString(0).equals(USERNAME_EXISTED)) {
                                    this.mException = new AmBacSiAuthUserCollisionException(
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_USERNAME_EXISTED,
                                            AmBacSiAuthUserCollisionException.ERROR_CODE_SIGNUP_USERNAME_EXISTED
                                    );
                                }
                            } else if (__result.has("password")) {
                                JSONArray error = __result.getJSONArray("password");

                                if (error.getString(0).equals(PASSWORD_INVALID)) {
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
