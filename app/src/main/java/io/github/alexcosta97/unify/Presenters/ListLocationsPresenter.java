package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Location;
import io.github.alexcosta97.unify.Models.Response.LocationResponse;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.Network;
import io.github.alexcosta97.unify.Services.ResponseConverter;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.ListLocationsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLocationsPresenter {
    private ListLocationsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    String token;

    public ListLocationsPresenter(ListLocationsView parentView, final Context context){
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

    public static void itemClicked(int itemPosition){

    }

    public static void deleteItem(int itemPosition){}
    public static void editItem(int itemPosition){}
}
