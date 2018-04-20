package br.com.starstore.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import br.com.starstore.model.HistoricCart;
import br.com.starstore.util.StringUtil;

/**
 * Created by filipenunes on 04/20/18.
 */
public class HistoricItemViewModel extends BaseObservable {

    public ObservableField<String> value;
    public ObservableField<String> name;
    public ObservableField<String> date;
    public ObservableField<String> numberCard;

    public HistoricItemViewModel() {
        name = new ObservableField<>();
        value = new ObservableField<>();
        date = new ObservableField<>();
        numberCard = new ObservableField<>();
    }

    public void setContents(HistoricCart cart) {
        name.set(cart.getNameCard());
        date.set(String.valueOf(cart.getDate()));
        value.set(StringUtil.convertToDecimal(cart.getTotal()));
        numberCard.set(cart.getCardNumber());
    }

}

