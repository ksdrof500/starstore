package br.com.starstore.ui.adapter;

import android.app.Activity;

import br.com.starstore.R;
import br.com.starstore.databinding.ItemHistoryBinding;
import br.com.starstore.model.Item;
import br.com.starstore.util.BindingViewHolder;
import br.com.starstore.util.DataBindingAdapter;
import br.com.starstore.viewmodel.HistoryItemViewModel;

/**
 * Created by filipenunes on 26/12/2017.
 */
public class ItemHistoryListAdapter extends DataBindingAdapter<ItemHistoryBinding, Item> {

    public ItemHistoryListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected int getItemResource() {
        return R.layout.item_history;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemHistoryBinding> holder, int position) {
        if (holder.binding.getViewModel() == null) {
            HistoryItemViewModel viewModel = new HistoryItemViewModel();
            holder.binding.setViewModel(viewModel);
        }
        holder.binding.getViewModel().setContents(items.get(position));
    }

}
