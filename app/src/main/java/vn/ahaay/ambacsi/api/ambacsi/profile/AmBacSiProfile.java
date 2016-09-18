package vn.ahaay.ambacsi.api.ambacsi.profile;

import android.support.annotation.NonNull;

import vn.ahaay.ambacsi.api.ambacsi.Task;

/**
 * Created by Can on 9/2/2016.
 */
public abstract class AmBacSiProfile {
    public static void downloadProfile() {
        // TODO: Download profile according to role
        // change Synchronized t
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
}
