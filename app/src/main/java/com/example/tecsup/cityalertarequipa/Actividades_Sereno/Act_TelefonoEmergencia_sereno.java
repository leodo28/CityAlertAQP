package com.example.tecsup.cityalertarequipa.Actividades_Sereno;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.R;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Act_TelefonoEmergencia_sereno extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Cls_Persona sereno;
    TextView nombreapp;
    LinearLayout policia,bombero,ambulancia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefonoemergencia_sereno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent i = getIntent();
        sereno=(Cls_Persona) i.getSerializableExtra("sereno");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        policia = findViewById(R.id.btn_policia);
        bombero = findViewById(R.id.btn_bomberos);
        ambulancia = findViewById(R.id.btn_ambulancia);

        policia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:105"));
                startActivity(i);
            }
        });

        bombero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:106"));
                startActivity(i);
            }
        });

        ambulancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:102"));
                startActivity(i);
            }
        });
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
