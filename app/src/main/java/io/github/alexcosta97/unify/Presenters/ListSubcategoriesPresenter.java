package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.DetailsProduct;
import io.github.alexcosta97.unify.DetailsSubcategory;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Subcategory;
import io.github.alexcosta97.unify.Models.Response.SubcategoryResponse;
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

public class ListSubcategoriesPresenter {
    private ListItemsView view;
    Context mContext;
    APIClient client;
    Authorization auth;
    AppDatabase db;
    String token;

    public ListSubcategoriesPresenter(ListItemsView parentView, final Context context){
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

    public List<Subcategory> getSubcategories(){
        final List<Subcategory> subcategories = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            Call<List<SubcategoryResponse>> call = client.getSubcategories();
            call.enqueue(new Callback<List<SubcategoryResponse>>() {
                @Override
                public void onResponse(Call<List<SubcategoryResponse>> call, Response<List<SubcategoryResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<Subcategory> dbSubcategories = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                Subcategory subcategory = ResponseConverter.responseToModel(response.body().get(i));
                                dbSubcategories.add(subcategory);
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.subcategoryDao().insertMany(dbSubcategories);
                                }
                            });
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<SubcategoryResponse>> call, Throwable t) {

                }
            });
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                subcategories.addAll(db.subcategoryDao().getAll());
            }
        });

        return subcategories;
    }

    public void itemClicked(int itemPosition){
        view.launchNextActivity(DetailsSubcategory.class, itemPosition);
    }

    public void deleteItem(final int itemPosition){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Subcategory subcategory = db.subcategoryDao().getById(itemPosition);
                Call<ResponseBody> call = client.deleteSubcategory(subcategory.subcategoryId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Operation Successful", Toast.LENGTH_LONG).show();
                        db.subcategoryDao().deleteOne(subcategory);
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
        //TODO: call launchNextActivity on AddSubcategoryActivity after creation
    }
}
