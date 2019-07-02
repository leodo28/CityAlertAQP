package com.example.tecsup.cityalertarequipa.Actividades;

import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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
import androidx.fragment.app.FragmentActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.R;

public class Act_Ubicacion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    Cls_Persona sup;
    TextView nombreapp;
    private GoogleMap mMap;

    Cls_Persona sereno1 = new Cls_Persona("Pepe","Paz","3247324525"
            ,"sereno1@gmail.com","934832424","S/N venezuela"
            ,-16.429299,-71.519191);

    Cls_Persona sereno2 = new Cls_Persona("Jose","Guerra","8721643834"
            ,"sereno2@gmail.com","987457359","S/N independencia"
            ,-16.431299,-71.529191);

    Cls_Persona sereno3 = new Cls_Persona("Patrick","Velasquez","9734774383"
            ,"sereno3@gmail.com","976463930","S/N goyeneche"
            ,-16.441299,-71.539191);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        Intent i = getIntent();
        sup=(Cls_Persona) i.getSerializableExtra("supervisor");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ser1 = new LatLng(sereno1.getLatitud(), sereno1.getLongitud());
        mMap.addMarker(new MarkerOptions().position(ser1).title(sereno1.getNombre()));
        LatLng ser2 = new LatLng(sereno2.getLatitud(), sereno2.getLongitud());
        mMap.addMarker(new MarkerOptions().position(ser2).title(sereno2.getNombre()));
        LatLng ser3 = new LatLng(sereno3.getLatitud(), sereno3.getLongitud());
        mMap.addMarker(new MarkerOptions().position(ser3).title(sereno3.getNombre()));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(ser1)
                .zoom(13)
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
        nombreapp.setText(sup.getNombre()+" "+sup.getApellido());
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
            Intent i = new Intent(this,Act_InicioSupervisor.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        } else if (id == R.id.serenos) {
            Intent i = new Intent(this,Act_Serenos.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        }else if (id == R.id.incidencias) {
            Intent i = new Intent(this,Act_Incidencia.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        }else if (id == R.id.ubicacion) {
            Intent i = new Intent(this,Act_Ubicacion.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        }else if (id == R.id.telefonos) {
            Intent i = new Intent(this,Act_TelefonoEmergencia.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        }else if (id == R.id.editar) {
            Intent i = new Intent(this,Act_Perfil.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        }else if (id == R.id.logout) {
            Intent i = new Intent(this,Act_InicioSupervisor.class);
            Toast.makeText(this,"Cerro Sesion",Toast.LENGTH_LONG).show();
            i.putExtra("supervisor",sup);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
