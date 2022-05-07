package com.pete.storehouseApp;

import com.google.common.base.Stopwatch;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static final String defaultTimezone = "Europe/Athens";

    /** Date format "dd/MM/yyyy" for Greek locale */
    public static SimpleDateFormat DATE_FORMAT_EL = new SimpleDateFormat(
            "dd/MM/yyyy");

    /** Datetime ISO 8601 format "dd/MM/yyyy HH:mm:ss" */
    public static SimpleDateFormat DATETIME_FORMAT_EL = new SimpleDateFormat(
            "dd/MM/yyyy HH:mm:ss");

    /** Datetime ISO 8601 format "yyyy-MM-ddThh:mm:ss" */
    public static SimpleDateFormat DATETIME_FORMAT_ISO = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss");

    /** Datetime ISO 8601 format "yyyy-MM-ddThh:mm" */
    public static SimpleDateFormat DATETIME_NO_SECS_FORMAT_ISO = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm");

    /** Date ISO 8601 format "yyyy-MM-dd" */
    public static SimpleDateFormat DATE_FORMAT_ISO = new SimpleDateFormat(
            "yyyy-MM-dd");

    /** Date format "dd/MM/yyyy" for Greek locale */
    public static final String DATE_FORMAT_EL_STRING = "dd/MM/yyyy";

    /** Date format "dd/MM/yyyy HH:mm:ss" for Greek locale */
    public static final String DATE_FULL_FORMAT_EL_STRING = "dd/MM/yyyy HH:mm:ss";

    /** Date format "dd/MM/yyyy HH:mm" for Greek locale */
    public static final String DATE_FORMAT_EL_NO_SECS_STRING = "dd/MM/yyyy HH:mm";

    /** Datetime ISO 8601 format "yyyy-MM-dd hh:mm:ss" */
    public static final String DATETIME_FORMAT_ISO_STRING = "yyyy-MM-dd'T'HH:mm:ss";

    /** Datetime ISO 8601 format "yyyy-MM-dd hh:mm" */
    public static final String DATETIME_NO_SECS_FORMAT_ISO_STRING = "yyyy-MM-dd HH:mm";

    /** Date ISO 8601 format "yyyy-MM-dd" */
    public static final String DATE_FORMAT_ISO_STRING = "yyyy-MM-dd";

    /**
     * Converts the milliseconds since January 1, 1970, 00:00:00 GMT to <a
     * href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> Time format,
     * i.e. <code>hh:mm:ss.sss</code>, a more human readable format. For example
     * 4530436 ms is converted to 01:15:30.436 .
     *
     * <p>
     * In order to make the conversion we used this StackOverflow <a
     * href="http://stackoverflow.com/a/625624">question</a>.
     * </p>
     *
     * @param millis
     *            the milliseconds since January 1, 1970, 00:00:00 GMT.
     */
    public static String millisToHumanReadable(long millis) {
        // http://stackoverflow.com/a/625624
        // Don't leave me this way

        return String.format(
                "%02d:%02d:%02d.%03d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                        .toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                        .toMinutes(millis)),
                millis
                        - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS
                        .toSeconds(millis)));
    }

    /**
     * Converts the {@link Stopwatch#elapsed(TimeUnit) elapsed} milliseconds to
     * <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> Time
     * format, i.e. <code>hh:mm:ss.sss</code>, a more human readable format. For
     * example a stopwatch with a value of {@link Stopwatch#elapsed(TimeUnit)
     * elapsed} 4530436 ms is converted to 01:15:30.436 .
     *
     * @param watch
     *            a {@link Stopwatch}.
     * @return an ISO 8601 formatted date string (<code>hh:mm:ss.sss</code>) or
     *         <code>null</code> if given parameter is <code>null</code>.
     */
    public static String millisToHumanReadable(Stopwatch watch) {
        if (watch == null) {
            return null;
        }

        return millisToHumanReadable(watch.elapsed(TimeUnit.MILLISECONDS));
    }

    /**
     * Returns <code>true</code> if this currentDate > (dueDate + gracePeriod).
     * This method takes into account grace period in days.
     *
     * @param dueDate
     *            date an obligation falls <a
     *            href="https://en.oxforddictionaries.com/definition/due"
     *            >due</a>.
     * @param checkDate
     *            date to compare with due date.
     * @param gracePeriod
     *            the period in days which starts immediately after the deadline
     *            of an obligation during which an overdue fee, is not subject
     *            to interest charges.
     *
     */
    public static boolean isOverdue(Date dueDate, Date checkDate,
                                    Integer gracePeriod) {
        if ((dueDate == null) || (checkDate == null)) {
            return false;
        }

        int newGracePeriod = (gracePeriod != null) ? gracePeriod : 0;

        Date newPaymentDueDate = new DateTime(dueDate).plusDays(newGracePeriod)
                .toDate();

        return checkDate.compareTo(newPaymentDueDate) > 0;
    }

    public static Date newDateOfTimezone(String timezone) {
        // Default timezone
        DateTime jodaDate = DateTime.now(DateTimeZone.forID( defaultTimezone ));
        if (timezone != null) {
            jodaDate = DateTime.now( DateTimeZone.forID( timezone ));
        }
//        String jodaDateStr = jodaDate.toString(DateTimeFormat.forPattern(DATETIME_FORMAT_ISO_STRING) );
//
//        Date date = new Date();
//        try {
//            date = DATETIME_FORMAT_ISO.parse(jodaDateStr);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return jodaDate.toDate();
    }

    public static Date today(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DateUtil.defaultTimezone));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date year(Date date){
        if(date!=null){
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DateUtil.defaultTimezone));
            cal.setTime(date);
            cal.set(Calendar.MONTH, 0);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            return cal.getTime();
        }else{
            return date;
        }
    }
}