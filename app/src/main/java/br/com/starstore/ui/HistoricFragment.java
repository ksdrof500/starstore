package br.com.starstore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.R;
import br.com.starstore.StoreApp;
import br.com.starstore.databinding.HistoricFragmentBinding;
import br.com.starstore.interaction.HistoricInteraction;
import br.com.starstore.model.HistoricCart;
import br.com.starstore.ui.adapter.ItemHistoricListAdapter;
import br.com.starstore.viewmodel.HistoricViewModel;

/**
 * Created by filipenunes on 12/26/17.
 */
@Singleton
public class HistoricFragment extends Fragment implements HistoricInteraction {

    @Inject
    HistoricViewModel viewModel;

    private ItemHistoricListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemHistoricListAdapter(getActivity());
        StoreApp.getAppComponent().inject(this);
        getActivity().setTitle(R.string.title_menu_history);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.historic_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HistoricFragmentBinding binding = HistoricFragmentBinding.bind(view);
        viewModel.setHistoricInteraction(this);
        binding.setViewModel(viewModel);
        bindAdapter(binding.itemList);
        viewModel.loadItems();
    }

    @Override
    public void displayItems(List<HistoricCart> items) {
        adapter.setItems(items);
    }

    private void bindAdapter(RecyclerView binding) {
        RecyclerView.LayoutManager horizontalManager = new LinearLayoutManager(getContext(),
                OrientationHelper.VERTICAL, false);

        binding.setLayoutManager(horizontalManager);
        binding.setAdapter(adapter);
    }
}
