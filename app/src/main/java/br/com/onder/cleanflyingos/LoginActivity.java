package br.com.onder.cleanflyingos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LoginActivity extends AppCompatActivity {

    String tipo;
    String nome;
    String textotipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Identifique-se");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Bypass

        Bundle params = new Bundle();

        params.putString("nome", "Marco");


        Intent intent2 = new Intent(LoginActivity.this, SincronismoActivity.class);
        //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent2.putExtras(params);
        startActivity(intent2);
        //
        finish();




    }

    public void scanQR(View view){

        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity


        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Mire o código na linha vermelha");
        //integrator.setResultDisplayDuration(0);
        //integrator.setCameraId(0);  // Use a specific camera of the device
        //integrator.initiateScan();
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        tipo = "";
        nome = "";
        //abrindo banco
        final BancoController crud = new BancoController(getBaseContext());

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();



            if(scanContent != null) {
                if (scanContent.contains("/")) {

                    String dados[] = scanContent.split("/");

                    tipo = dados[0];
                    nome = dados[1];

                } else {

                    tipo = "";
                    nome = "";

                }
            }
            textotipo = "LOGIN";

            if(tipo.equals("LOGIN")){
                Toast toast = Toast.makeText(getApplicationContext(), "TIPO OK: " + tipo, Toast.LENGTH_LONG);
                toast.show();

                Toast toast2 = Toast.makeText(getApplicationContext(), "TIPO TIPO;: " + tipo, Toast.LENGTH_LONG);
                toast2.show();

                //definindo a data
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                final String data = df.format(c.getTime());

                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                final String time = dft.format(c.getTime());

                String qtd = "0";

                crud.inserirlogin(nome, data);

                Bundle params = new Bundle();
                params.putString("nome", nome);


                    Intent intent2 = new Intent(LoginActivity.this, FichasActivity.class);
                    //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent2.putExtras(params);
                    startActivity(intent2);
                    //
                    finish();



            }else {

                //Toast toast = Toast.makeText(getApplicationContext(),"ESTE CÓDIGO NÃO É DE SEU CRACHÁ !!! "+ tipo, Toast.LENGTH_LONG);
                //toast.show();
            }

        }else{

            Toast toast = Toast.makeText(getApplicationContext(), "NÃO CONSEGUI LER, TENTE NOVAMENTE!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
