package com.example.tecsup.cityalertarequipa.Actividades_Supervisor;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Incidencia;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Tipo_Incidencia;
import com.example.tecsup.cityalertarequipa.R;
import com.example.tecsup.cityalertarequipa.ServiciosIncidencia;
import com.example.tecsup.cityalertarequipa.ServiciosPersona;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Act_Inicio_Supervisor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView num_serenos,num_inci_atendidos,num_inci_pendientes,nombreapp;
    LinearLayout serenos,inci_atendidos,inci_pendientes;
    Cls_Persona sup;
    List<Cls_Persona> serenos_list = new ArrayList<>();
    List<Cls_Persona> personas_list = new ArrayList<>();
    List<Cls_Incidencia> incidencias = new ArrayList<>();
    int id_supervisor;
    public static String url = "http://34.67.84.216:8090/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Incidencias","inicioaaaa");
        setContentView(R.layout.activity_inicio_supervisor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e("Incidencias","inicioppp");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        num_serenos = findViewById(R.id.num_sereno);
        num_inci_atendidos = findViewById(R.id.inc_atendidos);
        num_inci_pendientes = findViewById(R.id.inc_pendientes);

        serenos=findViewById(R.id.btn_sere_activos);
        inci_atendidos=findViewById(R.id.btn_inc_atendidas);
        inci_pendientes =findViewById(R.id.btn_inc_pendientes);

        id_supervisor=2;


        final ServiciosPersona personaservicios = retrofit.create(ServiciosPersona.class);
        Call<Cls_Persona> call1 = personaservicios.obtenerSupervisor(id_supervisor);
        call1.enqueue(new Callback<Cls_Persona>() {
            @Override
            public void onResponse(Call<Cls_Persona> call, Response<Cls_Persona> response) {
                Log.e("respuesta",response.code()+"");
                switch (response.code()){
                    case 200:
                        sup = (Cls_Persona) response.body();

                        nombreapp=findViewById(R.id.Nombreapp);
                        nombreapp.setText(sup.getNombres()+" "+sup.getApellidopaterno()+" "+sup.getApellidomaterno());
                        Call<List<Cls_Persona>> call2 = personaservicios.listarSerenos();
                        call2.enqueue(new Callback<List<Cls_Persona>>() {
                            @Override
                            public void onResponse(Call<List<Cls_Persona>> call, Response<List<Cls_Persona>> response) {
                                switch (response.code()){
                                    case 200:
                                        personas_list = (List<Cls_Persona>)response.body();
                                        Log.e("Supervisor",sup.getId()+"");
                                        Log.e("Sereno",personas_list.get(3).getSupervisor().getId()+"");
                                        for(int i =0; i<personas_list.size()-1;i++){
                                            if(personas_list.get(i).getSupervisor()!= null) {
                                                if (personas_list.get(i).getSupervisor().getId() == sup.getId()) {
                                                    serenos_list.add(personas_list.get(i));
                                                }
                                            }
                                        }
                                        num_serenos.setText(serenos_list.size()+"");
                                        break;
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Cls_Persona>> call, Throwable t) {
                            }
                        });
                        break;
                }
            }
            @Override
            public void onFailure(Call<Cls_Persona> call, Throwable t) {
                Log.d("error","no entro");
            }
        });


        ServiciosIncidencia incidenciasservicios = retrofit.create(ServiciosIncidencia.class);
        Call<List<Cls_Incidencia>> call3 = incidenciasservicios.listarincidencias();
        call3.enqueue(new Callback<List<Cls_Incidencia>>() {
            @Override
            public void onResponse(Call<List<Cls_Incidencia>> call, Response<List<Cls_Incidencia>> response) {
                Log.e("Incidencias",response.body().get(0).getId()+"");
                switch (response.code()){
                    case 200:
                        incidencias = (List<Cls_Incidencia>)response.body();
                        Log.e("Incidencias",incidencias.get(0).getId()+"");
                        int num_atendidos=0;
                        int num_pendientes=0;
                        for(int i =0;i<incidencias.size();i++){
                            if(incidencias.get(i).getEstado_incidencia_id()!= null){
                                Log.e("Estado incidencia",incidencias.get(i).getEstado_incidencia_id().getDescripcion()+"");
                                if(incidencias.get(i).getEstado_incidencia_id().getDescripcion().equals("CREADO")
                                        ||incidencias.get(i).getEstado_incidencia_id().getDescripcion().equals("asignado")){
                                    num_pendientes++;
                                }
                                if(incidencias.get(i).getEstado_incidencia_id().getDescripcion().equals("atendido")){
                                    num_atendidos++;
                                }
                            }
                        }
                        num_inci_atendidos.setText(num_atendidos+"");
                        num_inci_pendientes.setText(num_pendientes+"");
                        break;
                }
            }
            @Override
            public void onFailure(Call<List<Cls_Incidencia>> call, Throwable t) {
                Log.e("Incidencias",t.toString()+"");
            }
        });




       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       /* sup = new Cls_Persona("Pepito","Dospalotes","23684505",
                "p.dospalotes@gmail.com","948758935","psj s/n");*/

       /* Cls_Persona p1 = new Cls_Persona("Luis","Garcia","48743655",
                "luis@gmail.com","9475849554","psj s/n"
                ,-16.429299,-71.519191);
        Cls_Persona p2 = new Cls_Persona("Beto","Cruz","632438844",
                "beto@gmail.com","9873432984","psj s/n",
                -16.431299,-71.529191);

        serenos_list.add(p1.getNombre()+" "+p1.getApellido());
        serenos_list.add(p2.getNombre()+" "+p2.getApellido());*/

        Cls_Persona p1 = new Cls_Persona();
        Cls_Tipo_Incidencia t1 = new Cls_Tipo_Incidencia("robo",true);

        Cls_Incidencia incidencia1=new Cls_Incidencia(new Date(119, 8,12, 10, 5, 6),t1,p1);
        Cls_Incidencia incidencia2=new Cls_Incidencia(new Date(119, 8,14, 16, 50, 6),t1,p1);


        incidencias.add(incidencia1);
        incidencias.add(incidencia2);



        serenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Act_SerenosaCargo_Supervisor.class);
                i.putExtra("supervisor",sup);
                i.putExtra("serenos", (Serializable) serenos_list);
                startActivity(i);
            }
        });

        inci_atendidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Act_Incidencia_Supervisor.class);
                i.putExtra("supervisor",sup);
                i.putExtra("serenos", (Serializable) serenos_list);
                startActivity(i);
            }
        });

        inci_pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Act_Incidencia_Supervisor.class);
                i.putExtra("supervisor",sup);
                i.putExtra("serenos", (Serializable) serenos_list);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.iniciosupervisor2, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.inicio) {
            Intent i = new Intent(this,Act_Inicio_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        } else if (id == R.id.perfil) {
            Intent i = new Intent(this,Act_Perfil_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        } else if (id == R.id.serenos) {
            Intent i = new Intent(this,Act_SerenosaCargo_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        }else if (id == R.id.incidencias) {
            Intent i = new Intent(this,Act_Incidencia_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        }else if (id == R.id.ubicacion) {
            Intent i = new Intent(this,Act_Ubicacion_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        }else if (id == R.id.telefonos) {
            Intent i = new Intent(this,Act_TelefonoEmergencia_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        }else if (id == R.id.editar) {
            Intent i = new Intent(this,Act_Editar_Perfil_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        }else if (id == R.id.logout) {
            Intent i = new Intent(this,Act_Inicio_Supervisor.class);
            Toast.makeText(this,"Cerro Sesion",Toast.LENGTH_LONG).show();
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}