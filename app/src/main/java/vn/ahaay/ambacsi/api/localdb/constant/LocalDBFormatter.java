package vn.ahaay.ambacsi.api.localdb.constant;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Can on 24-Sep-16.
 */

public class LocalDBFormatter {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
}
