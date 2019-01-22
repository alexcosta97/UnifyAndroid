package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Supplier;
import io.github.alexcosta97.unify.Models.Database.Supplier;
import io.github.alexcosta97.unify.Models.Response.SupplierResponse;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.Network;
import io.github.alexcosta97.unify.Services.ResponseConverter;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.ListSuppliersView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSuppliersPresenter {
    private ListSuppliersView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    String token;

    public ListSuppliersPresenter(ListSuppliersView parentView, final Context context){
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
    public List<Supplier> getSuppliers(){
        final List<Supplier> suppliers = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            client = ServiceGenerator.createService(token);
            Call<List<SupplierResponse>> call = client.getSuppliers();
            call.enqueue(new Callback<List<SupplierResponse>>() {
                @Override
                public void onResponse(Call<List<SupplierResponse>> call, Response<List<SupplierResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<Supplier> dbSuppliers = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                Supplier supplier = ResponseConverter.responseToModel(response.body().get(i));
                                dbSuppliers.add(supplier);
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.supplierDao().insertMany(dbSuppliers);
                                }
                            });
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<SupplierResponse>> call, Throwable t) {

                }
            });
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                suppliers.addAll(db.supplierDao().getAll());
            }
        });

        return suppliers;
    }

    public static void itemClicked(int itemPosition){

    }

    public static void deleteItem(int itemPosition){}
    public static void editItem(int itemPosition){}
}
