package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Product;
import io.github.alexcosta97.unify.Models.Response.ProductResponse;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.Network;
import io.github.alexcosta97.unify.Services.ResponseConverter;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.ListProductsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsPresenter {
    private ListProductsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    String token;

    public ListProductsPresenter(ListProductsView parentView, final Context context){
        view = parentView;
        mContext = context;
        db = AppDatabase.getDatabase(context);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Authorization auth = db.authorizationDao().getAuthorization();
                token = auth.token;
            }
        });
    }

    public List<Product> getProducts(){
        final List<Product> products = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            client = ServiceGenerator.createService(token);
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

    public static void itemClicked(int itemPosition){

    }

    public static void deleteItem(int itemPosition){}
    public static void editItem(int itemPosition){}
}
