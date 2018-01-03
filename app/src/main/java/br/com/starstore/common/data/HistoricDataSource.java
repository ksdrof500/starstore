package br.com.starstore.common.data;

import java.util.List;

import javax.inject.Inject;

import br.com.starstore.model.HistoricCart;

/**
 * Created by filipenunes on 1/2/18.
 */

public class HistoricDataSource implements HistoricRepository {

    private HistoricDao historicDao;

    @Inject
    public HistoricDataSource(HistoricDao historicDao) {
        this.historicDao = historicDao;
    }


    @Override
    public List<HistoricCart> findAll() {
        return historicDao.findAll();
    }

    @Override
    public long insert(HistoricCart historicCart) {
        return historicDao.insert(historicCart);
    }
}