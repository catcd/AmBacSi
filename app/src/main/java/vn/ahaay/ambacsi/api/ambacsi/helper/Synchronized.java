package vn.ahaay.ambacsi.api.ambacsi.helper;

/**
 * Created by SONY on 13-Aug-16.
 */
public class Synchronized {
    protected static boolean mScheduleSynchronized = false;
    protected static boolean mProfileSynchronized = false;

    public static boolean isScheduleSynchronized() {
        return mScheduleSynchronized;
    }

    public static void setScheduleSynchronized(boolean _scheduleSynchronized) {
        mScheduleSynchronized = _scheduleSynchronized;
    }

    public static boolean isProfileSynchronized() {
        return mProfileSynchronized;
    }

    public static void setProfileSynchronized(boolean _profileSynchronized) {
        mProfileSynchronized = _profileSynchronized;
    }
}
