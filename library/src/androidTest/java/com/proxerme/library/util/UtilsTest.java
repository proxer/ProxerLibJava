package com.proxerme.library.util;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for {@link Utils}.
 *
 * @author Ruben Gees
 */
public class UtilsTest {

    @Test
    public void testTimestampToUnixTime() throws Exception {
        Assert.assertEquals(1470597160, Utils.timestampToUnixTime("2016-08-07 21:12:40"));
    }

    @Test(expected = Utils.UncheckedParseException.class)
    public void testTimestampToUnixTimeBadInput() throws Exception {
        Utils.timestampToUnixTime("cm0924fh43n");
    }
}
