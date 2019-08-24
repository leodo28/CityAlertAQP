package com.example.tecsup.city_alert_aqp_ale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecuperarContra2 extends AppCompatActivity {
Button bt_ver,bt_ver2,guardar;
EditText pass,confirma_pass;
TextView txt_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra2);
        getSupportActionBar().setTitle("RECUPERAR CONTRASEÑA");

        bt_ver = findViewById(R.id.button);
        bt_ver2 = findViewById(R.id.button22);
        guardar = findViewById(R.id.bt_r_4);
        txt_error = findViewById(R.id.txt_noidem);

        pass = findViewById(R.id.edt_r_3);
        confirma_pass=findViewById(R.id.edt_r_4);

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(pass.getText().toString().length()==6)){
                    txt_error.setText("La contraseña requiere de 6 caracteres ");
                    txt_error.setVisibility(View.VISIBLE);

                }else {
                    txt_error.setVisibility(View.INVISIBLE);
                }

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals(confirma_pass.getText().toString()))
                {
                    txt_error.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Se guardaron los cambios",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    finish();
                    startActivity(i);

                }else{
                    txt_error.setText("Contraseñas ingrasadas no coinciden");
                txt_error.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mn_equiz, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.cerrar_vista:
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
