package vn.ahaay.ambacsi.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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

import vn.ahaay.ambacsi.api.ambacsi.OnCompleteListener;
import vn.ahaay.ambacsi.api.ambacsi.OnFailureListener;
import vn.ahaay.ambacsi.api.ambacsi.OnSuccessListener;
import vn.ahaay.ambacsi.api.ambacsi.Task;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuthException;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiUser;
import vn.ahaay.ambacsi.api.device.DeviceUserData;
import vn.ahaay.ambacsi.helper.ConnectivityReceiver;
import vn.ahaay.ambacsi.ui.helpers.HelpActivity;
import vn.ahaay.ambacsi.ui.helpers.SettingsActivity;
import vn.ahaay.ambacsi.ui.medicals.AppointmentPlanerActivity;
import vn.ahaay.ambacsi.ui.medicals.MedicalRecordActivity;
import vn.ahaay.ambacsi.ui.medicals.MyAppointmentActivity;
import vn.ahaay.ambacsi.ui.medicals.MyScheduleActivity;
import vn.ahaay.ambacsi.ui.medicals.ScheduleActivity;
import vn.ahaay.ambacsi.ui.outsides.LoginActivity;
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

    public static final int PROFILE_DRAWER_ITEM_REGISTER = 0;
    public static final int PROFILE_DRAWER_ITEM_LINK_MY_ACCOUNT = 1;

    protected Drawer drawer;
    protected String displayName = "";
    protected String email = "";
    protected Drawable avatar = null;

    protected void setupNavigationDrawer(Activity mActivity, Toolbar mToolbar, int mSelected) {
        getAccountInformationForDrawer();

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
        ProfileDrawerItem itemAccountProfile = new ProfileDrawerItem()
                .withName(displayName)
                .withEmail(email)
                .withIcon(avatar);
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
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(vn.ahaay.ambacsi.R.drawable.google_now)
                .addProfiles(
                        itemAccountProfile,
                        itemRegister,
                        itemLinkMyAccount
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        if (currentProfile) {
                            startActivity(new Intent(AppDrawerActivity.this, MyProfileActivity.class));
                        }
                        return false;
                    }
                })
                .build();

//        if (!loginType.equals(Constant.LoginUserTypeConstant.LOGIN_USER_TYPE_ANONYMOUS)) {
//            header.removeProfileByIdentifier(PROFILE_DRAWER_ITEM_REGISTER);
//        } else {
//            header.removeProfileByIdentifier(PROFILE_DRAWER_ITEM_LINK_MY_ACCOUNT);
//        }

        //create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withActivity(mActivity)
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

    private void getAccountInformationForDrawer() {
        // get current name, email, avatar
        AmBacSiUser __user = null;
        try {
            __user = AmBacSiAuth.getLoginUser();
        } catch (AmBacSiAuthException _e) {
            _e.printStackTrace();
        }
        displayName = __user.getDisplayName();
        email = __user.getEmail();

        // TODO Move to profile
        // get avatar
        Bitmap avatar = DeviceUserData.loadUserAvatar();
        if (avatar != null) {
            this.avatar = new BitmapDrawable(getResources(), avatar);
        } else {
            this.avatar = getResources().getDrawable(vn.ahaay.ambacsi.R.drawable.am_bac_si_with_background);
        }
    }
}
