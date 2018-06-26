package com.gramajo.josue.quinielasvergas.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by josuegramajo on 6/26/18.
 */

public class SharedPreferencesUtils {
    public static SharedPreferencesUtils INSTANCE = new SharedPreferencesUtils();

    public void writeUserAndPassword(Activity activity, String user, String pass){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.commit();
    }
    public ArrayList<String> readUserAndPassword(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        final String user = sharedPref.getString("user", "");
        final String pass = sharedPref.getString("pass", "");
        return new ArrayList<String>() {{
            add(user);
            add(pass);
        }};
    }
}
