package com.example.tecsup.cityalertarequipa.Actividades_Sereno;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_Editar_Perfil_Supervisor;
import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_Incidencia_Supervisor;
import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_Perfil_Supervisor;
import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_SerenosaCargo_Supervisor;
import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_TelefonoEmergencia_Supervisor;
import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_Ubicacion;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Incidencia;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Act_Inicio_Sereno extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView num_inci_atendidos,num_inci_pendientes,nombreapp;
    LinearLayout inci_atendidos,inci_pendientes;
    Cls_Persona sereno;
    List<Cls_Incidencia> incidencias = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sereno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        sereno = new Cls_Persona("Pepito","Dospalotes","23684505",
                "p.dospalotes@gmail.com","948758935","psj s/n");

        /*Cls_Persona p1 = new Cls_Persona("Luis","Garcia","48743655",
                "luis@gmail.com","9475849554","psj s/n"
                ,-16.429299,-71.519191);
        Cls_Persona p2 = new Cls_Persona("Beto","Cruz","632438844",
                "beto@gmail.com","9873432984","psj s/n",
                -16.431299,-71.529191);*/

        num_inci_atendidos = findViewById(R.id.inc_atendidos);
        num_inci_pendientes = findViewById(R.id.inc_pendientes);

        Cls_Incidencia incidencia1=new Cls_Incidencia("24-07-19","20:20","robo",sereno,"Atendido");
        Cls_Incidencia incidencia2=new Cls_Incidencia("24-07-19","21:30","disturbio",sereno,"Atendido");

        incidencias.add(incidencia1);
        incidencias.add(incidencia2);


        inci_atendidos=findViewById(R.id.btn_inc_atendidos);
        inci_pendientes =findViewById(R.id.btn_inc_pendientes);


        num_inci_atendidos.setText(incidencias.size()+"");
        num_inci_pendientes.setText(incidencias.size()+"");

        inci_atendidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Act_Incidencia_sereno.class);
                i.putExtra("sereno",sereno);
                startActivity(i);
            }
        });

        inci_pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Act_Incidencia_sereno.class);
                i.putExtra("sereno",sereno);
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
        nombreapp=findViewById(R.id.Nombreapp);
        nombreapp.setText(sereno.getNombre()+" "+sereno.getApellido());
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

        if (id == R.id.inicio2) {
            Intent i = new Intent(this,Act_Inicio_Sereno.class);
            i.putExtra("sereno",sereno);
            startActivity(i);
        }else if (id == R.id.perfil2) {
            Intent i = new Intent(this,Act_Perfil_Sereno.class);
            i.putExtra("sereno",sereno);
            startActivity(i);
        }else if (id == R.id.incidencias2) {
            Intent i = new Intent(this,Act_Incidencia_sereno.class);
            i.putExtra("sereno",sereno);
            startActivity(i);
        }else if (id == R.id.telefonos2) {
            Intent i = new Intent(this,Act_TelefonoEmergencia_sereno.class);
            i.putExtra("sereno",sereno);
            startActivity(i);
        }else if (id == R.id.editar2) {
            Intent i = new Intent(this,Act_Editar_Perfil_Sereno.class);
            i.putExtra("sereno",sereno);
            startActivity(i);
        }else if (id == R.id.logout2) {
            Intent i = new Intent(this,Act_Perfil_Sereno.class);
            Toast.makeText(this,"Cerro Sesion",Toast.LENGTH_LONG).show();
            i.putExtra("sereno",sereno);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}