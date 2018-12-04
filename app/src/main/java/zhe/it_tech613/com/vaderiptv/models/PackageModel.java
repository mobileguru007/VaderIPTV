package zhe.it_tech613.com.vaderiptv.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PackageModel implements Serializable{
    @SerializedName("id")
    private long id;
    @SerializedName("maxConnections")
    private long maxConnections;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("credits")
    private int credits;
    @SerializedName("trial")
    private boolean trial;
    @SerializedName("creatable")
    private boolean creatable;
    @SerializedName("extensible")
    private boolean extensible;
    @SerializedName("allowedCategories")
    private List<String> allowedCategories;
    @SerializedName("expirationBoost")
    private int expirationBoost;
    @SerializedName("expirationBoostType")
    private String expirationBoostType;

    public PackageModel(long id,long maxConnections,
                        String name,String description,
                        int credits,boolean trial,boolean creatable,
                        boolean extensible,List<String> allowedCategories,
                        int expirationBoost, String expirationBoostType){
        this.allowedCategories=allowedCategories;
        this.creatable=creatable;
        this.credits=credits;
        this.description=description;
        this.expirationBoost=expirationBoost;
        this.expirationBoostType=expirationBoostType;
        this.extensible=extensible;
        this.trial=trial;
        this.id=id;
        this.maxConnections=maxConnections;
        this.name=name;
    }

    public long getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(long maxConnections) {
        this.maxConnections = maxConnections;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isTrial() {
        return trial;
    }

    public void setTrial(boolean trial) {
        this.trial = trial;
    }

    public boolean isCreatable() {
        return creatable;
    }

    public void setCreatable(boolean creatable) {
        this.creatable = creatable;
    }

    public boolean isExtensible() {
        return extensible;
    }

    public void setExtensible(boolean extensible) {
        this.extensible = extensible;
    }

    public List<String> getAllowedCategories() {
        return allowedCategories;
    }

    public void setAllowedCategories(List<String> allowedCategories) {
        this.allowedCategories = allowedCategories;
    }

    public int getExpirationBoost() {
        return expirationBoost;
    }

    public void setExpirationBoost(int expirationBoost) {
        this.expirationBoost = expirationBoost;
    }

    public String getExpirationBoostType() {
        return expirationBoostType;
    }

    public void setExpirationBoostType(String expirationBoostType) {
        this.expirationBoostType = expirationBoostType;
    }
}
