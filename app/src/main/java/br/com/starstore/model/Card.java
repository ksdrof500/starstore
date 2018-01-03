package br.com.starstore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by filipenunes on 1/3/18.
 */

public class Card {

    @SerializedName("card_number")
    private String cardNumber;

    @SerializedName("value")
    private double value;

    @SerializedName("cvv")
    private int cvv;

    @SerializedName("card_holder_name")
    private String name;

    @SerializedName("exp_date")
    private String date;

    public Card(String cardNumber, double value, String cvv, String name, String date) {
        this.cardNumber = cardNumber;
        this.value = value;
        this.cvv = Integer.parseInt(cvv);
        this.name = name;
        this.date = date;
    }
}
