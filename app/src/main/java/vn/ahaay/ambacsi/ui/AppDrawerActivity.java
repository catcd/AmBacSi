package vn.ahaay.ambacsi.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import vn.ahaay.ambacsi.R;
import vn.ahaay.ambacsi.api.ambacsi.OnCompleteListener;
import vn.ahaay.ambacsi.api.ambacsi.OnFailureListener;
import vn.ahaay.ambacsi.api.ambacsi.OnSuccessListener;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.model.profile.CacheProfile;
import vn.ahaay.ambacsi.api.sharedpreference.UserDataManager;
import vn.ahaay.ambacsi.helper.ConnectivityReceiver;
import vn.ahaay.ambacsi.ui.helpers.HelpActivity;
import vn.ahaay.ambacsi.ui.helpers.SettingsActivity;
import vn.ahaay.ambacsi.ui.appointment_schedule.AppointmentPlanerActivity;
import vn.ahaay.ambacsi.ui.medicals.MedicalRecordActivity;
import vn.ahaay.ambacsi.ui.appointment_schedule.MyAppointmentActivity;
import vn.ahaay.ambacsi.ui.appointment_schedule.MyScheduleActivity;
import vn.ahaay.ambacsi.ui.appointment_schedule.ScheduleActivity;
import vn.ahaay.ambacsi.ui.outsides.LoginActivity;
import vn.ahaay.ambacsi.ui.profiles.CreateProfileActivity;
import vn.ahaay.ambacsi.ui.profiles.LinkMyAccountActivity;
import vn.ahaay.ambacsi.ui.profiles.MyProfileActivity;
import vn.ahaay.ambacsi.ui.profiles.RegisterAnonymousActivity;
import vn.ahaay.ambacsi.ui.socials.NotificationsActivity;
import vn.ahaay.ambacsi.ui.socials.SearchActivity;

/**
 * Created by SONY on 07-Jul-16.
 */
public abstract class AppDrawerActivity extends AppBaseActivity {
    public static final int DRAWER_ITEM_MY_APPOINTMENT = 0;
    public static final int DRAWER_ITEM_MY_SCHEDULE = 1;
    public static final int DRAWER_ITEM_SEARCH = 2;
    public static final int DRAWER_ITEM_NOTIFICATIONS = 3;
    public static final int DRAWER_ITEM_APPOINTMENT_PLANER = 4;
    public static final int DRAWER_ITEM_SCHEDULE = 5;
    public static final int DRAWER_ITEM_MY_MEDICAL_RECORD = 6;
    public static final int DRAWER_ITEM_SETTINGS = 7;
    public static final int DRAWER_ITEM_HELP = 8;
    public static final int DRAWER_ITEM_LOGOUT = 9;

    public static final int PROFILE_DRAWER_ITEM_ADD_PROFILE = 100;
    public static final int PROFILE_DRAWER_ITEM_ACCOUNT_SETTING = 101;
    public static final int PROFILE_DRAWER_ITEM_REGISTER = 102;
    public static final int PROFILE_DRAWER_ITEM_LINK_MY_ACCOUNT = 103;

    public static final int PROFILE_MIN_IDENTIFIER = 200;

    protected Drawer drawer;
    protected List<CacheProfile> cacheProfiles = new ArrayList<>();
    protected CacheProfile selectedProfile;
    protected List<Bitmap> avatars = new ArrayList<>();

    protected String email;

    protected Drawable avatar;

    protected void setupNavigationDrawer(final Toolbar mToolbar, int mSelected){
        getAccountInformationForDrawer();
        final UserDataManager __userDataManager = new UserDataManager(this);
        // select profile
        if (__userDataManager.isSetPrimaryProfile()) {
            selectedProfile = __userDataManager.getPrimaryProfile();
            AmBacSiAuth.setAccountProfile(selectedProfile);

            // setup navigation drawer
            // extent from AppDrawerActivity
            buildNavigationDrawer(mToolbar, -1);
        } else if(cacheProfiles.size() == 1) {
            selectedProfile = cacheProfiles.get(0);
            __userDataManager.setPrimaryProfile(true);
            __userDataManager.setPrimaryProfile(selectedProfile);
            AmBacSiAuth.setAccountProfile(selectedProfile);

            // setup navigation drawer
            // extent from AppDrawerActivity
            buildNavigationDrawer(mToolbar, -1);
        } else {
            String[] __strings = new String[cacheProfiles.size()];
            for (int i = 0; i < cacheProfiles.size(); i++) {
                __strings[i] = cacheProfiles.get(i).getRoleString()
                        + ": "
                        + cacheProfiles.get(i).getDisplayName();
            }
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.home_select_profile_title))
                    .setSingleChoiceItems(
                            __strings,
                            0,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface _dialogInterface, int _i) {
                                    selectedProfile = cacheProfiles.get(_i);
                                    __userDataManager.setPrimaryProfile(true);
                                    __userDataManager.setPrimaryProfile(selectedProfile);
                                    AmBacSiAuth.setAccountProfile(selectedProfile);

                                    _dialogInterface.dismiss();

                                    // setup navigation drawer
                                    // extent from AppDrawerActivity
                                    buildNavigationDrawer(mToolbar, -1);
                                }
                            }
                    )
                    .setCancelable(false)
                    .create()
                    .show();
        }
    }

    private void getAccountInformationForDrawer() {
        // get all profile
        final UserDataManager __userDataManager = new UserDataManager(this);
        cacheProfiles = __userDataManager.getProfileList();

        try {
            this.email = AmBacSiAuth.getLoginAccount().getEmail();
        } catch (AmBacSiAuthException _e) {
            this.email = "";
        }

        // TODO get avatar
        this.avatar = getResources().getDrawable(vn.ahaay.ambacsi.R.drawable.am_bac_si_with_background);

//        Bitmap avatar = DeviceUserDataManager.loadUserAvatar();
//        if (avatar != null) {
//            this.avatar = new BitmapDrawable(getResources(), avatar);
//        } else {
//            this.avatar = getResources().getDrawable(vn.ahaay.ambacsi.R.drawable.am_bac_si_with_background);
//        }
    }

    private void buildNavigationDrawer(Toolbar mToolbar, int mSelected) {
        // Items for main drawer
        PrimaryDrawerItem itemCalendars = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_MY_APPOINTMENT)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_my_appointment)
                .withIcon(GoogleMaterial.Icon.gmd_event_available);
        PrimaryDrawerItem itemSharedWithMe = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_MY_SCHEDULE)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_my_schedule)
                .withIcon(GoogleMaterial.Icon.gmd_view_list);
        PrimaryDrawerItem itemSearch = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_SEARCH)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_search)
                .withIcon(GoogleMaterial.Icon.gmd_search);
        PrimaryDrawerItem itemNotifications = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_NOTIFICATIONS)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_notifications)
                .withIcon(GoogleMaterial.Icon.gmd_notifications)
                .withBadge(vn.ahaay.ambacsi.R.string.drawer_item_notifications_badge)
                .withBadgeStyle(new BadgeStyle()
                        .withTextColor(Color.WHITE)
                        .withColorRes(vn.ahaay.ambacsi.R.color.colorError)
                );
        PrimaryDrawerItem itemMeetingPlanner = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_APPOINTMENT_PLANER)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_appointment_planner)
                .withIcon(GoogleMaterial.Icon.gmd_schedule);
        PrimaryDrawerItem itemSchedule = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_SCHEDULE)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_schedule)
                .withIcon(GoogleMaterial.Icon.gmd_view_quilt);
        PrimaryDrawerItem itemImport = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_MY_MEDICAL_RECORD)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_medical_record)
                .withIcon(GoogleMaterial.Icon.gmd_file_download);
        PrimaryDrawerItem itemSettings = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_SETTINGS)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_settings)
                .withIcon(GoogleMaterial.Icon.gmd_settings);
        PrimaryDrawerItem itemHelp = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_HELP)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_help)
                .withIcon(GoogleMaterial.Icon.gmd_live_help);
        PrimaryDrawerItem itemLogout = new PrimaryDrawerItem()
                .withIdentifier(DRAWER_ITEM_LOGOUT)
                .withName(vn.ahaay.ambacsi.R.string.drawer_item_logout)
                .withIcon(GoogleMaterial.Icon.gmd_transfer_within_a_station);

        // Item on profile section
        ProfileSettingDrawerItem itemAddProfile = new ProfileSettingDrawerItem()
                .withIdentifier(PROFILE_DRAWER_ITEM_ADD_PROFILE)
                .withName(getResources().getString(R.string.profile_drawer_item_add_profile))
                .withIcon(GoogleMaterial.Icon.gmd_add)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        startActivity(new Intent(AppDrawerActivity.this, CreateProfileActivity.class));
                        return false;
                    }
                });
        ProfileSettingDrawerItem itemAccountSetting = new ProfileSettingDrawerItem()
                .withIdentifier(PROFILE_DRAWER_ITEM_ACCOUNT_SETTING)
                .withName(getResources().getString(R.string.profile_drawer_item_account_setting))
                .withIcon(GoogleMaterial.Icon.gmd_settings)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // TODO create account setting activity
                        startActivity(new Intent(AppDrawerActivity.this, LinkMyAccountActivity.class));
                        return false;
                    }
                });
        ProfileSettingDrawerItem itemRegister = new ProfileSettingDrawerItem()
                .withIdentifier(PROFILE_DRAWER_ITEM_REGISTER)
                .withName(getResources().getString(vn.ahaay.ambacsi.R.string.profile_drawer_item_register))
                .withIcon(GoogleMaterial.Icon.gmd_fiber_new)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        startActivity(new Intent(AppDrawerActivity.this, RegisterAnonymousActivity.class));
                        return false;
                    }
                });
        ProfileSettingDrawerItem itemLinkMyAccount = new ProfileSettingDrawerItem()
                .withIdentifier(PROFILE_DRAWER_ITEM_LINK_MY_ACCOUNT)
                .withName(getResources().getString(vn.ahaay.ambacsi.R.string.profile_drawer_item_link_my_account))
                .withIcon(GoogleMaterial.Icon.gmd_link)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        startActivity(new Intent(AppDrawerActivity.this, LinkMyAccountActivity.class));
                        return false;
                    }
                });

        // Create the AccountHeader
        AccountHeaderBuilder __headerBuilder = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(vn.ahaay.ambacsi.R.drawable.google_now)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        if (currentProfile) {
                            startActivity(new Intent(AppDrawerActivity.this, MyProfileActivity.class));
                        } else {
                            swapProfile(cacheProfiles.get((int) profile.getIdentifier() - PROFILE_MIN_IDENTIFIER));
                        }
                        return false;
                    }
                });

        int __i = PROFILE_MIN_IDENTIFIER;
        int __indexOfActiveProfile = PROFILE_MIN_IDENTIFIER;
        for (CacheProfile __item : cacheProfiles) {
            __headerBuilder.addProfiles(new ProfileDrawerItem()
                    .withName(__item.getDisplayName())
                    .withIcon(avatar)
                    .withEmail(email)
                    .withNameShown(true)
                    .withIdentifier(__i)
            );
            if (__indexOfActiveProfile == PROFILE_MIN_IDENTIFIER
                    && selectedProfile.getProfileId().equals(__item.getProfileId()))
            {
                __indexOfActiveProfile = __i;
            }
            __i++;
        }

        __headerBuilder.addProfiles(
                itemAddProfile,
                itemAccountSetting
//                itemRegister,
//                itemLinkMyAccount
        );
        AccountHeader header = __headerBuilder.build();
        header.setActiveProfile(__indexOfActiveProfile);

//        if (!loginType.equals(Constant.LoginUserTypeConstant.LOGIN_USER_TYPE_ANONYMOUS)) {
//            header.removeProfileByIdentifier(PROFILE_DRAWER_ITEM_REGISTER);
//        } else {
//            header.removeProfileByIdentifier(PROFILE_DRAWER_ITEM_LINK_MY_ACCOUNT);
//        }

        //create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(header)
                .addDrawerItems(
                        itemCalendars,
                        itemSharedWithMe,
                        itemSearch,
                        new DividerDrawerItem(),
                        itemNotifications,
                        itemMeetingPlanner,
                        itemSchedule,
                        itemImport,
                        new DividerDrawerItem(),
                        itemSettings,
                        itemHelp,
                        itemLogout
                )
                .withSelectedItem(mSelected)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier()) {
                            case DRAWER_ITEM_MY_APPOINTMENT:
                                startActivity(new Intent(AppDrawerActivity.this, MyAppointmentActivity.class));
                                return false;
                            case DRAWER_ITEM_MY_SCHEDULE:
                                startActivity(new Intent(AppDrawerActivity.this, MyScheduleActivity.class));
                                return false;
                            case DRAWER_ITEM_SEARCH:
                                startActivity(new Intent(AppDrawerActivity.this, SearchActivity.class));
                                return false;
                            case DRAWER_ITEM_NOTIFICATIONS:
                                startActivity(new Intent(AppDrawerActivity.this, NotificationsActivity.class));
                                return false;
                            case DRAWER_ITEM_APPOINTMENT_PLANER:
                                startActivity(new Intent(AppDrawerActivity.this, AppointmentPlanerActivity.class));
                                return false;
                            case DRAWER_ITEM_SCHEDULE:
                                startActivity(new Intent(AppDrawerActivity.this, ScheduleActivity.class));
                                return false;
                            case DRAWER_ITEM_MY_MEDICAL_RECORD:
                                startActivity(new Intent(AppDrawerActivity.this, MedicalRecordActivity.class));
                                return false;
                            case DRAWER_ITEM_SETTINGS:
                                startActivity(new Intent(AppDrawerActivity.this, SettingsActivity.class));
                                return false;
                            case DRAWER_ITEM_HELP:
                                startActivity(new Intent(AppDrawerActivity.this, HelpActivity.class));
                                return false;
                            case DRAWER_ITEM_LOGOUT:
                                actionLogout();
                                return false;
                            default:
                                return false;
                        }
                    }
                })
                .build();
    }

    private void actionLogout() {
        // logout confirm
        if (ConnectivityReceiver.isConnected()) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AppDrawerActivity.this)
                    .setTitle(vn.ahaay.ambacsi.R.string.logout_title)
                    .setMessage(vn.ahaay.ambacsi.R.string.logout_confirm)
                    .setPositiveButton(vn.ahaay.ambacsi.R.string.btn_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final ProgressDialog __dialog = new ProgressDialog(AppDrawerActivity.this);
                            __dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            __dialog.setMessage(getResources().getString(vn.ahaay.ambacsi.R.string.logout_progress));
                            __dialog.setIndeterminate(true);
                            __dialog.setCanceledOnTouchOutside(false);
                            __dialog.show();

                            AmBacSiAuth.logout()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> var1) {
                                            __dialog.dismiss();
                                        }
                                    })
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(@NonNull Task<Void> var1) {
                                            startActivity(new Intent(AppDrawerActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener<Void>() {
                                        @Override
                                        public void onFailure(@NonNull Task<Void> var1) {
                                            Toast.makeText(AppDrawerActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .execute();
                        }
                    })
                    .setNegativeButton(vn.ahaay.ambacsi.R.string.btn_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do nothing
                        }
                    });
//            if (loginType.equals(Constant.LoginUserTypeConstant.LOGIN_USER_TYPE_ANONYMOUS)) {
//                alertBuilder.setMessage(vn.ahaay.ambacsi.R.string.logout_anonymous_confirm).create().show();
//            } else {
//                alertBuilder.create().show();
//            }
        } else {
            Toast.makeText(AppDrawerActivity.this,
                    getResources().getString(vn.ahaay.ambacsi.R.string.logout_error_no_connection),
                    Toast.LENGTH_SHORT).show();
        }
    }

    protected void swapProfile(CacheProfile _profile) {
        selectedProfile = _profile;
        UserDataManager __userDataManager = new UserDataManager(this);
        __userDataManager.setPrimaryProfile(true);
        __userDataManager.setPrimaryProfile(selectedProfile);

        AmBacSiAuth.setAccountProfile(selectedProfile);
    }
}
