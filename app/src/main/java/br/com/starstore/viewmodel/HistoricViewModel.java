package br.com.starstore.viewmodel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.common.data.HistoricDataSource;
import br.com.starstore.common.viewmodel.CommonViewModel;
import br.com.starstore.interaction.HistoricInteraction;
import br.com.starstore.model.HistoricCart;
import br.com.starstore.util.ListUtils;

/**
 * Created by filipenunes on 12/26/17.
 */
@Singleton
public class HistoricViewModel extends CommonViewModel {

    private HistoricInteraction historicInteraction;
    private HistoricDataSource historicDataSource;

    @Inject
    public HistoricViewModel(HistoricDataSource historicDataSource) {
        this.historicDataSource = historicDataSource;
    }

    public void setHistoricInteraction(HistoricInteraction historicInteraction) {
        this.historicInteraction = historicInteraction;
    }

    public void loadItems() {
        displayProgress();
        List<HistoricCart> historicCartList = historicDataSource.findAll();
        if (ListUtils.isEmpty(historicCartList)) {
            displayEmpty();
        } else {
            historicInteraction.displayItems(historicDataSource.findAll());
            displayContent();
        }
    }

}

