package com.example.tecsup.cityalertarequipa.Actividades_Supervisor;

import android.content.Intent;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Incidencia;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Act_Ubicacion_Supervisor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    Cls_Persona sup;
    String sereno_sele=new String();
    TextView nombreapp;
    List<Cls_Incidencia> incidencias = new ArrayList<>();
    private GoogleMap mMap;
    List<Cls_Persona> serenos_list = new ArrayList<Cls_Persona>();
/*
    Cls_Persona sereno1 = new Cls_Persona("48743655","Luis Alberto","Garcia"," Lopez",
            "luis@gmail.com","psj s/n"
            ,"9475849554",-16.429329,-71.519363);
    Cls_Persona sereno2 = new Cls_Persona("632438844","Alberto Pepe","Cruz"," Rivera",
            "beto@gmail.com","psj s/n","9873432984",
            -16.429749,-71.519783);

    Cls_Persona sereno3 = new Cls_Persona("632438844","Alberto Pepe","Cruz"," Rivera",
            "beto@gmail.com","psj s/n","9873432984",
            -16.428889,-71.518843);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_supervisor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent i = getIntent();
        sup=(Cls_Persona) i.getSerializableExtra("supervisor");
        sup.setLatitud(-16.429749);
        sup.setLongitud(-71.519783);
        serenos_list=(List<Cls_Persona>)i.getSerializableExtra("serenos");
        incidencias = (List<Cls_Incidencia>)i.getSerializableExtra("incidencias");
        serenos_list.get(0).setLatitud(-16.429329);
        serenos_list.get(0).setLongitud(-71.519363);
        serenos_list.get(1).setLatitud(-16.429749);
        serenos_list.get(1).setLongitud(-71.519783);
        serenos_list.get(2).setLatitud(-16.428889);
        serenos_list.get(2).setLongitud(-71.518843);
        sereno_sele = (String) i.getSerializableExtra("sereno_selec");


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i =0;i<serenos_list.size();i++){
            if(serenos_list.get(i).getLongitud()!=0){
                if(sereno_sele!=null && sereno_sele.equals(serenos_list.get(i).getNombres()+" "+serenos_list.get(i).getApellidopaterno())){
                    LatLng ser = new LatLng(serenos_list.get(i).getLatitud(), serenos_list.get(i).getLongitud());
                    mMap.addMarker(new MarkerOptions().position(ser).title(serenos_list.get(i).getNombres()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                }else{
                LatLng ser = new LatLng(serenos_list.get(i).getLatitud(), serenos_list.get(i).getLongitud());
                mMap.addMarker(new MarkerOptions().position(ser).title(serenos_list.get(i).getNombres()));}
            }
        }
        LatLng superv = new LatLng(sup.getLatitud(), sup.getLongitud());
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(superv)
                .zoom(15)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
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
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        } else if (id == R.id.perfil) {
            Intent i = new Intent(this,Act_Perfil_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        } else if (id == R.id.serenos) {
            Intent i = new Intent(this,Act_SerenosaCargo_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        }else if (id == R.id.incidencias) {
            Intent i = new Intent(this,Act_Incidencia_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        }else if (id == R.id.ubicacion) {
            Intent i = new Intent(this,Act_Ubicacion_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        }else if (id == R.id.telefonos) {
            Intent i = new Intent(this,Act_TelefonoEmergencia_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        }else if (id == R.id.editar) {
            Intent i = new Intent(this,Act_Editar_Perfil_Supervisor.class);
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        }else if (id == R.id.logout) {
            Intent i = new Intent(this,Act_Inicio_Supervisor.class);
            Toast.makeText(this,"Cerro Sesion",Toast.LENGTH_LONG).show();
            i.putExtra("supervisor",sup);
            i.putExtra("serenos", (Serializable) serenos_list);
            i.putExtra("incidencias", (Serializable) incidencias);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
