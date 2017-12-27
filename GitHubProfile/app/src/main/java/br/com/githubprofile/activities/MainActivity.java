package br.com.githubprofile.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.githubprofile.R;
import br.com.githubprofile.adapter.UserAdapter;
import br.com.githubprofile.callback.GetUserCallback;
import br.com.githubprofile.callback.GetUsersListCallback;
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
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       ButterKnife.bind(this);

       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        GitHubService gitHubService = retrofit.create(GitHubService.class);

//     Call<User> callUser = gitHubService.getUser();
//     callUser.enqueue(new GetUserCallback());
       Call<UserList> call = gitHubService.getUsersByName("priscylla");
       call.enqueue(new Callback<UserList>() {
           @Override
           public void onResponse(Call<UserList> call, Response<UserList> response) {
               if (response.isSuccessful()){
                   users = response.body().getUserList();

                   //UserAdapter adapter = new UserAdapter(users, getApplicationContext());
                   populateAdapter(users);
                   Log.d("callback", users.toString());
                   Log.d("callback", String.valueOf(users.get(0)));
               }
           }

           @Override
           public void onFailure(Call<UserList> call, Throwable t) {
               Log.d("callback", "Failed");
           }
       });



    }

    private void populateAdapter(List<User> users){
        UserAdapter adapter = new UserAdapter(users, this);
        lv_usersList.setAdapter(adapter);
    }

    @OnItemClick(R.id.lv_usersList)
    void onItemClick(int position){
       if(users != null){
           User clickedUser = users.get(position);
           Intent intent = new Intent(this, UserDetailsActivity.class);
           intent.putExtra("login", users.get(position).getLogin());
           intent.putExtra("url",  users.get(position).getUrl());
           intent.putExtra("avatar_url", users.get(position).getAvatar());
           startActivity(intent);

       }



        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();

    }
}
