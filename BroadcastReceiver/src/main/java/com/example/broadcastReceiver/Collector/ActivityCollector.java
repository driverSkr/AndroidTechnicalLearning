package com.example.broadcastReceiver.Collector;

import android.app.Activity;

import java.util.ArrayList;

//管理所有的Activity
public class ActivityCollector {

    private static ArrayList<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
