package zhe.it_tech613.com.vaderiptv.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.VadersAPI;
import zhe.it_tech613.com.vaderiptv.models.ChannelModel;
import zhe.it_tech613.com.vaderiptv.utils.Constant;
import zhe.it_tech613.com.vaderiptv.utils.M3UParser;
import zhe.it_tech613.com.vaderiptv.utils.PreferenceManager;
import zhe.it_tech613.com.vaderiptv.utils.T;
import zhe.it_tech613.com.vaderiptv.utils.Utils;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity {

    VadersAPI vadersAPI;
    static final File STORAGE_DIRECTORY = Environment.getExternalStorageDirectory();
    public static final File dir = new File(STORAGE_DIRECTORY.getPath() + "/VaderIPTV");
    static File filepath;
    private static LoginActivity instance = null;
    public final String urlLink = "Add your own link";
    FirebaseAnalytics firebaseAnalytics;
    SharedPreferences.Editor editor;
    private EditText mEmailView;
    private EditText mPasswordView;
    private ProgressBar spinner;
    private String domain="http://vapi.vaders.tv/vod";
    M3UParser m3UParser;
    ProgressDialog mProgressDialog;
    public static LoginActivity getInstance() {
        if (instance == null) {
            instance = new LoginActivity();
        }
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_login);
        mProgressDialog = new ProgressDialog(this);
        vadersAPI =new VadersAPI(this);
        getWindow().setBackgroundDrawable(null);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }else {
            if (!dir.exists()) {
                dir.mkdir();
            }
            filepath = new File(dir.getPath() + "/data.m3u");
        }


        mEmailView = findViewById(R.id.input_username);
        mPasswordView = findViewById(R.id.input_password);
        spinner = findViewById(R.id.login_progress);
        final Button mEmailSignIn = findViewById(R.id.btn_login);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                checkNet();
                return true;
            }
        });

        mEmailSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkNet();
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "LoginActivity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);
    }

    public void checkNet() {
        if (Utils.getInstance().isNetworkAvailable(LoginActivity.this)) {
            attemptLogin();
        } else {
            attemptLogin();
        }
    }

    @SuppressWarnings("All")
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    filepath = new File(dir.getPath() + "/data.m3u");
                } else {
                    Utils.getInstance().Snack(this, "Permission denied", findViewById(R.id.activity_login));
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                return;
            }
            // other 'case' lines to check for other permissions this app might request
        }
    }

    private void attemptLogin() {

        // Reset errors.
//        mEmailView.setError(null);
//        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first form field with an error.
            focusView.requestFocus();
        } else {
            tokenLogin(false);
//            new _checkNetworkAvailable().execute(domain + "/vget?username=" + mEmailView.getText() + "&password=" + mPasswordView.getText());
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    protected void onResume() {
        super.onResume();
        boolean isAccess = PreferenceManager.getLoginStatus();
        boolean isAva = Utils.getInstance().isNetworkAvailable(LoginActivity.this);
        if (isAccess) {
            tokenLogin(true);
        }
//        if (!isAva){activateWIFI();}
    }

//    private void login(){
////        String Tag_req="req_login";
//        m3UParser=new M3UParser(LoginActivity.this);
//        String url= Constant.live_url + mEmailView.getText() + Constant.pass_subfix+ mPasswordView.getText()+"&type=m3u_plus&output=ts";
//        // Request a string response from the provided URL.
//
//        new VodRequest().execute(url);
//        Utils.getInstance().Snack(LoginActivity.this, "Loading channels...", findViewById(R.id.activity_login));
////        new _checkNetworkAvailable().execute(url);
//    }

//    @SuppressLint("StaticFieldLeak")
//    private class _checkNetworkAvailable extends AsyncTask<String, Void, Boolean> {
//
//        @Override
//        protected void onPreExecute() {
////            super.onPreExecute();
//            spinner.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            try {
//                URL myUrl = new URL(params[0]+ "&format=ts");//Arrays.toString(params)
////                URL myUrl = new URL("http://expedis.schamann.net/mobile/getinitialdata/198/");//Arrays.toString(params)
//                String result = "";
//                HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
//                con.setRequestMethod("GET");
//                con.setInstanceFollowRedirects(true);
//                con.setConnectTimeout(200000);
//                con.setReadTimeout(200000);
//                con.setDoOutput(true);
//                con.setDoInput(true);
//                con.connect();
//                boolean check = con.getResponseCode() == HttpURLConnection.HTTP_OK;
//                InputStream in = con.getInputStream();
//                m3UParser.parseFile(in);
//                InputStreamReader reader = new InputStreamReader(in);
//
//                int data = reader.read();
//
//                while (data != -1) {
//
//                    char current = (char) data;
//
//                    result += current;
//
//                    data = reader.read();
//
//                }
//                Log.e("Google", String.valueOf(check));
//                return check;
//            } catch (Exception e) {
//                Log.e("Google", e.toString());
//                return false;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            boolean bRes = result;
//            if (bRes) {
//                PreferenceManager.putString(LoginActivity.this,"name", "admin");
//                PreferenceManager.putString(LoginActivity.this,"id", mEmailView.getText().toString());
//                PreferenceManager.putString(LoginActivity.this,"password", mPasswordView.getText().toString());
//                PreferenceManager.putBoolean(LoginActivity.this,"isLogged", true);
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                spinner.setVisibility(View.GONE);
//                startActivity(intent);
//                finish();
////                new VodRequest().execute(domain + "/get.php?username=" + mEmailView.getText() + "&password=" + mPasswordView.getText() + "&type=m3u&output=ts");
////                Utils.getInstance().Snack(LoginActivity.this, "Loading channels...", findViewById(R.id.activity_login));
//            } else {
//                spinner.setVisibility(View.GONE);
//                Utils.getInstance().Snack(getApplicationContext(), "Account not found.", findViewById(R.id.activity_login));
//            }
//        }
//    }

    private void tokenLogin(boolean isLoggedin){
        String Tag_req="req_login";
        vadersAPI.kpHUD.show();
        String token;
        if (isLoggedin){
            token=PreferenceManager.getTOKEN();
        }else {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("username", mEmailView.getText().toString());
                jSONObject.put("password",  mPasswordView.getText().toString());
                token= new String(Base64.encode(jSONObject.toString().getBytes(), 0)).replace("\n","").replace("    ","");
                Log.e("token",token);
            } catch (Throwable e) {
                Log.e("UserManager", "Error encoding credentials in base64.", e);
                token=  "";
            }
        }

        String url=Constant.vod_token_url+token;
        String finalToken = token;
        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        vadersAPI.kpHUD.dismiss();
                        Log.e("login_response",response.toString());
                        if (vadersAPI.parseLogin(response)) {
                            if (!isLoggedin){
                                PreferenceManager.setUSERNAME(mEmailView.getText().toString());
                                PreferenceManager.setPASSWORD(mPasswordView.getText().toString());
                                PreferenceManager.setTOKEN(finalToken);
                                PreferenceManager.setLoginStatus(true);
                            }
                            getLiveChannels(finalToken);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        vadersAPI.kpHUD.dismiss();
                        T.showError(LoginActivity.this,getString(R.string.connection_error)+error.getMessage());
                    }
                });

            // add the request object to the queue to be executed
            PreferenceManager.getInstance().addToRequestQueue(req,Tag_req);

    }

    private void getLiveChannels(String finalToken) {
        String Tag_req="fetch_live_channel";
        Toast.makeText(getApplicationContext(),getString(R.string.start_fetch),Toast.LENGTH_LONG).show();
        String url=String.format(Constant.LIVE_CHANNEL_ALL, finalToken).replace("\n","").replace("  ","");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        long id=jsonObject.getLong("id");
//                        String stream_icon=jsonObject.getString("stream_icon");
//                        int tv_archive_duration=jsonObject.getInt("tv_archive_duration");
//                        String channel_id;
//                        try{
//                            channel_id=jsonObject.getString("channel_id");
//                        }catch (JSONException e){
//                            channel_id="";
//                        }
//                        int stream_type=jsonObject.getInt("stream_type");
//                        String stream_display_name=jsonObject.getString("stream_display_name");
//                        int category_id=jsonObject.getInt("category_id");
//                        String resolution=jsonObject.getString("resolution");
//                        ChannelRealmModel channelRealmModel =new ChannelRealmModel(id,category_id, stream_type,channel_id, tv_archive_duration, resolution,stream_icon, stream_display_name);
//                        PreferenceManager.realm.executeTransaction(new Realm.Transaction() {
//                            @Override
//                            public void execute(Realm realm) {
//                                realm.copyToRealmOrUpdate(channelRealmModel);
//                            }
//                        });
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getApplicationContext(),getString(R.string.fetech_failed),Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                }
                String responsearray=response.toString();
                Gson gson=new Gson();
                List<ChannelModel> channelModels= Arrays.asList(gson.fromJson(responsearray,ChannelModel[].class));
                Constant.channelModels.addAll(channelModels);
                Constant.fetch_finished=true;
                Toast.makeText(getApplicationContext(),getString(R.string.success_fetch),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),getString(R.string.fetech_failed),Toast.LENGTH_LONG).show();
            }
        });
        // add the request object to the queue to be executed
        PreferenceManager.getInstance().addToRequestQueue(jsonArrayRequest,Tag_req);
    }

//    private class VodRequest extends AsyncTask<String, Integer, Boolean> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            spinner.setVisibility(View.VISIBLE);
////            mProgressDialog.setMessage("Downloading...");
////            mProgressDialog.setIndeterminate(true);
////            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
////            mProgressDialog.setCancelable(false);
////            mProgressDialog.show();
//        }
//
//
//        @Override
//        protected Boolean doInBackground(String... f_url) {
//            InputStream input = null;
//            OutputStream output = null;
//            HttpURLConnection connection = null;
//            try {
//                URL url = new URL(f_url[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//                input = connection.getInputStream();
//                int fileLength = connection.getContentLength();
//                if(filepath.exists()){
//                    filepath.delete();
//                }
//                output = new FileOutputStream(filepath, false);
//
//                byte data[] = new byte[4096];
//                long total = 0;
//                int count;
//                while ((count = input.read(data)) != -1) {
//                    if (isCancelled()) {
//                        input.close();
//                        return null;
//                    }
//                    total += count;
//                    if (fileLength > 0)
//                        publishProgress((int) (total * 100 / fileLength));
//                    output.write(data, 0, count);
//                }
//
//                Constant.vodModels=m3UParser.parseVod(filepath.getPath());//input//filepath.getPath()
//                return true;
//            } catch (Exception e) {
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),getString(R.string.login_failed),Toast.LENGTH_LONG).show();
//                    }
//                });
//                Utils.getInstance().Snack(getApplicationContext(), getString(R.string.account_not_found), findViewById(R.id.activity_login));
//                return false;
//            } finally {
//                try {
//                    if (input != null)
//                        input.close();
//                } catch (IOException ignored) {
//                }
//                if (connection != null)
//                    connection.disconnect();
//            }
////            try {
////                URL yahoo = new URL(f_url[0]);
////                BufferedReader in = new BufferedReader(
////                        new InputStreamReader(yahoo.openStream()));
////                String inputLine;
////                OutputStreamWriter myOutWriter = new FileWriter(filepath);
////                while ((inputLine = in.readLine()) != null) {
////                    myOutWriter.write(inputLine + "\n");
////                }
////                myOutWriter.flush();
////                myOutWriter.close();
////                in.close();
////                Log.e("Google", "File done");
//////                RealmList<VodRealmModel> vodRealmModels =m3UParser.parseRealmVod(filepath.getPath());//STORAGE_DIRECTORY.getPath() + "/IPTV/data.m3u"
////                Constant.vodModels=m3UParser.parseVod(filepath.getPath());
////            } catch (Exception e) {
////                Log.d("Google", "VodRequest " + e.getMessage());
////            }
////            return null;
//        }
//
//        protected void onProgressUpdate(Integer... values) {
////            mProgressDialog.setIndeterminate(false);
////            mProgressDialog.setMax(100);
////            if(values[0]>90){
////                return;
////            }
////            mProgressDialog.setProgress(values[0]);
//        }
//
//        protected void onPostExecute(Boolean result) {
//            if (result){
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
//                spinner.setVisibility(View.GONE);
//                String url= Constant.live_url + mEmailView.getText() + Constant.pass_subfix+ mPasswordView.getText()+"&format=ts";
//                new LiveRequest().execute(url);
//            }
//            else spinner.setVisibility(View.GONE);
//
//        }
//    }

//    private class GetLiveChannel extends AsyncTask<String, Integer, Boolean> {
//        private String url;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//
//        @Override
//        protected Boolean doInBackground(String... f_url) {
//            InputStream input = null;
//            url=f_url[0];
//            HttpURLConnection connection = null;
//            try {
//                URL url = new URL(f_url[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//                input = connection.getInputStream();
//                return m3UParser.parseLiveChannelAll(input);
//            } catch (Exception e) {
//                runOnUiThread(new Runnable() {
//                    public void run() {
//
//                    }
//                });
//                return false;
//            } finally {
//                try {
//                    if (input != null)
//                        input.close();
//                } catch (IOException ignored) {
//                }
//                if (connection != null)
//                    connection.disconnect();
//            }
////            try {
////                URL yahoo = new URL(f_url[0]);
////                BufferedReader in = new BufferedReader(
////                        new InputStreamReader(yahoo.openStream()));
////                String inputLine;
////                OutputStreamWriter myOutWriter = new FileWriter(filepath);
////                while ((inputLine = in.readLine()) != null) {
////                    myOutWriter.write(inputLine + "\n");
////                }
////                myOutWriter.flush();
////                myOutWriter.close();
////                in.close();
////                Log.e("Google", "File done");
//////                RealmList<VodRealmModel> vodRealmModels =m3UParser.parseRealmVod(filepath.getPath());//STORAGE_DIRECTORY.getPath() + "/IPTV/data.m3u"
////                Constant.vodModels=m3UParser.parseVod(filepath.getPath());
////            } catch (Exception e) {
////                Log.d("Google", "VodRequest " + e.getMessage());
////            }
////            return null;
//        }
//
//        protected void onProgressUpdate(Integer... values) {
////            mProgressDialog.setIndeterminate(false);
////            mProgressDialog.setMax(100);
////            if(values[0]>90){
////                return;
////            }
////            mProgressDialog.setProgress(values[0]);
//        }
//
//        protected void onPostExecute(Boolean result) {
//            if (result){
//                runOnUiThread(new Runnable() {
//                    public void run() {
//
//                    }
//                });
//            }
//            else {
//                new GetLiveChannel().execute(url);
//            }
//
//        }
//    }

    private class LiveRequest extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog.setMessage("Downloading...");
//            mProgressDialog.setIndeterminate(true);
//            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            mProgressDialog.setCancelable(false);
//            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... f_url) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(f_url[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                input = connection.getInputStream();
//                int fileLength = connection.getContentLength();
//                if(filepath.exists()){
//                    filepath.delete();
//                }
//                output = new FileOutputStream(filepath, false);
//
//                byte data[] = new byte[4096];
//                long total = 0;
//                int count;
//                while ((count = input.read(data)) != -1) {
//                    if (isCancelled()) {
//                        input.close();
//                        return null;
//                    }
//                    total += count;
//                    if (fileLength > 0)
//                        publishProgress((int) (total * 100 / fileLength));
//                    output.write(data, 0, count);
//                }
                m3UParser.parseLive(input);//input//filepath.getPath()
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }
                if (connection != null)
                    connection.disconnect();
            }
            return null;
//            try {
//                URL yahoo = new URL(f_url[0]);
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(yahoo.openStream()));
//                String inputLine;
//                OutputStreamWriter myOutWriter = new FileWriter(filepath);
//                while ((inputLine = in.readLine()) != null) {
//                    myOutWriter.write(inputLine + "\n");
//                }
//                myOutWriter.flush();
//                myOutWriter.close();
//                in.close();
//                Log.e("Google", "File done");
////                RealmList<VodRealmModel> vodRealmModels =m3UParser.parseRealmVod(filepath.getPath());//STORAGE_DIRECTORY.getPath() + "/IPTV/data.m3u"
//                Constant.vodModels=m3UParser.parseVod(filepath.getPath());
//            } catch (Exception e) {
//                Log.d("Google", "VodRequest " + e.getMessage());
//            }
//            return null;
        }

        protected void onProgressUpdate(Integer... values) {
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.setMax(100);
//            if(values[0]>90){
//                return;
//            }
//            mProgressDialog.setProgress(values[0]);
        }

        protected void onPostExecute(String file_url) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
