package com.fossourier.nicolas.mynews.Utils;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferences {

    private static SharedPreferences instance;
    private android.content.SharedPreferences prefs;
    private static final String SECTIONOFNOTIFICATIONS = "SECTIONOFNOTIFICATIONS";
    private String notifTime;

    private SharedPreferences(Context context) {
        String PREFS="PREFS";
        prefs = context.getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
    }

      //-----------------------------------------------------------//
     // Initialization of the backup. If no backup, we create one //
    //-----------------------------------------------------------//
    public static SharedPreferences getInstance(Context context) {
        if (instance == null)
            instance = new SharedPreferences(context);
        return instance;
    }

      //------------------------------------------------------------------------//
     // Store the section choisen in case 0 and the section searched in case 1 //
    //------------------------------------------------------------------------//
    public void storeListSection(int i, ArrayList<String> listSection) {
        String prefSection = "SECTIONCHOISEN";
        switch (i) {
            case 0:
                prefSection = "SECTIONCHOISEN";
                break;
            case 1:
                prefSection = "SECTIONSEARCHED";
                break;
            default:
                break;
        }
        // Editing data to back up
        android.content.SharedPreferences.Editor editor=prefs.edit();
        //We store the data
        Gson gson=new Gson();
        String json=gson.toJson(listSection);
        editor.putString(prefSection, json);
        // We close the file and then apply the edition
        editor.apply();
    }

      //--------------------------------------------------------------------------------------------------//
     // Here we recover the saved data. The section choisen in case 0 and the section searched in case 1 //
    //--------------------------------------------------------------------------------------------------//
    public ArrayList<String> getListSection(int i) {
        String sectionSaved = "SECTIONCHOISEN";
        switch (i) {
            case 0:
                sectionSaved = "SECTIONCHOISEN";
                break;
            case 1:
                sectionSaved = "SECTIONSEARCHED";
                break;
            default:
                break;
        }
        Gson gson=new Gson();
        String json=prefs.getString(sectionSaved,"");

        ArrayList<String> listSection;

        if ((json != null ? json.length() : 0) < 1) {
            listSection=new ArrayList<>();
        } else {
            Type type=new TypeToken<ArrayList<String>>() {
            }.getType();
            listSection=gson.fromJson(json, type);
        }
        return listSection;
    }

      //-----------------------------------------//
     // Store sections of notifications choisen //
    //-----------------------------------------//
    public void storeSectionOfNotifications(List<String> categories) {
        //start writing
        android.content.SharedPreferences.Editor editor = prefs.edit();
        //put the data
        Gson gson = new Gson();
        String json = gson.toJson(categories);
        editor.putString(SECTIONOFNOTIFICATIONS, json);
        //close the file
        editor.apply();
    }

      //-------------------------------------//
     // Get sections of notifications saved //
    //-------------------------------------//
    public ArrayList<String> getSectionOfNotifications() {
        Gson gson = new Gson();
        String json = prefs.getString(SECTIONOFNOTIFICATIONS, "");
        ArrayList<String> sections;
        if (json.length() < 1) {
            sections = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            sections = gson.fromJson(json, type);
        }
        return sections;
    }

      //-------------------------------------------//
     // Store query term of notifications entered //
    //-------------------------------------------//
    public void storeQueryTermNotifications(String notifQuery) {
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.remove("QUERYTERMNOTIFICATIONS");
        editor.putString("QUERYTERMNOTIFICATIONS", notifQuery);
        editor.apply();
    }

      //---------------------------------------//
     // Get query term of notifications saved //
    //---------------------------------------//
    public String getQueryTermNotifications() {
        return prefs.getString("QUERYTERMNOTIFICATIONS", "");
    }

      //-----------------------------//
     // Store boolean of notisearch //
    //-----------------------------//
    public void storeNotiSearchBoolean(Boolean notifBool) {
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.remove("NOTIFICATIONSENABLE");
        editor.putBoolean("NOTIFICATIONSENABLE", notifBool);
        editor.apply();
    }

      //---------------------------//
     // Get boolean of notisearch //
    //---------------------------//
    public Boolean getNotiSearchBoolean() {
        return prefs.getBoolean("NOTIFICATIONSENABLE", false);
    }

    public void storeNotifTime(String time) {
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.remove("HOUROFNOTIFICATIONS");
        editor.putString("HOUROFNOTIFICATIONS", time);
        editor.apply();
    }

    public String getNotifTime() {
        return prefs.getString("HOUROFNOTIFICATIONS", "10:00");
    }

}
