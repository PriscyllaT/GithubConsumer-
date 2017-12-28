package br.com.githubprofile.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import javax.inject.Inject;
import br.com.githubprofile.R;
import br.com.githubprofile.adapter.UserAdapter;
import br.com.githubprofile.app.GithubApplication;
import br.com.githubprofile.callback.GetUsersListCallback;
import br.com.githubprofile.component.GithubComponent;
import br.com.githubprofile.models.User;
import br.com.githubprofile.models.UserList;
import br.com.githubprofile.services.GitHubService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 * Exibe lista de usários do github através da palavra digitada.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lv_usersList)
    ListView lv_usersList;
    @BindView(R.id.bt_search)
    Button bt_search;
    @BindView(R.id.et_search)
    EditText et_search;

    @Inject
    GitHubService gitHubService;
    private GithubComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GithubApplication application = (GithubApplication) getApplication();
        component = application.getComponent();
        component.inject(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubService = retrofit.create(GitHubService.class);

    }

    public void populateUsersAdapter(List<User> users) {
        UserAdapter adapter = new UserAdapter(users, this);
        lv_usersList.setAdapter(adapter);
    }

    @OnClick(R.id.bt_search)
    public void searchByName() {

        String text = et_search.getText().toString();
        et_search.setText("");

        if (!(text.equals(null)) && !(text.isEmpty())) {

            Call<UserList> call = gitHubService.getUsersByName(text);
            call.enqueue(new GetUsersListCallback(this));

        }
    }

    @OnItemClick(R.id.lv_usersList)
    public void onItemClick(int position) {
        User selectedUser = (User) lv_usersList.getItemAtPosition(position);
        et_search.setText("");
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("login", selectedUser.getLogin());
        intent.putExtra("url", selectedUser.getUrl());
        intent.putExtra("avatar_url", selectedUser.getAvatar());
        startActivity(intent);

    }
}
