package br.com.starstore.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import br.com.starstore.model.Item;
import br.com.starstore.util.StringUtil;

/**
 * Created by filipenunes on 04/20/18.
 */
public class HistoryItemViewModel extends BaseObservable {

    public ObservableField<String> name;
    public ObservableField<String> image;
    public ObservableField<String> price;
    public ObservableField<Integer> qtd;

    public HistoryItemViewModel() {
        name = new ObservableField<>();
        image = new ObservableField<>();
        price = new ObservableField<>();
        qtd = new ObservableField<>();
    }

    public void setContents(Item item) {
        name.set(item.title);
        image.set(item.thumbnailHd);
        price.set(StringUtil.convertToDecimal(item.price));
        qtd.set(item.qtd);
    }

}

