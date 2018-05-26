package br.com.starstore.viewmodel;

import br.com.starstore.interaction.AboutInteraction;


/**
 * Created by filipenunes on 04/20/18.
 */

public class AboutViewModel {

    AboutInteraction aboutInteraction;

    public AboutViewModel(AboutInteraction aboutInteraction) {
        this.aboutInteraction = aboutInteraction;
    }

    public void sendEmail() {
        aboutInteraction.sendEmail();
    }

}
