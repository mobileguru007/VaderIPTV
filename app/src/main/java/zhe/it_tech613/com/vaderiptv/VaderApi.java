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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;

import io.realm.Realm;
import zhe.it_tech613.com.vaderiptv.models.CategoryModel;
import zhe.it_tech613.com.vaderiptv.models.PackageModel;
import zhe.it_tech613.com.vaderiptv.models.UserModel;
import zhe.it_tech613.com.vaderiptv.utils.Constant;
import zhe.it_tech613.com.vaderiptv.utils.PreferenceManager;
import zhe.it_tech613.com.vaderiptv.utils.T;

public class VaderApi {
    private Realm realm;

    private Activity activity;
    private Context context;

    public KProgressHUD kpHUD;

    public static void initializeSSLContext(Context mContext){
        try {
            SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            ProviderInstaller.installIfNeeded(mContext.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public VaderApi(Activity activity) {
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

    public VaderApi(Context context) {
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
