package vn.ahaay.ambacsi.api.ambacsi.schedule;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.constant.ServerFormatter;

/**
 * Created by Can on 9/3/2016.
 */
public class ScheduleChangeRequest {
    private static final String KEY_COLOR_ID = "color_id";
    private static final String KEY_CLINICAL_CENTER_ID = "clinical_center_id";
    private static final String KEY_DOCTOR_ID = "doctor_id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_END_TIME_UNSPECIFIED = "end_time_unspecified";
    private static final String KEY_END = "end";
    private static final String KEY_START = "start";
    private static final String KEY_SUMMARY = "summary";
    private static final String KEY_STATUS = "status";
    private static final String KEY_SOURCE = "source";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_RECURRENCE = "recurrence";
    private static final String KEY_REMINDERS = "reminders";
    private static final String KEY_VISIBILITY = "visibility";

    protected JSONObject request;
    protected String id;

    public ScheduleChangeRequest(String _id) {
        request = new JSONObject();
        id = _id;
    }

    public ScheduleChangeRequest() {
        request = new JSONObject();
    }

    public String getHttpRequestBody() {
        return request.toString();
    }

    public ScheduleChangeRequest setColorId(String  _colorId) {
        try {
            request.putOpt(KEY_COLOR_ID, _colorId);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setClinicalCenterId(String  _clinicalCenterId) {
        try {
            request.putOpt(KEY_CLINICAL_CENTER_ID, _clinicalCenterId);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setDoctorId(String  _doctorId) {
        try {
            request.putOpt(KEY_DOCTOR_ID, _doctorId);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setDescription(String  _description) {
        try {
            request.putOpt(KEY_DESCRIPTION, _description);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setEndTimeUnspecified(boolean  _endTimeUnspecified) {
        try {
            request.putOpt(KEY_END_TIME_UNSPECIFIED, _endTimeUnspecified);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setEnd(Calendar _end) {
        try {
            request.putOpt(KEY_END, ServerFormatter.DATE_FORMAT.format(_end.getTime()));
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setStart(Calendar  _start) {
        try {
            request.putOpt(KEY_START, ServerFormatter.DATE_FORMAT.format(_start.getTime()));
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setSummary(String  _summary) {
        try {
            request.putOpt(KEY_SUMMARY, _summary);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setStatus(String  _status) {
        try {
            request.putOpt(KEY_STATUS, _status);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setSource(String  _source) {
        try {
            request.putOpt(KEY_SOURCE, _source);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setLocation(String  _location) {
        try {
            request.putOpt(KEY_LOCATION, _location);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setRecurrence(String  _recurrence) {
        try {
            request.putOpt(KEY_RECURRENCE, _recurrence);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setReminders(String  _reminders) {
        try {
            request.putOpt(KEY_REMINDERS, _reminders);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ScheduleChangeRequest setVisibility(String  _visibility) {
        try {
            request.putOpt(KEY_VISIBILITY, _visibility);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }
}
