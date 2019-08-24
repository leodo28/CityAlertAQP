package com.example.tecsup.city_alert_aqp_ale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Registro extends AppCompatActivity {
    EditText nomb, apell, u_dni, celu, email, direcc, pass;
    Button registro;
    ImageView fotoperfil;
    Button btn_cambiarfoto;
    ConstraintLayout mView;

    private String APP_DIRECTORY="myPictureApp/";
    private String MEDIA_DIRECTORY= APP_DIRECTORY+"media";
    private String TEMPORAL_PICTURE_NAME="temporal.jpg";

    private final int PHOTO_CODE = 100;
    private final int MY_PERMISSIONS = 200;
    private final int SELECT_PICTURE = 300;

    private String mpath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        getSupportActionBar().setTitle("REGISTRO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nomb = findViewById(R.id.edt_nombre);
        apell = findViewById(R.id.edt_apellido);
        u_dni = findViewById(R.id.edt_dni);
        celu = findViewById(R.id.edt_celu);
        email = findViewById(R.id.edt_correo);
        direcc = findViewById(R.id.edt_direccion);
        pass = findViewById(R.id.edt_contraseña);
        fotoperfil=findViewById(R.id.fotoperfil);
        btn_cambiarfoto=findViewById(R.id.btn_foto);
        mView=findViewById(R.id.mview);

        if(myRequestStoragePermiso())
            btn_cambiarfoto.setEnabled(true);
        else
            btn_cambiarfoto.setEnabled(false);



        btn_cambiarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] opcciones = {"Tomar foto","Elegir de galeria","Cancelar"};


                //fotoperfil.setBackground();
                Bitmap bitmapfoto=BitmapFactory.decodeResource(getResources(),R.id.fotoperfil);
                RoundedBitmapDrawable roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(),bitmapfoto);
                roundedBitmapDrawable.setCircular(true);
                fotoperfil.setImageDrawable(roundedBitmapDrawable);
                final AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                builder.setTitle("Elegir una opcion: ");
                builder.setItems(opcciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if(opcciones[seleccion]=="Tomar foto"){
                            openCamera();
                        }else if (opcciones[seleccion]=="Elegir de galeria"){
                            Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent,"Seleccione de donde elegir sus images"),SELECT_PICTURE);
                        }else if(opcciones[seleccion]=="Cancelar"){
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();


            }
        });





    }
    private boolean myRequestStoragePermiso(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&& (checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED))
            return true;
        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))|| (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(mView,"Los permisos son necesarios para poder usar la aplicacion",Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},MY_PERMISSIONS);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},MY_PERMISSIONS);
        }
        return false;
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();
        if(!isDirectoryCreated)
            isDirectoryCreated=file.mkdirs();
        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis()/1000;
            String imageName = timestamp.toString()+".jpg";
            mpath = Environment.getExternalStorageDirectory()+File.separator+MEDIA_DIRECTORY+File.separator+imageName;
            File newfile = new File(mpath);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newfile));
            startActivityForResult(intent, PHOTO_CODE);

        }




    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file-path",mpath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mpath = savedInstanceState.getString("file-path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this, new String[]{mpath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage","Scanned"+ path+":");
                        Log.i("ExternalStorage","->Uri = "+uri);
                        }
                    });

                Bitmap bitmap = BitmapFactory.decodeFile(mpath);
                fotoperfil.setImageBitmap(bitmap);


                    break;
                case SELECT_PICTURE:
                    if (resultCode == RESULT_OK) {
                        Uri path = data.getData();
                        fotoperfil.setImageURI(path);
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_PERMISSIONS){
            if(grantResults.length==2&&grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Permisos aceptados",Toast.LENGTH_LONG).show();
                btn_cambiarfoto.setEnabled(true);
            }else{
                mostrarExplanation();
            }
        }
    }

    private void mostrarExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        builder.setTitle("Permiso Denegado");
        builder.setMessage("Para usar las funciones de la App necesitas permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri  = Uri.fromParts("package", getPackageName(),null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();

    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        fotoperfil.setImageBitmap(bitmap);
    }

    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mn_equiz, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                retornar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.cerrar_vista:
                retornar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
    }

        private void retornar() {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            finish();
            startActivity(intent);
        }


}
