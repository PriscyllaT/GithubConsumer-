package br.com.githubprofile.services;

import java.util.List;

import br.com.githubprofile.models.Repo;
import br.com.githubprofile.models.User;
import br.com.githubprofile.models.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public interface GitHubService {

    @GET("/search/users")
    Call<UserList> getUsersByName(@Query("q") String name);

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);
}
