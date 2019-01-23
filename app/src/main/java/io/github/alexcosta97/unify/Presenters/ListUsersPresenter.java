package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.AddUser;
import io.github.alexcosta97.unify.DetailUser;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.User;
import io.github.alexcosta97.unify.Models.Response.UserResponse;
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

public class ListUsersPresenter {
    private ListItemsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    Authorization auth;
    String token;
    public ListUsersPresenter(ListItemsView parentView, final Context context){
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
    public List<User> getUsers(){
        final List<User> users = new ArrayList<>();
        if(Network.isAvailable(mContext)){
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

    public void itemClicked(final int itemPosition){
        view.launchNextActivity(DetailUser.class, itemPosition);
    }

    public void deleteItem(final int itemPosition){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final User user = db.userDao().getById(itemPosition);
                Call<ResponseBody> call = client.deleteUser(user.userId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Operation was successful.", Toast.LENGTH_LONG).show();
                        if(user.userId.contentEquals(auth.userId)){
                            db.authorizationDao().deleteAuthorization(auth);
                        }
                        db.userDao().deleteOne(user);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "There was an error.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void editItem(int itemPosition){
        view.launchNextActivity(AddUser.class, itemPosition);
    }
}
