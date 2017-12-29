package br.com.githubprofile.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import br.com.githubprofile.services.GitHubService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Priscylla-SSD-2016 on 28/12/2017.
 */
@Module
public class GithubModule {

    private Application app;

    public GithubModule(Application app) {
        this.app = app;
    }

    @Provides
    public GitHubService getChatService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService chatService = retrofit.create(GitHubService.class);

        return chatService;
    }

    @Provides
    public Picasso picasso() {
        Picasso picasso = new Picasso.Builder(app).build();
        return picasso;
    }
}
