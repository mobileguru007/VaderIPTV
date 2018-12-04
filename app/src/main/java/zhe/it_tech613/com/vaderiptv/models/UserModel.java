package zhe.it_tech613.com.vaderiptv.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable{
    @SerializedName("id")
    private long id;
    @SerializedName("member_id")
    private long member_id;
    @SerializedName("force_server_id")
    private int force_server_id;
    @SerializedName("is_admin")
    private boolean is_admin;
    @SerializedName("is_super_admin")
    private boolean is_super_admin;
    @SerializedName("is_restreamer")
    private boolean is_restreamer;
    @SerializedName("is_trial")
    private boolean is_trial;
    @SerializedName("admin_enabled")
    private boolean admin_enabled;
    @SerializedName("enabled")
    private boolean enabled;
    @SerializedName("roles")
    private List<String> roles;
    @SerializedName("username")
    private String username;
    public UserModel(long id,long member_id,
                     int force_server_id,boolean is_admin,
                     boolean is_super_admin, boolean is_restreamer,
                     boolean is_trial,boolean admin_enabled,
                     boolean enabled,List<String> roles,
                     String username){
        this.id=id;
        this.member_id=member_id;
        this.force_server_id=force_server_id;
        this.is_admin=is_admin;
        this.is_super_admin=is_super_admin;
        this.is_restreamer=is_restreamer;
        this.is_trial=is_trial;
        this.admin_enabled=admin_enabled;
        this.enabled=enabled;
        this.roles=roles;
        this.username=username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public int getForce_server_id() {
        return force_server_id;
    }

    public void setForce_server_id(int force_server_id) {
        this.force_server_id = force_server_id;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isIs_super_admin() {
        return is_super_admin;
    }

    public void setIs_super_admin(boolean is_super_admin) {
        this.is_super_admin = is_super_admin;
    }

    public boolean isIs_restreamer() {
        return is_restreamer;
    }

    public void setIs_restreamer(boolean is_restreamer) {
        this.is_restreamer = is_restreamer;
    }

    public boolean isIs_trial() {
        return is_trial;
    }

    public void setIs_trial(boolean is_trial) {
        this.is_trial = is_trial;
    }

    public boolean isAdmin_enabled() {
        return admin_enabled;
    }

    public void setAdmin_enabled(boolean admin_enabled) {
        this.admin_enabled = admin_enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
