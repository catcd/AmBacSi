package vn.ahaay.ambacsi.api.model;

import android.graphics.Bitmap;

/**
 * Created by SONY on 13-Aug-16.
 */
public class CacheProfile {
    private String accountId;
    private String name;
    private String thumbPath;
    private Bitmap thumb;

    public CacheProfile() {
    }

    public CacheProfile(String _thumbPath, String _name, String _accountId) {
        thumbPath = _thumbPath;
        name = _name;
        accountId = _accountId;
    }

    public CacheProfile(String _thumbPath, String _name, String _accountId, Bitmap _thumb) {
        thumbPath = _thumbPath;
        name = _name;
        accountId = _accountId;
        thumb = _thumb;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String _thumbPath) {
        thumbPath = _thumbPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String _accountId) {
        accountId = _accountId;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap _thumb) {
        thumb = _thumb;
    }
}
