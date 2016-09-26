package vn.ahaay.ambacsi.api.model.profile;

import android.graphics.Bitmap;

/**
 * Status: UPDATED
 * Created by SONY on 13-Aug-16.
 * Last updated by Cat Can on 26-Sep-2016.
 */
public class CacheProfile {
    private String accountId;
    private String profileId;
    private int role;
    private String displayName;
    private String thumbPath;
    private Bitmap thumb;

    public CacheProfile() {
    }

    // without bitmap thumb
    public CacheProfile(String _accountId, String _profileId, int _role, String _displayName, String _thumbPath) {
        accountId = _accountId;
        profileId = _profileId;
        role = _role;
        displayName = _displayName;
        thumbPath = _thumbPath;
    }

    // with bitmap thumb
    public CacheProfile(String _accountId, String _profileId, int _role, String _displayName, String _thumbPath, Bitmap _thumb) {
        accountId = _accountId;
        profileId = _profileId;
        role = _role;
        displayName = _displayName;
        thumbPath = _thumbPath;
        thumb = _thumb;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String _accountId) {
        accountId = _accountId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String _profileId) {
        profileId = _profileId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int _role) {
        role = _role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String _displayName) {
        displayName = _displayName;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String _thumbPath) {
        thumbPath = _thumbPath;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap _thumb) {
        thumb = _thumb;
    }
}
