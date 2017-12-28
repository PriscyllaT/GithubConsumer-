package br.com.githubprofile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Priscylla-SSD-2016 on 28/12/2017.
 */

public class Repo {

    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("id")
    @Expose
    Long id;
    @SerializedName("language")
    @Expose
    String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

}
