package com.ahay.ambacsi.api.ambacsi.auth;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ahay.ambacsi.api.ambacsi.Task;
import com.ahay.ambacsi.api.ambacsi.profile.UserProfileChangeRequest;

/**
 * Created by SONY on 21-Jul-16.
 */
public class AmBacSiUser {
    private String mUid;
    private String mEmail;
    private String mDisplayName;
    private Uri mPhotoUri;
    private boolean isAnonymous;

    public AmBacSiUser() {
    }

    public AmBacSiUser(String mUid, String mEmail, String mDisplayName) {
        this.mUid = mUid;
        this.mEmail = mEmail;
        this.mDisplayName = mDisplayName;
    }

    public AmBacSiUser(String mUid, String mEmail, String mDisplayName, Uri mPhotoUri, boolean isAnonymous) {
        this.mUid = mUid;
        this.mEmail = mEmail;
        this.mDisplayName = mDisplayName;
        this.mPhotoUri = mPhotoUri;
        this.isAnonymous = isAnonymous;
    }

    @NonNull
    public String getUid(){
        return this.mUid;
    }

    public boolean isAnonymous(){
        return this.isAnonymous;
    }

    @Nullable
    public String getDisplayName() {
        return mDisplayName;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return mPhotoUri;
    }

    @Nullable
    public String getEmail() {
        return mEmail;
    }

    @NonNull
    public Task<GetTokenResult> getToken(boolean var1) {
        return new Task<GetTokenResult>() {
            @Override
            protected GetTokenResult doInBackground(Void... voids) {
                // TODO get user token
                return null;
            }
        };
    }

    @NonNull
    public Task<Void> updateProfile(@NonNull UserProfileChangeRequest var1) {
        return new Task<Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // TODO change profile
                return null;
            }
        };
    }

    @NonNull
    public Task<Void> updateEmail(@NonNull String var1) {
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
