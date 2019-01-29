package basico.android.cftic.edu.cajacolores.actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import basico.android.cftic.edu.cajacolores.R;

public class FotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        //así dibujo la flecha de navegación estandar atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void tomarFoto (View v)
    {
        Log.d("MIAPP", "QUIERO TOMAR UNA FOTO");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 500);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==500)//viene de mi petición de tirar mi foto
        {
            switch (resultCode)
            {
                case RESULT_OK:Log.d("MIAPP", "Tiró la foto bien");
                    try {
                        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                        Log.d("MIAPP", "URI = " +data.getData().toString());
                        ImageView im = (ImageView) findViewById(R.id.imageView);
                        im.setImageBitmap(thumbnail);
                    }catch (Throwable t)
                    {
                        Log.e("MIAPP", "ERROR AL SETEAR LA FOTO", t);
                    }
                    break;

                case RESULT_CANCELED:Log.d("MIAPP", "Canceló la foto");
                    break;

            }
        }

    }

    /**
     * Recibimos el evento sobre una opcion del menú superior
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO permitir cambiar de nombre al usuario
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.d("MIAPP", "Tocó ir hacia atrás");
                super.onBackPressed();
                break;
        }
        return true;
    }

}
