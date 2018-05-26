package br.com.starstore;

import android.app.Application;

import br.com.starstore.inject.components.AppComponent;
import br.com.starstore.inject.components.DaggerAppComponent;
import br.com.starstore.inject.modules.AppModule;
import br.com.starstore.inject.modules.NetworkModule;
import br.com.starstore.inject.modules.RoomModule;

public class StoreApp extends Application {

    private static AppComponent component;

    public static AppComponent getAppComponent() {
        return component;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .roomModule(new RoomModule(this))
                    .networkModule(new NetworkModule(getResources().getString(R.string.API_STONE_URL),
                            getResources().getString(R.string.API_STORE_URL)))
                    .build();
        }
    }

}
