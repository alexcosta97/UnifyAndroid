package io.github.alexcosta97.unify.Presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alexcosta97.unify.DetailsTemplate;
import io.github.alexcosta97.unify.Models.Database.Authorization;
import io.github.alexcosta97.unify.Models.Database.Template;
import io.github.alexcosta97.unify.Models.Response.TemplateResponse;
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

public class ListTemplatesPresenter {
    private ListItemsView view;
    Context mContext;
    APIClient client;
    AppDatabase db;
    Authorization auth;
    String token;

    public ListTemplatesPresenter(ListItemsView parentView, final Context context){
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
    public List<Template> getTemplates(){
        final List<Template> templates = new ArrayList<>();
        if(Network.isAvailable(mContext)){
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

    public void itemClicked(int itemPosition){
        view.launchNextActivity(DetailsTemplate.class, itemPosition);
    }

    public void deleteItem(final int itemPosition){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Template template = db.templateDao().getById(itemPosition);
                Call<ResponseBody> call = client.deleteTemplate(template.templateId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Operation Successful", Toast.LENGTH_LONG).show();
                        db.templateDao().deleteOne(template);
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
        //TODO: run launchNextActivity on AddTemplate after creation
    }
}
