package br.com.starstore.viewmodel;

import android.os.Handler;

import br.com.starstore.common.interaction.CommonNavigation;
import br.com.starstore.ui.LoginActivity;


/**
 * Created by filipenunes on 04/20/18.
 */

public class SplashViewModel {

    private CommonNavigation commonNavigation;

    public SplashViewModel(CommonNavigation commonNavigation, int timer) {
        this.commonNavigation = commonNavigation;
        startSplashTimeout(timer);
    }

    public void moveToNextTouched() {
        commonNavigation.moveForward(new LoginActivity());
    }


    private void startSplashTimeout(int timer) {
        new Handler().postDelayed(this::moveToNextTouched, timer);
    }
}
