package com.example.tecsup.cityalertarequipa.Actividades_Supervisor;

import android.content.Intent;

import com.example.tecsup.cityalertarequipa.Adaptadores.AdapterIncidencia;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Incidencia;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Act_Incidencia_Supervisor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Cls_Persona sup;
    TextView nombreapp;
    List<Cls_Incidencia> incidencias = new ArrayList<>();
    ListView lv;
    List<Cls_Persona> serenos_list = new ArrayList<>();
    AdapterIncidencia adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia_supervisor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        sup=(Cls_Persona) i.getSerializableExtra("supervisor");
        serenos_list=(List<Cls_Persona>)i.getSerializableExtra("serenos");

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

        Cls_Persona p1 = new Cls_Persona("48743655","Luis Alberto","Garcia"," Lopez",
                "luis@gmail.com","psj s/n"
                ,"9475849554",-16.429299,-71.519191);
        Cls_Persona p2 = new Cls_Persona("632438844","Alberto Pepe","Cruz"," Rivera",
                "beto@gmail.com","psj s/n","9873432984",
                -16.431299,-71.529191);

        Cls_Incidencia incidencia1=new Cls_Incidencia("24-07-19","20:20","robo",p1,"Atendido");
        Cls_Incidencia incidencia2=new Cls_Incidencia("24-07-19","21:30","disturbio",p1,"Atendido");

        incidencias.add(incidencia1);
        incidencias.add(incidencia2);

        lv= findViewById(R.id.listv);

        adaptador = new AdapterIncidencia(incidencias,getApplicationContext(),R.layout.item_incidencia);
        lv.setAdapter(adaptador);

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
        nombreapp.setText(sup.getNombres()+" "+sup.getApellidopaterno()+" "+sup.getApellidomaterno());
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
