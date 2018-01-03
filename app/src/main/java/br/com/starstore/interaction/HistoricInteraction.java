package br.com.starstore.interaction;

import java.util.List;

import br.com.starstore.model.HistoricCart;

/**
 * Created by filipenunes on 26/12/17.
 */

public interface HistoricInteraction {
    void displayItems(List<HistoricCart> items);
}
