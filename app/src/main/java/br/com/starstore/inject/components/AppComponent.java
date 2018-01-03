package br.com.starstore.inject.components;

import javax.inject.Singleton;

import br.com.starstore.inject.modules.AppModule;
import br.com.starstore.inject.modules.NetworkModule;
import br.com.starstore.inject.modules.RoomModule;
import br.com.starstore.ui.CartFragment;
import br.com.starstore.ui.HistoricFragment;
import br.com.starstore.ui.HistoryFragment;
import br.com.starstore.util.AnalyticsManager;
import br.com.starstore.util.FontManager;
import br.com.starstore.viewmodel.CartItemViewModel;
import br.com.starstore.viewmodel.CartViewModel;
import dagger.Component;

/**
 * Created by filipenunes on 23/12/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RoomModule.class})
public interface  AppComponent {

    void inject(CartViewModel homeItemViewModel);
    void inject(CartFragment cartFragment);
    void inject(CartItemViewModel cartItemViewModel);
    void inject(HistoryFragment historyFragment);
    void inject(HistoricFragment historicFragment);

    FontManager getFontManager();

    AnalyticsManager getAnalyticsManager();
}
