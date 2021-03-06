package br.com.starstore.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by filipenunes on 04/20/18.
 */

public class StringUtil {

    public static final String NUMBER_CARD_MASK = "####-####-####-####";
    public static final String DATE_MASK = "##/##";
    private static final DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private static final int FINAL_FOUR = 4;

    public static String convertToDecimal(double value) {
        return decimalFormat.format(value);
    }

    public static String convertToDate(Date date) {
        return dateFormat.format(date);
    }

    public static String pickFourEndDigitsCard(String cardNumber) {
        return cardNumber.substring(cardNumber.length() - FINAL_FOUR);
    }


}
