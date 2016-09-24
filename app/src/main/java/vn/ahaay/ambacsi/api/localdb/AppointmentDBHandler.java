package vn.ahaay.ambacsi.api.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.UUID;

import vn.ahaay.ambacsi.api.model.Appointment;
import vn.ahaay.ambacsi.api.localdb.constant.LocalDBFormatter;

/**
 * Created by SONY on 06-Aug-16.
 */
public class AppointmentDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wecare";
    public static final String TABLE_NAME = "apt_appointments";

    public static final String COLUMN_LOCAL_ID = "localId";
    public static final String COLUMN_SERVER_ID = "serverId";
    public static final String COLUMN_ACCOUNT_ID = "accountId";
    public static final String COLUMN_DOCTOR_ID = "doctorId";
    public static final String COLUMN_CLINICAL_CENTER_ID = "clinicalCenterId";
    public static final String COLUMN_APPOINTMENT_TIME = "appointmentTime";
    public static final String COLUMN_INSURANCE_ID = "insuranceId";
    public static final String COLUMN_DEPOSITED = "deposited";
    public static final String COLUMN_SPECIALTY_ID_LIST = "specialtyIdList";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_PAYMENT_METHOD = "paymentMethod";
    public static final String COLUMN_PAID_AMOUNT = "paidAmount";
    public static final String COLUMN_TOTAL_PAYMENT = "totalPayment";
    public static final String COLUMN_IS_VISIT_BEFORE = "isVisitBefore";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_CANCEL_BY = "cancelBy";
    public static final String COLUMN_UPDATED_AT = "updatedAt";
    public static final String COLUMN_CREATED_AT = "createdAt";

    public AppointmentDBHandler(Context _context, SQLiteDatabase.CursorFactory _factory) {
        super(_context, DATABASE_NAME, _factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _database) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_LOCAL_ID + " TEXT PRIMARY KEY,"
                + COLUMN_SERVER_ID + " TEXT UNIQUE,"
                + COLUMN_ACCOUNT_ID + " TEXT,"
                + COLUMN_DOCTOR_ID + " TEXT,"
                + COLUMN_CLINICAL_CENTER_ID + " TEXT,"
                + COLUMN_APPOINTMENT_TIME + " INTEGER,"
                + COLUMN_INSURANCE_ID + " TEXT,"
                + COLUMN_DEPOSITED + " INTEGER,"
                + COLUMN_SPECIALTY_ID_LIST + " TEXT,"
                + COLUMN_NOTE + " TEXT,"
                + COLUMN_PAYMENT_METHOD + " INTEGER,"
                + COLUMN_PAID_AMOUNT + " REAL,"
                + COLUMN_TOTAL_PAYMENT + " REAL,"
                + COLUMN_IS_VISIT_BEFORE + " INTEGER,"
                + COLUMN_STATUS + " INTEGER,"
                + COLUMN_CANCEL_BY + " TEXT,"
                + COLUMN_UPDATED_AT + " INTEGER,"
                + COLUMN_CREATED_AT + " INTEGER"
                + ")";
        _database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _database, int _oldVersion, int _newVersion) {
        _database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(_database);
    }

    public String addAppointment(Appointment _appointment) {
        String __uuid = UUID.randomUUID().toString();
        ContentValues __row = parseAppointment(_appointment);
        _appointment.setLocalId(__uuid);
        __row.put(COLUMN_LOCAL_ID, __uuid);

        SQLiteDatabase __database = this.getWritableDatabase();
        __database.insertWithOnConflict(TABLE_NAME, null, __row, SQLiteDatabase.CONFLICT_REPLACE);
        __database.close();

        return __uuid;
    }

    public String addAppointment(JSONObject _appointment) {
        String __uuid = UUID.randomUUID().toString();
        try {
            ContentValues __row = parseAppointment(_appointment);
            __row.put(COLUMN_LOCAL_ID, __uuid);

            SQLiteDatabase __database = this.getWritableDatabase();
            __database.insertWithOnConflict(TABLE_NAME, null, __row, SQLiteDatabase.CONFLICT_REPLACE);
            __database.close();

            return __uuid;
        } catch (JSONException | ParseException _e) {
            return "";
        }
    }

    public int deleteAppointment(String _localId) {
        SQLiteDatabase __database = this.getWritableDatabase();
        int __count = __database.delete(TABLE_NAME, COLUMN_LOCAL_ID + " = ?", new String[]{_localId});
        __database.close();
        return __count;
    }

    public int truncate() {
        SQLiteDatabase __database = this.getWritableDatabase();
        int __count = __database.delete(TABLE_NAME, "1", null);
        __database.close();
        return __count;
    }

    public int updateAppointment(Appointment _appointment) {
        SQLiteDatabase __database = this.getWritableDatabase();
        ContentValues __row = parseAppointment(_appointment);
        int __count = __database.update(
                TABLE_NAME,
                __row,
                COLUMN_LOCAL_ID + " = '?'", new String[]{_appointment.getLocalId()}
        );
        __database.close();
        return __count;
    }

    @Nullable
    public Appointment findAppointment(String _localId) {
        SQLiteDatabase __database = this.getReadableDatabase();
        Cursor __cursor = __database.query(
                TABLE_NAME, null,
                COLUMN_LOCAL_ID + " = '?'", new String[]{_localId},
                null, null, null
        );

        Appointment __appointment = null;
        try {
            __appointment = parseCursor(__cursor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        __cursor.close();
        __database.close();
        return __appointment;
    }

    public Cursor fetchAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    @NonNull
    private ContentValues parseAppointment(Appointment _appointment) {
        ContentValues row = new ContentValues();
        row.put(COLUMN_SERVER_ID, _appointment.getServerId());
        row.put(COLUMN_STATUS, _appointment.getStatus());
        row.put(COLUMN_ACCOUNT_ID, _appointment.getAccountId());
        row.put(COLUMN_DOCTOR_ID, _appointment.getDoctorId());
        row.put(COLUMN_CLINICAL_CENTER_ID, _appointment.getClinicalCenterId());
        row.put(COLUMN_APPOINTMENT_TIME, _appointment.getAppointmentTime().getTimeInMillis()/1000);
        row.put(COLUMN_INSURANCE_ID, _appointment.getInsuranceId());
        row.put(COLUMN_DEPOSITED, _appointment.isDeposited() ? 1 : 0);
        row.put(COLUMN_SPECIALTY_ID_LIST, _appointment.getSpecialtyIdList());
        row.put(COLUMN_NOTE, _appointment.getNote());
        row.put(COLUMN_PAYMENT_METHOD, _appointment.getPaymentMethod());
        row.put(COLUMN_PAID_AMOUNT, _appointment.getPaidAmount());
        row.put(COLUMN_TOTAL_PAYMENT, _appointment.getTotalPayment());
        row.put(COLUMN_IS_VISIT_BEFORE, _appointment.isVisitBefore() ? 1 : 0);
        row.put(COLUMN_STATUS, _appointment.getStatus());
        row.put(COLUMN_CANCEL_BY, _appointment.getCancelBy());
        row.put(COLUMN_UPDATED_AT, _appointment.getUpdatedAt().getTimeInMillis()/1000);
        row.put(COLUMN_CREATED_AT, _appointment.getCreatedAt().getTimeInMillis()/1000);

        return row;
    }

    @NonNull
    private ContentValues parseAppointment(JSONObject _appointment) throws JSONException, ParseException {
        ContentValues row = new ContentValues();
        row.put(COLUMN_SERVER_ID, _appointment.getString("id"));

        row.put(COLUMN_ACCOUNT_ID, _appointment.getJSONObject("account").getInt("account_id"));
        row.put(COLUMN_DOCTOR_ID, _appointment.getJSONObject("doctor").getInt("account_id"));
        if (_appointment.getJSONObject("clinical_center") != null) {
            row.put(COLUMN_CLINICAL_CENTER_ID, _appointment.getJSONObject("clinical_center").getInt("account_id"));
        }

        Calendar __appointmentTime = Calendar.getInstance();
        __appointmentTime.setTime(LocalDBFormatter.DATETIME_FORMAT.parse(_appointment.getString("appointment_time")));
        row.put(COLUMN_APPOINTMENT_TIME, __appointmentTime.getTimeInMillis()/1000);

        row.put(COLUMN_INSURANCE_ID, _appointment.getString("insurance_id"));
        row.put(COLUMN_DEPOSITED, _appointment.getInt("deposited"));
        row.put(COLUMN_SPECIALTY_ID_LIST, _appointment.getString("specialty_id_list"));
        row.put(COLUMN_NOTE, _appointment.getString("note"));
        row.put(COLUMN_PAYMENT_METHOD, _appointment.getInt("payment_method"));
        row.put(COLUMN_PAID_AMOUNT, _appointment.getDouble("paid_amount"));
        row.put(COLUMN_TOTAL_PAYMENT, _appointment.getDouble("total_payment"));
        row.put(COLUMN_IS_VISIT_BEFORE, _appointment.getInt("is_visit_before"));
        row.put(COLUMN_STATUS, _appointment.getInt("status"));
        row.put(COLUMN_CANCEL_BY, _appointment.getString("cancel_by"));

        Calendar __updatedAt = Calendar.getInstance();
        __updatedAt.setTime(LocalDBFormatter.DATETIME_FORMAT.parse(_appointment.getString("updated_at")));
        row.put(COLUMN_UPDATED_AT, __updatedAt.getTimeInMillis()/1000);

        Calendar __createdAt = Calendar.getInstance();
        __createdAt.setTime(LocalDBFormatter.DATETIME_FORMAT.parse(_appointment.getString("created_at")));
        row.put(COLUMN_CREATED_AT, __createdAt.getTimeInMillis()/1000);

        return row;
    }

    @Nullable
    private Appointment parseCursor(Cursor _cursor) throws JSONException {
        Appointment __appointment = new Appointment();

        if (_cursor.moveToFirst()) {
            __appointment.setLocalId(_cursor.getString(0));
            __appointment.setServerId(_cursor.getString(1));
            __appointment.setAccountId(_cursor.getString(2));
            __appointment.setDoctorId(_cursor.getString(3));
            __appointment.setClinicalCenterId(_cursor.getString(4));

            Calendar __appointmentTime = Calendar.getInstance();
            __appointmentTime.setTimeInMillis(Long.parseLong(_cursor.getString(5)) * 1000);
            __appointment.setAppointmentTime(__appointmentTime);

            __appointment.setInsuranceId(_cursor.getString(6));
            __appointment.setDeposited(!_cursor.getString(7).equals("0"));
            __appointment.setSpecialtyIdList(_cursor.getString(8));
            __appointment.setNote(_cursor.getString(9));
            __appointment.setPaymentMethod(Integer.parseInt(_cursor.getString(10)));
            __appointment.setPaidAmount(Double.parseDouble(_cursor.getString(11)));
            __appointment.setTotalPayment(Double.parseDouble(_cursor.getString(12)));
            __appointment.setVisitBefore(!_cursor.getString(13).equals("0"));
            __appointment.setStatus(Integer.parseInt(_cursor.getString(14)));
            __appointment.setCancelBy(_cursor.getString(15));

            Calendar __updated = Calendar.getInstance();
            __updated.setTimeInMillis(Integer.parseInt(_cursor.getString(16)));
            __appointment.setUpdatedAt(__updated);

            Calendar __created = Calendar.getInstance();
            __created.setTimeInMillis(Integer.parseInt(_cursor.getString(17)));
            __appointment.setCreatedAt(__created);
        } else {
            __appointment = null;
        }

        return __appointment;
    }
}
