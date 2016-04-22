package com.doea.app.presentation.utility;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.doea.app.presentation.ApplicationController;

import java.util.Set;

public class PrefManager {

    // The class Log identifier
    private static final String LOG_TAG = PrefManager.class.getSimpleName();


    private static final String USER_ID = "preferences_user_id";
    private static final String TOKEN = "preferences_token";
    private static final String INSTANCE_URL = "preferences_instance";
    private static final String INSTANCE_DOMAIN = "preferences_domain";
    private static final String PORT = "preferences_port";

    public static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(ApplicationController.getAppControllerInstance().getApplicationContext());
    }

    public static void clearPrefs() {
        getPreferences().edit().clear().commit();
    }

    public static int getInt(String preferenceKey, int preferenceDefaultValue) {
        return getPreferences().getInt(preferenceKey, preferenceDefaultValue);
    }

    public static void putInt(String preferenceKey, int preferenceValue) {
        getPreferences().edit().putInt(preferenceKey, preferenceValue).commit();
    }

    public static long getLong(String preferenceKey, long preferenceDefaultValue) {
        return getPreferences().getLong(preferenceKey, preferenceDefaultValue);
    }

    public static void putLong(String preferenceKey, long preferenceValue) {
        getPreferences().edit().putLong(preferenceKey, preferenceValue).commit();
    }

    public static float getFloat(String preferenceKey, float preferenceDefaultValue) {
        return getPreferences().getFloat(preferenceKey, preferenceDefaultValue);
    }

    public static void putFloat(String preferenceKey, float preferenceValue) {
        getPreferences().edit().putFloat(preferenceKey, preferenceValue).commit();
    }

    public static boolean getBoolean(String preferenceKey, boolean preferenceDefaultValue) {
        return getPreferences().getBoolean(preferenceKey, preferenceDefaultValue);
    }

    public static void putBoolean(String preferenceKey, boolean preferenceValue) {
        getPreferences().edit().putBoolean(preferenceKey, preferenceValue).commit();
    }

    public static String getString(String preferenceKey, String preferenceDefaultValue) {
        return getPreferences().getString(preferenceKey, preferenceDefaultValue);
    }

    public static void putString(String preferenceKey, String preferenceValue) {
        getPreferences().edit().putString(preferenceKey, preferenceValue).commit();
    }

    public static void putStringSet(String preferencesKey, Set<String> values) {
        getPreferences().edit().putStringSet(preferencesKey, values).commit();
    }

    public static Set<String> getStringSet(String preferencesKey) {
        return getPreferences().getStringSet(preferencesKey, null);
    }

    /**
     * Authentication
     */

    public static void saveToken(String token) {
        putString(TOKEN, token);
    }

    public static void clearToken() {
        putString(TOKEN, "");
    }

    public static String getToken() {
        return getString(TOKEN, "");
    }

    public static boolean isAuthenticated() {
        return !TextUtils.isEmpty(getToken());
    }

    public static int getUserId() {
        return getInt(USER_ID, -1);
    }

    public static void setUserId(int id) {
        putInt(USER_ID, id);
    }

    /**
     * Connection
     */
    public static void setInstanceUrl(String instanceUrl) {
        putString(INSTANCE_URL, instanceUrl);
    }

    public static String getInstanceUrl() {
        return getString(INSTANCE_URL, "");
    }

    public static void setInstanceDomain(String instanceDomain) {
        putString(INSTANCE_DOMAIN, instanceDomain);
    }

    public static String getInstanceDomain() {
        return getString(INSTANCE_DOMAIN, "demo.openmf.org");
    }

    public static void setPort(String port) {
        if (!TextUtils.isEmpty(port))
            putString(PORT, port);
    }

    public static String getPort() {
        return getString(PORT, "80");
    }
}


