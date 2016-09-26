package vn.ahaay.ambacsi.api.localdb.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.ahaay.ambacsi.api.model.profile.UserProfile;

/**
 * Status: UPDATED
 * Created by SONY on 06-Aug-16.
 * Last updated by Cat Can on 26-Sep-2016.
 */
public class UserProfileDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wecare";
    private static final String TABLE_NAME = "users";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ACCOUNT_ID = "accountId";
    private static final String COLUMN_AVATAR_PATH = "avatarPath";
    private static final String COLUMN_COVER_PATH = "coverPath";
    private static final String COLUMN_FIRST_NAME = "firstName";
    private static final String COLUMN_LAST_NAME = "lastName";
    private static final String COLUMN_NICK_NAME = "nickName";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_MARRIED_STATUS = "marriedStatus";
    private static final String COLUMN_HOME_ZIPCODE = "homeZipcode";
    private static final String COLUMN_PASSPORT = "passport";
    private static final String COLUMN_ID_CARD_NO = "idCardNo";
    private static final String COLUMN_HOME_ADDRESS = "homeAddress";
    private static final String COLUMN_HOMEPHONE = "homephone";
    private static final String COLUMN_MOBILEPHONE = "mobilephone";
    private static final String COLUMN_WORKPHONE = "workphone";
    private static final String COLUMN_FAX = "fax";
    private static final String COLUMN_EDUCATION_LEVEL = "educationLevel";
    private static final String COLUMN_WORKING_EMAIL = "workingEmail";
    private static final String COLUMN_LANGUAGES = "languages";
    private static final String COLUMN_CREATED_AT = "createdAt";
    private static final String COLUMN_UPDATED_AT = "updatedAt";

    public UserProfileDBHandler(Context _context, SQLiteDatabase.CursorFactory _factory) {
        super(_context, DATABASE_NAME, _factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _database) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_ACCOUNT_ID + " TEXT,"
                + COLUMN_AVATAR_PATH + " TEXT,"
                + COLUMN_COVER_PATH + " TEXT,"
                + COLUMN_FIRST_NAME + " TEXT,"
                + COLUMN_LAST_NAME + " TEXT,"
                + COLUMN_NICK_NAME + " TEXT,"
                + COLUMN_DOB + " INTEGER,"
                + COLUMN_GENDER + " INTEGER,"
                + COLUMN_MARRIED_STATUS + " INTEGER,"
                + COLUMN_HOME_ZIPCODE + " TEXT,"
                + COLUMN_PASSPORT + " TEXT,"
                + COLUMN_ID_CARD_NO + " TEXT,"
                + COLUMN_HOME_ADDRESS + " TEXT,"
                + COLUMN_HOMEPHONE + " TEXT,"
                + COLUMN_MOBILEPHONE + " TEXT,"
                + COLUMN_WORKPHONE + " TEXT,"
                + COLUMN_FAX + " TEXT,"
                + COLUMN_EDUCATION_LEVEL + " INTEGER,"
                + COLUMN_WORKING_EMAIL + " TEXT,"
                + COLUMN_LANGUAGES + " TEXT,"
                + COLUMN_CREATED_AT + " INTEGER,"
                + COLUMN_UPDATED_AT + " INTEGER"
                + ")";
        _database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _database, int _oldVersion, int _newVersion) {
        _database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(_database);
    }

    public void addUserProfile(UserProfile _userProfile) {
        ContentValues __row = parseUserProfile(_userProfile);

        SQLiteDatabase __database = this.getWritableDatabase();
        __database.insertWithOnConflict(TABLE_NAME, null, __row, SQLiteDatabase.CONFLICT_REPLACE);
        __database.close();
    }

    public int deleteUserProfile(String _id) {
        SQLiteDatabase __database = this.getWritableDatabase();
        int __count = __database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{_id});
        __database.close();
        return __count;
    }

    public int truncate() {
        SQLiteDatabase __database = this.getWritableDatabase();
        int __count = __database.delete(TABLE_NAME, "1", null);
        __database.close();
        return __count;
    }

    public int updateUserProfile(UserProfile _userProfile) {
        SQLiteDatabase __database = this.getWritableDatabase();
        ContentValues __row = parseUserProfile(_userProfile);
        int __count = __database.update(
                TABLE_NAME,
                __row,
                COLUMN_ID + " = '?'", new String[]{_userProfile.getId()}
        );
        __database.close();
        return __count;
    }

    @Nullable
    public UserProfile findUserProfile(String _id) {
        SQLiteDatabase __database = this.getReadableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, null,
                COLUMN_ID + " = '?'", new String[]{_id},
                null, null, null
        );

        UserProfile __userProfile = null;
        if (__cursor.moveToFirst()) {
            __userProfile = parseCursor(__cursor);
        } else {
            __userProfile = null;
        }
        __cursor.close();
        __database.close();
        return __userProfile;
    }

    public List<UserProfile> fetchAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor __cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        List<UserProfile> __userProfiles = new ArrayList<>();
        if (__cursor != null) {
            __cursor.moveToFirst();
            while (__cursor.moveToNext()) {
                __userProfiles.add(parseCursor(__cursor));
            }
            __cursor.close();
        }
        return __userProfiles;
    }

    @NonNull
    private ContentValues parseUserProfile(UserProfile _userProfile) {
        ContentValues row = new ContentValues();
        row.put(COLUMN_ID, _userProfile.getId());
        row.put(COLUMN_ACCOUNT_ID, _userProfile.getAccountId());
        row.put(COLUMN_AVATAR_PATH, _userProfile.getAvatarPath());
        row.put(COLUMN_COVER_PATH, _userProfile.getCoverPath());
        row.put(COLUMN_FIRST_NAME, _userProfile.getFirstName());
        row.put(COLUMN_LAST_NAME, _userProfile.getLastName());
        row.put(COLUMN_NICK_NAME, _userProfile.getNickName());
        row.put(COLUMN_DOB, _userProfile.getDob().getTimeInMillis()/1000);
        row.put(COLUMN_GENDER, _userProfile.getGender());
        row.put(COLUMN_MARRIED_STATUS, _userProfile.getMarriedStatus());
        row.put(COLUMN_HOME_ZIPCODE, _userProfile.getHomeZipcode());
        row.put(COLUMN_PASSPORT, _userProfile.getPassport());
        row.put(COLUMN_ID_CARD_NO, _userProfile.getIdCardNo());
        row.put(COLUMN_HOME_ADDRESS, _userProfile.getHomeAddress());
        row.put(COLUMN_HOMEPHONE, _userProfile.getHomephone());
        row.put(COLUMN_MOBILEPHONE, _userProfile.getMobilephone());
        row.put(COLUMN_WORKPHONE, _userProfile.getWorkphone());
        row.put(COLUMN_FAX, _userProfile.getFax());
        row.put(COLUMN_EDUCATION_LEVEL, _userProfile.getEducationLevel());
        row.put(COLUMN_WORKING_EMAIL, _userProfile.getWorkingEmail());
        row.put(COLUMN_LANGUAGES, _userProfile.getLanguages());
        row.put(COLUMN_CREATED_AT, _userProfile.getCreatedAt().getTimeInMillis()/1000);
        row.put(COLUMN_UPDATED_AT, _userProfile.getUpdatedAt().getTimeInMillis()/1000);
        return row;
    }

    @NonNull
    private UserProfile parseCursor(Cursor _cursor) {
        UserProfile __userProfile = new UserProfile();

        __userProfile.setId(_cursor.getString(0));
        __userProfile.setAccountId(_cursor.getString(1));
        __userProfile.setAvatarPath(_cursor.getString(2));
        __userProfile.setCoverPath(_cursor.getString(3));
        __userProfile.setFirstName(_cursor.getString(4));
        __userProfile.setLastName(_cursor.getString(5));
        __userProfile.setNickName(_cursor.getString(6));

        Calendar __dob = Calendar.getInstance();
        __dob.setTimeInMillis(Long.parseLong(_cursor.getString(7)) * 1000);
        __userProfile.setDob(__dob);

        __userProfile.setGender(Integer.parseInt(_cursor.getString(8)));
        __userProfile.setMarriedStatus(Integer.parseInt(_cursor.getString(9)));
        __userProfile.setHomeZipcode(_cursor.getString(10));
        __userProfile.setPassport(_cursor.getString(11));
        __userProfile.setIdCardNo(_cursor.getString(12));
        __userProfile.setHomeAddress(_cursor.getString(13));
        __userProfile.setHomephone(_cursor.getString(14));
        __userProfile.setMobilephone(_cursor.getString(15));
        __userProfile.setWorkphone(_cursor.getString(16));
        __userProfile.setFax(_cursor.getString(17));
        __userProfile.setEducationLevel(Integer.parseInt(_cursor.getString(18)));
        __userProfile.setWorkingEmail(_cursor.getString(19));
        __userProfile.setLanguages(_cursor.getString(20));

        Calendar __created = Calendar.getInstance();
        __created.setTimeInMillis(Integer.parseInt(_cursor.getString(21)));
        __userProfile.setCreatedAt(__created);

        Calendar __updated = Calendar.getInstance();
        __updated.setTimeInMillis(Integer.parseInt(_cursor.getString(22)));
        __userProfile.setCreatedAt(__updated);

        return __userProfile;
    }
}
