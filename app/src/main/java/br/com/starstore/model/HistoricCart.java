package br.com.starstore.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import br.com.starstore.util.StringUtil;


/**
 * Created by filipenunes on 1/2/18.
 */
@Entity(tableName = "historic")
public class HistoricCart {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private  int id;

    @ColumnInfo(name = "total")
    private double total;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "cardNumber")
    private String cardNumber;

    @ColumnInfo(name = "nameCard")
    private String nameCard;

    public HistoricCart() {
    }

    @Ignore
    public HistoricCart(double total, String cardNumber, String nameCard) {
        this.total = total;
        this.date = StringUtil.convertToDate(new Date());
        this.cardNumber = StringUtil.pickFourEndDigitsCard(cardNumber);
        this.nameCard = nameCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }
}
