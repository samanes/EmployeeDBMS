package util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {
    private static final ZoneId SERVER_TIMEZONE = ZoneId.of("UTC");
    private static final ZoneId LOCAL_TIMEZONE = ZoneId.systemDefault();

    public static ZonedDateTime toServerTimezone(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(SERVER_TIMEZONE);
    }

    public static ZonedDateTime toLocalTimezone(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(LOCAL_TIMEZONE);
    }

    public static ZoneId getServerTimezone() {
        return SERVER_TIMEZONE;
    }
}
