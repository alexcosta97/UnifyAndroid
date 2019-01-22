package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Template;
import io.github.alexcosta97.unify.Models.Response.TemplateResponse;
import io.github.alexcosta97.unify.Services.APIClient;
import io.github.alexcosta97.unify.Services.AppDatabase;
import io.github.alexcosta97.unify.Services.Network;
import io.github.alexcosta97.unify.Services.ResponseConverter;
import io.github.alexcosta97.unify.Services.ServiceGenerator;
import io.github.alexcosta97.unify.Views.ListTemplatesView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTemplatesPresenter {
    private ListTemplatesView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    String token;

    public ListTemplatesPresenter(ListTemplatesView parentView, final Context context){
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
    public List<Template> getTemplates(){
        final List<Template> templates = new ArrayList<>();
        if(Network.isAvailable(mContext)){
            client = ServiceGenerator.createService(token);
            Call<List<TemplateResponse>> call = client.getTemplates();
            call.enqueue(new Callback<List<TemplateResponse>>() {
                @Override
                public void onResponse(Call<List<TemplateResponse>> call, Response<List<TemplateResponse>> response) {
                    switch (response.code()){
                        case 200:
                            final List<Template> dbTemplates = new ArrayList<>();
                            for(int i = 0; i < response.body().size(); i++){
                                Template template = ResponseConverter.responseToModel(response.body().get(i));
                                dbTemplates.add(template);
                            }
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    db.templateDao().insertMany(dbTemplates);
                                }
                            });
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<TemplateResponse>> call, Throwable t) {

                }
            });
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                templates.addAll(db.templateDao().getAll());
            }
        });

        return templates;
    }

    public static void itemClicked(int itemPosition){

    }

    public static void deleteItem(int itemPosition){}
    public static void editItem(int itemPosition){}
}
