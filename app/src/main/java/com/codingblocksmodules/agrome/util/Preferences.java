package com.codingblocksmodules.agrome.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//class to store the user data if user has toggled on remember me saving the required info
public class Preferences {

    private static final String DATA_LOGIN = "status_login", //to see if the user has toggled on the rememer me or not
    DATA_AS = "type", //to store the type of user (seller/consumer)
    USER_NUMBER = "user_number"; //to save user's number if he toggled on the remember me

    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean getDataLogin(Context context){
        return  getSharedPreferences(context).getBoolean(DATA_LOGIN,false);
    }

    public static void setDataLogin(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(DATA_LOGIN, status);
        editor.apply();
    }

    public static String getDataAs(Context context){
        return  getSharedPreferences(context).getString(DATA_AS,"");
    }

    public static void setDataAs(Context context, String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_AS,data);
        editor.apply();
    }

    public static String getUserNumber(Context context){
        return  getSharedPreferences(context).getString(USER_NUMBER,"");
    }

    public static void setUserNumber(Context context, String number){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_NUMBER,number);
        editor.apply();
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(DATA_LOGIN);
        editor.remove(DATA_AS);
        editor.remove(USER_NUMBER);
        editor.apply();
    }

}
