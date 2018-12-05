package zhe.it_tech613.com.vaderiptv.models;

import android.content.Context;
import zhe.it_tech613.com.vaderiptv.R;

public enum VodType {
    tv,
    movie,
    tvepisode;

    /* renamed from: a */
    public static String m10328a() {
        return "VOD_TAG";
    }

    /* renamed from: a */
    public static String m10329a(Context context, VodType vodType) {
        int i;
        if (vodType.equals(tv)) {
            i = R.string.vod_menu_item_tv;
        } else if (!vodType.equals(movie)) {
            return "";
        } else {
            i = R.string.vod_menu_item_movie;
        }
        return context.getString(i);
    }
}
