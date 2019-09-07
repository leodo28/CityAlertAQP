package com.example.tecsup.city_alert_aqp_ale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tecsup.city_alert_aqp_ale.Clases.Cls_Persona;
import com.example.tecsup.city_alert_aqp_ale.Clases.Cls_Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_login;
    EditText txt_username, txt_password;
    TextView recuperar, registrar;
    Intent intent, intent2;
    ConnectivityManager conMgr;
    int success;
    int id_login;
    SharedPreferences sharedpreferences;
    Boolean session = false;
    int id;
    String username;
    List<Cls_Persona> usuarios_final;

    private String URL = "http://34.67.84.216:8090";
    private static final String TAG = Login.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Verifica conexion a internet
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No esta conectado a internet",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        recuperar = (TextView) findViewById(R.id.recup_pass);
        registrar = (TextView) findViewById(R.id.regist_user);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        username = sharedpreferences.getString(TAG_USERNAME, null);

        if (session) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_USERNAME, username);
            finish();
            startActivity(intent);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                // eliminar espacios en blanco (checkea)
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        CheckLogin(username, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "No tiene conexion a internet", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Existen campos en blanco", Toast.LENGTH_LONG).show();
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(Login.this, RecuperarContra.class);
                startActivity(intent2);
            }
        });

    }

    private void CheckLogin(final String username, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
        pDialog.setMessage("Accediendo ...");
        showDialog();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        ServiciosLogin servidor = retrofit.create(ServiciosLogin.class);
        Call<Cls_Usuario> registrar=servidor.VerificarLogin(username,password);
        registrar.enqueue(new Callback<Cls_Usuario>() {
            @Override
            public void onResponse(Call<Cls_Usuario> call, Response<Cls_Usuario> response) {
                Log.e("Codigo usuario",response.code()+"");
                switch (response.code()){

                    case 200:
                        Cls_Usuario user=response.body();
                        id=user.getId();
                        Log.d("cancharesponse",user.getId()+"");

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_USERNAME, username);
                        editor.commit();

                        Intent intent2 = new Intent(Login.this, MainActivity.class);
                        intent2.putExtra(TAG_ID, id);
                        intent2.putExtra(TAG_USERNAME, username);
                        finish();
                        startActivity(intent2);
                        break;
                    case 400:
                        Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cls_Usuario> call, Throwable t) {
                Log.e("Error login",t.getMessage());
            }
        });
    }


        private void showDialog () {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog () {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }
