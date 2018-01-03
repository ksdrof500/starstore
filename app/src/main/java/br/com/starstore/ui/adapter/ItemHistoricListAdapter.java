package br.com.starstore.ui.adapter;

import android.app.Activity;

import br.com.starstore.R;
import br.com.starstore.databinding.ItemHistoricBinding;
import br.com.starstore.model.HistoricCart;
import br.com.starstore.util.BindingViewHolder;
import br.com.starstore.util.DataBindingAdapter;
import br.com.starstore.viewmodel.HistoricItemViewModel;

/**
 * Created by filipenunes on 26/12/2017.
 */
public class ItemHistoricListAdapter extends DataBindingAdapter<ItemHistoricBinding, HistoricCart> {

    public ItemHistoricListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected int getItemResource() {
        return R.layout.item_historic;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemHistoricBinding> holder, int position) {
        if (holder.binding.getViewModel() == null) {
            HistoricItemViewModel viewModel = new HistoricItemViewModel();
            holder.binding.setViewModel(viewModel);
        }
        holder.binding.getViewModel().setContents(items.get(position));
    }

}
