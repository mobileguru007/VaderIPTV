package zhe.it_tech613.com.vaderiptv.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import java.util.HashSet;
import java.util.Set;
import net.danlew.android.joda.JodaTimeAndroid;
import tv.vaders.apk.auth.UserManager;
import tv.vaders.apk.utils.ExceptionHandler;

public class VaderActivity extends FragmentActivity {
    /* renamed from: q */

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        AppCompatDelegate.m1951a(true);
        Set hashSet = new HashSet();
        hashSet.add(new RequestLoggingListener());
        Fresco.m3941a((Context) this, ImagePipelineConfig.m4231a(getApplicationContext()).m4228a(hashSet).m4229a());
        JodaTimeAndroid.m8840a(this);
    }

    protected void onResume() {
        super.onResume();
        setVolumeControlStream(3);
        setRequestedOrientation(0);
        getWindow().setFlags(16777216, 16777216);
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }
}
