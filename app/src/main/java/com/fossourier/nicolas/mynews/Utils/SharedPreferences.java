package com.fossourier.nicolas.mynews.Utils;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferences {

    private static SharedPreferences instance;
    private android.content.SharedPreferences prefs;

    private SharedPreferences(Context context) {
        String PREFS="PREFS";
        prefs = context.getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
    }

    // Initialization of the backup. If no backup, we create one
    public static SharedPreferences getInstance(Context context) {
        if (instance == null)
            instance = new SharedPreferences(context);
        return instance;
    }

    // Store the section choisen in case 0 and the section searched in case 1
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

    // Here we recover the saved data. The section choisen in case 0 and the section searched in case 1
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
}
