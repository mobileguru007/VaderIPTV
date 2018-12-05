package zhe.it_tech613.com.vaderiptv.adapter;

import android.content.Context;
import android.preference.PreferenceManager;

import zhe.it_tech613.com.vaderiptv.activity.MainActivity;

class UserManager {
    private Context context;

    public UserManager(Context context) {
        this.context = context;
    }

    /* renamed from: b */
    public String getStrFromPref(String key, String default_value) {
        try {
            return PreferenceManager.getDefaultSharedPreferences(this.context).getString(key, default_value);
        } catch (Exception e) {
            e.printStackTrace();
            return default_value;
        }
    }
}
