package br.com.starstore.common.data;

import java.util.List;

import br.com.starstore.model.HistoricCart;

/**
 * Created by filipenunes on 04/20/18.
 */

public interface HistoricRepository {
    List<HistoricCart> findAll();
    long insert(HistoricCart historicCart);
}
