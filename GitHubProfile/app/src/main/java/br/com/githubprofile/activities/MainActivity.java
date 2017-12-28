package br.com.githubprofile.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import br.com.githubprofile.R;
import br.com.githubprofile.adapter.UserAdapter;
import br.com.githubprofile.models.User;
import br.com.githubprofile.models.UserList;
import br.com.githubprofile.services.GitHubService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lv_usersList)
    ListView lv_usersList;
    @BindView(R.id.bt_search)
    Button bt_search;
    @BindView(R.id.et_search)
    EditText et_search;
    List<User> users;
    GitHubService gitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       ButterKnife.bind(this);

       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       gitHubService = retrofit.create(GitHubService.class);


    }

    private void populateUsersAdapter(List<User> users){
        UserAdapter adapter = new UserAdapter(users, this);
        lv_usersList.setAdapter(adapter);
    }

    @OnClick(R.id.bt_search)
    public void searchByName(){

        String text = et_search.getText().toString();
        et_search.setText("");

        if(!(text.equals(null)) && !(text.isEmpty()) ) {

            Call<UserList> call = gitHubService.getUsersByName(text);
            call.enqueue(new Callback<UserList>() {
                @Override
                public void onResponse(Call<UserList> call, Response<UserList> response) {
                    if (response.isSuccessful()) {
                        users = response.body().getUserList();
                        populateUsersAdapter(users);

                    }
                }

                @Override
                public void onFailure(Call<UserList> call, Throwable t) {
                    Log.d("callback", "Failed");
                }
            });
        }
    }

    @OnItemClick(R.id.lv_usersList)
    public void onItemClick(int position){
       if(users != null){
           et_search.setText("");
           Intent intent = new Intent(this, UserDetailsActivity.class);
           intent.putExtra("login", users.get(position).getLogin());
           intent.putExtra("url",  users.get(position).getUrl());
           intent.putExtra("avatar_url", users.get(position).getAvatar());
           startActivity(intent);

       }

    }
}
