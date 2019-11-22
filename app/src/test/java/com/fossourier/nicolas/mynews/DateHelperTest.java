package com.fossourier.nicolas.mynews;

import com.fossourier.nicolas.mynews.Utils.DateHelper;

import org.junit.Assert;
import org.junit.Test;

public class DateHelperTest {

    // Test for convertDate
    @Test
    public void convertDateTest(){
        String dateInput = "2019-09-21T11:20:42";
        String dateOutput = "21/09/2019";
        Assert.assertEquals(dateOutput, DateHelper.convertDate(dateInput));
    }

    // Test for convertDatePicker
    @Test
    public void convertDatePickerTest(){
        String dateInput = "2019-09-21";
        String dateOutput = "21/09/2019";
        Assert.assertEquals(dateOutput, DateHelper.convertDatePicker(dateInput));
    }

    // Test for testDatesAreValid
    @Test
    public void dateValidationForSearchTest(){
        String beginDate = "20160711";
        String endDate = "20180612";
        assert(DateHelper.testDatesAreValid(beginDate, endDate));
    }
}
