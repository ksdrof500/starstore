package br.com.starstore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.starstore.R;
import br.com.starstore.StoreApp;
import br.com.starstore.databinding.CartFragmentBinding;
import br.com.starstore.interaction.CartInteraction;
import br.com.starstore.model.Item;
import br.com.starstore.ui.adapter.ItemListAdapter;
import br.com.starstore.util.AlertUtil;
import br.com.starstore.viewmodel.CartViewModel;

/**
 * Created by filipenunes on 04/20/18.
 */
@Singleton
public class CartFragment extends Fragment implements CartInteraction,
        android.widget.SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    @Inject
    CartViewModel viewModel;

    private ItemListAdapter adapter;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new ItemListAdapter(getActivity());
        StoreApp.getAppComponent().inject(this);
        getActivity().setTitle(R.string.title_menu_home);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        configureSearch(menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cart_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CartFragmentBinding binding = CartFragmentBinding.bind(view);
        viewModel.setCartInteraction(this);
        binding.setViewModel(viewModel);
        bindAdapter(binding.itemList);
        initSheet(binding.bottomSheet);
        viewModel.loadItems();
    }

    @Override
    public void displayItems(List<Item> items) {
        adapter.setItems(items);
    }

    @Override
    public void stateBottom() {
        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        viewModel.filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (TextUtils.isEmpty(s)) {
            viewModel.clear();
        }
        return false;
    }

    private void configureSearch(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.widget.SearchView searchView = (android.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.hint_search));

    }

    private void bindAdapter(RecyclerView binding) {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        binding.setLayoutManager(layoutManager);
        binding.setAdapter(adapter);
    }

    private void initSheet(View bottomSheet) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void moveForward(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contents, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showAlert(int resource) {
        AlertUtil.showAlert(getActivity(), getString(resource));
    }
}
