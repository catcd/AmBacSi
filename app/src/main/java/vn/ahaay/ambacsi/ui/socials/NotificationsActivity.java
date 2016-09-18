package vn.ahaay.ambacsi.ui.socials;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import vn.ahaay.ambacsi.ui.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppBaseActivity {
    @BindView(vn.ahaay.ambacsi.R.id.mToolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_notifications);
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
