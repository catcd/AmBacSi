package com.ahay.ambacsi.api.ambacsi.profile;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.ahay.ambacsi.api.ambacsi.Constant.UserGroupConstant.GROUP_CLINICAL_CENTER;

/**
 * Created by SONY on 29-Jul-16.
 */
public final class ClinicalCenterProfileChangeRequest extends ProfileChangeRequest {
    public ClinicalCenterProfileChangeRequest() {
        super();
        request.remove("group");
        try {
            request.put("group", GROUP_CLINICAL_CENTER);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
    }

    public ClinicalCenterProfileChangeRequest setName(String _name) {
        try {
            request.put("name", _name);
        } catch (JSONException _e) {
            request.remove("name");
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setType(int _type) {
        try {
            request.put("type", _type);
        } catch (JSONException _e) {
            request.remove("type");
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setFoundYear(Calendar _fYear) {
        try {
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            request.put("foundYear", sdf.format(_fYear.getTime()));
        } catch (JSONException _e) {
            request.remove("foundYear");
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setTel(String _tel) {
        try {
            request.put("tel", _tel);
        } catch (JSONException _e) {
            request.remove("tel");
            _e.printStackTrace();
        }
        return this;
    }

    public ClinicalCenterProfileChangeRequest setLocation(String _location) {
        try {
            request.put("location", _location);
        } catch (JSONException _e) {
            request.remove("location");
            _e.printStackTrace();
        }
        return this;
    }
}
