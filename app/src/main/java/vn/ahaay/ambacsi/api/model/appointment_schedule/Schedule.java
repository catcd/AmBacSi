package vn.ahaay.ambacsi.api.model.appointment_schedule;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.constant.ServerFormatter;

/**
 * Status: ON DEBUGGING
 * Created by SONY on 06-Aug-16.
 * Last updated by Cat Can on 26-Sep-16.
 */

public class Schedule {
    private String localId;
    private String serverId;
    private String status;
    private Calendar updatedAt;
    private Calendar createdAt;
    private String summary;
    private String description;
    private String location;
    private int colorId;
    private String doctorId;
    private String clinicalCenterId;
    private Calendar start;
    private Calendar end;
    private boolean endTimeUnspecified;
    private String[] recurrence;
    private String recurringEventId;
    private Calendar originalStartTime;
    private String visibility;
    private JSONObject reminders;
    private String source;

    public Schedule() {
    }

    // simple
    public Schedule(Calendar _start, Calendar _end, String _summary) {
        start = _start;
        end = _end;
        summary = _summary;
    }

    // create new
    public Schedule(String _status, Calendar _updatedAt, Calendar _createdAt, String _summary, String _description, String _location, int _colorId, String _doctorId, String _clinicalCenterId, Calendar _start, Calendar _end, boolean _endTimeUnspecified, String[] _recurrence, String _visibility, JSONObject _reminders, String _source) {
        status = _status;
        updatedAt = _updatedAt;
        createdAt = _createdAt;
        summary = _summary;
        description = _description;
        location = _location;
        colorId = _colorId;
        doctorId = _doctorId;
        clinicalCenterId = _clinicalCenterId;
        start = _start;
        end = _end;
        endTimeUnspecified = _endTimeUnspecified;
        recurrence = _recurrence;
        visibility = _visibility;
        reminders = _reminders;
        source = _source;
    }

    // load from server
    public Schedule(String _serverId, String _status, Calendar _updatedAt, Calendar _createdAt, String _summary, String _description, String _location, int _colorId, String _doctorId, String _clinicalCenterId, Calendar _start, Calendar _end, boolean _endTimeUnspecified, String[] _recurrence, String _visibility, JSONObject _reminders, String _source) {
        serverId = _serverId;
        status = _status;
        updatedAt = _updatedAt;
        createdAt = _createdAt;
        summary = _summary;
        description = _description;
        location = _location;
        colorId = _colorId;
        doctorId = _doctorId;
        clinicalCenterId = _clinicalCenterId;
        start = _start;
        end = _end;
        endTimeUnspecified = _endTimeUnspecified;
        recurrence = _recurrence;
        visibility = _visibility;
        reminders = _reminders;
        source = _source;
    }

    // generated from recurrent schedule
    public Schedule(String _localId, String _serverId, String _status, Calendar _updatedAt, Calendar _createdAt, String _summary, String _description, String _location, int _colorId, String _doctorId, String _clinicalCenterId, Calendar _start, Calendar _end, boolean _endTimeUnspecified, String[] _recurrence, String _recurringEventId, Calendar _originalStartTime, String _visibility, JSONObject _reminders, String _source) {
        localId = _localId;
        serverId = _serverId;
        status = _status;
        updatedAt = _updatedAt;
        createdAt = _createdAt;
        summary = _summary;
        description = _description;
        location = _location;
        colorId = _colorId;
        doctorId = _doctorId;
        clinicalCenterId = _clinicalCenterId;
        start = _start;
        end = _end;
        endTimeUnspecified = _endTimeUnspecified;
        recurrence = _recurrence;
        recurringEventId = _recurringEventId;
        originalStartTime = _originalStartTime;
        visibility = _visibility;
        reminders = _reminders;
        source = _source;
    }

    public Schedule(JSONObject _schedule) throws JSONException, ParseException {
        serverId = _schedule.getString("id");
        status = _schedule.getString("status");

        Calendar __updatedAt = Calendar.getInstance();
        __updatedAt.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("updated_at")));
        updatedAt = __updatedAt;


        Calendar __createdAt = Calendar.getInstance();
        __createdAt.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("created_at")));
        createdAt = __createdAt;

        summary = _schedule.getString("summary");
        description = _schedule.getString("description");
        location = _schedule.getString("location");
        colorId = _schedule.getInt("color_id");
        doctorId = _schedule.getJSONObject("doctor").getString("account_id");
        if(_schedule.has("clinical_center")) {
            clinicalCenterId = _schedule.getJSONObject("clinical_center").getString("account_id");
        }

        Calendar __start = Calendar.getInstance();
        __start.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("start")));
        start = __start;

        Calendar __end = Calendar.getInstance();
        __end.setTime(ServerFormatter.DATETIME_FORMAT.parse(_schedule.getString("end")));
        end = __end;

        endTimeUnspecified = _schedule.getInt("end_time_unspecified") != 0;
        recurrence = TextUtils.split(_schedule.getString("recurrence"), ";");
        visibility = _schedule.getString("visibility");
        reminders = _schedule.getJSONObject("reminders");
        source = _schedule.getString("source");
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String _localId) {
        localId = _localId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String _serverId) {
        serverId = _serverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String _status) {
        status = _status;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar _updatedAt) {
        updatedAt = _updatedAt;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar _createdAt) {
        createdAt = _createdAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String _summary) {
        summary = _summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String _location) {
        location = _location;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int _colorId) {
        colorId = _colorId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String _doctorId) {
        doctorId = _doctorId;
    }

    public String getClinicalCenterId() {
        return clinicalCenterId;
    }

    public void setClinicalCenterId(String _clinicalCenterId) {
        clinicalCenterId = _clinicalCenterId;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar _start) {
        start = _start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar _end) {
        end = _end;
    }

    public boolean isEndTimeUnspecified() {
        return endTimeUnspecified;
    }

    public void setEndTimeUnspecified(boolean _endTimeUnspecified) {
        endTimeUnspecified = _endTimeUnspecified;
    }

    public String[] getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String[] _recurrence) {
        recurrence = _recurrence;
    }

    public String getRecurringEventId() {
        return recurringEventId;
    }

    public void setRecurringEventId(String _recurringEventId) {
        recurringEventId = _recurringEventId;
    }

    public Calendar getOriginalStartTime() {
        return originalStartTime;
    }

    public void setOriginalStartTime(Calendar _originalStartTime) {
        originalStartTime = _originalStartTime;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String _visibility) {
        visibility = _visibility;
    }

    public JSONObject getReminders() {
        return reminders;
    }

    public void setReminders(JSONObject _reminders) {
        reminders = _reminders;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String _source) {
        source = _source;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + serverId + '\'' +
                ", status='" + status + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", color_id=" + colorId +
                ", doctor_id='" + doctorId + '\'' +
                ", clinical_center_id='" + clinicalCenterId + '\'' +
                ", start=" + ServerFormatter.DATETIME_FORMAT.format(start) +
                ", end=" + ServerFormatter.DATETIME_FORMAT.format(end) +
                ", end_time_unspecified=" + (endTimeUnspecified ? 1 : 0) +
                ", recurrence=" + TextUtils.join(";", recurrence) +
                ", visibility='" + visibility + '\'' +
                ", reminders=" + reminders +
                ", source='" + source + '\'' +
                '}';
    }
}
