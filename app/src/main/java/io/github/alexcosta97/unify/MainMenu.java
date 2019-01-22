package io.github.alexcosta97.unify;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import io.github.alexcosta97.unify.Presenters.MainMenuPresenter;
import io.github.alexcosta97.unify.Views.MainMenuView;

public class MainMenu extends AppCompatActivity implements MainMenuView {

    MainMenuPresenter presenter;
    private DrawerLayout mDrawerLayout;
    CardView newOrderCard;
    CardView templateOrderCard;
    CardView reportsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        presenter = new MainMenuPresenter(this);

        newOrderCard = findViewById(R.id.newOrderCard);
        templateOrderCard = findViewById(R.id.templateOrderCard);
        reportsCard = findViewById(R.id.reportsCard);

        //Setting up the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_orders);
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
                                //This is the page, do nothing
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

        //Managing cards behaviour
        newOrderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.newOrderClicked();
            }
        });

        templateOrderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.templateOrderClicked();
            }
        });

        reportsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.reportsClicked();
            }
        });
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
}
