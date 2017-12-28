package br.com.githubprofile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import br.com.githubprofile.R;
import butterknife.BindView;

public class SplashScreenActivity extends AppCompatActivity {
    //duração em milisegundos na splash screen
    private static final int DURACAO = 1000;
    private Timer timer;
    @BindView(R.id.tv_splash)
    TextView tv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setClass(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DURACAO);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


}
