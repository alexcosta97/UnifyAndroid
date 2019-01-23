package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.DetailsCategories;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Category;
import io.github.alexcosta97.unify.Models.Response.CategoryResponse;
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

public class ListCategoriesPresenter{
    private ListItemsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    Authorization auth;
    String token;

    public ListCategoriesPresenter(ListItemsView parentView, final Context context){
        view = parentView;
        mContext = context;
        db = AppDatabase.getDatabase(context);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                auth = db.authorizationDao().getAuthorization();
                token = auth.token;
            }
        });
    }
    public List<Category> getCategories(){
        final List<Category> categories = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            client = ServiceGenerator.createService(token);
            Call<List<CategoryResponse>> call = client.getCategories();
            call.enqueue(new Callback<List<CategoryResponse>>() {
                @Override
                public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<Category> dbCategories = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                Category category = ResponseConverter.responseToModel(response.body().get(i));
                                dbCategories.add(category);
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.categoryDao().insertMany(dbCategories);
                                }
                            });
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {

                }
            });
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                categories.addAll(db.categoryDao().getAll());
            }
        });

        return categories;
    }

    public void itemClicked(int itemPosition){
        view.launchNextActivity(DetailsCategories.class, itemPosition);
    }

    public void deleteItem(final int itemPosition){
        final AppDatabase db = AppDatabase.getDatabase(mContext);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Category category = db.categoryDao().getById(itemPosition);
                APIClient client = ServiceGenerator.createService(token);
                Call<ResponseBody> call = client.deleteCategory(category.categoryId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Operation Successful", Toast.LENGTH_LONG).show();
                        db.categoryDao().deleteOne(category);
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
        //TODO: Add launchNextActivity after creating AddCategory Activity
    }
}
