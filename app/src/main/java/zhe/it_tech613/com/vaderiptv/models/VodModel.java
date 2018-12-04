package zhe.it_tech613.com.vaderiptv.models;

public class VodModel {
    private long id;
    private String name, logo_url, channel_url, group;

    public VodModel(){}

    public VodModel(long id, String name, String logo_url, String channel_url, String group){
        this.id=id;
        this.name=name;
        this.logo_url=logo_url;
        this.channel_url=channel_url;
        this.group=group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getChannel_url() {
        return channel_url;
    }

    public void setChannel_url(String channel_url) {
        this.channel_url = channel_url;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
