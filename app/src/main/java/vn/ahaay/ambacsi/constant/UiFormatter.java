package vn.ahaay.ambacsi.constant;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Can on 24-Sep-16.
 */

public class UiFormatter {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, yyyy-MM-dd", Locale.US);
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm", Locale.US);
    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
}
