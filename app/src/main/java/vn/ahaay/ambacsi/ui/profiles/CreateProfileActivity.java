package vn.ahaay.ambacsi.ui.profiles;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.ahaay.ambacsi.R;
import vn.ahaay.ambacsi.api.ambacsi.OnCompleteListener;
import vn.ahaay.ambacsi.api.ambacsi.OnFailureListener;
import vn.ahaay.ambacsi.api.ambacsi.OnSuccessListener;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.ambacsi.constant.UserRole;
import vn.ahaay.ambacsi.api.ambacsi.profile.ClinicalCenterProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.profile.DoctorProfileChangeRequest;
import vn.ahaay.ambacsi.api.ambacsi.profile.UserProfileChangeRequest;
import vn.ahaay.ambacsi.api.model.profile.CacheProfile;
import vn.ahaay.ambacsi.api.sharedpreference.UserDataManager;
import vn.ahaay.ambacsi.ui.medicals.HomeActivity;

public class CreateProfileActivity extends AppCompatActivity implements
        SelectGroupFragment.OnFragmentInteractionListener,
        CreateProfileUserFragment.OnFragmentInteractionListener,
        CreateProfileDoctorFragment.OnFragmentInteractionListener,
        CreateProfileClinicalCenterFragment.OnFragmentInteractionListener {

    private final String REPLACE_FRAGMENT_STACK_TAG = "SGroup_to_Group";
    private final String REPLACE_FRAGMENT_TAG = "Second_Step";

    private Menu menu;
    private MenuItem itemSkip;
    private MenuItem itemDone;

    private boolean showDone = false;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_profile, menu);
        itemSkip = menu.findItem(R.id.menu_action_skip);
        itemDone = menu.findItem(R.id.menu_action_done);
        showDone = new UserDataManager(CreateProfileActivity.this).isHaveProfile();

        if (showDone) {
            itemDone.setVisible(true);
            itemSkip.setVisible(false);
        } else {
            itemDone.setVisible(false);
            itemSkip.setVisible(true);
        }

        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;

            case R.id.menu_action_done:
                startActivity(new Intent(CreateProfileActivity.this, HomeActivity.class));
                finish();
                return true;

            case R.id.menu_action_skip:
                new AlertDialog.Builder(CreateProfileActivity.this)
                        .setTitle(R.string.create_profile_skip_title)
                        .setMessage(R.string.create_profile_skip_message)
                        .setPositiveButton(vn.ahaay.ambacsi.R.string.btn_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final ProgressDialog __dialog = new ProgressDialog(CreateProfileActivity.this);
                                __dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                __dialog.setIndeterminate(true);
                                __dialog.setCanceledOnTouchOutside(false);
                                __dialog.show();

                                final UserProfileChangeRequest __request = new UserProfileChangeRequest()
                                        .setFirstName("amBacSi")
                                        .setLastName("User");
                                try {
                                    AmBacSiAuth.getLoginAccount().createUserProfile(__request)
                                            .addOnCompleteListener(new OnCompleteListener<CacheProfile>() {
                                                @Override
                                                public void onComplete(@NonNull Task<CacheProfile> var1) {
                                                    __dialog.dismiss();
                                                }
                                            })
                                            .addOnSuccessListener(new OnSuccessListener<CacheProfile>() {
                                                @Override
                                                public void onSuccess(@NonNull Task<CacheProfile> var1) {
                                                    startActivity(new Intent(CreateProfileActivity.this, HomeActivity.class));
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener<CacheProfile>() {
                                                @Override
                                                public void onFailure(@NonNull Task<CacheProfile> var1) {
                                                    Toast.makeText(CreateProfileActivity.this, "Try again latter", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .execute();
                                } catch (AmBacSiAuthException _e) {
                                    Toast.makeText(CreateProfileActivity.this, "Try again latter", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(vn.ahaay.ambacsi.R.string.btn_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Do nothing
                            }
                        })
                        .create()
                        .show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        itemDone.setVisible(showDone);
        itemSkip.setVisible(!showDone);

        if (getSupportFragmentManager().findFragmentByTag(REPLACE_FRAGMENT_TAG) != null) {
            getSupportFragmentManager().popBackStack(REPLACE_FRAGMENT_STACK_TAG,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void createUserProfile(final UserProfileChangeRequest _request) throws AmBacSiAuthException {
        final CreateProfileUserFragment _fm = (CreateProfileUserFragment) getSupportFragmentManager().findFragmentById(R.id.createProfileContent);
        _fm.startLoading();
        AmBacSiAuth.getLoginAccount().createUserProfile(_request)
                .addOnFailureListener(new OnFailureListener<CacheProfile>() {
                    @Override
                    public void onFailure(@NonNull Task<CacheProfile> var1) {
                        Toast.makeText(CreateProfileActivity.this,
                                "Failed, try again latter",
                                Toast.LENGTH_LONG
                        ).show();
                        _fm.stopLoading();

                        // TODO queuing this function
//                        try {
//                            createUserProfile(_request);
//                        } catch (AmBacSiAuthException _e) {
//                            _e.printStackTrace();
//                        }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<CacheProfile>() {
                    @Override
                    public void onSuccess(@NonNull Task<CacheProfile> var1) {
                        UserDataManager __userDataManager = new UserDataManager(CreateProfileActivity.this);
                        List<CacheProfile> __prevList = __userDataManager.getProfileList();
                        __prevList.add(var1.getResult());
                        __userDataManager.setProfileList(
                                __prevList
                        );

                        Toast.makeText(CreateProfileActivity.this,
                                "Success, Add more or click DONE to go Home",
                                Toast.LENGTH_LONG
                        ).show();
                        showDone = true;
                        onBackPressed();
                    }
                })
                .execute();
    }

    @Override
    public void createDoctorProfile(final DoctorProfileChangeRequest _request) throws AmBacSiAuthException {
        final CreateProfileDoctorFragment _fm = (CreateProfileDoctorFragment) getSupportFragmentManager().findFragmentById(R.id.createProfileContent);
        _fm.startLoading();
        AmBacSiAuth.getLoginAccount().createDoctorProfile(_request)
                .addOnFailureListener(new OnFailureListener<CacheProfile>() {
                    @Override
                    public void onFailure(@NonNull Task<CacheProfile> var1) {
                        Toast.makeText(CreateProfileActivity.this,
                                "Failed, try again latter",
                                Toast.LENGTH_LONG
                        ).show();
                        _fm.stopLoading();

                        // TODO queuing this function
//                        try {
//                            createDoctorProfile(_request);
//                        } catch (AmBacSiAuthException _e) {
//                            _e.printStackTrace();
//                        }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<CacheProfile>() {
                    @Override
                    public void onSuccess(@NonNull Task<CacheProfile> var1) {
                        UserDataManager __userDataManager = new UserDataManager(CreateProfileActivity.this);
                        List<CacheProfile> __prevList = __userDataManager.getProfileList();
                        __prevList.add(var1.getResult());
                        __userDataManager.setProfileList(
                                __prevList
                        );

                        Toast.makeText(CreateProfileActivity.this,
                                "Success, Add more or click DONE to go Home",
                                Toast.LENGTH_LONG
                        ).show();
                        showDone = true;
                        onBackPressed();
                    }
                })
                .execute();
    }

    @Override
    public void createClinicalCenterProfile(final ClinicalCenterProfileChangeRequest _request) throws AmBacSiAuthException {
        final CreateProfileClinicalCenterFragment _fm = (CreateProfileClinicalCenterFragment) getSupportFragmentManager().findFragmentById(R.id.createProfileContent);
        _fm.startLoading();
        AmBacSiAuth.getLoginAccount().createClinicalCenterProfile(_request)
                .addOnFailureListener(new OnFailureListener<CacheProfile>() {
                    @Override
                    public void onFailure(@NonNull Task<CacheProfile> var1) {
                        Toast.makeText(CreateProfileActivity.this,
                                "Failed, try again latter",
                                Toast.LENGTH_LONG
                        ).show();
                        _fm.stopLoading();

                        // TODO queuing this function
//                        try {
//                            createClinicalCenterProfile(_request);
//                        } catch (AmBacSiAuthException _e) {
//                            _e.printStackTrace();
//                        }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<CacheProfile>() {
                    @Override
                    public void onSuccess(@NonNull Task<CacheProfile> var1) {
                        UserDataManager __userDataManager = new UserDataManager(CreateProfileActivity.this);
                        List<CacheProfile> __prevList = __userDataManager.getProfileList();
                        __prevList.add(var1.getResult());
                        __userDataManager.setProfileList(
                                __prevList
                        );

                        Toast.makeText(CreateProfileActivity.this,
                                "Success, Add more or click DONE to go Home",
                                Toast.LENGTH_LONG
                        ).show();
                        showDone = true;
                        onBackPressed();
                    }
                })
                .execute();
    }

    @Override
    public void selectGroup(int group) {
        UserDataManager.LoggedInAccount __account = new UserDataManager(CreateProfileActivity.this).getLoggedInAccount();
        String __email = __account != null ? __account.getEmail() : "";
        String __username = __account != null ? __account.getUserName() : "";

        Fragment fragment;

        switch (group) {
            case UserRole.ROLE_USER:
                fragment = CreateProfileUserFragment.newInstance(__username, __email);
                break;
            case UserRole.ROLE_DOCTOR:
                fragment = CreateProfileDoctorFragment.newInstance(__username, __email);
                break;
            case UserRole.ROLE_CLINICAL_CENTER:
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menu.setGroupVisible(0, false);
    }
}
