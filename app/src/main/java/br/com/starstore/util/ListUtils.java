package br.com.starstore.util;

import java.util.Collection;

/**
 * Created by filipenunes on 12/26/17.
 */

public class ListUtils {

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
