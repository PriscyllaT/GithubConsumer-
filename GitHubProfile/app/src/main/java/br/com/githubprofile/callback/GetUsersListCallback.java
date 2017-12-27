package br.com.githubprofile.callback;



import android.util.Log;

import java.util.List;

import br.com.githubprofile.models.User;
import br.com.githubprofile.models.UserList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class GetUsersListCallback implements Callback<UserList> {
    @Override
    public void onResponse(Call<UserList> call, Response<UserList> response) {
        if (response.isSuccessful()){
            UserList users = response.body();
            Log.d("callback", users.toString());
            Log.d("callback", String.valueOf(users.getUserList()));
        }
    }

    @Override
    public void onFailure(Call<UserList> call, Throwable t) {
        Log.d("callback", "Failed");
    }
}
