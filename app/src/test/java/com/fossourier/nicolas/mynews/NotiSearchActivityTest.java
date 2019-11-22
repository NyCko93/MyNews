package com.fossourier.nicolas.mynews;

import com.fossourier.nicolas.mynews.Controllers.Activities.NotiSearchActivity;


import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class NotiSearchActivityTest {

    // Test if buttonSwitchNotifications is checked
    @Test
    public void checkButtonSwitchNotificationsTest(){
        assertTrue(NotiSearchActivity.checkButtonSwitchNotifications(true));
    }
}
