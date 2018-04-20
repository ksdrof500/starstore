package br.com.starstore.common.service;

import com.google.gson.Gson;

/**
 * Created by filipenunes on 04/20/18.
 */

public class GenericJsonParser {
    public static <T> T jsonToObject(String stringToParse, Class<T> type) {
        return new Gson().fromJson(stringToParse, type);
    }

    public static <T> String objectToJson(T objectToParse) {
        Gson gson = new Gson();
        return gson.toJson(objectToParse);
    }

}
