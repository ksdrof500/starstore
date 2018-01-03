package br.com.starstore.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.starstore.R;
import br.com.starstore.StoreApp;


/**
 * Created by filipenunes on 2/3/16.
 */
public final class BindAdapters {

    @BindingAdapter("font")
    public static void setTypeFace(TextView view, String font) {
        FontManager fontManager = StoreApp.getAppComponent().getFontManager();
        view.setTypeface(fontManager.get(font));
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(view);
    }

    @BindingAdapter("visible")
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}
