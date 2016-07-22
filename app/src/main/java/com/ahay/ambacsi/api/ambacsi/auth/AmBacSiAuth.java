package com.ahay.ambacsi.api.ambacsi.auth;

import com.ahay.ambacsi.api.ambacsi.Task;

/**
 * Created by SONY on 21-Jul-16.
 */
public class AmBacSiAuth {
    private static AmBacSiAuth mAuth;
    private static AmBacSiUser mUser;

    private AmBacSiAuth() {

    }

    public static AmBacSiAuth getInstance() {
        if (mAuth != null) {
            mAuth = new AmBacSiAuth();
        }
        return mAuth;
    }

    public static AmBacSiUser getLoginUser() {
        return mUser;
    }

    public Task<AmBacSiUser> authWithUsernameAndPassword(String mUsername, String mPassword) {
        return new Task<AmBacSiUser>() {
            @Override
            protected AmBacSiUser doInBackground(Void... voids) {
                // TODO Http call
                // TODO complete change this.isComplete to true else false
                // TODO success construct user and return
                // TODO success assign AmBacSiAuth::mUser to result
                // TODO if exception change this.mException and return null
                this.isComplete = true;
                return new AmBacSiUser();
            }
        };
    }
}
