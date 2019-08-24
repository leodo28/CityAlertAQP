package com.example.tecsup.city_alert_aqp_ale;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class RecuperarContra3 extends AppCompatActivity {
EditText C1,C2,C3,C4;
String code_ingre,code_recup,final_code;
ConstraintLayout espacio;
Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra3);

        C1=findViewById(R.id.c1);
        C2=findViewById(R.id.c2);
        C3=findViewById(R.id.c3);
        C4=findViewById(R.id.c4);
        espacio=findViewById(R.id.constrain);


        code_recup="1234";


        C1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                code_ingre=C1.getText().toString();
            if(C1.getText().toString().length()==1){
                C2.requestFocus();
            }
            }
        });

        C2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                code_ingre=code_ingre+C2.getText().toString();
                if(C2.getText().toString().length()==1){
                    C3.requestFocus();
                }
            }
        });

        C3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                code_ingre=code_ingre+C3.getText().toString();
                if(C3.getText().toString().length()==1){
                    C4.requestFocus();
                }
            }
        });

        C4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                code_ingre=code_ingre+C4.getText().toString();


            if(C4.getText().toString().length()==1){

                C1.setEnabled(false);
                C2.setEnabled(false);
                C3.setEnabled(false);
                C4.setEnabled(false);



            }

            saltar();


            }
        });




    }

    public void saltar() {

        if (C1.getText().toString().length() > 0 && C2.getText().toString().length() > 0 && C3.getText().toString().length() > 0 && C4.getText().toString().length() > 0) {

            if (code_ingre.equals(code_recup)) {
                C1.setBackgroundResource(R.drawable.borde3bien);
                C2.setBackgroundResource(R.drawable.borde3bien);
                C3.setBackgroundResource(R.drawable.borde3bien);
                C4.setBackgroundResource(R.drawable.borde3bien);

                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(),RecuperarContra2.class);
                        finish();
                        startActivity(i);
                    }
                },1000);

                }
                else if(!code_ingre.equals(code_recup)){
                C1.setBackgroundResource(R.drawable.borde3mal);
                C2.setBackgroundResource(R.drawable.borde3mal);
                C3.setBackgroundResource(R.drawable.borde3mal);
                C4.setBackgroundResource(R.drawable.borde3mal);
                Toast.makeText(getApplicationContext(),"El codigo es incorrecto, intente de nuevo",Toast.LENGTH_LONG).show();
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        C1.setBackgroundResource(R.drawable.borde3);
                        C2.setBackgroundResource(R.drawable.borde3);
                        C3.setBackgroundResource(R.drawable.borde3);
                        C4.setBackgroundResource(R.drawable.borde3);
                        C1.setEnabled(true);
                        C2.setEnabled(true);
                        C3.setEnabled(true);
                        C4.setEnabled(true);
                        C1.getText().clear();
                        C2.getText().clear();
                        C3.getText().clear();
                        C4.getText().clear();
                        C1.requestFocus();
                    }
                },3000);



            }
        }
    }
}
