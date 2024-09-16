package util;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link DateTimeUtil} utility class.
 */
class DateTimeUtilTest {

    @Test
    void testToServerTimezone() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime serverTime = DateTimeUtil.toServerTimezone(now);

        assertEquals("UTC", serverTime.getZone().getId());
    }

    @Test
    void testToLocalTimezone() {
        ZonedDateTime serverTime = ZonedDateTime.now().withZoneSameInstant(DateTimeUtil.getServerTimezone());
        ZonedDateTime localTime = DateTimeUtil.toLocalTimezone(serverTime);

        assertEquals(ZonedDateTime.now().getZone(), localTime.getZone());
    }
}
