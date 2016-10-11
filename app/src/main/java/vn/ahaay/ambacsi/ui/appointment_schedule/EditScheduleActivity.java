package vn.ahaay.ambacsi.ui.appointment_schedule;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.ahaay.ambacsi.R;
import vn.ahaay.ambacsi.api.localdb.appointment_schedule.ScheduleDBHandler;
import vn.ahaay.ambacsi.api.model.appointment_schedule.Schedule;
import vn.ahaay.ambacsi.constant.UiFormatter;
import vn.ahaay.ambacsi.ui.AppBaseActivity;

public class EditScheduleActivity extends AppBaseActivity {
    public static final String OLD_SCHEDULE_ID = "extra_old_schedule_id";
    public static final int EDIT_SCHEDULE_RESULT_CODE_CHANGED = 0;
    public static final int EDIT_SCHEDULE_RESULT_CODE_NOT_CHANGED = 1;

    private Schedule oldSchedule;

    protected ScheduleDBHandler scheduleDBHandler;

    @BindView(R.id.mToolbar) Toolbar mToolbar;
    @BindView(R.id.startDate) TextView startDate;
    @BindView(R.id.startTime) TextView startTime;
    @BindView(R.id.endDate) TextView endDate;
    @BindView(R.id.endTime) TextView endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_edit_schedule);
        ButterKnife.bind(this);

        // setup toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scheduleDBHandler = new ScheduleDBHandler(this, null);

        // get old schedule
        String __oldScheduleId = getIntent().getStringExtra(OLD_SCHEDULE_ID);
        if (__oldScheduleId != null) {
            oldSchedule = scheduleDBHandler.findSchedule(__oldScheduleId);
            // TODO get data and set to edit text
        } else {
            oldSchedule = null;
            getSupportActionBar().setTitle(R.string.edit_schedule_title_new);

            Calendar __now = Calendar.getInstance();
            __now.set(Calendar.HOUR, __now.get(Calendar.HOUR) + 1);
            __now.set(Calendar.MINUTE, 0);
            startDate.setText(UiFormatter.DATE_FORMAT.format(__now.getTime()));
            startTime.setText(UiFormatter.TIME_FORMAT.format(__now.getTime()));

            __now.set(Calendar.HOUR, __now.get(Calendar.HOUR) + 1);
            endDate.setText(UiFormatter.DATE_FORMAT.format(__now.getTime()));
            endTime.setText(UiFormatter.TIME_FORMAT.format(__now.getTime()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.menu_action_cancel:
                setResult(EDIT_SCHEDULE_RESULT_CODE_NOT_CHANGED);
                finish();
                return true;

            case R.id.menu_action_save:
                // TODO save the schedule
                setResult(EDIT_SCHEDULE_RESULT_CODE_CHANGED);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
