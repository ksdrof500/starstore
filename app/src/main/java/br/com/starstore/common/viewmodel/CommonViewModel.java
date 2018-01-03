package br.com.starstore.common.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

/**
 * Created by filipenunes on 26/12/17.
 */
public abstract class CommonViewModel extends BaseObservable {

    public ObservableBoolean showProgress = new ObservableBoolean(true);
    public ObservableBoolean showContent = new ObservableBoolean();
    public ObservableBoolean showError = new ObservableBoolean();
    public ObservableBoolean showEmpty = new ObservableBoolean();

    protected void displayContent() {
        showContent.set(true);
        showProgress.set(false);
        showEmpty.set(false);
    }

    protected void displayProgress() {
        showContent.set(false);
        showEmpty.set(false);
        showProgress.set(true);
    }

    protected void displayError(Throwable throwable) {
        showProgress.set(false);
        showEmpty.set(false);
        showError.set(true);
    }

    protected void displayEmpty() {
        showContent.set(false);
        showProgress.set(false);
        showEmpty.set(true);
    }

}
