package com.ahay.ambacsi.ui.profiles;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ahay.ambacsi.R;
import com.ahay.ambacsi.ui.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterAnonymousActivity extends AppBaseActivity {
    @BindView(R.id.mToolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_anonymous);
        ButterKnife.bind(this);

        // setup toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
