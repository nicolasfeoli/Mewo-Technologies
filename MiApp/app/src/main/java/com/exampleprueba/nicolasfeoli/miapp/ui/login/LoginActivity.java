package com.exampleprueba.nicolasfeoli.miapp.ui.login;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.exampleprueba.nicolasfeoli.miapp.AgregarUsuario;
import com.exampleprueba.nicolasfeoli.miapp.MenuSuper;
import com.exampleprueba.nicolasfeoli.miapp.Model.DAO;
import com.exampleprueba.nicolasfeoli.miapp.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        //TODO
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                    //Toast.makeText(getApplicationContext(),"Nop", Toast.LENGTH_LONG).show();
                }
                if (loginResult.getSuccess() != null) {
                    //Toast.makeText(getApplicationContext(),"Funciona", Toast.LENGTH_LONG).show();

                    //updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                //finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                //TODO

                DAO instance = new DAO();
                //Toast.makeText(getApplicationContext(),"Toast Test", Toast.LENGTH_LONG);

                //launchActivity();
                //Log.d("Mierda 1 ---", " ME CAGOOOOO")

                if(isOnline()) {
                    doLogin(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                }
                else
                {
                    Log.d("Error","Internet");
                    Toast.makeText(getApplicationContext(),R.string.errorInternet,Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(!isOnline())
        {
            Log.d("Error","Internet");
            Toast.makeText(getApplicationContext(),R.string.errorInternet,Toast.LENGTH_SHORT).show();
        }
    }
/*
    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }
*/
    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void doLogin(final String username,final String password){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        Log.d("Username ", username);
        Log.d("Password ", password);
        String req = "{\"username\":\""+ username +"\",\"password\":\"" + password + "\"}";
        Log.d("Request ",req);
        RequestBody body = RequestBody.create(mediaType, req);
        Request request = new Request.Builder()
                .url("http://192.168.128.21:8151/ApiServer/api/login")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response response) {
                Log.d("Response ---", " ME CAGOOOOO");
                if(response.isSuccessful()){
                    String temp = null;//.toString();
                    try {
                        temp = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("SUCCESS ---", " ME CAGOOOOO PERO FUE SUCCESFUL");
                    if(response.code()==401){
                        Log.d("Error", "Usuario o Contraseña Incorrecta");
                        //Toast.makeText(LoginActivity.this, "Error. Usuario Incorrecto", Toast.LENGTH_LONG).show();
                        //clearAll();
                    } else if (temp.equals("invalid_username_or_password")){
                        Log.d("Error", "Contraseña Incorrecta");
                        //clearPass();
                        //Toast.makeText(LoginActivity.this, "Error. Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            Log.d("PICHA", temp);

                            JSONObject reader = new JSONObject(temp);
                            JSONObject login_data  = reader.getJSONObject("LoginData");
                            JSONObject user_info = reader.getJSONObject("UserInfo");

                            //launchActivity();
                            //login start main activity
                            Intent intent = new Intent(LoginActivity.this, MenuSuper.class);/////------------------------
                            intent.putExtra("access_token", login_data.getString("access_token"));
                            intent.putExtra("expires_in", login_data.getInt("expires_in"));
                            intent.putExtra("token_type", login_data.getString("token_type"));
                            intent.putExtra("refresh_token", login_data.getString("refresh_token"));
                            intent.putExtra("scope", login_data.getString("scope"));

                            intent.putExtra("uid", user_info.getString("uid"));
                            intent.putExtra("givenName", user_info.getString("givenName"));
                            intent.putExtra("email", user_info.getString("email"));
                            intent.putExtra("role", user_info.getString("role"));

                            startActivity(intent);
                        } catch (JSONException e) {
                            Intent intent = new Intent(LoginActivity.this, MenuSuper.class);
                            startActivity(intent);
                            e.printStackTrace();
                        }
                    }
                } else {
                    //EditText passwordEditText = findViewById(R.id.password);
                    //passwordEditText.setError("Contraseña Incorrecta");
                    //Log.d("Mierda 3 ---", response.toString());
                    Log.d("Error", "Usuario o Contraseña Incorrecta");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            clearAll();
                            Toast.makeText(LoginActivity.this, "Error: Usuario o Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                //Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Fallo",e.getMessage());
            }
        });
    }

    private void launchActivity() {
        Intent intent = new Intent(this, AgregarUsuario.class);
        startActivity(intent);
    }

    private void clearAll(){
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        usernameEditText.setText("");
        passwordEditText.setText("");
        Toast.makeText(LoginActivity.this, "Error. Usuario Incorrecto", Toast.LENGTH_LONG).show();
    }

    private void clearPass(){

        final EditText passwordEditText = findViewById(R.id.password);
        //passwordEditText = findViewById(R.id.password);
        passwordEditText.setError("Contraseña Incorrecta");
        passwordEditText.setText("");
        Toast.makeText(LoginActivity.this, "Error. Contraseña Incorrecta", Toast.LENGTH_LONG).show();
    }
}
