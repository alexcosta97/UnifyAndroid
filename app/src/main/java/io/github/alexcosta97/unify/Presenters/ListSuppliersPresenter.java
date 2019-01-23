package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.DetailsSupplier;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Supplier;
import io.github.alexcosta97.unify.Models.Response.SupplierResponse;
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

public class ListSuppliersPresenter {
    private ListItemsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    Authorization auth;
    String token;

    public ListSuppliersPresenter(ListItemsView parentView, final Context context){
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
    public List<Supplier> getSuppliers(){
        final List<Supplier> suppliers = new ArrayList<>();
        if(Network.isAvailable(mContext)){
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

    public void itemClicked(int itemPosition){
        view.launchNextActivity(DetailsSupplier.class, itemPosition);
    }

    public void deleteItem(final int itemPosition){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Supplier supplier = db.supplierDao().getById(itemPosition);
                Call<ResponseBody> call = client.deleteSupplier(supplier.supplierId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Operation Successful", Toast.LENGTH_LONG).show();
                        db.supplierDao().deleteOne(supplier);
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
        //TODO: run launchNextActivity on AddSupplier after creation
    }
}
