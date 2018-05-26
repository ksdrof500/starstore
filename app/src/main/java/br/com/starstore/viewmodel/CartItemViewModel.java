package br.com.starstore.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import javax.inject.Inject;

import br.com.starstore.StoreApp;
import br.com.starstore.model.Item;
import br.com.starstore.util.StringUtil;

/**
 * Created by filipenunes on 04/20/18.
 */
public class CartItemViewModel extends BaseObservable {

    public ObservableField<String> name;
    public ObservableField<String> image;
    public ObservableField<String> price;
    public ObservableField<String> seller;

    @Inject
    CartViewModel CartViewModel;

    private Item item;


    public CartItemViewModel() {
        StoreApp.getAppComponent().inject(this);
        name = new ObservableField<>();
        image = new ObservableField<>();
        price = new ObservableField<>();
        seller = new ObservableField<>();
    }

    public void setContents(Item item) {
        this.item = item;
        name.set(item.title);
        image.set(item.thumbnailHd);
        price.set(StringUtil.convertToDecimal(item.price));
        seller.set(item.seller);
    }

    public void addProduct() {
        CartViewModel.addProduct(item);
    }
}

