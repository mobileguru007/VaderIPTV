package zhe.it_tech613.com.vaderiptv.communicator;

import com.google.gson.Gson;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import zhe.it_tech613.com.vaderiptv.models.ChannelModel;
import zhe.it_tech613.com.vaderiptv.utils.Constant;
import zhe.it_tech613.com.vaderiptv.utils.PreferenceManager;

public class ServiceCommunicator {

    public List<ChannelModel> liveStream(int category_id, DateTime start, DateTime end) throws IOException {
        BufferedReader bufferedReader = null;
        String url = String.format(Constant.LIVE_STREAM, PreferenceManager.getTOKEN(), category_id,start.toString("yyyyMMddhhmmss"),end.toString("yyyyMMddhhmmss"));
        URL jsonUrl = new URL(url);
//        URLConnection dc = jsonUrl.openConnection();
        HttpURLConnection connection = (HttpURLConnection) jsonUrl.openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();
//        dc.setConnectTimeout(1000000);
//        dc.setReadTimeout(1000000);

        bufferedReader = new BufferedReader(new InputStreamReader(input));

        // read the JSON results into a string
        String jsonResult = bufferedReader.readLine();
        Gson gson=new Gson();
        List<ChannelModel> channelModels= Arrays.asList(gson.fromJson(jsonResult,ChannelModel[].class));
        return channelModels;
    }
}
