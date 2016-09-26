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

import vn.ahaay.ambacsi.api.model.profile.DoctorProfile;

/**
 * Status: UPDATED
 * Created by SONY on 06-Aug-16.
 * Last updated by Cat Can on 26-Sep-2016.
 */
public class DoctorProfileDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wecare";
    private static final String TABLE_NAME = "doctors";

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
    private static final String COLUMN_PROFESSIONAL_STATEMENT = "professionalStatement";
    private static final String COLUMN_HOME_ZIPCODE = "homeZipcode";
    private static final String COLUMN_PASSPORT = "passport";
    private static final String COLUMN_ID_CARD_NO = "idCardNo";
    private static final String COLUMN_HOME_ADDRESS = "homeAddress";
    private static final String COLUMN_HOMEPHONE = "homephone";
    private static final String COLUMN_MOBILEPHONE = "mobilephone";
    private static final String COLUMN_WORKPHONE = "workphone";
    private static final String COLUMN_FAX = "fax";
    private static final String COLUMN_EXT_NUMBER = "extNumber";
    private static final String COLUMN_WORKING_EMAIL = "workingEmail";
    private static final String COLUMN_EDUCATION_LEVEL = "educationLevel";
    private static final String COLUMN_MEDICINE_EDUCATION_LEVEL = "medicineEducationLevel";
    private static final String COLUMN_EXPERIENCE_YEAR = "experienceYear";
    private static final String COLUMN_HAVING_PRIVATE_PRACTICE = "havingPrivatePractice";
    private static final String COLUMN_WORKING_FOR_PUBLIC_HOSPITAL = "workingForPublicHospital";
    private static final String COLUMN_ACCEPT_INSURANCE = "acceptInsurance";
    private static final String COLUMN_LANGUAGES = "languages";
    private static final String COLUMN_IS_VALIDATED = "isValidated";
    private static final String COLUMN_ONLINE_CONSULT = "onlineConsult";
    private static final String COLUMN_NEED_RESERVATION = "needReservation";
    private static final String COLUMN_ACCEPT_ONLINE_PAYMENT = "acceptOnlinePayment";
    private static final String COLUMN_ACCEPT_CARD = "acceptCard";
    private static final String COLUMN_DAILY_PRICE = "dailyPrice";
    private static final String COLUMN_WEEKEND_PRICE = "weekendPrice";
    private static final String COLUMN_INSURANCE_ID_LIST = "insuranceIdList";
    private static final String COLUMN_SPECIALTY_ID_LIST = "specialtyIdList";
    private static final String COLUMN_CREATED_AT = "createdAt";
    private static final String COLUMN_UPDATED_AT = "updatedAt";

    public DoctorProfileDBHandler(Context _context, SQLiteDatabase.CursorFactory _factory) {
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
                + COLUMN_PROFESSIONAL_STATEMENT + " TEXT,"
                + COLUMN_HOME_ZIPCODE + " TEXT,"
                + COLUMN_PASSPORT + " TEXT,"
                + COLUMN_ID_CARD_NO + " TEXT,"
                + COLUMN_HOME_ADDRESS + " TEXT,"
                + COLUMN_HOMEPHONE + " TEXT,"
                + COLUMN_MOBILEPHONE + " TEXT,"
                + COLUMN_WORKPHONE + " TEXT,"
                + COLUMN_FAX + " TEXT,"
                + COLUMN_EXT_NUMBER + " TEXT,"
                + COLUMN_WORKING_EMAIL + " TEXT,"
                + COLUMN_EDUCATION_LEVEL + " INTEGER,"
                + COLUMN_MEDICINE_EDUCATION_LEVEL + " INTEGER,"
                + COLUMN_EXPERIENCE_YEAR + " INTEGER,"
                + COLUMN_HAVING_PRIVATE_PRACTICE + " INTEGER,"
                + COLUMN_WORKING_FOR_PUBLIC_HOSPITAL + " ,INTEGER"
                + COLUMN_ACCEPT_INSURANCE + " INTEGER,"
                + COLUMN_LANGUAGES + " TEXT,"
                + COLUMN_IS_VALIDATED + " INTEGER,"
                + COLUMN_ONLINE_CONSULT + " INTEGER,"
                + COLUMN_NEED_RESERVATION + " INTEGER,"
                + COLUMN_ACCEPT_ONLINE_PAYMENT + " INTEGER,"
                + COLUMN_ACCEPT_CARD + " INTEGER,"
                + COLUMN_DAILY_PRICE + " REAL,"
                + COLUMN_WEEKEND_PRICE + " REAL,"
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

    public void addDoctorProfile(DoctorProfile _doctorProfile) {
        ContentValues __row = parseDoctorProfile(_doctorProfile);

        SQLiteDatabase __database = this.getWritableDatabase();
        __database.insertWithOnConflict(TABLE_NAME, null, __row, SQLiteDatabase.CONFLICT_REPLACE);
        __database.close();
    }

    public int deleteDoctorProfile(String _id) {
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

    public int updateDoctorProfile(DoctorProfile _doctorProfile) {
        SQLiteDatabase __database = this.getWritableDatabase();
        ContentValues __row = parseDoctorProfile(_doctorProfile);
        int __count = __database.update(
                TABLE_NAME,
                __row,
                COLUMN_ID + " = '?'", new String[]{_doctorProfile.getId()}
        );
        __database.close();
        return __count;
    }

    @Nullable
    public DoctorProfile findDoctorProfile(String _id) {
        SQLiteDatabase __database = this.getReadableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, null,
                COLUMN_ID + " = '?'", new String[]{_id},
                null, null, null
        );

        DoctorProfile __doctorProfile = null;
        if (__cursor.moveToFirst()) {
            __doctorProfile = parseCursor(__cursor);
        } else {
            __doctorProfile = null;
        }
        __cursor.close();
        __database.close();
        return __doctorProfile;
    }

    public List<DoctorProfile> fetchAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor __cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        List<DoctorProfile> __doctorProfiles = new ArrayList<>();
        if (__cursor != null) {
            __cursor.moveToFirst();
            while (__cursor.moveToNext()) {
                __doctorProfiles.add(parseCursor(__cursor));
            }
            __cursor.close();
        }
        return __doctorProfiles;
    }

    @NonNull
    private ContentValues parseDoctorProfile(DoctorProfile _doctorProfile) {
        ContentValues row = new ContentValues();
        row.put(COLUMN_ID, _doctorProfile.getId());
        row.put(COLUMN_ACCOUNT_ID, _doctorProfile.getAccountId());
        row.put(COLUMN_AVATAR_PATH, _doctorProfile.getAvatarPath());
        row.put(COLUMN_COVER_PATH, _doctorProfile.getCoverPath());
        row.put(COLUMN_FIRST_NAME, _doctorProfile.getFirstName());
        row.put(COLUMN_LAST_NAME, _doctorProfile.getLastName());
        row.put(COLUMN_NICK_NAME, _doctorProfile.getNickName());
        row.put(COLUMN_DOB, _doctorProfile.getDob().getTimeInMillis()/1000);
        row.put(COLUMN_GENDER, _doctorProfile.getGender());
        row.put(COLUMN_MARRIED_STATUS, _doctorProfile.getMarriedStatus());
        row.put(COLUMN_PROFESSIONAL_STATEMENT, _doctorProfile.getProfessionalStatement());
        row.put(COLUMN_HOME_ZIPCODE, _doctorProfile.getHomeZipcode());
        row.put(COLUMN_PASSPORT, _doctorProfile.getPassport());
        row.put(COLUMN_ID_CARD_NO, _doctorProfile.getIdCardNo());
        row.put(COLUMN_HOME_ADDRESS, _doctorProfile.getHomeAddress());
        row.put(COLUMN_HOMEPHONE, _doctorProfile.getHomephone());
        row.put(COLUMN_MOBILEPHONE, _doctorProfile.getMobilephone());
        row.put(COLUMN_WORKPHONE, _doctorProfile.getWorkphone());
        row.put(COLUMN_FAX, _doctorProfile.getFax());
        row.put(COLUMN_EXT_NUMBER, _doctorProfile.getExtNumber());
        row.put(COLUMN_WORKING_EMAIL, _doctorProfile.getWorkingEmail());
        row.put(COLUMN_EDUCATION_LEVEL, _doctorProfile.getEducationLevel());
        row.put(COLUMN_MEDICINE_EDUCATION_LEVEL, _doctorProfile.getMedicineEducationLevel());
        row.put(COLUMN_EXPERIENCE_YEAR, _doctorProfile.getExperienceYear());
        row.put(COLUMN_HAVING_PRIVATE_PRACTICE, _doctorProfile.isHavingPrivatePractice()?1:0);
        row.put(COLUMN_WORKING_FOR_PUBLIC_HOSPITAL, _doctorProfile.isWorkingForPublicHospital()?1:0);
        row.put(COLUMN_ACCEPT_INSURANCE, _doctorProfile.isAcceptInsurance()?1:0);
        row.put(COLUMN_LANGUAGES, _doctorProfile.getLanguages());
        row.put(COLUMN_IS_VALIDATED, _doctorProfile.isValidated()?1:0);
        row.put(COLUMN_ONLINE_CONSULT, _doctorProfile.isOnlineConsult()?1:0);
        row.put(COLUMN_NEED_RESERVATION, _doctorProfile.isNeedReservation()?1:0);
        row.put(COLUMN_ACCEPT_ONLINE_PAYMENT, _doctorProfile.isAcceptOnlinePayment()?1:0);
        row.put(COLUMN_ACCEPT_CARD, _doctorProfile.isAcceptCard()?1:0);
        row.put(COLUMN_DAILY_PRICE, _doctorProfile.getDailyPrice());
        row.put(COLUMN_WEEKEND_PRICE, _doctorProfile.getWeekendPrice());
        row.put(COLUMN_INSURANCE_ID_LIST, _doctorProfile.getInsuranceIdList());
        row.put(COLUMN_SPECIALTY_ID_LIST, _doctorProfile.getSpecialtyIdList());
        row.put(COLUMN_CREATED_AT, _doctorProfile.getCreatedAt().getTimeInMillis()/1000);
        row.put(COLUMN_UPDATED_AT, _doctorProfile.getUpdatedAt().getTimeInMillis()/1000);
        return row;
    }

    @NonNull
    private DoctorProfile parseCursor(Cursor _cursor) {
        DoctorProfile __doctorProfile = new DoctorProfile();

        __doctorProfile.setId(_cursor.getString(0));
        __doctorProfile.setAccountId(_cursor.getString(1));
        __doctorProfile.setAvatarPath(_cursor.getString(2));
        __doctorProfile.setCoverPath(_cursor.getString(3));
        __doctorProfile.setFirstName(_cursor.getString(4));
        __doctorProfile.setLastName(_cursor.getString(5));
        __doctorProfile.setNickName(_cursor.getString(6));

        Calendar __dob = Calendar.getInstance();
        __dob.setTimeInMillis(Long.parseLong(_cursor.getString(7)) * 1000);
        __doctorProfile.setDob(__dob);

        __doctorProfile.setGender(Integer.parseInt(_cursor.getString(8)));
        __doctorProfile.setMarriedStatus(Integer.parseInt(_cursor.getString(9)));
        __doctorProfile.setProfessionalStatement(_cursor.getString(10));
        __doctorProfile.setHomeZipcode(_cursor.getString(11));
        __doctorProfile.setPassport(_cursor.getString(12));
        __doctorProfile.setIdCardNo(_cursor.getString(13));
        __doctorProfile.setHomeAddress(_cursor.getString(14));
        __doctorProfile.setHomephone(_cursor.getString(15));
        __doctorProfile.setMobilephone(_cursor.getString(16));
        __doctorProfile.setWorkphone(_cursor.getString(17));
        __doctorProfile.setFax(_cursor.getString(18));
        __doctorProfile.setExtNumber(_cursor.getString(19));
        __doctorProfile.setWorkingEmail(_cursor.getString(20));
        __doctorProfile.setEducationLevel(Integer.parseInt(_cursor.getString(21)));
        __doctorProfile.setMedicineEducationLevel(Integer.parseInt(_cursor.getString(22)));
        __doctorProfile.setExperienceYear(Integer.parseInt(_cursor.getString(23)));
        __doctorProfile.setHavingPrivatePractice(_cursor.getString(24).equals("1"));
        __doctorProfile.setWorkingForPublicHospital(_cursor.getString(25).equals("1"));
        __doctorProfile.setAcceptInsurance(_cursor.getString(26).equals("1"));
        __doctorProfile.setLanguages(_cursor.getString(27));
        __doctorProfile.setValidated(_cursor.getString(28).equals("1"));
        __doctorProfile.setOnlineConsult(_cursor.getString(29).equals("1"));
        __doctorProfile.setNeedReservation(_cursor.getString(30).equals("1"));
        __doctorProfile.setAcceptOnlinePayment(_cursor.getString(31).equals("1"));
        __doctorProfile.setAcceptCard(_cursor.getString(32).equals("1"));
        __doctorProfile.setDailyPrice(Double.parseDouble(_cursor.getString(33)));
        __doctorProfile.setWeekendPrice(Double.parseDouble(_cursor.getString(34)));
        __doctorProfile.setInsuranceIdList(_cursor.getString(35));
        __doctorProfile.setSpecialtyIdList(_cursor.getString(36));

        Calendar __created = Calendar.getInstance();
        __created.setTimeInMillis(Integer.parseInt(_cursor.getString(37)));
        __doctorProfile.setCreatedAt(__created);

        Calendar __updated = Calendar.getInstance();
        __updated.setTimeInMillis(Integer.parseInt(_cursor.getString(38)));
        __doctorProfile.setCreatedAt(__updated);

        return __doctorProfile;
    }
}
