package br.com.starstore.viewmodel;

import android.os.Handler;

import br.com.starstore.common.interaction.CommonNavigation;
import br.com.starstore.ui.MainActivity;


/**
 * Created by filipenunes on 23/12/17.
 */

public class SplashViewModel {

    private CommonNavigation commonNavigation;

    public SplashViewModel(CommonNavigation commonNavigation, int timer) {
        this.commonNavigation = commonNavigation;
        startSplashTimeout(timer);
    }

    public void moveToNextTouched() {
        commonNavigation.moveForward(new MainActivity());
    }


    private void startSplashTimeout(int timer) {
        new Handler().postDelayed(this::moveToNextTouched, timer);
    }
}
