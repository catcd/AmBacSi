package vn.ahaay.ambacsi.api.ambacsi.profile;

import org.json.JSONException;

import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.Constant.UserGroupConstant;
import vn.ahaay.ambacsi.api.ambacsi.Constant.FormatConstant;

/**
 * Created by SONY on 29-Jul-16.
 */
public class UserProfileChangeRequest extends ProfileChangeRequest {
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dob";

    public UserProfileChangeRequest() {
        super();
        try {
            request.putOpt(KEY_ROLE, UserGroupConstant.GROUP_USER);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
    }

    @Override
    public String getDisplayName() {
        String __fName = request.optString(KEY_FIRST_NAME, "");
        String __lName = request.optString(KEY_LAST_NAME, "");

        return (!__fName.equals("") && !__lName.equals("")) ?
                (__fName + " " + __lName) :
                (!__fName.equals("") ?
                        __fName :
                        __lName
                );
    }

    public UserProfileChangeRequest setFirstName(String _firstName) {
        try {
            request.putOpt(KEY_FIRST_NAME, _firstName);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public UserProfileChangeRequest setLastName(String _lastName) {
        try {
            request.putOpt(KEY_LAST_NAME, _lastName);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public UserProfileChangeRequest setGender(int _gender) {
        try {
            request.putOpt(KEY_GENDER, _gender);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public UserProfileChangeRequest setDob(Calendar _dob) {
        try {
            request.putOpt(KEY_DOB, FormatConstant.DATE_FORMAT.format(_dob.getTime()));
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }
}
