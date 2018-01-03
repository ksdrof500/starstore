package br.com.starstore.common.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.R;
import br.com.starstore.api.StoneApiService;
import br.com.starstore.model.Cart;
import br.com.starstore.model.Item;
import io.reactivex.Observable;


/**
 * Created by filipenunes on 4/19/17.
 */
@Singleton
public class CartService {

    private static final String CART = "CART";
    StoneApiService stoneApiService;
    Context context;

    @Inject
    public CartService(StoneApiService stoneApiService, Context context) {
        this.stoneApiService = stoneApiService;
        this.context = context;
    }

    public void saveLocal(Cart cart) {
        getSharedPreferences().edit().putString(CART, GenericJsonParser.objectToJson(cart)).apply();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(
                context.getString(R.string.STONE_PREFS), Context.MODE_PRIVATE);
    }

    public Cart retrieveLocalSaved() {
        try {
            String userAccountGson = getSharedPreferences().getString(CART, null);
            if (userAccountGson != null)
                return GenericJsonParser.jsonToObject(userAccountGson, Cart.class);
            else return new Cart();
        } catch (Exception e) {
            return null;
        }
    }

    public void clear() {
        getSharedPreferences().edit().clear().apply();
    }


    public Observable<List<Item>> getItems() {
        return stoneApiService.getItems();
    }

}
