package br.com.starstore.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.R;
import br.com.starstore.StoreApp;
import br.com.starstore.common.service.CartService;
import br.com.starstore.common.viewmodel.CommonViewModel;
import br.com.starstore.interaction.CartInteraction;
import br.com.starstore.model.Cart;
import br.com.starstore.model.Item;
import br.com.starstore.ui.HistoryFragment;
import br.com.starstore.util.ListUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by filipenunes on 12/26/17.
 */
@Singleton
public class CartViewModel extends CommonViewModel {

    private static final int INIT_QTD = 0;
    private static final int NOT_EXIST_ITEM = -1;
    private CartInteraction cartInteraction;
    private Observable<Item> itemsListObservable;
    private Item item;
    private Cart cart;
    private CartService cartService;
    public ObservableField<Integer> qtd = new ObservableField<>(INIT_QTD);
    public ObservableField<String> totalProductCart = new ObservableField<>("0");

    @Inject
    public CartViewModel(CartService cartService) {
        this.cartService = cartService;
        StoreApp.getAppComponent().inject(this);
    }

    public void setCartInteraction(CartInteraction cartInteraction) {
        this.cartInteraction = cartInteraction;
    }

    public void loadItems() {
        cart = cartService.retrieveLocalSaved();
        totalProductCart.set(String.valueOf(cart.itemList.size()));

        itemsListObservable = cartService.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::fromIterable);


        itemsListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(response -> {
                    if (ListUtils.isEmpty(response)) {
                        displayEmpty();
                    } else {
                        displayContent();
                        checkCart(response);
                        cartInteraction.displayItems(response);
                    }
                }, this::displayError);
    }

    private void checkCart(List<Item> responseList) {
        for (Item item : cart.itemList) {
            for (Item response : responseList) {
                if (response.title.equals(item.title)) {
                    response.qtd = item.qtd;
                }
            }
        }
    }

    public void filter(String query) {
        StoreApp.getAppComponent().getAnalyticsManager().trackSearchAction(query);
        displayProgress();

        itemsListObservable.filter(item -> TextUtils.isEmpty(query) || match(query, item))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(response -> {
                    if (ListUtils.isEmpty(response)) {
                        displayEmpty();
                    } else {
                        cartInteraction.displayItems(response);
                        displayContent();
                    }
                }, this::displayError);
    }

    private boolean match(String query, Item item) {
        return item.title.toLowerCase().contains(query.toLowerCase());
    }

    public void clear() {
        filter(null);
    }

    public void addProduct(Item item) {
        this.item = item;
        qtd.set(item.qtd);
        cartInteraction.stateBottom();
    }

    public void addQtd() {
        qtd.set(qtd.get() + 1);
    }

    public void removeQtd() {
        if (qtd.get() != INIT_QTD) {
            qtd.set(qtd.get() - 1);
        }
    }

    public void saveProduct() {
        int index = cart.itemList.indexOf(item);
        if (index == NOT_EXIST_ITEM) {
            if (qtd.get() != INIT_QTD) {
                item.qtd = qtd.get();
                cart.itemList.add(item);
            }
        } else {
            if (qtd.get() == INIT_QTD) {
                cart.itemList.remove(item);
            } else {
                item.qtd = qtd.get();
                cart.itemList.set(index, item);
            }
        }
        totalProductCart.set(String.valueOf(cart.itemList.size()));
        cartInteraction.stateBottom();
        StoreApp.getAppComponent().getAnalyticsManager().trackAddAction(item.title);
    }

    public void goHistory() {
        if (ListUtils.isNotEmpty(cart.itemList)) {
            calcCart();
            cartService.saveLocal(cart);
            cartInteraction.moveForward(new HistoryFragment());
        } else {
            cartInteraction.showAlert(R.string.error_empty);
        }
    }

    private void calcCart() {
        cart.qtd = INIT_QTD;
        cart.total = INIT_QTD;
        for (Item item : cart.itemList) {
            double valTotal = item.qtd * item.price;
            cart.qtd = cart.qtd + item.qtd;
            cart.total = cart.total + valTotal;
        }
    }
}

