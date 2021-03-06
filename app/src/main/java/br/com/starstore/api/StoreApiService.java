package br.com.starstore.api;


import br.com.starstore.model.Card;
import br.com.starstore.model.SimpleResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by filipenunes on 04/20/18.
 */
public interface StoreApiService {

    @POST("starstore")
    Observable<SimpleResponse> sendDataCard(@Body Card card);

}
