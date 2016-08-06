package com.ahay.ambacsi.ui.medicals;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ahay.ambacsi.R;
import com.ahay.ambacsi.ui.AppDrawerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppDrawerActivity {
    private final int TIME_INTERVAL = 2000;

    private boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.mToolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // setup toolbar
        setSupportActionBar(mToolbar);

        // setup navigation drawer
        // extent from AppDrawerActivity
        setupNavigationDrawer(this, mToolbar, -1);
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
                    getResources().getString(R.string.notify_press_back_again_to_exit),
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

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar_select, menu);
        return true;
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