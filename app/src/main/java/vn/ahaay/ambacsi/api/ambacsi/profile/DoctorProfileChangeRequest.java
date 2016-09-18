package vn.ahaay.ambacsi.api.ambacsi.profile;

import org.json.JSONException;

import java.util.Calendar;

import vn.ahaay.ambacsi.api.ambacsi.Constant;

/**
 * Created by SONY on 29-Jul-16.
 */
public class DoctorProfileChangeRequest extends ProfileChangeRequest {
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dob";
    public static final String KEY_ID_CARD_NO = "id_card_no";
    public static final String KEY_HOME_ADDRESS= "home_address";
    public static final String KEY_EXPERIENCE_YEAR = "experience_year";

    public DoctorProfileChangeRequest() {
        super();
        try {
            request.putOpt(KEY_ROLE, Constant.UserGroupConstant.GROUP_DOCTOR);
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

    public DoctorProfileChangeRequest setFirstName(String _firstName) {
        try {
            request.putOpt(KEY_FIRST_NAME, _firstName);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setLastName(String _lastName) {
        try {
            request.putOpt(KEY_LAST_NAME, _lastName);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setGender(int _gender) {
        try {
            request.putOpt(KEY_GENDER, _gender);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setDob(Calendar _dob) {
        try {
            request.putOpt(KEY_DOB, Constant.FormatConstant.DATE_FORMAT.format(_dob.getTime()));
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setIdCardNo(String _idCardNo) {
        try {
            request.putOpt(KEY_ID_CARD_NO, _idCardNo);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setHomeAddress(String _homeAddress) {
        try {
            request.putOpt(KEY_HOME_ADDRESS, _homeAddress);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }

    public DoctorProfileChangeRequest setExperienceYear(int _experienceYear) {
        try {
            request.putOpt(KEY_EXPERIENCE_YEAR, _experienceYear);
        } catch (JSONException _e) {
            _e.printStackTrace();
        }
        return this;
    }
}
