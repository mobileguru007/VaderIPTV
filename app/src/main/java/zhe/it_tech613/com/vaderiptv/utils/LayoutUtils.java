package zhe.it_tech613.com.vaderiptv.utils;

import android.os.Build.VERSION;
import android.widget.ImageView;

public class LayoutUtils {
    /* renamed from: a */
    public static void setImageSource(ImageView imageView, int i) {
        if (VERSION.SDK_INT > 20) {
            imageView.setImageResource(i);
        }
    }
}
