package com.exampleprueba.nicolasfeoli.miapp.ui.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    /*
    @Headers({
            "Content-type: application/json"
    })
    */

    @POST("login/")
    Call<UserSession> getLoginToken(@Body LoginInfo body);

}
