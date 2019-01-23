package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.DetailsLocations;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Location;
import io.github.alexcosta97.unify.Models.Response.LocationResponse;
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

public class ListLocationsPresenter {
    private ListItemsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    Authorization auth;
    String token;

    public ListLocationsPresenter(ListItemsView parentView, final Context context){
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

    public List<Location> getLocations(){
        final List<Location> locations = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            client = ServiceGenerator.createService(token);
            Call<List<LocationResponse>> call = client.getLocations();
            call.enqueue(new Callback<List<LocationResponse>>() {
                @Override
                public void onResponse(Call<List<LocationResponse>> call, Response<List<LocationResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<Location> dbLocations = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                Location user = ResponseConverter.responseToModel(response.body().get(i));
                                dbLocations.add(user);
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.locationDao().insertMany(dbLocations);
                                }
                            });
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<LocationResponse>> call, Throwable t) {

                }
            });
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                locations.addAll(db.locationDao().getAll());
            }
        });

        return locations;
    }

    public void itemClicked(int itemPosition){
        view.launchNextActivity(DetailsLocations.class, itemPosition);
    }

    public void deleteItem(final int itemPosition){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Location location = db.locationDao().getById(itemPosition);
                Call<ResponseBody> call = client.deleteLocation(location.locationId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Operation Successful", Toast.LENGTH_LONG).show();
                        db.locationDao().deleteOne(location);
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
        //TODO: use launchNextActivity after creating AddLocationActivity
    }
}
