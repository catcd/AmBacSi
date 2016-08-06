package com.ahay.ambacsi.api.ambacsi.profile;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.ahay.ambacsi.api.ambacsi.Constant.UserGroupConstant;
import com.ahay.ambacsi.api.ambacsi.Constant.FormatConstant;

/**
 * Created by SONY on 29-Jul-16.
 */
public final class ClinicalCenterProfileChangeRequest extends ProfileChangeRequest {
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type";
    public static final String KEY_FOUND_YEAR = "foundYear";
    public static final String KEY_TEL = "tel";
    public static final String KEY_LOCATION = "location";

    public ClinicalCenterProfileChangeRequest() {
        super();
        try {
            request.putOpt(KEY_ROLE, UserGroupConstant.GROUP_CLINICAL_CENTER);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
    }

    @Override
    public String getDisplayName() {
        return request.optString(KEY_NAME, "");
    }

    public ClinicalCenterProfileChangeRequest setName(String _name) {
        try {
            request.putOpt(KEY_NAME, _name);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setType(int _type) {
        try {
            request.putOpt(KEY_TYPE, _type);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setFoundYear(Calendar _fYear) {
        try {
            String myFormat = FormatConstant.DATE_FORMAT;
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            request.putOpt(KEY_FOUND_YEAR, sdf.format(_fYear.getTime()));
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setTel(String _tel) {
        try {
            request.putOpt(KEY_TEL, _tel);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setLocation(String _location) {
        try {
            request.putOpt(KEY_LOCATION, _location);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }
}
