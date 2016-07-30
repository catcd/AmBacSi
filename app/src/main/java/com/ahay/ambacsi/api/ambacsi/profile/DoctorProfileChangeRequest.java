package com.ahay.ambacsi.api.ambacsi.profile;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.ahay.ambacsi.api.ambacsi.Constant.UserGroupConstant.GROUP_DOCTOR;

/**
 * Created by SONY on 29-Jul-16.
 */
public class DoctorProfileChangeRequest extends ProfileChangeRequest {
    public DoctorProfileChangeRequest() {
        super();
        request.remove("group");
        try {
            request.put("group", GROUP_DOCTOR);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
    }

    public DoctorProfileChangeRequest setFirstName(String _firstName) {
        try {
            request.put("firstName", _firstName);
        } catch (JSONException _e) {
            request.remove("firstName");
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setLastName(String _lastName) {
        try {
            request.put("lastName", _lastName);
        } catch (JSONException _e) {
            request.remove("lastName");
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setGender(int _gender) {
        try {
            request.put("gender", _gender);
        } catch (JSONException _e) {
            request.remove("gender");
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setDob(Calendar _dob) {
        try {
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            request.put("dob", sdf.format(_dob.getTime()));
        } catch (JSONException _e) {
            request.remove("dob");
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setIdCardNo(String _idCardNo) {
        try {
            request.put("idCardNo", _idCardNo);
        } catch (JSONException _e) {
            request.remove("idCardNo");
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setHomeAddress(String _homeAddress) {
        try {
            request.put("homeAddress", _homeAddress);
        } catch (JSONException _e) {
            request.remove("homeAddress");
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setExperienceYear(int _experienceYear) {
        try {
            request.put("experienceYear", _experienceYear);
        } catch (JSONException _e) {
            request.remove("experienceYear");
            _e.printStackTrace();
        }
        return this;
    }
}
