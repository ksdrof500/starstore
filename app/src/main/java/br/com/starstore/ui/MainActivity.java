package br.com.starstore.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.com.starstore.R;
import br.com.starstore.databinding.MainActivityBinding;
import br.com.starstore.interaction.MainInteraction;

/**
 * Created by filipenunes on 26/12/17.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainInteraction {

    private MainActivityBinding binding;
    private int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        initToolbar();
        navigateTo(R.id.nav_home);
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawer,
                binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawer.addDrawerListener(toggle);

        toggle.syncState();
        binding.navigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return navigateTo(item.getItemId());
    }


    @Override
    public void closeDrawer() {
        new Handler().postDelayed(() -> binding.drawer.closeDrawer(GravityCompat.START), 200);
    }

    public boolean navigateTo(int id) {

        if (id != selectedItem) {

            closeDrawer();

            Fragment fragment = null;
            switch (id) {
                case R.id.nav_home:
                    fragment = new CartFragment();
                    break;
                case R.id.nav_history:
                    fragment = new HistoricFragment();
                    break;
                default:
                    break;
            }

            enableScroll(fragment);

            selectedItem = id;
        }

        return true;
    }

    private void enableScroll(Fragment fragment) {

        final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) binding.contents.getLayoutParams();
        params.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        binding.contents.requestLayout();

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contents, fragment)
                    .commit();
        }
    }

}
