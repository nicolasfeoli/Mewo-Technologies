package com.exampleprueba.nicolasfeoli.miapp.Model;

import android.util.Log;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DAO {
    private OkHttpClient client;
    private static String URL_API = "http://192.168.128.21:8151/ApiServer/api/";

    public DAO() {
        this.client = new OkHttpClient();
    }

    public boolean authenticate(final String user, String pass){

        String url = URL_API +"login";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"username\":\""+ user +"\",\"password\":\""+ pass +"\"}");
        Request request = new Request.Builder()
                .url("http://192.168.128.21:8151/ApiServer/api/login")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Session.getInstance().setStatus("Err");
                e.printStackTrace();

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    try {
                        Session sesion = Session.getInstance();
                        JSONObject reader = new JSONObject(myResponse);
                        //login data
                        JSONObject login_data  = reader.getJSONObject("LoginData");
                        sesion.setAccess_token(login_data.getString("access_token"));
                        sesion.setExpires_in(login_data.getInt("expires_in"));
                        sesion.setToken_type(login_data.getString("token_type"));
                        sesion.setRefresh_token(login_data.getString("refresh_token"));
                        sesion.setScope(login_data.getString("scope"));
                        //user info
                        JSONObject user_info = reader.getJSONObject("UserInfo");
                        sesion.setUid(user_info.getInt("uid"));
                        sesion.setGiven_name(user_info.getString("given_name"));
                        sesion.setEmail(user_info.getString("email"));
                        sesion.setRole(user_info.getString("role"));
                        sesion.setStatus("OK");
                    } catch (JSONException e) {
                        Session.getInstance().setStatus("Err");
                        e.printStackTrace();
                    }
                }
            }
        });
        if ("OK".equals(Session.getInstance().getStatus())){
            return true;
        }
        return false;
    }

}
