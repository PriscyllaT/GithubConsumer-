package br.com.githubprofile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class User {

    @SerializedName("login")
    @Expose
    String login;
    @SerializedName("id")
    @Expose
    Long id;
    @SerializedName("avatar_url")
    @Expose
    String avatar;
    @SerializedName("url")
    @Expose
    String url;

    public User(String login, String name, Long id, String avatar, String url) {
        this.login = login;
        this.id = id;
        this.avatar = avatar;
        this.url = url;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
