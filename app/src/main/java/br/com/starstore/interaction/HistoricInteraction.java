package br.com.starstore.interaction;

import java.util.List;

import br.com.starstore.model.HistoricCart;

/**
 * Created by filipenunes on 04/20/18.
 */

public interface HistoricInteraction {
    void displayItems(List<HistoricCart> items);
}
