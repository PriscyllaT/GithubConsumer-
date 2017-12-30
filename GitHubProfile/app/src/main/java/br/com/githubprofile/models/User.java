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
    @SerializedName("html_url")
    @Expose
    String url;
    @SerializedName("name")
    @Expose
    String name;

    public User(String login, Long id, String avatar, String url, String name, String location) {
        this.login = login;
        this.id = id;
        this.avatar = avatar;
        this.url = url;
        this.name = name;
        this.location = location;
    }

    @SerializedName("location")
    @Expose
    String location;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
