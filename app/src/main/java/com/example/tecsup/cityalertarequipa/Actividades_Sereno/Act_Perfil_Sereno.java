package com.example.tecsup.cityalertarequipa.Actividades_Sereno;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class Act_Perfil_Sereno extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nombre,DNI,correo,telefono,direccion,nombreapp;
    Cls_Persona sereno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_sereno);
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
        nombre = findViewById(R.id.nombre);
        DNI = findViewById(R.id.dni);
        correo = findViewById(R.id.correo);
        telefono = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);


        nombre.setText(sereno.getNombre()+" "+sereno.getApellido());
        DNI.setText(sereno.getDni()+"");
        correo.setText(sereno.getCorreo()+"");
        telefono.setText(sereno.getTelefono()+"");
        direccion.setText(sereno.getDireccion()+"");

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
