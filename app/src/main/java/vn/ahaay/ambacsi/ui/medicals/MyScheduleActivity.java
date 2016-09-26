package vn.ahaay.ambacsi.ui.medicals;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import vn.ahaay.ambacsi.constant.CalendarConstant;
import vn.ahaay.ambacsi.api.localdb.appointment_schedule.ScheduleDBHandler;
import vn.ahaay.ambacsi.api.ambacsi.helper.Synchronized;
import vn.ahaay.ambacsi.ui.AppBaseActivity;
import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyScheduleActivity extends AppBaseActivity implements
        WeekView.EventClickListener, MonthLoader.MonthChangeListener,
        WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    private int mWeekViewType = CalendarConstant.CAL_TYPE_THREE_DAY_VIEW;
    @BindView(vn.ahaay.ambacsi.R.id.mToolbar) Toolbar mToolbar;
    @BindView(vn.ahaay.ambacsi.R.id.weekCalendarView) WeekView weekCalendarView;

    protected ScheduleDBHandler scheduleDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_my_schedule);
        ButterKnife.bind(this);

        // setup week view
        setupWeekView();

        // setup toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (!Synchronized.isScheduleSynchronized()) {
            Toast.makeText(MyScheduleActivity.this, "Syncing...", Toast.LENGTH_SHORT).show();
        }

        scheduleDBHandler = new ScheduleDBHandler(this, null);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(vn.ahaay.ambacsi.R.menu.menu_calendar_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case vn.ahaay.ambacsi.R.id.menu_action_today:
                today();
                return true;

            case vn.ahaay.ambacsi.R.id.menu_action_day_calendar:
                setWeekViewType(CalendarConstant.CAL_TYPE_DAY_VIEW);
                return true;

            case vn.ahaay.ambacsi.R.id.menu_action_three_days_calendar:
                setWeekViewType(CalendarConstant.CAL_TYPE_THREE_DAY_VIEW);
                return true;

            case vn.ahaay.ambacsi.R.id.menu_action_week_calendar:
                setWeekViewType(CalendarConstant.CAL_TYPE_WEEK_VIEW);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void setupWeekView() {
        setWeekViewType(CalendarConstant.CAL_DEFAULT_TYPE_VIEW);

        // Show a toast message about the touched event.
        weekCalendarView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekCalendarView.setMonthChangeListener(this);

        // Set long press listener for events.
        weekCalendarView.setEventLongPressListener(this);

        // Set long press listener for empty view
        weekCalendarView.setEmptyViewLongPressListener(this);
    }

    public void setWeekViewType(int requestView) {
        if (requestView != mWeekViewType) {
            setupDateTimeInterpreter(requestView == CalendarConstant.CAL_TYPE_WEEK_VIEW);

            switch (requestView) {
                case CalendarConstant.CAL_TYPE_DAY_VIEW:
                    mWeekViewType = CalendarConstant.CAL_TYPE_DAY_VIEW;
                    weekCalendarView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    weekCalendarView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    weekCalendarView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    weekCalendarView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
                case CalendarConstant.CAL_TYPE_THREE_DAY_VIEW:
                    mWeekViewType = CalendarConstant.CAL_TYPE_THREE_DAY_VIEW;
                    weekCalendarView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    weekCalendarView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    weekCalendarView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    weekCalendarView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
                case CalendarConstant.CAL_TYPE_WEEK_VIEW:
                    mWeekViewType = CalendarConstant.CAL_TYPE_WEEK_VIEW;
                    weekCalendarView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    weekCalendarView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    weekCalendarView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    weekCalendarView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    break;
            }
        }
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        weekCalendarView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(java.util.Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    private String getEventTitle(java.util.Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(java.util.Calendar.HOUR_OF_DAY), time.get(java.util.Calendar.MINUTE), time.get(java.util.Calendar.MONTH)+1, time.get(java.util.Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(MyScheduleActivity.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(MyScheduleActivity.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(java.util.Calendar time) {
        Toast.makeText(MyScheduleActivity.this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return scheduleDBHandler.findScheduleForWeekView(newYear, newMonth);
    }

    public void today() {
        weekCalendarView.goToToday();
    }
}
