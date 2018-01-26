package br.com.starstore.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.R;
import br.com.starstore.common.data.HistoricDataSource;
import br.com.starstore.common.service.CartService;
import br.com.starstore.common.service.StoreService;
import br.com.starstore.interaction.HistoryInteraction;
import br.com.starstore.model.Card;
import br.com.starstore.model.Cart;
import br.com.starstore.model.HistoricCart;
import br.com.starstore.util.StringUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by filipenunes on 12/26/17.
 */
@Singleton
public class HistoryViewModel {

    private HistoryInteraction historyInteraction;
    public ObservableField<Integer> qtd = new ObservableField<>(0);
    public ObservableField<String> totalCart = new ObservableField<>();
    private CartService cartService;
    private StoreService storeService;
    private HistoricDataSource historicDataSource;
    private Cart cart;

    @Inject
    public HistoryViewModel(CartService cartService, HistoricDataSource historicDataSource,
                            StoreService storeService) {
        this.cartService = cartService;
        this.historicDataSource = historicDataSource;
        this.storeService = storeService;
    }

    public void setHistoryInteraction(HistoryInteraction historyInteraction) {
        this.historyInteraction = historyInteraction;
    }

    public void loadItems() {
        cart = cartService.retrieveLocalSaved();
        qtd.set(cart.qtd);
        totalCart.set(StringUtil.convertToDecimal(cart.total));
        historyInteraction.displayItems(cart.itemList);
    }

    public void finish() {
        historyInteraction.showDialogCard();
    }

    public void finishCart(String name, String cardNumber, String cvv, String date) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(cardNumber)
                && !TextUtils.isEmpty(cvv) && !TextUtils.isEmpty(date)) {

            storeService.sendDataCard(new Card(cardNumber, cart.total, cvv, name, date))
                    .subscribeOn(Schedulers.io())
                    .map(simpleResponse -> simpleResponse.message)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String response) {
                            historyInteraction.showAlert(response);
                            historicDataSource.insert(new HistoricCart(cart.total, cardNumber, name));
                            cartService.clear();
                            historyInteraction.moveToHistoric();
                        }

                        @Override
                        public void onError(Throwable e) {
                            historyInteraction.showAlert(R.string.error_default);
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else {
            historyInteraction.showAlert(R.string.error_default);
        }
    }
}

