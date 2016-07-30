package com.ahay.ambacsi.ui.profiles;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ahay.ambacsi.R;
import com.ahay.ambacsi.api.ambacsi.profile.ProfileChangeRequest;

import static com.ahay.ambacsi.api.ambacsi.Constant.UserGroupConstant.GROUP_CLINICAL_CENTER;
import static com.ahay.ambacsi.api.ambacsi.Constant.UserGroupConstant.GROUP_DOCTOR;
import static com.ahay.ambacsi.api.ambacsi.Constant.UserGroupConstant.GROUP_USER;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_EMAIL;
import static com.ahay.ambacsi.constant.SharedPreferencesConstant.PREFS_LOGIN_USER_USERNAME;

public class CreateProfileActivity extends AppCompatActivity implements
        SelectGroupFragment.OnFragmentInteractionListener,
        CreateProfileUserFragment.OnFragmentInteractionListener,
        CreateProfileDoctorFragment.OnFragmentInteractionListener,
        CreateProfileClinicalCenterFragment.OnFragmentInteractionListener {

    private final String REPLACE_FRAGMENT_STACK_TAG = "SGroup_to_Group";
    private final String REPLACE_FRAGMENT_TAG = "Second_Step";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.createProfileContent, SelectGroupFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(REPLACE_FRAGMENT_TAG) != null) {
            getSupportFragmentManager().popBackStack(REPLACE_FRAGMENT_STACK_TAG,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void createProfile(ProfileChangeRequest _request) {

    }

    @Override
    public void selectGroup(int group) {
        SharedPreferences loginUser = getSharedPreferences(PREFS_LOGIN_USER, MODE_PRIVATE);
        String __email = loginUser.getString(PREFS_LOGIN_USER_EMAIL, "");
        String __username = loginUser.getString(PREFS_LOGIN_USER_USERNAME, "");

        Fragment fragment;

        switch (group) {
            case GROUP_USER:
                fragment = CreateProfileUserFragment.newInstance(__username, __email);
                break;
            case GROUP_DOCTOR:
                fragment = CreateProfileDoctorFragment.newInstance(__username, __email);
                break;
            case GROUP_CLINICAL_CENTER:
                fragment = CreateProfileClinicalCenterFragment.newInstance(__username, __email);
                break;
            default:
                fragment = CreateProfileUserFragment.newInstance(__username, __email);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                )
                .replace(R.id.createProfileContent, fragment, REPLACE_FRAGMENT_TAG)
                .addToBackStack(REPLACE_FRAGMENT_STACK_TAG)
                .commit();
    }
}
