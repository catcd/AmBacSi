package vn.ahaay.ambacsi.ui.medicals;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.ahaay.ambacsi.ui.AppDrawerActivity;

public class HomeActivity extends AppDrawerActivity {
    private final int TIME_INTERVAL = 2000;

    private boolean doubleBackToExitPressedOnce = false;

    @BindView(vn.ahaay.ambacsi.R.id.mToolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_home);
        ButterKnife.bind(this);

        // setup toolbar
        setSupportActionBar(mToolbar);

        setupNavigationDrawer(mToolbar, -1);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,
                    getResources().getString(vn.ahaay.ambacsi.R.string.notify_press_back_again_to_exit),
                    Toast.LENGTH_SHORT)
                    .show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, TIME_INTERVAL);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}