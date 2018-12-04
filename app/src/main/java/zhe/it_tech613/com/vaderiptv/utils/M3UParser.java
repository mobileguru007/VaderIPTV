package zhe.it_tech613.com.vaderiptv.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.realm.Realm;
import io.realm.RealmList;
import zhe.it_tech613.com.vaderiptv.models.ChannelRealmModel;
import zhe.it_tech613.com.vaderiptv.models.VodModel;
import zhe.it_tech613.com.vaderiptv.models.VodRealmModel;

public class M3UParser {
    private static final String seperator="\" ";
    private static final String semi_seperator="\",";
    private static final String EXT_M3U = "#EXTM3U\n\n";
    private static final String EXT_INF = "#EXTINF:-1 ";
    private static final String EXT_PLAYLIST_NAME = "#PLAYLIST";
    private static final String tvg_name="tvg-name=\"";
    private static final String tvg_id="tvg-id=\"";
    private static final String tvg_logo = "tvg-logo=\"";
    private static final String group_title="group-title=\"";
    private static final String EXT_URL = "http://";
    private static final String EXT_X_STREAM_INF="#EXT-X-STREAM-INF:";
    private static final String EXT_LOGO = "tvg-logo";

    private Realm realm;
    private Activity activity;
    private Context context;

    public KProgressHUD kpHUD;
    public M3UParser(){
        this.realm= PreferenceManager.realm;

        kpHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
               .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }
    public M3UParser(Context context){
        this.context = context;
        this.realm= PreferenceManager.realm;

        kpHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }

    public String convertStreamToString(InputStream is) {
        try {
            return new Scanner(is).useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public boolean parseLiveChannelAll(JSONArray response){
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject = response.getJSONObject(i);
                long id=jsonObject.getLong("id");
                String stream_icon=jsonObject.getString("stream_icon");
                int tv_archive_duration=jsonObject.getInt("tv_archive_duration");
                String channel_id;
                try{
                    channel_id=jsonObject.getString("channel_id");
                }catch (JSONException e){
                    channel_id="";
                }
                int stream_type=jsonObject.getInt("stream_type");
                String stream_display_name=jsonObject.getString("stream_display_name");
                int category_id=jsonObject.getInt("category_id");
                String resolution=jsonObject.getString("resolution");
                ChannelRealmModel channelRealmModel =new ChannelRealmModel(id,category_id, stream_type,channel_id, tv_archive_duration, resolution,stream_icon, stream_display_name);
                PreferenceManager.realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(channelRealmModel);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    //vod_from_string
    public RealmList<VodRealmModel> parseVodString(String response) throws FileNotFoundException {
        RealmList<VodRealmModel> vodRealmModels =new RealmList<>();
        if (response.contains(EXT_M3U)){
            response=response.substring(EXT_M3U.length()+EXT_INF.length()+1);
            String linesArray[] = response.split(EXT_INF);
            for (int i = 0; i < linesArray.length; i++) {
                String currLine = linesArray[i];
                String name,id,logo_url,group,channel_url;
                currLine=currLine.replaceAll("\n","");
                String[] fields=currLine.split(seperator);
                name=fields[0].substring(tvg_name.length());
                id=fields[1].substring(tvg_id.length());
                logo_url=fields[2].substring(tvg_logo.length());
                String[] group_channel=fields[3].substring(group_title.length()).split(semi_seperator);
                group=group_channel[0];
                channel_url=group_channel[1].substring(group_channel[1].indexOf(EXT_URL));
                VodRealmModel vodRealmModel =new VodRealmModel(Long.valueOf(id),name,logo_url,channel_url,group);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(vodRealmModel);
                    }
                });
                vodRealmModels.add(vodRealmModel);
            }
        }else return null;

        return vodRealmModels;
    }

    public RealmList<VodRealmModel> parseRealmVod(String filepath) throws FileNotFoundException {
        InputStream inputStream=new FileInputStream(filepath);
        Realm backgroundRealm = Realm.getDefaultInstance();

        RealmList<VodRealmModel> vodRealmModels =new RealmList<>();

        String response = convertStreamToString(inputStream);
        if (response.contains(EXT_M3U)){
            response=response.substring(EXT_M3U.length()+EXT_INF.length()+1);
            String linesArray[] = response.split(EXT_INF);
            for (int i = 0; i < linesArray.length; i++) {
                String currLine = linesArray[i];
                String name,id,logo_url,group,channel_url;
                currLine=currLine.replaceAll("\n","");
                String[] fields=currLine.split(seperator);
                name=fields[0].substring(tvg_name.length());
                id=fields[1].substring(tvg_id.length());
                logo_url=fields[2].substring(tvg_logo.length());
                String[] group_channel=fields[3].substring(group_title.length()).split(semi_seperator);
                group=group_channel[0];
                channel_url=group_channel[1].substring(group_channel[1].indexOf(EXT_URL));
                VodRealmModel vodRealmModel =new VodRealmModel(Long.valueOf(id),name,logo_url,channel_url,group);
//                backgroundRealm.executeTransactionAsync(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        realm.copyToRealmOrUpdate(vodRealmModel);
//                    }
//                });
                vodRealmModels.add(vodRealmModel);
            }
            return vodRealmModels;
        }else return null;
    }

    public ArrayList<VodModel> parseVod(String filepath) {
        ArrayList<VodModel> vodRealmModels =new ArrayList<>();
        InputStream inputStream= null;
        int i = 0;
        try {
            inputStream = new FileInputStream(filepath);

            String response = convertStreamToString(inputStream);
            if (response.contains(EXT_M3U)){
                response=response.substring(EXT_M3U.length()+EXT_INF.length());
                String linesArray[] = response.split(EXT_INF);
                for (i = 0; i < linesArray.length; i++) {
                    String currLine = linesArray[i];
                    String name,id,logo_url,group,channel_url;
                    currLine=currLine.replaceAll("\n","");
                    String[] fields=currLine.split(seperator);
                    name=fields[0].substring(tvg_name.length());
                    id=fields[1].substring(tvg_id.length());
                    logo_url=fields[2].substring(tvg_logo.length());
                    String[] group_channel=fields[3].substring(group_title.length()).split(semi_seperator);
                    group=group_channel[0];
                    channel_url=group_channel[1].substring(group_channel[1].indexOf(EXT_URL));
                    VodModel vodRealmModel =new VodModel(Long.valueOf(id),name,logo_url,channel_url,group);
                    vodRealmModels.add(vodRealmModel);
                }
                return vodRealmModels;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("iteration",String.valueOf(i));
        }
        return vodRealmModels;
    }

    public ArrayList<VodModel> parseVod(InputStream inputStream) {
        ArrayList<VodModel> vodModels =new ArrayList<>();
        String response = convertStreamToString(inputStream);
        if (response.contains(EXT_M3U)){
            response=response.substring(EXT_M3U.length()+EXT_INF.length());
            String linesArray[] = response.split(EXT_INF);
            for (int i = 0; i < linesArray.length; i++) {
                String currLine = linesArray[i];
                String name,id,logo_url,group,channel_url;
                currLine=currLine.replaceAll("\n","");
                String[] fields=currLine.split(seperator);
                name=fields[0].substring(tvg_name.length());
                id=fields[1].substring(tvg_id.length());
                logo_url=fields[2].substring(tvg_logo.length());
                String[] group_channel=fields[3].substring(group_title.length()).split(semi_seperator);
                group=group_channel[0];
                channel_url=group_channel[1].substring(group_channel[1].indexOf(EXT_URL));
                VodModel vodModel =new VodModel(Long.valueOf(id),name,logo_url,channel_url,group);
                vodModels.add(vodModel);
                Constant.vodModels.add(vodModel);
            }
            return vodModels;
        }
        return vodModels;
    }

    public ArrayList<VodModel> parseLive(String filepath) {
        ArrayList<VodModel> vodRealmModels =new ArrayList<>();
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(filepath);

            String response = convertStreamToString(inputStream);
            if (response.contains(EXT_M3U)){
                response=response.substring(EXT_M3U.length()+EXT_INF.length());
                String linesArray[] = response.split(EXT_INF);
                for (int i = 0; i < linesArray.length; i++) {
                    String currLine = linesArray[i];
                    String name,id,logo_url,group,channel_url;
                    currLine=currLine.replaceAll("\n","");
                    String[] fields=currLine.split(seperator);
                    name=fields[0].substring(tvg_name.length());
                    id=fields[1].substring(tvg_id.length());
                    logo_url=fields[2].substring(tvg_logo.length());
                    String[] group_channel=fields[3].substring(group_title.length()).split(semi_seperator);
                    group=group_channel[0];
                    channel_url=group_channel[1].substring(group_channel[1].indexOf(EXT_URL));
                    VodModel vodRealmModel =new VodModel(Long.valueOf(id),name,logo_url,channel_url,group);
                    vodRealmModels.add(vodRealmModel);
                }
                return vodRealmModels;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return vodRealmModels;
    }

    public ArrayList<VodModel> parseLive(InputStream inputStream) {
        ArrayList<VodModel> vodModels =new ArrayList<>();
        String response = convertStreamToString(inputStream);
        if (response.contains(EXT_M3U)){
            response=response.substring(EXT_M3U.length()+EXT_INF.length());
            String linesArray[] = response.split(EXT_INF);
            for (int i = 0; i < linesArray.length; i++) {
                String currLine = linesArray[i];
                String name,id,logo_url,group,channel_url;
                currLine=currLine.replaceAll("\n","");
                String[] fields=currLine.split(seperator);
                name=fields[0].substring(tvg_name.length());
                id=fields[1].substring(tvg_id.length());
                logo_url=fields[2].substring(tvg_logo.length());
                String[] group_channel=fields[3].substring(group_title.length()).split(semi_seperator);
                group=group_channel[0];
                channel_url=group_channel[1].substring(group_channel[1].indexOf(EXT_URL));
                VodModel vodModel =new VodModel(Long.valueOf(id),name,logo_url,channel_url,group);
                vodModels.add(vodModel);
                Constant.vodModels.add(vodModel);
            }
            return vodModels;
        }
        return vodModels;
    }

    public M3UPlaylist parseFile(InputStream inputStream) throws FileNotFoundException {
        M3UPlaylist m3UPlaylist = new M3UPlaylist();
        List<M3UItem> playlistItems = new ArrayList<>();
        String stream = convertStreamToString(inputStream);
        String linesArray[] = stream.split(EXT_INF);
        for (int i = 0; i < linesArray.length; i++) {
            String currLine = linesArray[i];
            if (currLine.contains(EXT_M3U)) {
                //header of file
                if (currLine.contains(EXT_PLAYLIST_NAME)) {
                    String fileParams = currLine.substring(EXT_M3U.length(), currLine.indexOf(EXT_PLAYLIST_NAME));
                    String playListName = currLine.substring(currLine.indexOf(EXT_PLAYLIST_NAME) + EXT_PLAYLIST_NAME.length()).replace(":", "");
                    m3UPlaylist.setPlaylistName(playListName);
                    m3UPlaylist.setPlaylistParams(fileParams);
                } else {
                    m3UPlaylist.setPlaylistName("Noname Playlist");
                    m3UPlaylist.setPlaylistParams("No Params");
                }
            } else {
                M3UItem playlistItem = new M3UItem();
                String[] dataArray = currLine.split(",");
                if (dataArray[0].contains(EXT_LOGO)) {
                    String duration = dataArray[0].substring(0, dataArray[0].indexOf(EXT_LOGO)).replace(":", "").replace("\n", "");
                    String icon = dataArray[0].substring(dataArray[0].indexOf(EXT_LOGO) + EXT_LOGO.length()).replace("=", "").replace("\"", "").replace("\n", "");
                    playlistItem.setItemDuration(duration);
                    playlistItem.setItemIcon(icon);
                } else {
                    String duration = dataArray[0].replace(":", "").replace("\n", "");
                    playlistItem.setItemDuration(duration);
                    playlistItem.setItemIcon("");
                }
                try {
                    String url = dataArray[1].substring(dataArray[1].indexOf(EXT_URL)).replace("\n", "").replace("\r", "");
                    String name = dataArray[1].substring(0, dataArray[1].indexOf(EXT_URL)).replace("\n", "");
                    playlistItem.setItemName(name);
                    playlistItem.setItemUrl(url);
                } catch (Exception fdfd) {
                    Log.e("Google", "Error: " + fdfd.fillInStackTrace());
                }
                playlistItems.add(playlistItem);
            }
        }
        m3UPlaylist.setPlaylistItems(playlistItems);
        return m3UPlaylist;
    }
}
