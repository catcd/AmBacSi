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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import vn.ahaay.ambacsi.api.GlobalContext;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.constant.ApiUrl;
import vn.ahaay.ambacsi.api.ambacsi.helper.JSONParse;
import vn.ahaay.ambacsi.api.ambacsi.profile.AmBacSiProfile;
import vn.ahaay.ambacsi.api.ambacsi.profile.ClinicalCenterProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.profile.DoctorProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.profile.ProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.profile.UserProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.schedule.AmBacSiSchedule;
import vn.ahaay.ambacsi.api.model.profile.CacheProfile;
import vn.ahaay.ambacsi.api.model.profile.Profile;
import vn.ahaay.ambacsi.api.sharedpreference.constant.LoginUserPreference;

/**
 * Created by SONY on 21-Jul-16.
 */
public class AmBacSiAccount {
    private static final String DEFAULT_USERNAME = "ambacsi";
    private static final String DEFAULT_EMAIL = "ambacsi@ahay.com.vn";
    private static final String DEFAULT_DISPLAY_NAME = "AmBacSi User";

    private static final String TAG = "AmBacSiAccount";

    private String uid;
    private String username;
    private String email;
    private String token;
    private String displayName;
    private Uri photoUri;
    private boolean isAnonymous;

    private CacheProfile profile;

    // login anonymous
    public AmBacSiAccount(String _uid, String _token) {
        uid = _uid;
        token = _token;

        username = DEFAULT_USERNAME;
        email = DEFAULT_EMAIL;
        displayName = DEFAULT_DISPLAY_NAME;
        isAnonymous = true;
    }

    // use when create account or login but not select account
    public AmBacSiAccount(String _uid, String _username, String _email, String _token) {
        uid = _uid;
        username = _username;
        email = _email;
        token = _token;

        displayName = DEFAULT_DISPLAY_NAME;

        photoUri = null;
        isAnonymous = false;
    }

    // login normal without profile
    public AmBacSiAccount(String _uid, String _username, String _email, String _token, String _displayName, String _photoUri) {
        uid = _uid;
        username = _username;
        email = _email;
        displayName = _displayName;
        photoUri = new Uri.Builder().path(_photoUri).build();
        token = _token;
        isAnonymous = false;
    }

    // login normal with profile
    public AmBacSiAccount(String _uid, String _username, String _email, String _token, String _displayName, Uri _photoUri, CacheProfile _profile) {
        uid = _uid;
        username = _username;
        email = _email;
        token = _token;
        displayName = _displayName;
        photoUri = _photoUri;
        isAnonymous = false;
        profile = _profile;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String _uid) {
        uid = _uid;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String _username) {
        username = _username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String _email) {
        email = _email;
    }

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String _token) {
        token = _token;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@Nullable String _displayName) {
        displayName = _displayName;
    }

    @Nullable
    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(@Nullable Uri _photoUri) {
        photoUri = _photoUri;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean _anonymous) {
        isAnonymous = _anonymous;
    }

    @Nullable
    public CacheProfile getProfile() {
        return profile;
    }

    public void setProfile(@Nullable CacheProfile _profile) {
        profile = _profile;
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
    public Task<CacheProfile> createUserProfile(@NonNull final UserProfileChangeRequest _changeRequest) {
        return new Task<CacheProfile>() {
            @Override
            protected CacheProfile doInBackground(Void... voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_CREATE_USER_PROFILE;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                CacheProfile __profile = null;

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
                            __profile = new CacheProfile(JSONParse.parse(__httpResponse));
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED,
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED
                            );
                        } else {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                            );
                        }
                    } catch (IOException | JSONException _e) {
                        // writing error to Log
                        Log.e(TAG + ":cProfile", _e.getMessage());

                        this.isComplete = true;
                        this.mException = _e;
                    }
                }
                catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    Log.e(TAG + ":cProfile", e.getMessage());

                    this.isComplete = true;
                    this.mException = e;
                }

                return __profile;
            }
        };
    }

    @NonNull
    public Task<CacheProfile> createDoctorProfile(@NonNull final DoctorProfileChangeRequest _changeRequest) {
        return new Task<CacheProfile>() {
            @Override
            protected CacheProfile doInBackground(Void... voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_CREATE_DOCTOR_PROFILE;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                CacheProfile __profile = null;

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
                            __profile = new CacheProfile(JSONParse.parse(__httpResponse));
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED,
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED
                            );
                        } else {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                            );
                        }
                    } catch (IOException | JSONException _e) {
                        // writing error to Log
                        Log.e(TAG + ":cProfile", _e.getMessage());

                        this.isComplete = true;
                        this.mException = _e;
                    }
                }
                catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    Log.e(TAG + ":cProfile", e.getMessage());

                    this.isComplete = true;
                    this.mException = e;
                }

                return __profile;
            }
        };
    }

    @NonNull
    public Task<CacheProfile> createClinicalCenterProfile(@NonNull final ClinicalCenterProfileChangeRequest _changeRequest) {
        return new Task<CacheProfile>() {
            @Override
            protected CacheProfile doInBackground(Void... voids) {
                // URL
                String __url = ApiUrl.PREFIX_URL + ApiUrl.URL_CREATE_CLINICAL_CENTER_PROFILE;

                // Creating HTTP client
                HttpClient __httpClient = new DefaultHttpClient();

                // Creating HTTP Post
                HttpPost __httpPost = new HttpPost(__url);

                CacheProfile __profile = null;

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
                            __profile = new CacheProfile(JSONParse.parse(__httpResponse));
                        } else if (__httpResponse.getStatusLine().getStatusCode() == 400) {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED,
                                    AmBacSiAuthException.ERROR_CODE_CREATE_PROFILE_FAILED
                            );
                        } else {
                            this.isComplete = true;
                            this.mException = new AmBacSiAuthException(
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE,
                                    AmBacSiAuthException.ERROR_CODE_SERVER_STATUS_CODE
                            );
                        }
                    } catch (IOException | JSONException _e) {
                        // writing error to Log
                        Log.e(TAG + ":cProfile", _e.getMessage());

                        this.isComplete = true;
                        this.mException = _e;
                    }
                }
                catch (UnsupportedEncodingException e) {
                    // writing error to Log
                    Log.e(TAG + ":cProfile", e.getMessage());

                    this.isComplete = true;
                    this.mException = e;
                }

                return __profile;
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
