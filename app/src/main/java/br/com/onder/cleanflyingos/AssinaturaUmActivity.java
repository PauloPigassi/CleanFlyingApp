package br.com.onder.cleanflyingos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class AssinaturaUmActivity extends AppCompatActivity  {

    String nrFicha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assinatura_um);
        this.setTitle("ENTRADA - RESPONSÁVEL PELA AERONAVE");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            nrFicha = bundle.getString("nrFicha");
            //Log.i("Script", "nr ficha: "+nrFicha);

        }




        Button bt_gravar = (Button) findViewById(R.id.bt_ok_um);
        bt_gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeArquivo = nrFicha+"um";

                //PintarTela.persistSignatureAtSDCard(nrFicha);
                PintarTela.persistSignatureAtSDCard(nomeArquivo);

                //adsasdasd

                String uploadFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CF/Assinatura/";
                String uploadFileName = nrFicha + "um.png";
                String filename = uploadFilePath + uploadFileName;

                Toast.makeText(AssinaturaUmActivity.this,"Arquivo:: "+filename, Toast.LENGTH_LONG).show();

                File file = new File(filename);
                if (file.exists() == true)
                {

                    //Toast.makeText(AssinaturaUmActivity.this,"Arquivo existe: ", Toast.LENGTH_LONG).show();

                    // checa se o arquivo não está transparente
                    //String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                    //String filePath = baseDir + "/your_file_name.jpg";

                    // Toast.makeText(AssinaturaActivity.this,"Arquivo: possui assintaura ", Toast.LENGTH_LONG).show();
                    //atualiza banco de dados
                    String sassinatura = "1";
                    //assinok = 1;
                    //crud.update_assinatura(id_cad_giro_giro,sassinatura);


                }else{

                    //Toast.makeText(AssinaturaUmActivity.this,"Arquivo não existe: ", Toast.LENGTH_LONG).show();

                }


                finish();
            }




        });


    }
}
