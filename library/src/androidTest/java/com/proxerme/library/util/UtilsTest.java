package com.proxerme.library.util;

import junit.framework.Assert;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Tests for {@link Utils}.
 *
 * @author Ruben Gees
 */
public class UtilsTest {

    @Test
    public void testTimestampToUnixTime() throws Exception {
        GregorianCalendar calendar = new GregorianCalendar(Locale.getDefault());

        calendar.set(2016, GregorianCalendar.AUGUST, 7, 21, 12, 40);

        Assert.assertEquals(calendar.getTimeInMillis() / 1000,
                Utils.timestampToUnixTime("2016-08-07 21:12:40"));
    }

    @Test(expected = Utils.UncheckedParseException.class)
    public void testTimestampToUnixTimeBadInput() throws Exception {
        Utils.timestampToUnixTime("cm0924fh43n");
    }
}
