package br.com.starstore.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.starstore.R;
import br.com.starstore.common.interaction.CommonNavigation;
import br.com.starstore.viewmodel.SplashViewModel;

/**
 * Created by filipenunes on 26/12/17.
 */

public class SplashActivity extends AppCompatActivity implements CommonNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.splash_activity);
        new SplashViewModel(this, getResources().getInteger(R.integer.splash_time_out));
    }

    public void moveForward(Activity activity) {
        startActivity(new Intent(SplashActivity.this, activity.getClass()));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
