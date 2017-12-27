package br.com.githubprofile.callback;



import android.util.Log;

import br.com.githubprofile.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class GetUserCallback implements Callback<User> {
    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()){
            User user = response.body();
            Log.d("callback", user.toString());
            Log.d("callback", user.getLogin());
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {

    }
}
