package br.com.githubprofile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import br.com.githubprofile.R;
import br.com.githubprofile.adapter.RepoAdapter;
import br.com.githubprofile.adapter.UserAdapter;
import br.com.githubprofile.models.Repo;
import br.com.githubprofile.models.User;
import br.com.githubprofile.services.GitHubService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Priscylla-SSD-2016 on 27/12/2017.
 */

public class UserDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.lv_repo)
    ListView lv_repo;
    GitHubService gitHubService;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        String avatar = bundle.getString("avatar_url");
        String login = bundle.getString("login");

        if(!(avatar.equals(null)) && !(avatar.isEmpty()) && !(login.equals(null)) && !(login.isEmpty())){
            Picasso.with(this)
                    .load(avatar)
                    .into(iv_avatar);
            tv_username.setText(login);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl( "https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            gitHubService = retrofit.create(GitHubService.class);

            Call<List<Repo>> call = gitHubService.listRepos(login);

            call.enqueue(new Callback<List<Repo>>() {

                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    if (response.isSuccessful()) {
                        List<Repo> repositories = response.body();
                        populateReposAdapter(repositories);

                    }
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {
                    Log.d("callback", "Failed");
                }
            });

        }

    }

    private void populateReposAdapter(List<Repo> repos){
        RepoAdapter repoAdapter = new RepoAdapter(repos,this);
        lv_repo.setAdapter(repoAdapter);
    }


}
