package br.com.starstore.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import br.com.starstore.interaction.LoginInteraction;
import br.com.starstore.ui.MainActivity;
import br.com.starstore.util.AlertUtil;


/**
 * Created by filipenunes on 04/20/18.
 */

public class LoginViewModel {

    public ObservableField<Boolean> showLogin = new ObservableField<>();
    private LoginInteraction loginInteraction;
    private FirebaseAuth firebaseAuth;

    public LoginViewModel(LoginInteraction loginInteraction, FirebaseAuth firebaseAuth) {
        this.loginInteraction = loginInteraction;
        this.firebaseAuth = firebaseAuth;
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, Activity activity) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        updateUI();
                    } else {
                        AlertUtil.showToatsError(activity);
                        updateUI();
                    }
                });
    }

    public void updateUI() {
        if (firebaseAuth.getCurrentUser() != null) {
            showLogin.set(false);
            loginInteraction.moveForward(new MainActivity());
        } else {
            showLogin.set(true);
        }
    }

    public void loginClick() {
        loginInteraction.login();
    }
}
