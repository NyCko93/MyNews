package com.fossourier.nicolas.mynews;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import com.fossourier.nicolas.mynews.Utils.SharedPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesTest {

    private SharedPreferences mSharedPreferences;

    @Before
    public void setUp()  {
        Context context = InstrumentationRegistry.getTargetContext();
        mSharedPreferences = SharedPreferences.getInstance(context);
    }

    @After
    public void tearDown() {
        mSharedPreferences = null;
    }

    @Test
    public void assertListIsStoredAndReturned(){
        List<String> listSectionOfNotificationsTest = new ArrayList<>();
        listSectionOfNotificationsTest.add("Art");
        listSectionOfNotificationsTest.add("Business");
        mSharedPreferences.storeListSectionOfNotificationsTest(listSectionOfNotificationsTest);
        Assert.assertEquals(listSectionOfNotificationsTest, getListSectionOfNotificationsTest());
    }

    private List<String> getListSectionOfNotificationsTest(){
        return mSharedPreferences.getListSectionOfNotificationsTest();
    }
}
