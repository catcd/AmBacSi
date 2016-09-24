package vn.ahaay.ambacsi.api.localdb;

//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.graphics.Color;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//
//import vn.ahaay.ambacsi.api.ambacsi.Constant.FormatConstant;
//import vn.ahaay.ambacsi.api.model.Schedule;
//import vn.ahaay.ambacsi.api.model.SimpleSchedule;
//import com.alamkanak.weekview.WeekViewEvent;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.UUID;

/**
 * Created by SONY on 06-Aug-16.
 */
public class ClinicalCenterDBHandler /*extends SQLiteOpenHelper*/ {
//    private static final int DATABASE_VERSION = 3;
//    private static final String DATABASE_NAME = "wecare";
//    public static final String TABLE_NAME = "apt_schedules";
//
//    public static final String COLUMN_LOCAL_ID = "localId";
//    public static final String COLUMN_SERVER_ID = "serverId";
//    public static final String COLUMN_STATUS = "status";
//    public static final String COLUMN_UPDATED_AT = "updatedAt";
//    public static final String COLUMN_CREATED_AT = "createdAt";
//    public static final String COLUMN_SUMMARY = "summary";
//    public static final String COLUMN_DESCRIPTION = "description";
//    public static final String COLUMN_LOCATION = "location";
//    public static final String COLUMN_COLOR_ID = "colorId";
//    public static final String COLUMN_DOCTOR_ID = "doctorId";
//    public static final String COLUMN_CLINICAL_CENTER_ID = "clinicalCenterId";
//    public static final String COLUMN_START = "start";
//    public static final String COLUMN_END = "end";
//    public static final String COLUMN_END_TIME_UNSPECIFIED = "endTimeUnspecified";
//    public static final String COLUMN_RECURRENCE = "recurrence";
//    public static final String COLUMN_RECURRING_EVENT_ID = "recurringEventId";
//    public static final String COLUMN_VISIBILITY = "visibility";
//    public static final String COLUMN_REMINDERS = "reminders";
//    public static final String COLUMN_SOURCE = "source";
//
//    public ClinicalCenterDBHandler(Context _context, SQLiteDatabase.CursorFactory _factory) {
//        super(_context, DATABASE_NAME, _factory, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase _database) {
//        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
//                + "("
//                + COLUMN_LOCAL_ID + " TEXT PRIMARY KEY,"
//                + COLUMN_SERVER_ID + " INTEGER UNIQUE,"
//                + COLUMN_STATUS + " TEXT,"
//                + COLUMN_UPDATED_AT + " INTEGER,"
//                + COLUMN_CREATED_AT + " INTEGER,"
//                + COLUMN_SUMMARY + " TEXT,"
//                + COLUMN_DESCRIPTION + " TEXT,"
//                + COLUMN_LOCATION + " TEXT,"
//                + COLUMN_COLOR_ID + " INTEGER,"
//                + COLUMN_DOCTOR_ID + " INTEGER,"
//                + COLUMN_CLINICAL_CENTER_ID + " INTEGER,"
//                + COLUMN_START + " INTEGER,"
//                + COLUMN_END + " INTEGER,"
//                + COLUMN_END_TIME_UNSPECIFIED + " INTEGER,"
//                + COLUMN_RECURRENCE + " TEXT,"
//                + COLUMN_RECURRING_EVENT_ID + " TEXT,"
//                + COLUMN_VISIBILITY + " TEXT,"
//                + COLUMN_REMINDERS + " TEXT,"
//                + COLUMN_SOURCE + " TEXT"
//                + ")";
//        _database.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase _database, int _oldVersion, int _newVersion) {
//        _database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(_database);
//    }
//
//    public String addSchedule(Schedule _schedule) {
//        String __uuid = UUID.randomUUID().toString();
//        ContentValues __row = parseSchedule(_schedule);
//        _schedule.setLocalId(__uuid);
//        __row.put(COLUMN_LOCAL_ID, __uuid);
//
//        SQLiteDatabase __database = this.getWritableDatabase();
//        __database.insertWithOnConflict(TABLE_NAME, null, __row, SQLiteDatabase.CONFLICT_REPLACE);
//        __database.close();
//
//        return __uuid;
//    }
//
//    public String addSchedule(JSONObject _schedule) {
//        String __uuid = UUID.randomUUID().toString();
//        try {
//            ContentValues __row = parseSchedule(_schedule);
//            __row.put(COLUMN_LOCAL_ID, __uuid);
//
//            SQLiteDatabase __database = this.getWritableDatabase();
//            __database.insertWithOnConflict(TABLE_NAME, null, __row, SQLiteDatabase.CONFLICT_REPLACE);
//            __database.close();
//
//            return __uuid;
//        } catch (JSONException | ParseException _e) {
//            return "";
//        }
//    }
//
//    public int deleteSchedule(String _localId) {
//        SQLiteDatabase __database = this.getWritableDatabase();
//        int __count = __database.delete(TABLE_NAME, COLUMN_LOCAL_ID + " = ?", new String[]{_localId});
//        __database.close();
//        return __count;
//    }
//
//    public int truncate() {
//        SQLiteDatabase __database = this.getWritableDatabase();
//        int __count = __database.delete(TABLE_NAME, "1", null);
//        __database.close();
//        return __count;
//    }
//
//    public int updateSchedule(Schedule _schedule) {
//        SQLiteDatabase __database = this.getWritableDatabase();
//        ContentValues __row = parseSchedule(_schedule);
//        int __count = __database.update(
//                TABLE_NAME,
//                __row,
//                COLUMN_LOCAL_ID + " = '?'", new String[]{_schedule.getLocalId()}
//        );
//        __database.close();
//        return __count;
//    }
//
//    @Nullable
//    public Schedule findSchedule(String _localId) {
//        SQLiteDatabase __database = this.getReadableDatabase();
//        Cursor __cursor = __database.query(
//                TABLE_NAME, null,
//                COLUMN_LOCAL_ID + " = '?'", new String[]{_localId},
//                null, null, null
//        );
//
//        Schedule __schedule = null;
//        try {
//            __schedule = parseCursor(__cursor);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        __cursor.close();
//        __database.close();
//        return __schedule;
//    }
//
//    @Nullable
//    public List<? extends WeekViewEvent> findScheduleForWeekView(int _year, int _month) {
//        SQLiteDatabase __database = this.getReadableDatabase();
//        String[] __columns = new String[]{
//                COLUMN_LOCAL_ID,
//                COLUMN_SERVER_ID,
//                COLUMN_SUMMARY,
//                COLUMN_START,
//                COLUMN_END
//        };
//
//        Calendar __arg1 = Calendar.getInstance();
//        __arg1.set(_year, _month - 1, 1, 0, 0, 0);
//
//        Calendar __arg2 = Calendar.getInstance();
//        __arg2.set(_year, _month, 1, 0, 0, 0);
//
//        String[] __args = new String[]{
//                Long.toString(__arg1.getTimeInMillis()/1000),
//                Long.toString(__arg2.getTimeInMillis()/1000)
//        };
//
//        // Populate the week view with some events.
//        List<SimpleSchedule> __schedules = new ArrayList<>();
//
//        Cursor __cursor = __database.query(
//                TABLE_NAME, __columns,
//                COLUMN_START + " >= ? AND " + COLUMN_START + " < ?", __args,
//                null, null, null
//        );
//
//        if(__cursor != null) {
//            String __localId;
//            String __serverId;
//            String __summary;
//            Calendar __start;
//            Calendar __end;
//            SimpleSchedule __schedule;
//
//            while (__cursor.moveToNext()) {
//                __localId = __cursor.getString(0);
//                __serverId = __cursor.getString(1);
//                __summary = __cursor.getString(2);
//
//                __start = Calendar.getInstance();
//                __start.setTimeInMillis(Long.parseLong(__cursor.getString(3)) * 1000);
//
//                __end = Calendar.getInstance();
//                __end.setTimeInMillis(Long.parseLong(__cursor.getString(4)) * 1000);
//
//                __schedule = new SimpleSchedule(__summary, __start, __end, __localId, __serverId);
//                __schedule.setColor(Color.parseColor("#636161"));
//                __schedules.add(__schedule);
//            }
//        }
//
//        return __schedules;
//    }
//
//    public Cursor fetchAll() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(ClinicalCenterDBHandler.TABLE_NAME, null, null, null, null, null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }
//
//    @NonNull
//    private ContentValues parseSchedule(Schedule _schedule) {
//        ContentValues row = new ContentValues();
//        row.put(COLUMN_SERVER_ID, _schedule.getServerId());
//        row.put(COLUMN_STATUS, _schedule.getStatus());
//        row.put(COLUMN_UPDATED_AT, _schedule.getUpdatedAt().getTimeInMillis()/1000);
//        row.put(COLUMN_CREATED_AT, _schedule.getCreatedAt().getTimeInMillis()/1000);
//        row.put(COLUMN_SUMMARY, _schedule.getSummary());
//        row.put(COLUMN_DESCRIPTION, _schedule.getDescription());
//        row.put(COLUMN_LOCATION, _schedule.getLocation());
//        row.put(COLUMN_COLOR_ID, _schedule.getColorId());
//        row.put(COLUMN_DOCTOR_ID, _schedule.getDoctorId());
//        row.put(COLUMN_CLINICAL_CENTER_ID, _schedule.getClinicalCenterId());
//        row.put(COLUMN_START, _schedule.getStart().getTimeInMillis()/1000);
//        row.put(COLUMN_END, _schedule.getEnd().getTimeInMillis()/1000);
//        row.put(COLUMN_END_TIME_UNSPECIFIED, _schedule.isEndTimeUnspecified() ? 1 : 0);
//        row.put(COLUMN_RECURRENCE, TextUtils.join(";", _schedule.getRecurrence()));
//        row.put(COLUMN_RECURRING_EVENT_ID, _schedule.getRecurringEventId());
//        row.put(COLUMN_VISIBILITY, _schedule.getVisibility());
//        row.put(COLUMN_REMINDERS, _schedule.getReminders().toString());
//        row.put(COLUMN_SOURCE, _schedule.getSource());
//        return row;
//    }
//
//    @NonNull
//    private ContentValues parseSchedule(JSONObject _schedule) throws JSONException, ParseException {
//        ContentValues row = new ContentValues();
//        row.put(COLUMN_SERVER_ID, _schedule.getString("id"));
//        row.put(COLUMN_STATUS, _schedule.getString("status"));
//
//        Calendar __updatedAt = Calendar.getInstance();
//        __updatedAt.setTime(FormatConstant.DATETIME_FORMAT.parse(_schedule.getString("updated_at")));
//        row.put(COLUMN_UPDATED_AT, __updatedAt.getTimeInMillis()/1000);
//
//        Calendar __createdAt = Calendar.getInstance();
//        __createdAt.setTime(FormatConstant.DATETIME_FORMAT.parse(_schedule.getString("created_at")));
//        row.put(COLUMN_UPDATED_AT, __createdAt.getTimeInMillis()/1000);
//
//        row.put(COLUMN_SUMMARY, _schedule.getString("summary"));
//        row.put(COLUMN_DESCRIPTION, _schedule.getString("description"));
//        row.put(COLUMN_LOCATION, _schedule.getString("location"));
//        row.put(COLUMN_COLOR_ID, _schedule.getInt("color_id"));
//
//        row.put(COLUMN_DOCTOR_ID, _schedule.getJSONObject("doctor").getInt("account_id"));
//        if (_schedule.getJSONObject("clinical_center") != null) {
//            row.put(COLUMN_CLINICAL_CENTER_ID, _schedule.getJSONObject("clinical_center").getInt("account_id"));
//        }
//
//        Calendar __start = Calendar.getInstance();
//        __start.setTime(FormatConstant.DATETIME_FORMAT.parse(_schedule.getString("start")));
//        row.put(COLUMN_START, __start.getTimeInMillis()/1000);
//
//        Calendar __end = Calendar.getInstance();
//        __end.setTime(FormatConstant.DATETIME_FORMAT.parse(_schedule.getString("end")));
//        row.put(COLUMN_END, __end.getTimeInMillis()/1000);
//
//        row.put(COLUMN_LOCATION, _schedule.getInt("end_time_unspecified"));
//        row.put(COLUMN_RECURRENCE, _schedule.getString("recurrence"));
//        row.put(COLUMN_RECURRING_EVENT_ID, _schedule.getString("recurring_event_id"));
//        row.put(COLUMN_VISIBILITY, _schedule.getString("visibility"));
//        row.put(COLUMN_REMINDERS, _schedule.getString("reminders"));
//        row.put(COLUMN_SOURCE, _schedule.getString("source"));
//
//        return row;
//    }
//
//    @Nullable
//    private Schedule parseCursor(Cursor _cursor) throws JSONException {
//        Schedule __schedule = new Schedule();
//
//        if (_cursor.moveToFirst()) {
//            __schedule.setLocalId(_cursor.getString(0));
//            __schedule.setServerId(_cursor.getString(1));
//            __schedule.setStatus(_cursor.getString(2));
//
//            Calendar __updated = Calendar.getInstance();
//            __updated.setTimeInMillis(Integer.parseInt(_cursor.getString(3)));
//            __schedule.setUpdatedAt(__updated);
//
//            Calendar __created = Calendar.getInstance();
//            __created.setTimeInMillis(Integer.parseInt(_cursor.getString(4)));
//            __schedule.setCreatedAt(__created);
//
//            __schedule.setSummary(_cursor.getString(5));
//            __schedule.setDescription(_cursor.getString(6));
//            __schedule.setLocation(_cursor.getString(7));
//            __schedule.setColorId(Integer.parseInt(_cursor.getString(8)));
//
//            Calendar __start = Calendar.getInstance();
//            __start.setTimeInMillis(Long.parseLong(_cursor.getString(11)) * 1000);
//            __schedule.setStart(__start);
//
//            Calendar __end = Calendar.getInstance();
//            __end.setTimeInMillis(Long.parseLong(_cursor.getString(12)) * 1000);
//            __schedule.setEnd(__end);
//
//            __schedule.setEndTimeUnspecified(!_cursor.getString(13).equals("0"));
//            __schedule.setRecurrence(TextUtils.split(_cursor.getString(14), ";"));
//            __schedule.setRecurringEventId(_cursor.getString(15));
//            __schedule.setVisibility(_cursor.getString(16));
//            __schedule.setReminders(new JSONObject(_cursor.getString(17)));
//            __schedule.setSource(_cursor.getString(18));
//        } else {
//            __schedule = null;
//        }
//
//        return __schedule;
//    }
}
