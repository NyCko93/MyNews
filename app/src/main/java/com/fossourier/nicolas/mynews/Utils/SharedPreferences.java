package com.fossourier.nicolas.mynews.Utils;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferences {

//    private static Prefs instance;
//    private static final String MOOD="MOOD";
//    private android.content.SharedPreferences prefs;
//
//    private Prefs(Context context) {
//
//        String PREFS="saveMood";
//        prefs=context.getSharedPreferences(PREFS, Activity.MODE_PRIVATE);
//
//    }
//
//
//    // Initialization of the backup. If no backup, we create one
//    public static Prefs getInstance(Context context) {
//        if (instance == null)
//            instance=new Prefs(context);
//        return instance;
//    }
//
//
//    public void saveMood(ArrayList<Mood> moodArrayList) {
//        // Editing data to back up
//        android.content.SharedPreferences.Editor editor=prefs.edit();
//        //We store the data
//        Gson gson=new Gson();
//        String json=gson.toJson(moodArrayList);
//        editor.putString(MOOD, json);
//        // We close the file and then apply the edition
//        editor.apply();
//    }
//
//    // Here we recover the saved data. If null, create a new list
//    public ArrayList<Mood> getMoodArrayList() {
//        Gson gson=new Gson();
//        String json=prefs.getString(MOOD, "");
//
//        ArrayList<Mood> moodArrayList;
//
//        if ((json != null ? json.length() : 0) < 1) {
//            moodArrayList=new ArrayList<>();
//        } else {
//            Type type=new TypeToken<ArrayList<Mood>>() {
//            }.getType();
//            moodArrayList=gson.fromJson(json, type);
//        }
//        return moodArrayList;
//    }
}
