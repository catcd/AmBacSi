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

import vn.ahaay.ambacsi.api.model.profile.ClinicalCenterProfile;

/**
 * Status: UPDATED
 * Created by SONY on 06-Aug-16.
 * Last updated by Cat Can on 26-Sep-2016.
 */
public class ClinicalCenterProfileDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wecare";
    private static final String TABLE_NAME = "clinical_centers";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ACCOUNT_ID = "accountId";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AVATAR_PATH = "avatarPath";
    private static final String COLUMN_COVER_PATH = "coverPath";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_ZIPCODE = "zipcode";
    private static final String COLUMN_TEL = "tel";
    private static final String COLUMN_EXT_NUMBER = "extNumber";
    private static final String COLUMN_FAX = "fax";
    private static final String COLUMN_NUM_OF_DOC = "numOfDoc";
    private static final String COLUMN_NUM_OF_ROOM = "numOfRoom";
    private static final String COLUMN_NUM_OF_BED = "numOfBed";
    private static final String COLUMN_NUM_OF_NURSE = "numOfNurse";
    private static final String COLUMN_FOUND_DATE = "foundDate";
    private static final String COLUMN_DIRECTOR = "director";
    private static final String COLUMN_DIRECTOR_ID = "directorId";
    private static final String COLUMN_RATE = "rate";
    private static final String COLUMN_AREA = "area";
    private static final String COLUMN_ACCEPT_INSURANCE = "acceptInsurance";
    private static final String COLUMN_ACCEPT_ONLINE_PAYMENT = "acceptOnlinePayment";
    private static final String COLUMN_ACCEPT_CARD = "acceptCard";
    private static final String COLUMN_WORKING_ON_SAT = "workingOnSat";
    private static final String COLUMN_WORKING_ON_SUN = "workingOnSun";
    private static final String COLUMN_NEED_RESERVATION = "needReservation";
    private static final String COLUMN_INSURANCE_ID_LIST = "insuranceIdList";
    private static final String COLUMN_SPECIALTY_ID_LIST = "specialtyIdList";
    private static final String COLUMN_CREATED_AT = "createdAt";
    private static final String COLUMN_UPDATED_AT = "updatedAt";

    public ClinicalCenterProfileDBHandler(Context _context, SQLiteDatabase.CursorFactory _factory) {
        super(_context, DATABASE_NAME, _factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _database) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_ACCOUNT_ID + " TEXT,"
                + COLUMN_TYPE + " INTEGER,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_AVATAR_PATH + " TEXT,"
                + COLUMN_COVER_PATH + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_ZIPCODE + " TEXT,"
                + COLUMN_TEL + " TEXT,"
                + COLUMN_EXT_NUMBER + " TEXT,"
                + COLUMN_FAX + " TEXT,"
                + COLUMN_NUM_OF_DOC + " INTEGER,"
                + COLUMN_NUM_OF_ROOM + " INTEGER,"
                + COLUMN_NUM_OF_BED + " INTEGER,"
                + COLUMN_NUM_OF_NURSE + " INTEGER,"
                + COLUMN_FOUND_DATE + " INTEGER,"
                + COLUMN_DIRECTOR + " TEXT,"
                + COLUMN_DIRECTOR_ID + " TEXT,"
                + COLUMN_RATE + " INTEGER,"
                + COLUMN_AREA + " REAL,"
                + COLUMN_ACCEPT_INSURANCE + " INTEGER,"
                + COLUMN_ACCEPT_ONLINE_PAYMENT + " INTEGER,"
                + COLUMN_ACCEPT_CARD + " INTEGER,"
                + COLUMN_WORKING_ON_SAT + " INTEGER,"
                + COLUMN_WORKING_ON_SUN + " INTEGER,"
                + COLUMN_NEED_RESERVATION + " INTEGER,"
                + COLUMN_INSURANCE_ID_LIST + " TEXT,"
                + COLUMN_SPECIALTY_ID_LIST + " TEXT,"
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

    public void addClinicalCenterProfile(ClinicalCenterProfile _clinicalCenterProfile) {
        ContentValues __row = parseClinicalCenterProfile(_clinicalCenterProfile);

        SQLiteDatabase __database = this.getWritableDatabase();
        __database.insertWithOnConflict(TABLE_NAME, null, __row, SQLiteDatabase.CONFLICT_REPLACE);
        __database.close();
    }

    public int deleteClinicalCenterProfile(String _id) {
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

    public int updateClinicalCenterProfile(ClinicalCenterProfile _clinicalCenterProfile) {
        SQLiteDatabase __database = this.getWritableDatabase();
        ContentValues __row = parseClinicalCenterProfile(_clinicalCenterProfile);
        int __count = __database.update(
                TABLE_NAME,
                __row,
                COLUMN_ID + " = '?'", new String[]{_clinicalCenterProfile.getId()}
        );
        __database.close();
        return __count;
    }

    @Nullable
    public ClinicalCenterProfile findClinicalCenterProfile(String _id) {
        SQLiteDatabase __database = this.getReadableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, null,
                COLUMN_ID + " = '?'", new String[]{_id},
                null, null, null
        );

        ClinicalCenterProfile __clinicalCenterProfile = null;
        if (__cursor.moveToFirst()) {
            __clinicalCenterProfile = parseCursor(__cursor);
        } else {
            __clinicalCenterProfile = null;
        }
        __cursor.close();
        __database.close();
        return __clinicalCenterProfile;
    }

    public List<ClinicalCenterProfile> fetchAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor __cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        List<ClinicalCenterProfile> __ClinicalCenterProfiles = new ArrayList<>();
        if (__cursor != null) {
            __cursor.moveToFirst();
            while (__cursor.moveToNext()) {
                __ClinicalCenterProfiles.add(parseCursor(__cursor));
            }
            __cursor.close();
        }
        return __ClinicalCenterProfiles;
    }

    @NonNull
    private ContentValues parseClinicalCenterProfile(ClinicalCenterProfile _clinicalCenterProfile) {
        ContentValues row = new ContentValues();
        row.put(COLUMN_ID, _clinicalCenterProfile.getId());
        row.put(COLUMN_ACCOUNT_ID, _clinicalCenterProfile.getAccountId());
        row.put(COLUMN_TYPE, _clinicalCenterProfile.getType());
        row.put(COLUMN_NAME, _clinicalCenterProfile.getName());
        row.put(COLUMN_AVATAR_PATH, _clinicalCenterProfile.getAvatarPath());
        row.put(COLUMN_COVER_PATH, _clinicalCenterProfile.getCoverPath());
        row.put(COLUMN_LOCATION, _clinicalCenterProfile.getLocation());
        row.put(COLUMN_ZIPCODE, _clinicalCenterProfile.getZipcode());
        row.put(COLUMN_TEL, _clinicalCenterProfile.getTel());
        row.put(COLUMN_EXT_NUMBER, _clinicalCenterProfile.getExtNumber());
        row.put(COLUMN_FAX, _clinicalCenterProfile.getFax());
        row.put(COLUMN_NUM_OF_DOC, _clinicalCenterProfile.getNumOfDoc());
        row.put(COLUMN_NUM_OF_ROOM, _clinicalCenterProfile.getNumOfRoom());
        row.put(COLUMN_NUM_OF_BED, _clinicalCenterProfile.getNumOfBed());
        row.put(COLUMN_NUM_OF_NURSE, _clinicalCenterProfile.getNumOfNurse());
        row.put(COLUMN_FOUND_DATE, _clinicalCenterProfile.getFoundDate().getTimeInMillis()/1000);
        row.put(COLUMN_DIRECTOR, _clinicalCenterProfile.getDirector());
        row.put(COLUMN_DIRECTOR_ID, _clinicalCenterProfile.getDirectorId());
        row.put(COLUMN_RATE, _clinicalCenterProfile.getRate());
        row.put(COLUMN_AREA, _clinicalCenterProfile.getArea());
        row.put(COLUMN_ACCEPT_INSURANCE, _clinicalCenterProfile.isAcceptInsurance()?1:0);
        row.put(COLUMN_ACCEPT_ONLINE_PAYMENT, _clinicalCenterProfile.isAcceptOnlinePayment()?1:0);
        row.put(COLUMN_ACCEPT_CARD, _clinicalCenterProfile.isAcceptCard()?1:0);
        row.put(COLUMN_WORKING_ON_SAT, _clinicalCenterProfile.isWorkingOnSat()?1:0);
        row.put(COLUMN_WORKING_ON_SUN, _clinicalCenterProfile.isWorkingOnSun()?1:0);
        row.put(COLUMN_NEED_RESERVATION, _clinicalCenterProfile.isNeedReservation()?1:0);
        row.put(COLUMN_INSURANCE_ID_LIST, _clinicalCenterProfile.getInsuranceIdList());
        row.put(COLUMN_SPECIALTY_ID_LIST, _clinicalCenterProfile.getSpecialtyIdList());
        row.put(COLUMN_CREATED_AT, _clinicalCenterProfile.getUpdatedAt().getTimeInMillis()/1000);
        row.put(COLUMN_UPDATED_AT, _clinicalCenterProfile.getCreatedAt().getTimeInMillis()/1000);
        return row;
    }

    @NonNull
    private ClinicalCenterProfile parseCursor(Cursor _cursor) {
        ClinicalCenterProfile __clinicalCenterProfile = new ClinicalCenterProfile();

        __clinicalCenterProfile.setId(_cursor.getString(0));
        __clinicalCenterProfile.setAccountId(_cursor.getString(1));
        __clinicalCenterProfile.setType(Integer.parseInt(_cursor.getString(2)));
        __clinicalCenterProfile.setName(_cursor.getString(3));
        __clinicalCenterProfile.setAvatarPath(_cursor.getString(4));
        __clinicalCenterProfile.setCoverPath(_cursor.getString(5));
        __clinicalCenterProfile.setLocation(_cursor.getString(6));
        __clinicalCenterProfile.setZipcode(_cursor.getString(7));
        __clinicalCenterProfile.setTel(_cursor.getString(8));
        __clinicalCenterProfile.setExtNumber(_cursor.getString(9));
        __clinicalCenterProfile.setFax(_cursor.getString(10));
        __clinicalCenterProfile.setNumOfDoc(Integer.parseInt(_cursor.getString(11)));
        __clinicalCenterProfile.setNumOfRoom(Integer.parseInt(_cursor.getString(12)));
        __clinicalCenterProfile.setNumOfBed(Integer.parseInt(_cursor.getString(13)));
        __clinicalCenterProfile.setNumOfNurse(Integer.parseInt(_cursor.getString(14)));

        Calendar __foundDate = Calendar.getInstance();
        __foundDate.setTimeInMillis(Long.parseLong(_cursor.getString(15)) * 1000);
        __clinicalCenterProfile.setFoundDate(__foundDate);

        __clinicalCenterProfile.setDirector(_cursor.getString(16));
        __clinicalCenterProfile.setDirectorId(_cursor.getString(17));
        __clinicalCenterProfile.setRate(Integer.parseInt(_cursor.getString(18)));
        __clinicalCenterProfile.setArea(Double.parseDouble(_cursor.getString(19)));
        __clinicalCenterProfile.setAcceptInsurance(_cursor.getString(20).equals("1"));
        __clinicalCenterProfile.setAcceptOnlinePayment(_cursor.getString(21).equals("1"));
        __clinicalCenterProfile.setAcceptCard(_cursor.getString(22).equals("1"));
        __clinicalCenterProfile.setWorkingOnSat(_cursor.getString(23).equals("1"));
        __clinicalCenterProfile.setWorkingOnSun(_cursor.getString(24).equals("1"));
        __clinicalCenterProfile.setNeedReservation(_cursor.getString(25).equals("1"));
        __clinicalCenterProfile.setInsuranceIdList(_cursor.getString(26));
        __clinicalCenterProfile.setSpecialtyIdList(_cursor.getString(27));

        Calendar __created = Calendar.getInstance();
        __created.setTimeInMillis(Integer.parseInt(_cursor.getString(28)));
        __clinicalCenterProfile.setCreatedAt(__created);

        Calendar __updated = Calendar.getInstance();
        __updated.setTimeInMillis(Integer.parseInt(_cursor.getString(29)));
        __clinicalCenterProfile.setCreatedAt(__updated);

        return __clinicalCenterProfile;
    }
}
