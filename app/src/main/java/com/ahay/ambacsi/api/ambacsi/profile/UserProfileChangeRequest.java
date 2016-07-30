package com.ahay.ambacsi.api.ambacsi.profile;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.ahay.ambacsi.api.ambacsi.Constant.UserGroupConstant.GROUP_USER;

/**
 * Created by SONY on 29-Jul-16.
 */
public class UserProfileChangeRequest extends ProfileChangeRequest {
    public UserProfileChangeRequest() {
        super();
        request.remove("group");
        try {
            request.put("group", GROUP_USER);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
    }

    public UserProfileChangeRequest setFirstName(String _firstName) {
        try {
            request.put("firstName", _firstName);
        } catch (JSONException _e) {
            request.remove("firstName");
            _e.printStackTrace();
        }
        return this;
    }

    public UserProfileChangeRequest setLastName(String _lastName) {
        try {
            request.put("lastName", _lastName);
        } catch (JSONException _e) {
            request.remove("lastName");
            _e.printStackTrace();
        }
        return this;
    }

    public UserProfileChangeRequest setGender(int _gender) {
        try {
            request.put("gender", _gender);
        } catch (JSONException _e) {
            request.remove("gender");
            _e.printStackTrace();
        }
        return this;
    }

    public UserProfileChangeRequest setDob(Calendar _dob) {
        try {
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            request.put("gender", sdf.format(_dob.getTime()));
        } catch (JSONException _e) {
            request.remove("gender");
            _e.printStackTrace();
        }
        return this;
    }
}
