package net.soroushjavdan.passcodeapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by TNP24 on 1/25/2016.
 */
public class MApplication extends Application {
    @Override
    public void onCreate() {
        // No need to add any code to every activity.
        // Everything is contained in MyLifecycleHandler
        // with just a few lines of code.
        registerActivityLifecycleCallbacks(new MyLifecycleHandler());


    }


}
