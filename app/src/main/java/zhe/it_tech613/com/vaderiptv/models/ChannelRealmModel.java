package zhe.it_tech613.com.vaderiptv.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class ChannelRealmModel extends RealmObject {
    @PrimaryKey
    private long id;
    private String stream_icon,stream_display_name,resolution,channel_id;
    private int category_id,stream_type,tv_archive_duration;

    public ChannelRealmModel(){}
    public ChannelRealmModel(long id, int category_id,
                             int stream_type, String channel_id,
                             int tv_archive_duration,
                             String resolution, String stream_icon,
                             String stream_display_name){
        this.category_id=category_id;
        this.channel_id=channel_id;
        this.stream_display_name=stream_display_name;
        this.id=id;
        this.stream_type=stream_type;
        this.stream_icon=stream_icon;
        this.tv_archive_duration=tv_archive_duration;
        this.resolution=resolution;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStream_icon() {
        return stream_icon;
    }

    public void setStream_icon(String stream_icon) {
        this.stream_icon = stream_icon;
    }

    public String getStream_display_name() {
        return stream_display_name;
    }

    public void setStream_display_name(String stream_display_name) {
        this.stream_display_name = stream_display_name;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getStream_type() {
        return stream_type;
    }

    public void setStream_type(int stream_type) {
        this.stream_type = stream_type;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public int getTv_archive_duration() {
        return tv_archive_duration;
    }

    public void setTv_archive_duration(int tv_archive_duration) {
        this.tv_archive_duration = tv_archive_duration;
    }
}
