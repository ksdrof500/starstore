package br.com.starstore.ui.adapter;

import android.app.Activity;

import br.com.starstore.R;
import br.com.starstore.databinding.ItemCartBinding;
import br.com.starstore.model.Item;
import br.com.starstore.util.BindingViewHolder;
import br.com.starstore.util.DataBindingAdapter;
import br.com.starstore.viewmodel.CartItemViewModel;

/**
 * Created by filipenunes on 26/12/2017.
 */
public class ItemListAdapter extends DataBindingAdapter<ItemCartBinding, Item> {

    public ItemListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected int getItemResource() {
        return R.layout.item_cart;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemCartBinding> holder, int position) {
        if (holder.binding.getViewModel() == null) {
            CartItemViewModel viewModel = new CartItemViewModel();
            holder.binding.setViewModel(viewModel);
        }
        holder.binding.getViewModel().setContents(items.get(position));
    }
}
