package br.com.githubprofile.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;
import br.com.githubprofile.R;
import br.com.githubprofile.adapter.RepoAdapter;
import br.com.githubprofile.app.GithubApplication;
import br.com.githubprofile.callback.GetRepositoriesCallback;
import br.com.githubprofile.callback.GetUserCallback;
import br.com.githubprofile.component.GithubComponent;
import br.com.githubprofile.models.Repo;
import br.com.githubprofile.models.User;
import br.com.githubprofile.services.GitHubService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Priscylla-SSD-2016 on 27/12/2017.
 * Exibe detalhes do usário do github: nome, foto e repositórios.
 */

public class UserDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.lv_repo)
    ListView lv_repo;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_location)
    TextView tv_location;
    Bundle bundle;

    @Inject
    GitHubService gitHubService;
    private GithubComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);
        GithubApplication application = (GithubApplication) getApplication();
        component = application.getComponent();
        component.inject(this);

        //recupera dados do usuário pelo getExtras
        bundle = getIntent().getExtras();
        String avatar = bundle.getString("avatar_url");
        String login = bundle.getString("login");

        if(!(avatar.equals(null)) && !(avatar.isEmpty()) && !(login.equals(null)) && !(login.isEmpty())){
            Picasso.with(this)
                    .load(avatar)
                    .into(iv_avatar);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl( "https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            gitHubService = retrofit.create(GitHubService.class);
            getRepos(login);
            getUserInfo(login);

        }

    }

    public void getRepos(String login){
        Call<List<Repo>> call = gitHubService.listRepos(login);
        call.enqueue(new GetRepositoriesCallback(this));
    }

    public void getUserInfo(String login){
       Call<User> call = gitHubService.getUser(login);
       call.enqueue(new GetUserCallback(this));
    }

    public void populateReposAdapter(List<Repo> repos){
        RepoAdapter repoAdapter = new RepoAdapter(repos,this);
        lv_repo.setAdapter(repoAdapter);
    }

   public void populateUserInfo(User user){
        if(user !=null){
            tv_username.setText(user.getLogin());
            tv_name.setText(user.getName());
            tv_location.setText(user.getLocation());
        }


   }

    @OnItemClick(R.id.lv_repo)
    public void showRepoIntent(int position){
        Repo selectedRepo = (Repo) lv_repo.getItemAtPosition(position);
        String repoName = selectedRepo.getName();
        String link = getIntent().getExtras().getString("url");
        if(!link.equals(null) && !link.isEmpty() && !repoName.equals(null) && !repoName.isEmpty()){
            String completedLink = link + "/" + repoName;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(completedLink));
            startActivity(i);
        }

    }

    public void warningNoUser(){
        Toast.makeText(this, R.string.warningNoUser, Toast.LENGTH_SHORT).show();
    }

    public void notPossibleToConnect(){
        Toast.makeText(this, R.string.notPossibleToConnect, Toast.LENGTH_SHORT).show();
    }
}
