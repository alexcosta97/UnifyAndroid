package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Subcategory;
import io.github.alexcosta97.unify.Models.Response.SubcategoryResponse;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.Network;
import io.github.alexcosta97.unify.Services.ResponseConverter;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.ListSubcategoriesView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSubcategoriesPresenter {
    private ListSubcategoriesView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    String token;

    public ListSubcategoriesPresenter(ListSubcategoriesView parentView, final Context context){
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

    public List<Subcategory> getSubcategories(){
        final List<Subcategory> subcategories = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            client = ServiceGenerator.createService(token);
            Call<List<SubcategoryResponse>> call = client.getSubcategories();
            call.enqueue(new Callback<List<SubcategoryResponse>>() {
                @Override
                public void onResponse(Call<List<SubcategoryResponse>> call, Response<List<SubcategoryResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<Subcategory> dbSubcategories = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                Subcategory user = ResponseConverter.responseToModel(response.body().get(i));
                                dbSubcategories.add(user);
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

    public static void itemClicked(int itemPosition){

    }

    public static void deleteItem(int itemPosition){}
    public static void editItem(int itemPosition){}
}
