package br.com.githubprofile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.githubprofile.R;
import br.com.githubprofile.models.User;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Priscylla-SSD-2016 on 26/12/2017.
 */

public class UserAdapter extends BaseAdapter {

    private List<User> userList;
    private Activity activity;
    private Context context;
    @BindView(R.id.username)
    TextView name;
    @BindView(R.id.profileImage)
    ImageView image;

    public UserAdapter(List<User> userList, Activity activity) {
        this.userList = userList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = activity.getLayoutInflater().inflate(R.layout.user_row_layout, parent, false);

        ButterKnife.bind(this, row);

        User user = userList.get(position);

        name.setText(user.getLogin());

        Picasso.with(activity)
                .load(user.getAvatar())
                .into(image);

        return row;
    }
}
