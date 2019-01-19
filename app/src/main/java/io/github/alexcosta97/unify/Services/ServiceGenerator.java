package io.github.alexcosta97.unify.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://unify-android-test-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit = builder.build();

    public static APIClient createService(){
        return retrofit.create(APIClient.class);
    }
}
