package br.com.githubprofile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import br.com.githubprofile.R;
import br.com.githubprofile.models.Repo;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Priscylla-SSD-2016 on 27/12/2017.
 */

public class RepoAdapter extends BaseAdapter {

    private List<Repo> repoList;
    private Activity activity;
    @BindView(R.id.tv_repoName)
    TextView tv_name;
    @BindView(R.id.tv_langage)
    TextView tv_language;

    public RepoAdapter(List<Repo> repoList, Activity activity) {
        this.repoList = repoList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return repoList.size();
    }

    @Override
    public Object getItem(int position) {
        return repoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = activity.getLayoutInflater().inflate(R.layout.repo_row_layout, parent, false);

        ButterKnife.bind(this, row);

        Repo repo = repoList.get(position);

        tv_name.setText(repo.getName());
        tv_language.setText(repo.getLanguage());

        return row;
    }
}
