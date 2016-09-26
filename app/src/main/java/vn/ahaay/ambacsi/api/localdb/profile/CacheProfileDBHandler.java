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
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "wecare";
    private static final String TABLE_NAME = "cache_profiles";

    private static final String COLUMN_ACCOUNT_ID = "accountId";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_THUMB_PATH = "thumbPath";

    public CacheProfileDBHandler(Context _context, SQLiteDatabase.CursorFactory _factory) {
        super(_context, DATABASE_NAME, _factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _database) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_ACCOUNT_ID + " TEXT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
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
        boolean __result = true;
        SQLiteDatabase __database = this.getWritableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, null,
                COLUMN_ACCOUNT_ID + " = '?'", new String[]{_profile.getAccountId()},
                null, null, null
        );
        ContentValues __row = parseCacheProfile(_profile);

        if (__cursor.moveToFirst()) {
            if (!(!__cursor.getString(1).equals(_profile.getDisplayName()) || !__cursor.getString(2).equals(_profile.getThumbPath()))) {
                __result = false;
            } else {
                __result = !__cursor.getString(2).equals(_profile.getThumbPath());
                __database.update(
                        TABLE_NAME,
                        __row,
                        COLUMN_ACCOUNT_ID + " = '?'", new String[]{_profile.getAccountId()}
                );
            }
        } else {
            __database.insert(TABLE_NAME, null, __row);
            __result = true;
        }
        __database.close();

        return __result;
    }

    // return true if need update image thumb
    public boolean addCacheProfile(JSONObject _profile) {
        String __uuid = UUID.randomUUID().toString();
        try {
            boolean __result;
            SQLiteDatabase __database = this.getWritableDatabase();
            Cursor __cursor = __database.query(
                    TABLE_NAME, null,
                    COLUMN_ACCOUNT_ID + " = '?'", new String[]{_profile.getString("account_id")},
                    null, null, null
            );
            ContentValues __row = parseCacheProfile(_profile);

            if (__cursor.moveToFirst()) {
                if (!(!__cursor.getString(1).equals(_profile.getString("name")) || !__cursor.getString(2).equals(_profile.getString("thumb_path")))) {
                    __result = false;
                } else {
                    __result = !__cursor.getString(2).equals(_profile.getString("thumb_path"));
                    __database.update(
                            TABLE_NAME,
                            __row,
                            COLUMN_ACCOUNT_ID + " = '?'", new String[]{_profile.getString("account_id")}
                    );
                }
            } else {
                __database.insert(TABLE_NAME, null, __row);
                __result = true;
            }
            __cursor.close();
            __database.close();

            return __result;
        } catch (JSONException _e) {
            return false;
        }
    }

    public int deleteCacheProfile(String _accountId) {
        SQLiteDatabase __database = this.getWritableDatabase();
        int __count = __database.delete(TABLE_NAME, COLUMN_ACCOUNT_ID + " = ?", new String[]{_accountId});
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
                COLUMN_ACCOUNT_ID + " = '?'", new String[]{_profile.getAccountId()}
        );
        __database.close();
        return __count;
    }

    @Nullable
    public CacheProfile findCacheProfile(String _accountId) {
        SQLiteDatabase __database = this.getReadableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, null,
                COLUMN_ACCOUNT_ID + " = '?'", new String[]{_accountId},
                null, null, null
        );
        CacheProfile __profile = parseCursor(__cursor);
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
        row.put(COLUMN_NAME, _profile.getDisplayName());
        row.put(COLUMN_THUMB_PATH, _profile.getThumbPath());
        return row;
    }

    @NonNull
    private ContentValues parseCacheProfile(JSONObject _profile) throws JSONException {
        ContentValues row = new ContentValues();
        row.put(COLUMN_ACCOUNT_ID, _profile.getString("account_id"));
        row.put(COLUMN_NAME, _profile.getString("name"));
        row.put(COLUMN_THUMB_PATH, _profile.getString("thumb_path"));
        return row;
    }

    @Nullable
    private CacheProfile parseCursor(Cursor _cursor) {
        CacheProfile __profile = new CacheProfile();

        if (_cursor.moveToFirst()) {
            _cursor.moveToFirst();

            __profile.setAccountId(_cursor.getString(0));
            __profile.setDisplayName(_cursor.getString(1));
            __profile.setThumbPath(_cursor.getString(2));
        } else {
            __profile = null;
        }

        return __profile;
    }
}
