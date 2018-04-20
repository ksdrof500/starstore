package br.com.starstore.util;

import java.util.Collection;

/**
 * Created by filipenunes on 04/20/18.
 */

public class ListUtils {

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
