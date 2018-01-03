package br.com.starstore.api;


import java.util.List;

import br.com.starstore.model.Item;
import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by filipenunes on 2/9/16.
 */
public interface StoneApiService {

    @GET("products")
    Observable<List<Item>> getItems();


}
