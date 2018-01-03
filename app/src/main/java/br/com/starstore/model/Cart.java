package br.com.starstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filipenunes on 1/2/18.
 */

public class Cart {

    public int id;
    public List<Item> itemList;
    public int qtd;
    public double total;

    public Cart(List<Item> itemList, int qtd, double total) {
        this.itemList = itemList;
        this.qtd = qtd;
        this.total = total;
    }

    public Cart() {
        itemList = new ArrayList<>();
    }
}
