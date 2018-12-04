package zhe.it_tech613.com.vaderiptv.utils;

import java.util.ArrayList;
import java.util.List;

import zhe.it_tech613.com.vaderiptv.models.CategoryModel;
import zhe.it_tech613.com.vaderiptv.models.ChannelModel;
import zhe.it_tech613.com.vaderiptv.models.PackageModel;
import zhe.it_tech613.com.vaderiptv.models.UserModel;
import zhe.it_tech613.com.vaderiptv.models.VodModel;


public class Constant {
    private static String base_url="http://vapi.vaders.tv";
    public static String vod_token_url=base_url+"/vod/user?token=";
    public static String APP_UPDATER_XML="http://vaders.tv/apk_update.xml";
    public static String VOD_TV_FAVORITES=base_url+"/vod/user/tv/favorites?token=%s";
    public static String GET_BOUQUETS=base_url+"/xc/bouquets?token=%s";
    public static String LIVE_CHANNEL=base_url+"/epg/streams?token=%s&category_id=%d";
    public static String LIVE_CHANNEL_ALL=base_url+"/epg/streams?token=%s";//&sort=asc
    public static String LIVE_STREAM=base_url+"/epg/channels?token=%s&category_id=%d&start=%s&end=%s";
    public static String LIVE_STREAM_ALL=base_url+"/epg/channels?token=%s&start=%s&end=%s";
    public static String SINGLE_LIVE_STREAM=base_url+"/epg/channels/%s?token=%s&start=%s&end=%s";
    public static String LIVETV_CATEGORIES=base_url+"/epg/categories?token=%s";
    public static String STREAM_EXP=base_url+"/play/%s?token=%s";
    public static String MC_CATEGORIES=base_url+"/mc/categories?token=%s";
    public static String VOD_RECENT=base_url+"/vod/streams/recent?token=%s";
    public static String VOD_TV=base_url+"/vod/streams/tv?sortField=showName&ascending=true&token=%s&pageSize=10000";
    public static String VOD_MOVIE=base_url+"/vod/streams/movie?token=%s&pageSize=10000";
    public static String MC_SCHEDULE=base_url+"/mc/schedule?token=%s&start=%s&end=%s%s";

    public static String CATCHUP=base_url+"/play/dvr/%s/%s?duration=%s&token=%s";
    public static String VOD_TV_EPISODE=base_url+"/vod/streams/tv/%s?token=%s";
    public static String VOD_MOVIE_STREAM=base_url+"/play/vod/%s.%s.m3u8?token=%s";

    public static String VOD_CATEGORIES=base_url+"/vod/genres?token=%s";
    public static String VOD_PROGRESS=base_url+"/vod/user/progress?token=%s";
    public static String SEND_LOG="http://vaders.tv/submitLog";
    public static String GET_SERVERS=base_url+"/user/server?action=getServers&token=%s";
    public static String GET_USER_SERVER=base_url+"/user/server?action=getUserServer&token=%s";
    public static String SAVE_SERVER=base_url+"/user/server?action=serverChange&token=%s&serverIp=%s";
    public static String GET_USER_PREFS=base_url+"/user/preferences?token=%s";
    public static String USER_FAVORITES=base_url+"/user/favorites?token=%s";
    public static String VOD_MOVIE_FAVORITES=base_url+"/vod/user/movie/favorites?token=%s";
    public static String vod_url ="http://vapi.vaders.tv/vod/vget?username=";
    public static String live_url="http://vaders.tv/vget?username=";
    public static String xml_url="http://vaders.tv/p2.xml";
    public static String pass_subfix="&password=";

    public static boolean fetch_finished=false;
    public static ArrayList<VodModel> vodModels=new ArrayList<>();
    public static UserModel userModel;
    public static PackageModel packageModel;
    public static ArrayList<CategoryModel> categoryModels;
    public static ArrayList<ChannelModel> channelModels=new ArrayList<>();
}
