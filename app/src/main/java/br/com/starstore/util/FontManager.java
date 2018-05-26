package br.com.starstore.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

/**
 * Created by filipenunes on 04/20/18.
 */
public class FontManager {

    public static final String BOLD = "bold";
    public static final String REGULAR = "regular";
    public static final String SEMI_BOLD = "semi_bold";
    private Context context;
    private HashMap<String, Typeface> typefaces;
    public FontManager(Context context) {
        this.context = context;
        typefaces = new HashMap<>();
    }

    public Typeface get(@FontType String type) {

        Typeface typeface = typefaces.get(type);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), type + ".ttf");
            typefaces.put(type, typeface);
        }

        return typeface;
    }

    @StringDef({BOLD, REGULAR, SEMI_BOLD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FontType {
    }
}
