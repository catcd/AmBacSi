package vn.ahaay.ambacsi.ui.profiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import vn.ahaay.ambacsi.api.ambacsi.OnCompleteListener;
import vn.ahaay.ambacsi.api.ambacsi.OnFailureListener;
import vn.ahaay.ambacsi.api.ambacsi.OnSuccessListener;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.profile.ProfileChangeRequest;
import vn.ahaay.ambacsi.ui.medicals.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.ahaay.ambacsi.api.ambacsi.Constant;

public class CreateProfileActivity extends AppCompatActivity implements
        SelectGroupFragment.OnFragmentInteractionListener,
        CreateProfileUserFragment.OnFragmentInteractionListener,
        CreateProfileDoctorFragment.OnFragmentInteractionListener,
        CreateProfileClinicalCenterFragment.OnFragmentInteractionListener {

    private final String REPLACE_FRAGMENT_STACK_TAG = "SGroup_to_Group";
    private final String REPLACE_FRAGMENT_TAG = "Second_Step";

    @BindView(vn.ahaay.ambacsi.R.id.mToolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_create_profile);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(vn.ahaay.ambacsi.R.id.createProfileContent, SelectGroupFragment.newInstance())
                .commit();

        // setup toolbar
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(REPLACE_FRAGMENT_TAG) != null) {
            getSupportFragmentManager().popBackStack(REPLACE_FRAGMENT_STACK_TAG,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void createProfile(ProfileChangeRequest _request) {
        // TODO show create profile loading
        AmBacSiAuth.getLoginUser().createProfile(_request)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> var1) {
                        // TODO hide create profile loading
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Task<Void> var1) {
                        startActivity(new Intent(CreateProfileActivity.this, HomeActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener<Void>() {
                    @Override
                    public void onFailure(@NonNull Task<Void> var1) {
                        Toast.makeText(CreateProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .execute();
    }

    @Override
    public void selectGroup(int group) {
        SharedPreferences loginUser = getSharedPreferences(Constant.SharedPreferencesConstant.PREFS_LOGIN_USER, MODE_PRIVATE);
        String __email = loginUser.getString(Constant.SharedPreferencesConstant.PREFS_LOGIN_USER_EMAIL, "");
        String __username = loginUser.getString(Constant.SharedPreferencesConstant.PREFS_LOGIN_USER_USERNAME, "");

        Fragment fragment;

        switch (group) {
            case Constant.UserGroupConstant.GROUP_USER:
                fragment = CreateProfileUserFragment.newInstance(__username, __email);
                break;
            case Constant.UserGroupConstant.GROUP_DOCTOR:
                fragment = CreateProfileDoctorFragment.newInstance(__username, __email);
                break;
            case Constant.UserGroupConstant.GROUP_CLINICAL_CENTER:
                fragment = CreateProfileClinicalCenterFragment.newInstance(__username, __email);
                break;
            default:
                fragment = CreateProfileUserFragment.newInstance(__username, __email);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        vn.ahaay.ambacsi.R.anim.enter_from_right,
                        vn.ahaay.ambacsi.R.anim.exit_to_left,
                        vn.ahaay.ambacsi.R.anim.enter_from_left,
                        vn.ahaay.ambacsi.R.anim.exit_to_right
                )
                .replace(vn.ahaay.ambacsi.R.id.createProfileContent, fragment, REPLACE_FRAGMENT_TAG)
                .addToBackStack(REPLACE_FRAGMENT_STACK_TAG)
                .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
