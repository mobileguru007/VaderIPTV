package zhe.it_tech613.com.vaderiptv;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.SSLContext;

import io.realm.Realm;
import zhe.it_tech613.com.vaderiptv.models.CategoryModel;
import zhe.it_tech613.com.vaderiptv.models.PackageModel;
import zhe.it_tech613.com.vaderiptv.models.UserModel;
import zhe.it_tech613.com.vaderiptv.models.VadersApiUrl;
import zhe.it_tech613.com.vaderiptv.utils.Constant;
import zhe.it_tech613.com.vaderiptv.utils.PreferenceManager;
import zhe.it_tech613.com.vaderiptv.utils.T;
import zhe.it_tech613.com.vaderiptv.R;

public class VadersAPI {
    private Realm realm;

    private Activity activity;
    private Context context;

    public KProgressHUD kpHUD;

    /* renamed from: A */
    private static final VadersApiUrl f8001A = new VadersApiUrl("VOD_TV_FAVORITES", "/vod/user/tv/favorites?token=%s");
    /* renamed from: B */
    private static final VadersApiUrl f8002B = new VadersApiUrl("GET_BOUQUETS", "/xc/bouquets?token=%s");
    /* renamed from: C */
    private static final VadersApiUrl f8003C = new VadersApiUrl("APP_UPDATER_XML", "http://vaders.tv/apk_update.xml");//update
    /* renamed from: a */
    private static SimpleDateFormat f8004a = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    /* renamed from: b */
    private static final VadersApiUrl f8005b = new VadersApiUrl("LIVE_CHANNEL", "/epg/streams?token=%s&category_id=%d");
    /* renamed from: c */
    private static final VadersApiUrl f8006c = new VadersApiUrl("LIVE_CHANNEL_ALL", "/epg/streams?token=%s");
    /* renamed from: d */
    private static final VadersApiUrl f8007d = new VadersApiUrl("LIVE_STREAM", "/epg/channels?token=%s&category_id=%d&start=%s&end=%s");
    /* renamed from: e */
    private static final VadersApiUrl f8008e = new VadersApiUrl("LIVE_STREAM_ALL", "/epg/channels?token=%s&start=%s&end=%s");
    /* renamed from: f */
    private static final VadersApiUrl f8009f = new VadersApiUrl("SINGLE_LIVE_STREAM", "/epg/channels/%s?token=%s&start=%s&end=%s");
    /* renamed from: g */
    private static final VadersApiUrl f8010g = new VadersApiUrl("LIVETV_CATEGORIES", "/epg/categories?token=%s");
    /* renamed from: h */
    private static final VadersApiUrl f8011h = new VadersApiUrl("STREAM_EXP", "/play/%s?token=%s");
    /* renamed from: i */
    private static final VadersApiUrl catchupUrl = new VadersApiUrl("CATCHUP", "/play/dvr/%s/%s?duration=%s&token=%s");
    /* renamed from: j */
    private static final VadersApiUrl authUrl = new VadersApiUrl("AUTH", "/vod/user?token=%s");
    /* renamed from: k */
    private static final VadersApiUrl f8014k = new VadersApiUrl("MC_SCHEDULE", "/mc/schedule?token=%s&start=%s&end=%s%s");
    /* renamed from: l */
    private static final VadersApiUrl f8015l = new VadersApiUrl("MC_CATEGORIES", "/mc/categories?token=%s");
    /* renamed from: m */
    private static final VadersApiUrl f8016m = new VadersApiUrl("VOD_RECENT", "/vod/streams/recent?token=%s");
    /* renamed from: n */
    private static final VadersApiUrl f8017n = new VadersApiUrl("VOD_TV", "/vod/streams/tv?sortField=showName&ascending=true&token=%s&pageSize=10000");
    /* renamed from: o */
    private static final VadersApiUrl f8018o = new VadersApiUrl("VOD_MOVIE", "/vod/streams/movie?token=%s&pageSize=10000");
    /* renamed from: p */
    private static final VadersApiUrl f8019p = new VadersApiUrl("VOD_TV_EPISODE", "/vod/streams/tv/%s?token=%s");
    /* renamed from: q */
    private static final VadersApiUrl vod_movie_stream_url = new VadersApiUrl("VOD_MOVIE_STREAM", "/play/vod/%s.%s.m3u8?token=%s");
    /* renamed from: r */
    private static final VadersApiUrl f8021r = new VadersApiUrl("VOD_CATEGORIES", "/vod/genres?token=%s");
    /* renamed from: s */
    private static final VadersApiUrl f8022s = new VadersApiUrl("VOD_PROGRESS", "/vod/user/progress?token=%s");
    /* renamed from: t */
    private static final VadersApiUrl f8023t = new VadersApiUrl("SEND_LOG", "http://vaders.tv/submitLog");
    /* renamed from: u */
    private static final VadersApiUrl f8024u = new VadersApiUrl("GET_SERVERS", "/user/server?action=getServers&token=%s");
    /* renamed from: v */
    private static final VadersApiUrl f8025v = new VadersApiUrl("GET_USER_SERVER", "/user/server?action=getUserServer&token=%s");
    /* renamed from: w */
    private static final VadersApiUrl f8026w = new VadersApiUrl("SAVE_SERVER", "/user/server?action=serverChange&token=%s&serverIp=%s");
    /* renamed from: x */
    private static final VadersApiUrl f8027x = new VadersApiUrl("GET_USER_PREFS", "/user/preferences?token=%s");
    /* renamed from: y */
    private static final VadersApiUrl f8028y = new VadersApiUrl("USER_FAVORITES", "/user/favorites?token=%s");
    /* renamed from: z */
    private static final VadersApiUrl f8029z = new VadersApiUrl("VOD_MOVIE_FAVORITES", "/vod/user/movie/favorites?token=%s");

    public static VadersApiUrl m9981a(Context context, int i) {
        String str = PreferenceManager.getBoolean(context,"livetv_mpegts", false) ? ".ts" : ".m3u8";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("username", PreferenceManager.getUSERNAME());
            jSONObject.put("password", PreferenceManager.getPASSWORD());
            String a = f8011h.getKey();
            String b = f8011h.getUrlValue();
            Object[] objArr = new Object[2];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(str);
            objArr[0] = stringBuilder.toString();
            objArr[1] = PreferenceManager.getTOKEN();
            return new VadersApiUrl(a, getRealUrlFromSubfix(context, String.format(b, objArr)));
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    private static String getRealUrlFromSubfix(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://");
        stringBuilder.append(context.getResources().getString(R.string.vapi_server_url));
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static VadersApiUrl getVodMovieStreamUrl(Context context, int i, String str) {
        return new VadersApiUrl(vod_movie_stream_url.getKey(), getRealUrlFromSubfix(context, String.format(vod_movie_stream_url.getUrlValue(), i, str, PreferenceManager.getTOKEN())));
    }

    public static void initializeSSLContext(Context mContext){
        try {
            SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            ProviderInstaller.installIfNeeded(mContext.getApplicationContext());
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public VadersAPI(Activity activity) {
        this.activity = activity;
        this.realm=PreferenceManager.realm;
//        this.realmCustomerUser=RealmController.with(activity).getRealmCustomerUser();
//        this.realmClinicUser=RealmController.with(activity).getRealmClinicUser();
//        this.realmSpecialty=RealmController.with(activity).getRealmSpecialty();
//        this.realmCity=RealmController.with(activity).getRealmCity();
        initializeSSLContext(activity);
        kpHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
//                .setLabel("Login")
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }

    public VadersAPI(Context context) {
        this.context = context;
        this.realm= PreferenceManager.realm;
//        this.realmCustomerUser=RealmController.with(activity).getRealmCustomerUser();
//        this.realmClinicUser=RealmController.with(activity).getRealmClinicUser();
//        this.realmSpecialty=RealmController.with(activity).getRealmSpecialty();
//        this.realmCity=RealmController.with(activity).getRealmCity();
        initializeSSLContext(context);
        kpHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
//                .setLabel("Login")
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }

    public boolean parseLogin(JSONObject response) {
        if (response.has("message")) {
            String message;
            try {
                message=response.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
                message="Unknown Error happened";
            }
            T.showError(activity,message);
            return false;
        }
        Gson gson;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        try {
            Constant.userModel=gson.fromJson(response.toString(), UserModel.class);
            Constant.packageModel= gson.fromJson(response.getJSONObject("package").toString(), PackageModel.class);
            JSONArray categories=response.getJSONArray("categories");

            if (categories.length()>0){
                List<CategoryModel> categoryModels= Arrays.asList(gson.fromJson(categories.toString(),CategoryModel[].class));
                Constant.categoryModels=new ArrayList<CategoryModel>(categoryModels);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
