package io.github.alexcosta97.unify.Services;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String URL;
    private OkHttpClient client;

    public HTTPService(){
        URL = "https://unify-android-test-api.herokuapp.com/api/";
        client = new OkHttpClient();
    }

    public Response postNoToken(String endpoint, String requestBody) throws IOException{
        String requestURL = URL + endpoint;
        Request req = new Request.Builder()
                .url(requestURL)
                .post(RequestBody.create(JSON, requestBody))
                .build();

        try{
            Response response = client.newCall(req).execute();
            if(!response.isSuccessful()) throw new IOException("Something went wrong" + response);
            return response;
        }catch (IOException exception){
            return null;
        }
    }

    public Response getToken(String endpoint, String token) throws IOException{
        String requestURL = URL + endpoint;
        Request req = new Request.Builder()
                .url(requestURL)
                .addHeader("x-auth-token", token)
                .get()
                .build();

        try{
            Response response = client.newCall(req).execute();
            if(!response.isSuccessful()) throw  new IOException("Something went wrong" + response);
            return response;
        } catch (IOException ex){
            return null;
        }
    }

    public Response postToken(String endpoint, String requestBody, String token) throws IOException{
        String requestURL = URL + endpoint;
        Request req = new Request.Builder()
                .url(requestURL)
                .addHeader("x-auth-token", token)
                .post(RequestBody.create(JSON, requestBody))
                .build();

        try{
            Response response = client.newCall(req).execute();
            if(!response.isSuccessful()) throw  new IOException("Something went wrong" + response);
            return response;
        } catch (IOException ex){
            return null;
        }
    }

    public Response putToken(String endpoint, String requestBody, String token) throws IOException{
        String requestURL = URL + endpoint;
        Request req = new Request.Builder()
                .url(requestURL)
                .addHeader("x-auth-token", token)
                .put(RequestBody.create(JSON, requestBody))
                .build();

        try{
            Response response = client.newCall(req).execute();
            if(!response.isSuccessful()) throw  new IOException("Something went wrong" + response);
            return response;
        } catch (IOException ex){
            return null;
        }
    }

    public Response deleteToken(String endpoint, String token) throws IOException{
        String requestURL = URL + endpoint;
        Request req = new Request.Builder()
                .url(requestURL)
                .addHeader("x-auth-token", token)
                .delete()
                .build();

        try{
            Response response = client.newCall(req).execute();
            if(!response.isSuccessful()) throw  new IOException("Something went wrong" + response);
            return response;
        } catch (IOException ex){
            return null;
        }
    }
}
