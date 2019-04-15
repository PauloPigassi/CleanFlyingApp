package br.com.onder.cleanflyingos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class FotoSaidaActivity extends AppCompatActivity {


    String nrFicha;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_saida);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setTitle("FOTO FIM");
        //getSupportActionBar().hide();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle !=null) {

            // resgata o valor do status bundle

            nrFicha = bundle.getString("nrFicha");

        }


        File imageFile = new File(Environment.getExternalStorageDirectory(),"CF/Saida/"+nrFicha +".jpg");

        if(imageFile.exists()){
            ImageView imageView= (ImageView) findViewById(R.id.iv_foto_saida);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));


            Bitmap bitmapImage = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            int nh = (int) ( bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);
            imageView.setImageBitmap(scaled);





            //faz a atualização do status no banco
            String sfoto = "1";



            //Log.d("LOG ", "FOTO EXISTE !!!");

        }




    }
}
