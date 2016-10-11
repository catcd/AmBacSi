package vn.ahaay.ambacsi.api.localdb.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import vn.ahaay.ambacsi.api.model.profile.CacheProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Status: ON DEBUGGING
 * Created by SONY on 13-Aug-16.
 * Last updated by Cat Can on 26-Sep-2016.
 */
public class CacheProfileDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "wecare";
    private static final String TABLE_NAME = "cache_profiles";

    private static final String COLUMN_ACCOUNT_ID = "accountId";
    private static final String COLUMN_PROFILE_ID = "profileId";
    private static final String COLUMN_ROLE= "role";
    private static final String COLUMN_DISPLAY_NAME = "displayName";
    private static final String COLUMN_THUMB_PATH = "thumbPath";

    public CacheProfileDBHandler(Context _context, SQLiteDatabase.CursorFactory _factory) {
        super(_context, DATABASE_NAME, _factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _database) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_ACCOUNT_ID + " TEXT,"
                + COLUMN_PROFILE_ID + " TEXT PRIMARY KEY,"
                + COLUMN_ROLE + " INT,"
                + COLUMN_DISPLAY_NAME + " TEXT,"
                + COLUMN_THUMB_PATH + " TEXT"
                + ")";
        _database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _database, int _oldVersion, int _newVersion) {
        _database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(_database);
    }

    // return true if need update image thumb
    public boolean addCacheProfile(CacheProfile _profile) {
        boolean __result;
        SQLiteDatabase __database = this.getWritableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, new String[] {COLUMN_DISPLAY_NAME, COLUMN_THUMB_PATH},
                COLUMN_PROFILE_ID + " = ?", new String[]{_profile.getProfileId()},
                null, null, null
        );
        ContentValues __row = parseCacheProfile(_profile);

        if (__cursor.moveToFirst()) {
            if (__cursor.getString(0).equals(_profile.getDisplayName()) && __cursor.getString(1).equals(_profile.getThumbPath())) {
                __result = false;
            } else {
                __result = !__cursor.getString(4).equals(_profile.getThumbPath());
                __database.update(
                        TABLE_NAME,
                        __row,
                        COLUMN_PROFILE_ID + " = ?", new String[]{_profile.getProfileId()}
                );
            }
        } else {
            __database.insert(TABLE_NAME, null, __row);
            __result = true;
        }
        __cursor.close();
        __database.close();

        return __result;
    }

    public int deleteCacheProfile(String _profileId) {
        SQLiteDatabase __database = this.getWritableDatabase();
        int __count = __database.delete(TABLE_NAME, COLUMN_PROFILE_ID + " = ?", new String[]{_profileId});
        __database.close();
        return __count;
    }

    public int truncate() {
        SQLiteDatabase __database = this.getWritableDatabase();
        int __count = __database.delete(TABLE_NAME, "1", null);
        __database.close();
        return __count;
    }

    public int updateCacheProfile(CacheProfile _profile) {
        SQLiteDatabase __database = this.getWritableDatabase();
        ContentValues __row = parseCacheProfile(_profile);
        int __count = __database.update(
                TABLE_NAME,
                __row,
                COLUMN_PROFILE_ID + " = ?", new String[]{_profile.getProfileId()}
        );
        __database.close();
        return __count;
    }

    @Nullable
    public CacheProfile findCacheProfile(String _profileId) {
        SQLiteDatabase __database = this.getReadableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, null,
                COLUMN_PROFILE_ID + " = ?", new String[]{_profileId},
                null, null, null
        );
        CacheProfile __profile;
        if (__cursor.moveToFirst()) {
            __profile = parseCursor(__cursor);
        } else {
            __profile = null;
        }
        __cursor.close();
        __database.close();
        return __profile;
    }

    public Cursor fetchAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(CacheProfileDBHandler.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    @NonNull
    private ContentValues parseCacheProfile(CacheProfile _profile) {
        ContentValues row = new ContentValues();
        row.put(COLUMN_ACCOUNT_ID, _profile.getAccountId());
        row.put(COLUMN_PROFILE_ID, _profile.getProfileId());
        row.put(COLUMN_ROLE, _profile.getRole());
        row.put(COLUMN_DISPLAY_NAME, _profile.getDisplayName());
        row.put(COLUMN_THUMB_PATH, _profile.getThumbPath());
        return row;
    }

    @Nullable
    private CacheProfile parseCursor(Cursor _cursor) {
        CacheProfile __profile = new CacheProfile();

        __profile.setAccountId(_cursor.getString(0));
        __profile.setProfileId(_cursor.getString(1));
        __profile.setRole(Integer.parseInt(_cursor.getString(2)));
        __profile.setDisplayName(_cursor.getString(3));
        __profile.setThumbPath(_cursor.getString(4));

        return __profile;
    }
}
