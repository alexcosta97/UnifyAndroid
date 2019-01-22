package io.github.alexcosta97.unify;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import io.github.alexcosta97.unify.Adapters.LocationsAdapter;
import io.github.alexcosta97.unify.Presenters.ListLocationsPresenter;
import io.github.alexcosta97.unify.Views.ListLocationsView;

public class ListLocations extends AppCompatActivity implements ListLocationsView {

    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListLocationsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_locations);
        presenter = new ListLocationsPresenter(this, this);

        setAppBar();
        setNavigationDrawer();
        setRecyclerView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void launchNextActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void setNavigationDrawer(){
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_locations);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        switch(menuItem.getItemId()){
                            case R.id.nav_orders:
                                launchNextActivity(MainMenu.class);
                                break;
                            case R.id.nav_users:
                                launchNextActivity(ListUsers.class);
                                break;
                            case R.id.nav_locations:
                                //Do nothing
                                break;
                            case R.id.nav_suppliers:
                                launchNextActivity(ListSuppliers.class);
                                break;
                            case R.id.nav_categories:
                                launchNextActivity(ListCategories.class);
                                break;
                            case R.id.nav_subcategories:
                                launchNextActivity(ListSubcategories.class);
                                break;
                            case R.id.nav_products:
                                launchNextActivity(ListProducts.class);
                                break;
                            case R.id.nav_templates:
                                launchNextActivity(ListTemplates.class);
                                break;
                        }

                        return true;
                    }
                });
    }

    public void setAppBar(){
        //Setting up the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    public void setRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.locations_recycler_view);

        //Using a linear layout manager for the recycler view
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Specifying the recycler view adapter
        mAdapter = new LocationsAdapter(presenter.getLocations());
        mRecyclerView.setAdapter(mAdapter);
    }
}
