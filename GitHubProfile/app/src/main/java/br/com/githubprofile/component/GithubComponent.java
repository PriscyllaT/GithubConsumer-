package br.com.githubprofile.component;

import br.com.githubprofile.activities.MainActivity;
import br.com.githubprofile.activities.UserDetailsActivity;
import br.com.githubprofile.module.GithubModule;
import dagger.Component;

/**
 * Created by Priscylla-SSD-2016 on 28/12/2017.
 */

@Component(modules = GithubModule.class)
public interface GithubComponent {

    void inject(MainActivity mainActivity);
    void inject(UserDetailsActivity userDetailsActivity);

}
