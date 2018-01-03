package br.com.starstore.interaction;


import java.util.List;

import br.com.starstore.common.interaction.CommonNavigationFragment;
import br.com.starstore.common.interaction.CommonNotification;
import br.com.starstore.model.Item;

/**
 * Created by filipenunes on 26/12/17.
 */

public interface CartInteraction extends CommonNotification, CommonNavigationFragment {
    void displayItems(List<Item> items);
    void stateBottom();
}
