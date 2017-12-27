package br.com.githubprofile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.githubprofile.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.lv_repo)
    ListView lv_repo;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        Picasso.with(this)
                .load(bundle.getString("avatar_url"))
                .into(iv_avatar);
        tv_username.setText(bundle.getString("login"));



    }




}
