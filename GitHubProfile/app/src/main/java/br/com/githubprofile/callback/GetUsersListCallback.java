package br.com.githubprofile.callback;



import android.util.Log;

import java.util.List;

import br.com.githubprofile.activities.MainActivity;
import br.com.githubprofile.models.User;
import br.com.githubprofile.models.UserList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class GetUsersListCallback implements Callback<UserList> {

    private MainActivity activity;

    public GetUsersListCallback(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public void onResponse(Call<UserList> call, Response<UserList> response) {
        if (response.isSuccessful()) {
            List<User> users = response.body().getUserList();
            if(users.size() == 0){
                activity.warningNoUser();
            }else {
                activity.populateUsersAdapter(users);
            }



        }
    }

    @Override
    public void onFailure(Call<UserList> call, Throwable t) {
        Log.d("callback", "Failed");
    }
}
