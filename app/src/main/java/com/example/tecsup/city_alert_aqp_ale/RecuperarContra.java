package com.example.tecsup.city_alert_aqp_ale;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RecuperarContra extends AppCompatActivity{
    Button bt_enviar_r;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);
        getSupportActionBar().setTitle("");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        bt_enviar_r = findViewById(R.id.bt_reset_pass);
        //bt_aceptar_r = findViewById(R.id.bt_r_2);
        //bt_reenviar_r = findViewById(R.id.bt_r_3);

        //email_fono = findViewById(R.id.edt_r_1);
        //codigosms=findViewById(R.id.edt_r_2);

        bt_enviar_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo para q envie automaticamente sms con codigo de recuperacion

                //email_fono.setEnabled(false);
                Intent intent = new Intent(getApplicationContext(), RecuperarContra3.class);
                startActivity(intent);

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                retornar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void retornar() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        finish();
        startActivity(intent);
    }

}
