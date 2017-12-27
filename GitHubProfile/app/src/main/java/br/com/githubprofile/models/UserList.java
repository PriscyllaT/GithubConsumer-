package br.com.githubprofile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class UserList {
    @SerializedName("items")
    @Expose
    private List<User> userList;

    public List<User> getUserList(){
        return userList;
    }
    public void setUserList(List<User>userList){
        this.userList = userList;
    }
}
