package br.com.githubprofile.callback;

import java.util.List;
import br.com.githubprofile.activities.UserDetailsActivity;
import br.com.githubprofile.models.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class GetRepositoriesCallback implements Callback<List<Repo>> {

    UserDetailsActivity userDetailsActivity;

    public GetRepositoriesCallback(UserDetailsActivity userDetailsActivity) {
        this.userDetailsActivity = userDetailsActivity;
    }


    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        if (response.isSuccessful()) {
            List<Repo> repositories = response.body();
            userDetailsActivity.populateReposAdapter(repositories);

        }
    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
        userDetailsActivity.notPossibleToConnect();
    }
}
