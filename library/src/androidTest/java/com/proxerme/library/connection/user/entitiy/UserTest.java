package com.proxerme.library.connection.user.entitiy;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class UserTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "secretPassword";
    private static final String ID = "123";
    private static final String IMAGE_ID = "abc.png";

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(ID, generateTestUser().getId());
    }

    @Test
    public void testGetImageId() throws Exception {
        Assert.assertEquals(IMAGE_ID, generateTestUser().getImageId());
    }

    @Test(expected = User.UserInitializationException.class)
    public void testGetUnsetId() throws Exception {
        generateUninitializedTestUser().getId();
    }

    @Test(expected = User.UserInitializationException.class)
    public void testGetUnsetImageId() throws Exception {
        generateUninitializedTestUser().getImageId();
    }

    private User generateTestUser() {
        return new User(USERNAME, PASSWORD, ID, IMAGE_ID);
    }

    private User generateUninitializedTestUser() {
        return new User(USERNAME, PASSWORD);
    }

}