package br.com.starstore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by filipenunes on 12/26/17.
 */

public class Item implements Serializable {

    @SerializedName("title")
    public String title;

    @SerializedName("price")
    public double price;

    @SerializedName("seller")
    public String seller;

    @SerializedName("thumbnailHd")
    public String thumbnailHd;

    public int qtd;
}
