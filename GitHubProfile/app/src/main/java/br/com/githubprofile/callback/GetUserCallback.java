package br.com.githubprofile.callback;


import java.util.List;

import br.com.githubprofile.activities.MainActivity;
import br.com.githubprofile.activities.UserDetailsActivity;
import br.com.githubprofile.models.User;
import br.com.githubprofile.models.UserList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class GetUserCallback implements Callback<User> {

    private UserDetailsActivity activity;

    public GetUserCallback(UserDetailsActivity activity){
        this.activity = activity;
    }
    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
            User user = response.body();
            if(user != null){
               activity.populateUserInfo(user);
            }else {
                activity.warningNoUser();
            }

        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        activity.notPossibleToConnect();
    }
}
