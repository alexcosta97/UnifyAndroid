package io.github.alexcosta97.unify;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import io.github.alexcosta97.unify.Adapters.SubcategoriesAdapter;
import io.github.alexcosta97.unify.Models.Database.Subcategory;
import io.github.alexcosta97.unify.Models.Database.Subcategory;
import io.github.alexcosta97.unify.Presenters.ListSubcategoriesPresenter;
import io.github.alexcosta97.unify.Presenters.SignInActivityPresenter;
import io.github.alexcosta97.unify.Views.ListItemsView;

public class ListSubcategories extends AppCompatActivity implements ListItemsView {

    private DrawerLayout mDrawerLayout;
    private ListSubcategoriesPresenter presenter;
    SubcategoriesAdapter adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        presenter = new ListSubcategoriesPresenter(this, this);

        setAppBar();
        setNavigationDrawer();
        setListView(presenter.getSubcategories());
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(presenter.getSubcategories());
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

    public void launchNextActivity(Class activity, int itemId){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void setListView(List<Subcategory> subcategories){
        adapter = new SubcategoriesAdapter(this, subcategories);

        list = findViewById(R.id.list_view_subcategories);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.itemClicked(position);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int itemPosition, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListSubcategories.this);
                ListView modeListView = new ListView(ListSubcategories.this);
                String[] modes = new String[] {"Edit Subcategory", "Delete Subcategory"};
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(ListSubcategories.this, android.R.layout.simple_list_item_1, android.R.id.text1, modes);
                modeListView.setAdapter(modeAdapter);
                builder.setView(modeListView);
                final Dialog dialog = builder.create();
                dialog.show();
                modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //edit subcategory
                        if(position == 0){
                            presenter.editItem(itemPosition);
                        }
                        //delete subcategory
                        else{
                            presenter.deleteItem(itemPosition, ListSubcategories.this);
                        }
                        dialog.dismiss();
                    }
                });
                return true;
            }
        });
    }

    public void setNavigationDrawer(){
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_products);
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
                                launchNextActivity(ListLocations.class);
                                break;
                            case R.id.nav_suppliers:
                                launchNextActivity(ListSuppliers.class);
                                break;
                            case R.id.nav_categories:
                                launchNextActivity(ListCategories.class);
                                break;
                            case R.id.nav_subcategories:
                                //Do nothing
                                break;
                            case R.id.nav_products:
                                launchNextActivity(ListProducts.class);
                                break;
                            case R.id.nav_templates:
                                launchNextActivity(ListTemplates.class);
                                break;
                            case R.id.nav_logout:
                                SignInActivityPresenter.logOut(ListSubcategories.this);
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
}
