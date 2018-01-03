package br.com.starstore.inject.modules;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import br.com.starstore.api.StoneApiService;
import br.com.starstore.api.StoreApiService;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by filipenunes on 23/12/17.
 */
@Module
public class NetworkModule {
    private final String stoneUrl;
    private final String storeUrl;

    public NetworkModule(String stoneUrl, String storeUrl) {
        this.stoneUrl = stoneUrl;
        this.storeUrl = storeUrl;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>)
                (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));

        return gsonBuilder.create();
    }

    @Provides
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);
        return client.build();
    }

    @Provides
    @Singleton
    public StoneApiService provideStoreApi(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(stoneUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(StoneApiService.class);

    }

    @Provides
    @Singleton
    public StoreApiService provideStoneApi(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(storeUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(StoreApiService.class);
    }

}
