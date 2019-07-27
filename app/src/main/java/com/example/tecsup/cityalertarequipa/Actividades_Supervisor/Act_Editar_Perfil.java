package com.example.tecsup.cityalertarequipa.Actividades_Supervisor;

import android.content.Intent;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.R;

public class Act_Editar_Perfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Cls_Persona sup;
    TextView nombreapp,subtitulo;
    Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_supervisor);
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

        subtitulo = findViewById(R.id.subtitulo);

        editar = findViewById(R.id.btn_editar);
        Bundle b = i.getExtras();
        int o = b.getInt("codigo");
        if(!(o ==1)){
            editar.setText("Editar");
            subtitulo.setText("Editar Perfil");
        }
        else{
            editar.setText("Agregar");
            subtitulo.setText("Agregar Sereno");
        }
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            Intent i = new Intent(this,Act_Inicio_Supervisor.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        } else if (id == R.id.perfil) {
            Intent i = new Intent(this,Act_Perfil_Supervisor.class);
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
            Intent i = new Intent(this,Act_Editar_Perfil.class);
            i.putExtra("supervisor",sup);
            startActivity(i);
        }else if (id == R.id.logout) {
            Intent i = new Intent(this,Act_Inicio_Supervisor.class);
            Toast.makeText(this,"Cerro Sesion",Toast.LENGTH_LONG).show();
            i.putExtra("supervisor",sup);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
