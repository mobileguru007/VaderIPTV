package zhe.it_tech613.com.vaderiptv.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    private List<ChannelModel> channelModels;
    private boolean is_allowable=false;
    public CategoryModel(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_allowable() {
        return is_allowable;
    }

    public void setIs_allowable(boolean is_allowable) {
        this.is_allowable = is_allowable;
    }

    public List<ChannelModel> getChannelModels() {
        return channelModels;
    }

    public void setChannelModels(List<ChannelModel> channelModels) {
        this.channelModels = channelModels;
    }
}
