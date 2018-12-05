package zhe.it_tech613.com.vaderiptv.utils;

import android.app.Activity;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealInterceptorChain;
import tv.danmaku.ijk.media.player.BuildConfig;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

public class HttpUtils {
    /* renamed from: a */
    public static final String f8456a = String.valueOf(new Random().nextInt(8999) + IjkMediaCodecInfo.RANK_MAX);

    /* renamed from: a */
    public static String m10253a() {
        return String.format("VAPI/%s-%s-%s-%s", "tv.vaders.apk", "2.0.21", BuildConfig.BUILD_TYPE, f8456a);
    }

    /* renamed from: a */
    public static OkHttpClient getOkHttpClient(final Activity activity) {
        Builder builder = new Builder();
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.followRedirects(false);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request().newBuilder().header("User-Agent", HttpUtils.m10253a()).build());
                if (activity == null) {
                    return response;
                }
                if (response.code() != 500 && response.code() != 502) {
                    return response;
                }
                ExceptionHandler.m10249a(activity, "Server Error", "It appears that our servers are down. Please try again later.", null);
                return response.newBuilder().body(ResponseBody.create(MediaType.get("application/json"), "")).build();
            }
        });
        return builder.build();
    }
}
