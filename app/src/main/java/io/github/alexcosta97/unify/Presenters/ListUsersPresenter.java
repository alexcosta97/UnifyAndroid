package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.User;
import io.github.alexcosta97.unify.Models.Response.UserResponse;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.Network;
import io.github.alexcosta97.unify.Services.ResponseConverter;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.ListUsersView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUsersPresenter {
    private ListUsersView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    String token;

    public ListUsersPresenter(ListUsersView parentView, final Context context){
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
    public List<User> getUsers(){
        final List<User> users = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            client = ServiceGenerator.createService(token);
            Call<List<UserResponse>> call = client.getUsers();
            call.enqueue(new Callback<List<UserResponse>>() {
                @Override
                public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<User> dbUsers = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                User user = ResponseConverter.responseToModel(response.body().get(i));
                                dbUsers.add(user);
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.userDao().insertMany(dbUsers);
                                }
                            });
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<UserResponse>> call, Throwable t) {

                }
            });
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                users.addAll(db.userDao().getAll());
            }
        });

        return users;
    }

    public static void itemClicked(int itemPosition){
        Log.i("tag", "clicked item");
    }

    public static void deleteItem(int itemPosition){}
    public static void editItem(int itemPosition){}
}
