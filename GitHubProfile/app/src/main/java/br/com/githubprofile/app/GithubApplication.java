package br.com.githubprofile.app;

import android.app.Application;
import br.com.githubprofile.component.DaggerGithubComponent;
import br.com.githubprofile.component.GithubComponent;
import br.com.githubprofile.module.GithubModule;

/**
 * Created by Priscylla-SSD-2016 on 28/12/2017.
 */

public class GithubApplication extends Application {
    private GithubComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerGithubComponent.builder().githubModule(new GithubModule(this)).build();
    }

    public GithubComponent getComponent() {
        return component;
    }
}
