package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.DetailsLocations;
import io.github.alexcosta97.unify.DetailsProduct;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Product;
import io.github.alexcosta97.unify.Models.Response.ProductResponse;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.Network;
import io.github.alexcosta97.unify.Services.ResponseConverter;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.ListItemsView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsPresenter {
    private ListItemsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    Authorization auth;
    String token;

    public ListProductsPresenter(ListItemsView parentView, final Context context){
        view = parentView;
        mContext = context;
        db = AppDatabase.getDatabase(context);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                auth = db.authorizationDao().getAuthorization();
                token = auth.token;
                client = ServiceGenerator.createService(token);
            }
        });
    }

    public List<Product> getProducts(){
        final List<Product> products = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            Call<List<ProductResponse>> call = client.getProducts();
            call.enqueue(new Callback<List<ProductResponse>>() {
                @Override
                public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<Product> dbProducts = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                Product user = ResponseConverter.responseToModel(response.body().get(i));
                                dbProducts.add(user);
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.productDao().insertMany(dbProducts);
                                }
                            });
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<ProductResponse>> call, Throwable t) {

                }
            });
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                products.addAll(db.productDao().getAll());
            }
        });

        return products;
    }

    public void itemClicked(int itemPosition){
        view.launchNextActivity(DetailsProduct.class, itemPosition);
    }

    public void deleteItem(final int itemPosition){
        final AppDatabase db = AppDatabase.getDatabase(mContext);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Product product = db.productDao().getById(itemPosition);
                Call<ResponseBody> call = client.deleteProduct(product.productId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Operation Successful", Toast.LENGTH_LONG).show();
                        db.productDao().deleteOne(product);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "There was an issue processing your request", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public void editItem(int itemPosition){
        //TODO: Add launchNextActivity on AddProductsActivity after creation
    }
}
