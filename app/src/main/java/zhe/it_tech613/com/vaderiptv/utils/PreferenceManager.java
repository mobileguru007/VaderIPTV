package zhe.it_tech613.com.vaderiptv.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import net.danlew.android.joda.JodaTimeAndroid;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sajja on 3/4/2018.
 */

public class PreferenceManager extends Application {
    public static final String TAG = android.preference.PreferenceManager.class.getSimpleName();
    private RequestQueue mRequestQueue;

    private static final String PREFERENCES = "VaderIPTV";
    private static PreferenceManager instance = null;
    private static SharedPreferences preferences;
    static SharedPreferences.Editor prefEditor;
    @SuppressLint("StaticFieldLeak")
    public static Realm realm;
    private static PreferenceManager mInstance;
    public static String PASSWORD = "PASSWORD";
    public static String TOKEN = "TOKEN";
    public static String USERNAME ="USERNAME";
    public static String LoginStatus = "LoginStatus";                           // boolean


    public static void resetAll() {

        prefEditor.clear();
        prefEditor.commit();

    }
    public static void clear() {

        prefEditor.clear();
    }

    public static PreferenceManager getInstance() {
        if (instance == null) {
            instance = new PreferenceManager();
        }
        return instance;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mInstance, new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    @Override
    public void onCreate() {

        super.onCreate();

        mInstance = this;
        preferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        prefEditor = preferences.edit();
        prefEditor.apply();
        JodaTimeAndroid.init(this);
        // -----------------------------------------------------------------------------------------
        Realm.init(this);
        final RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("VaderIPTV.realm")
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .migration(new RealmMigrations())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        realm= Realm.getInstance(realmConfiguration);


    }

    private static SharedPreferences getSharedPreferences(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        }
        return preferences;
    }

    public void onTerminate() {
        // TODO Auto-generated method stub
        realm.close();
        realm.deleteAll();
        super.onTerminate();
    }
    public static String getPASSWORD() {
        return preferences.getString(PASSWORD, "");
    }

    public static void setPASSWORD(String PASSWORD) {
        prefEditor.putString(PASSWORD, PASSWORD);
        prefEditor.commit();
    }

    public static void setTOKEN(String TOKEN) {
        prefEditor.putString(PreferenceManager.TOKEN, TOKEN);
        prefEditor.commit();
    }

    public static String getTOKEN() {
        return preferences.getString(TOKEN, "");
    }

    public static void setUSERNAME(String USERNAME) {
        prefEditor.putString(PreferenceManager.USERNAME, USERNAME);
        prefEditor.commit();
    }

    public static String getUSERNAME() {
        return preferences.getString(USERNAME, "");
    }

    public static void setLoginStatus(boolean loginStatus) {
        prefEditor.putBoolean(LoginStatus, loginStatus);
        prefEditor.apply();
    }

    public static boolean getLoginStatus() {
        return preferences.getBoolean(LoginStatus, false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value).apply();
    }

    public static Boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(key, defValue);
    }
}
