package kr.co.mashup.moamoa.ui.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.co.mashup.moamoa.ui.init.MoaNoGroupActivity;
import kr.co.mashup.moamoa.ui.login.KakaoLoginActivity;
import kr.co.mashup.moamoa.R;
import kr.co.mashup.moamoa.ui.main.MoaMainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moa_splash);

        findViewById(R.id.splash).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MoaMainActivity.class));
                finish();
            }
        }, 1500);
    }
}
