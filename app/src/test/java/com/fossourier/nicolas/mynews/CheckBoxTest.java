package com.fossourier.nicolas.mynews;

import android.widget.CheckBox;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

public class CheckBoxTest {

    @Test
    public void checkBoxTest () {

        // Mock the checkbox of search and notif for simulate a check
        CheckBox Arts = mock(CheckBox.class);
        CheckBox Automobile = mock(CheckBox.class);
        CheckBox Books = mock(CheckBox.class);
        CheckBox Business = mock(CheckBox.class);
        CheckBox Fashion = mock(CheckBox.class);
        CheckBox Food = mock(CheckBox.class);
        CheckBox Health = mock(CheckBox.class);
        CheckBox Home = mock(CheckBox.class);
        CheckBox Insider = mock(CheckBox.class);
        CheckBox Magazine = mock(CheckBox.class);

        // Test when is checked
        Mockito.when(Arts.isChecked()).thenReturn(Boolean.valueOf("Arts"));
        Mockito.when(Automobile.isChecked()).thenReturn(Boolean.valueOf("Automobile"));
        Mockito.when(Books.isChecked()).thenReturn(Boolean.valueOf("Books"));
        Mockito.when(Business.isChecked()).thenReturn(Boolean.valueOf("Business"));
        Mockito.when(Fashion.isChecked()).thenReturn(Boolean.valueOf("Fashion"));
        Mockito.when(Food.isChecked()).thenReturn(Boolean.valueOf("Food"));
        Mockito.when(Health.isChecked()).thenReturn(Boolean.valueOf("Health"));
        Mockito.when(Home.isChecked()).thenReturn(Boolean.valueOf("Home"));
        Mockito.when(Insider.isChecked()).thenReturn(Boolean.valueOf("Insider"));
        Mockito.when(Magazine.isChecked()).thenReturn(Boolean.valueOf("Magazine"));
    }
}
