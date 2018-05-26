package br.com.starstore.inject.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.starstore.StoreApp;
import br.com.starstore.util.AnalyticsManager;
import br.com.starstore.util.FontManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by filipenunes on 04/20/18.
 */
@Module
public class AppModule {

    private final StoreApp application;

    public AppModule(StoreApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public AnalyticsManager provideAnalyticsManager() {
        return new AnalyticsManager(application.getApplicationContext());
    }

    @Provides
    @Singleton
    public FontManager provideFontManager() {
        return new FontManager(application);
    }

}
