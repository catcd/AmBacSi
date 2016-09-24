package vn.ahaay.ambacsi.api.ambacsi.auth;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import vn.ahaay.ambacsi.api.GlobalContext;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.constant.ApiUrl;
import vn.ahaay.ambacsi.api.ambacsi.profile.AmBacSiProfile;
import vn.ahaay.ambacsi.api.ambacsi.profile.ProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.schedule.AmBacSiSchedule;
import vn.ahaay.ambacsi.api.sharedpreference.constant.LoginUserPreference;

/**
 * Created by SONY on 21-Jul-16.
 */
public class AmBacSiUser {
    public static final String DEFAULT_USERNAME = "ambacsi";
    public static final String DEFAULT_EMAIL = "ambacsi@ahay.com.vn";
    public static final String DEFAULT_DISPLAY_NAME = "AmBacSi User";

    private static final String TAG = "AmBacSiUser";

    private String uid;
    private String username;
    private String email;
    private String displayName;
    private Uri photoUri;
    private String token;
    private boolean isAnonymous;
    private int role;

    public AmBacSiUser(String _uid, String _token) {
        uid = _uid;
        token = _token;

        username = DEFAULT_USERNAME;
        email = DEFAULT_EMAIL;
        displayName = DEFAULT_DISPLAY_NAME;

        role = 0;
        photoUri = null;
        isAnonymous = true;
    }

    public AmBacSiUser(String _uid, String _username, String _email, String _token) {
        uid = _uid;
        username = _username;
        email = _email;
        token = _token;

        displayName = DEFAULT_DISPLAY_NAME;

        role = 0;
        photoUri = null;
        isAnonymous = false;
    }

    public AmBacSiUser(String _uid, String _username, String _email, String _displayName, String _photoUri, String _token, int _role) {
        uid = _uid;
        username = _username;
        email = _email;
        displayName = _displayName;
        photoUri = new Uri.Builder().path(_photoUri).build();
        token = _token;
        isAnonymous = false;
        role = _role;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    @Nullable
    public Uri getPhotoUri() {
        return photoUri;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public String getToken() {
        return token;
    }

    @NonNull
    public Task<GetTokenResult> refreshToken() {
        return new Task<GetTokenResult>() {
            @Override
            protected GetTokenResult doInBackground(Void... voids) {
                // TODO refresh user token
                return null;
            }
        };
    }

    public void downloadData() throws AmBacSiAuthException {
        AmBacSiProfile.downloadProfile();
        AmBacSiSchedule.downloadSchedule(null, null);
    }

    public void refreshData() {
        // TODO: refresh data when login again
    }

    @NonNull
    public Task<Void> createProfile(@NonNull final ProfileChangeRequest _changeRequest) {
        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_PROFILE;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                // Url Encoding the POST parameters
                try {
                    __httpPost.setEntity(new StringEntity(_changeRequest.getHttpRequestBody()));
                    __httpPost.addHeader("Content-Type", "application/json");
                    __httpPost.addHeader("Authorization", "Token " + token);

                    // Making HTTP Request
                    try {
                        HttpResponse __httpResponse = __httpClient.execute(__httpPost);

                        if (__httpResponse.getStatusLine().getStatusCode() == 201) {
                            this.isComplete = true;

                            displayName = _changeRequest.getDisplayName();

                            GlobalContext.getContext()
                                    .getSharedPreferences(
                                            LoginUserPreference.PREFS_LOGIN_USER,
                                            Context.MODE_PRIVATE
                                    )
                                    .edit()
                                    .putString(
                                            LoginUserPreference.PREFS_LOGIN_USER_DISPLAY_NAME,
                                            _changeRequest.getDisplayName()
                                    )
                                    .apply();

                            // TODO update profile data to local DB
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED,
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED
                            );
                        } else {
                            this.isComplete = false;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                            );
                        }
                    } catch (IOException _e) {
                        // writing error to Log
                        Log.e(TAG + ":cProfile", _e.getMessage());

                        this.isComplete = false;
                        this.mException = _e;
                    }
                }
                catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    Log.e(TAG + ":cProfile", e.getMessage());

                    this.isComplete = false;
                    this.mException = e;
                }

                return null;
            }
        };
    }

    @NonNull
    public Task<Void> updateEmail(@NonNull String newEmail) {
        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // TODO update email
                return null;
            }
        };
    }

    @NonNull
    public Task<Void> updatePassword(@NonNull String newPassword) {
        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // TODO change profile
                return null;
            }
        };
    }
}
