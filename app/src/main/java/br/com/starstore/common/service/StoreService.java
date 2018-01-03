package br.com.starstore.common.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.api.StoreApiService;
import br.com.starstore.model.Card;
import br.com.starstore.model.SimpleResponse;
import io.reactivex.Observable;


/**
 * Created by filipenunes on 4/19/17.
 */
@Singleton
public class StoreService {

    StoreApiService apiService;

    @Inject
    public StoreService(StoreApiService apiService) {
        this.apiService = apiService;
    }


    public Observable<SimpleResponse> sendDataCard(Card card) {
        return apiService.sendDataCard(card);
    }

}
