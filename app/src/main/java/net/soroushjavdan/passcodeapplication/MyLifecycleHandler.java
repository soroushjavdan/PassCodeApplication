package net.soroushjavdan.passcodeapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Soroush on 1/25/2016.
 */
public class MyLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;

        Toast.makeText(activity , "application is visible: " + (started > stopped) , Toast.LENGTH_SHORT).show();
        //  check if activity start from background state
        SharedPreferences pref =   activity.getSharedPreferences("myStorePref", Context.MODE_PRIVATE);
        boolean isAppLeaved = pref.getBoolean("isAppLeaved", false);
        if(isAppLeaved){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isAppLeaved", false);
            editor.commit();
            // send user to passcode activity to get password
            Intent intent = new Intent(activity , PassCodeActivity.class);
            activity.startActivity(intent);
        }

    }


    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
        Toast.makeText(activity , "application is visible: " + (started > stopped) , Toast.LENGTH_SHORT).show();
        if(!isApplicationVisible()){
            // here you store the application background state in preferences
            SharedPreferences pref =  activity.getSharedPreferences("myStorePref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isAppLeaved", true);
            editor.commit();
        }
    }

    private static int started;
    private static int stopped;

    public static boolean isApplicationVisible() {
        return started > stopped;
    }


}