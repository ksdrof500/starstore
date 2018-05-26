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
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.R;
import br.com.starstore.StoreApp;
import br.com.starstore.databinding.HistoryFragmentBinding;
import br.com.starstore.interaction.HistoryInteraction;
import br.com.starstore.model.Item;
import br.com.starstore.ui.adapter.ItemHistoryListAdapter;
import br.com.starstore.util.AlertUtil;
import br.com.starstore.util.StringUtil;
import br.com.starstore.util.textwatcher.FreeFormatWatcher;
import br.com.starstore.viewmodel.HistoryViewModel;

/**
 * Created by filipenunes on 04/20/18.
 */
@Singleton
public class HistoryFragment extends Fragment implements HistoryInteraction {

    @Inject
    HistoryViewModel viewModel;

    private ItemHistoryListAdapter adapter;
    private EditText nameCard;
    private EditText cvv;
    private EditText numberCard;
    private EditText date;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemHistoryListAdapter(getActivity());
        StoreApp.getAppComponent().inject(this);
        getActivity().setTitle(R.string.title_menu_home_cart);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HistoryFragmentBinding binding = HistoryFragmentBinding.bind(view);
        viewModel.setHistoryInteraction(this);
        binding.setViewModel(viewModel);
        bindAdapter(binding.itemList);
        viewModel.loadItems();
    }

    @Override
    public void displayItems(List<Item> items) {
        adapter.setItems(items);
    }

    private void bindAdapter(RecyclerView binding) {
        RecyclerView.LayoutManager horizontalManager = new LinearLayoutManager(getContext(),
                OrientationHelper.VERTICAL, false);

        binding.setLayoutManager(horizontalManager);
        binding.setAdapter(adapter);
    }

    @Override
    public void showDialogCard() {
        MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                .title(R.string.title_card)
                .customView(R.layout.card_dialog, true)
                .positiveText(R.string.confirm)
                .negativeText(android.R.string.cancel)
                .onPositive(
                        (dialog1, which) -> viewModel.finishCart(nameCard.getText().toString(),
                                numberCard.getText().toString(),
                                cvv.getText().toString(),
                                date.getText().toString()))
                .build();

        nameCard = dialog.getCustomView().findViewById(R.id.name_card);
        numberCard = dialog.getCustomView().findViewById(R.id.number_card);
        numberCard.addTextChangedListener(new FreeFormatWatcher(StringUtil.NUMBER_CARD_MASK, numberCard));
        date = dialog.getCustomView().findViewById(R.id.date_card);
        date.addTextChangedListener(new FreeFormatWatcher(StringUtil.DATE_MASK, date));
        cvv = dialog.getCustomView().findViewById(R.id.cvv_card);

        dialog.show();

    }

    @Override
    public void showAlert(String message) {
        AlertUtil.showAlert(getActivity(), message);
    }

    @Override
    public void showAlert(int resource) {
        AlertUtil.showAlert(getActivity(), getString(resource));
    }

    @Override
    public void moveToHistoric() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.navigateTo(R.id.nav_history);
    }
}
