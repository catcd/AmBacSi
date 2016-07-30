package com.ahay.ambacsi.api.ambacsi.auth;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ahay.ambacsi.api.ambacsi.Task;
import com.ahay.ambacsi.api.ambacsi.profile.ProfileChangeRequest;

/**
 * Created by SONY on 21-Jul-16.
 */
public class AmBacSiUser {
    private String uid;
    private String username;
    private String email;
    private String displayName;
    private Uri photoUri;
    private String token;
    private boolean isAnonymous;

    public AmBacSiUser(String _uid, String _token) {
        uid = _uid;
        token = _token;

        username = "";
        email = "";
        displayName = "Anonymous";
        photoUri = null;
        isAnonymous = true;
    }

    public AmBacSiUser(String _uid, String _username, String _email, String _token) {
        uid = _uid;
        username = _username;
        email = _email;
        token = _token;

        displayName = "";
        photoUri = null;
        isAnonymous = false;
    }

    public AmBacSiUser(String _uid, String _username, String _email, String _displayName, Uri _photoUri, String _token) {
        uid = _uid;
        username = _username;
        email = _email;
        displayName = _displayName;
        photoUri = _photoUri;
        token = _token;
        isAnonymous = false;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getToken() {
        return token;
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

    @NonNull
    public Task<Void> createProfile(@NonNull ProfileChangeRequest var1) {
        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // TODO create profile
                return null;
            }
        };
    }

    @NonNull
    public Task<Void> updateProfile(@NonNull ProfileChangeRequest var1) {
        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // TODO change profile
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
