package br.com.onder.cleanflyingos;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CkUmActivity extends AppCompatActivity {


    String nrFicha, prefixo, modelo, cliente, setor, dt_inicio, dt_termino, hr_inicio, hr_termino, obsObs;
    String valorRgUm, valorRgDois, valorRgTres, valorRgQuatro, valorRgCinco, valorRgSeis, valorRgSete, valorRgOito, valorRgNove, valorRgDez, valorRgOnze, valorRgDoze;
    TextView tv_visualizar, tv_prefixo, tv_modelo, tv_cliente, tv_setor, tv_dt_inicio, tv_dt_termino, tv_hr_inicio, tv_hr_termino;
    TextView tv_ck1, tv_ck2, tv_ck3, tv_ck4, tv_ck5, tv_ck6, tv_ck7, tv_ck8, tv_ck9, tv_ck10, tv_ck11, tv_ck12;
    String flag;
    String obsUm, obsDois, obsTres, obsQuatro, obsCinco, obsSeis, obsSete, obsOito, obsNove, obsDez, obsOnze, obsDoze;
    static int TAKE_PIC = 1;

    Uri outPutfileUri;
    ImageView imgClick;
    LinearLayout linearLayout;
    private RadioButton radioButtonUm, radioButtonDois, radioButtonTres, radioButtonQuatro, radioButtonCinco, radioButtonSeis, radioButtonSete, radioButtonOito, radioButtonNove, radioButtonDez, radioButtonOnze, radioButtonDoze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_um);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

/*
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(CkUmActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(CkUmActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(CkUmActivity.this,
                        new String[]{Manifest.permission.CA},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            }
        }
        else
        {


        }

*/
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
               // Log.v("TAG", "Permission is granted");
                //File write logic here
                //return true;
            }
        }



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            nrFicha = bundle.getString("nrFicha");
            prefixo = bundle.getString("prefixo");
            modelo = bundle.getString("modelo");
            cliente = bundle.getString("cliente");
            setor = bundle.getString("setor");
            obsObs = bundle.getString("obsObs");
            dt_inicio = bundle.getString("dt_inicio");
            hr_inicio = bundle.getString("hr_inicio");
            dt_termino = bundle.getString("dt_termino");
            hr_termino = bundle.getString("hr_termino");


            //Log.i("Script", "position: "+position);

        }

        final BancoController crud = new BancoController(getBaseContext());

        tv_prefixo = (TextView) findViewById(R.id.tv_ck1_prefixo);
        tv_modelo = (TextView) findViewById(R.id.tv_ck1_modelo);
        tv_cliente = (TextView) findViewById(R.id.tv_ck1_cliente);
        tv_setor = (TextView) findViewById(R.id.tv_ck1_setor);
//        tv_dt_inicio = (TextView) findViewById(R.id.tv_ck1_data_inicio);
//        tv_dt_termino = (TextView) findViewById(R.id.tv_ck1_data_termino);
//        tv_hr_inicio = (TextView) findViewById(R.id.tv_ck1_hora_inicio);
//        tv_hr_termino = (TextView) findViewById(R.id.tv_ck1_hora_termino);
        tv_visualizar = (TextView) findViewById(R.id.tv_ck1_visualizar);

        tv_prefixo.setText(prefixo);
        tv_modelo.setText(modelo);
        tv_cliente.setText(cliente);
        tv_setor.setText(setor);
//        tv_dt_inicio.setText(dt_inicio);
//        tv_dt_termino.setText(dt_termino);
//        tv_hr_inicio.setText(hr_inicio);
//        tv_hr_termino.setText(hr_termino);


        //Verifica se a pasta de foto existe
        //Cria pastas se as mesmas não existirem
        File foldercf = new File(Environment.getExternalStorageDirectory() + "/CF");
        boolean successcf = true;
        if (!foldercf.exists()) {

            successcf = foldercf.mkdir();

        }



        //Log.d("MainActivity", ">> Let's debug why this directory isn't being created: ");
        //Log.d("MainActivity", "Is it working?: " + foldercf.mkdirs());
        //Log.d("MainActivity", "Does it exist?: " + foldercf.exists());
        //Log.d("MainActivity", "What is the full URI?: " + foldercf.toURI());
        //Log.d("MainActivity", "--");
        //Log.d("MainActivity", "Can we write to this file?: " + foldercf.canWrite());
        if (!foldercf.canWrite()) {
            //Log.d("MainActivity", ">> We can't write! Do we have WRITE_EXTERNAL_STORAGE permission?");
            if (getBaseContext().checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_DENIED) {
               // Log.d("MainActivity", ">> We don't have permission to write - please add it.");
            } else {
                //Log.d("MainActivity", "We do have permission - the problem lies elsewhere.");
            }
        }
        //Log.d("MainActivity", "Are we even allowed to read this file?: " + foldercf.canRead());
       // Log.d("MainActivity", "--");
        //Log.d("MainActivity", ">> End of debugging.");



        File foldercfentrada = new File(Environment.getExternalStorageDirectory() + "/CF/Entrada");
        boolean successentrada = true;
        if (!foldercfentrada.exists()) {
            successentrada = foldercfentrada.mkdir();
        }

        File foldercfsaida = new File(Environment.getExternalStorageDirectory() + "/CF/Saida");
        boolean successsaida = true;
        if (!foldercfsaida.exists()) {
            successsaida = foldercfsaida.mkdir();
        }

        File folderass = new File(Environment.getExternalStorageDirectory() + "/CF/Assinatura");
        boolean success = true;
        if (!folderass.exists()) {
            success = folderass.mkdir();
        }

        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg_um);
        final RadioGroup rg2 = (RadioGroup) findViewById(R.id.rg_dois);
        final RadioGroup rg3 = (RadioGroup) findViewById(R.id.rg_tres);
        final RadioGroup rg4 = (RadioGroup) findViewById(R.id.rg_quatro);
        final RadioGroup rg5 = (RadioGroup) findViewById(R.id.rg_cinco);
        final RadioGroup rg6 = (RadioGroup) findViewById(R.id.rg_seis);
        final RadioGroup rg7 = (RadioGroup) findViewById(R.id.rg_sete);
        final RadioGroup rg8 = (RadioGroup) findViewById(R.id.rg_oito);
        final RadioGroup rg9 = (RadioGroup) findViewById(R.id.rg_nove);
        final RadioGroup rg10 = (RadioGroup) findViewById(R.id.rg_dez);
        final RadioGroup rg11 = (RadioGroup) findViewById(R.id.rg_onze);
        final RadioGroup rg12 = (RadioGroup) findViewById(R.id.rg_doze);

        //apagar radio group
        tv_ck1 = (TextView) findViewById(R.id.tv_ck1);
        tv_ck2 = (TextView) findViewById(R.id.tv_ck2);
        tv_ck3 = (TextView) findViewById(R.id.tv_ck3);
        tv_ck4 = (TextView) findViewById(R.id.tv_ck4);
        tv_ck5 = (TextView) findViewById(R.id.tv_ck5);
        tv_ck6 = (TextView) findViewById(R.id.tv_ck6);
        tv_ck7 = (TextView) findViewById(R.id.tv_ck7);
        tv_ck8 = (TextView) findViewById(R.id.tv_ck8);
        tv_ck9 = (TextView) findViewById(R.id.tv_ck9);
        tv_ck10 = (TextView) findViewById(R.id.tv_ck10);
        tv_ck11 = (TextView) findViewById(R.id.tv_ck11);
        tv_ck12 = (TextView) findViewById(R.id.tv_ck12);

        //Recuperando valores dos radios
        Cursor cur = crud.buscarValoresCklUm(nrFicha);
        try {
            valorRgUm = cur.getString(cur.getColumnIndex("porta_escada"));
            valorRgDois = cur.getString(cur.getColumnIndex("pilot"));
            valorRgTres = cur.getString(cur.getColumnIndex("para_brisas"));
            valorRgQuatro = cur.getString(cur.getColumnIndex("para_sol"));
            valorRgCinco = cur.getString(cur.getColumnIndex("radone"));
            valorRgSeis = cur.getString(cur.getColumnIndex("trem_principal"));
            valorRgSete = cur.getString(cur.getColumnIndex("bordos_ataque"));
            valorRgOito = cur.getString(cur.getColumnIndex("descarregadores"));
            valorRgNove = cur.getString(cur.getColumnIndex("flaps"));
            valorRgDez = cur.getString(cur.getColumnIndex("motores"));
            valorRgOnze = cur.getString(cur.getColumnIndex("helices"));
            valorRgDoze = cur.getString(cur.getColumnIndex("farois"));

        } finally {
            if (cur != null) {
                cur.close();
            }
        }




        RadioButton rb1A =(RadioButton)findViewById(R.id.radioButton1A);
        RadioButton rb1R =(RadioButton)findViewById(R.id.radioButton1R);
        RadioButton rb1X =(RadioButton)findViewById(R.id.radioButton1X);
        RadioButton rb1F =(RadioButton)findViewById(R.id.radioButton1F);
        RadioButton rb1O =(RadioButton)findViewById(R.id.radioButton1O);

        RadioButton rb2A =(RadioButton)findViewById(R.id.radioButton2A);
        RadioButton rb2R =(RadioButton)findViewById(R.id.radioButton2R);
        RadioButton rb2X =(RadioButton)findViewById(R.id.radioButton2X);
        RadioButton rb2F =(RadioButton)findViewById(R.id.radioButton2F);
        RadioButton rb2O =(RadioButton)findViewById(R.id.radioButton2O);

        RadioButton rb3A =(RadioButton)findViewById(R.id.radioButton3A);
        RadioButton rb3R =(RadioButton)findViewById(R.id.radioButton3R);
        RadioButton rb3X =(RadioButton)findViewById(R.id.radioButton3X);
        RadioButton rb3F =(RadioButton)findViewById(R.id.radioButton3F);
        RadioButton rb3O =(RadioButton)findViewById(R.id.radioButton3O);

        RadioButton rb4A =(RadioButton)findViewById(R.id.radioButton4A);
        RadioButton rb4R =(RadioButton)findViewById(R.id.radioButton4R);
        RadioButton rb4X =(RadioButton)findViewById(R.id.radioButton4X);
        RadioButton rb4F =(RadioButton)findViewById(R.id.radioButton4F);
        RadioButton rb4O =(RadioButton)findViewById(R.id.radioButton4O);

        RadioButton rb5A =(RadioButton)findViewById(R.id.radioButton5A);
        RadioButton rb5R =(RadioButton)findViewById(R.id.radioButton5R);
        RadioButton rb5X =(RadioButton)findViewById(R.id.radioButton5X);
        RadioButton rb5F =(RadioButton)findViewById(R.id.radioButton5F);
        RadioButton rb5O =(RadioButton)findViewById(R.id.radioButton5O);

        RadioButton rb6A =(RadioButton)findViewById(R.id.radioButton6A);
        RadioButton rb6R =(RadioButton)findViewById(R.id.radioButton6R);
        RadioButton rb6X =(RadioButton)findViewById(R.id.radioButton6X);
        RadioButton rb6F =(RadioButton)findViewById(R.id.radioButton6F);
        RadioButton rb6O =(RadioButton)findViewById(R.id.radioButton6O);

        RadioButton rb7A =(RadioButton)findViewById(R.id.radioButton7A);
        RadioButton rb7R =(RadioButton)findViewById(R.id.radioButton7R);
        RadioButton rb7X =(RadioButton)findViewById(R.id.radioButton7X);
        RadioButton rb7F =(RadioButton)findViewById(R.id.radioButton7F);
        RadioButton rb7O =(RadioButton)findViewById(R.id.radioButton7O);

        RadioButton rb8A =(RadioButton)findViewById(R.id.radioButton8A);
        RadioButton rb8R =(RadioButton)findViewById(R.id.radioButton8R);
        RadioButton rb8X =(RadioButton)findViewById(R.id.radioButton8X);
        RadioButton rb8F =(RadioButton)findViewById(R.id.radioButton8F);
        RadioButton rb8O =(RadioButton)findViewById(R.id.radioButton8O);

        RadioButton rb9A =(RadioButton)findViewById(R.id.radioButton9A);
        RadioButton rb9R =(RadioButton)findViewById(R.id.radioButton9R);
        RadioButton rb9X =(RadioButton)findViewById(R.id.radioButton9X);
        RadioButton rb9F =(RadioButton)findViewById(R.id.radioButton9F);
        RadioButton rb9O =(RadioButton)findViewById(R.id.radioButton9O);

        RadioButton rb10A =(RadioButton)findViewById(R.id.radioButton10A);
        RadioButton rb10R =(RadioButton)findViewById(R.id.radioButton10R);
        RadioButton rb10X =(RadioButton)findViewById(R.id.radioButton10X);
        RadioButton rb10F =(RadioButton)findViewById(R.id.radioButton10F);
        RadioButton rb10O =(RadioButton)findViewById(R.id.radioButton10O);

        RadioButton rb11A =(RadioButton)findViewById(R.id.radioButton11A);
        RadioButton rb11R =(RadioButton)findViewById(R.id.radioButton11R);
        RadioButton rb11X =(RadioButton)findViewById(R.id.radioButton11X);
        RadioButton rb11F =(RadioButton)findViewById(R.id.radioButton11F);
        RadioButton rb11O =(RadioButton)findViewById(R.id.radioButton11O);

        RadioButton rb12A =(RadioButton)findViewById(R.id.radioButton12A);
        RadioButton rb12R =(RadioButton)findViewById(R.id.radioButton12R);
        RadioButton rb12X =(RadioButton)findViewById(R.id.radioButton12X);
        RadioButton rb12F =(RadioButton)findViewById(R.id.radioButton12F);
        RadioButton rb12O =(RadioButton)findViewById(R.id.radioButton12O);


        if(valorRgUm.equals("A")){rb1A.setChecked(true);}
        if(valorRgUm.equals("R")){rb1R.setChecked(true);}
        if(valorRgUm.equals("X")){rb1X.setChecked(true);}
        if(valorRgUm.equals("F")){rb1F.setChecked(true);}
        if(valorRgUm.equals("O")){rb1O.setChecked(true);}

        if(valorRgDois.equals("A")){rb2A.setChecked(true);}
        if(valorRgDois.equals("R")){rb2R.setChecked(true);}
        if(valorRgDois.equals("X")){rb2X.setChecked(true);}
        if(valorRgDois.equals("F")){rb2F.setChecked(true);}
        if(valorRgDois.equals("O")){rb2O.setChecked(true);}

        if(valorRgTres.equals("A")){rb3A.setChecked(true);}
        if(valorRgTres.equals("R")){rb3R.setChecked(true);}
        if(valorRgTres.equals("X")){rb3X.setChecked(true);}
        if(valorRgTres.equals("F")){rb3F.setChecked(true);}
        if(valorRgTres.equals("O")){rb3O.setChecked(true);}

        if(valorRgQuatro.equals("A")){rb4A.setChecked(true);}
        if(valorRgQuatro.equals("R")){rb4R.setChecked(true);}
        if(valorRgQuatro.equals("X")){rb4X.setChecked(true);}
        if(valorRgQuatro.equals("F")){rb4F.setChecked(true);}
        if(valorRgQuatro.equals("O")){rb4O.setChecked(true);}

        if(valorRgCinco.equals("A")){rb5A.setChecked(true);}
        if(valorRgCinco.equals("R")){rb5R.setChecked(true);}
        if(valorRgCinco.equals("X")){rb5X.setChecked(true);}
        if(valorRgCinco.equals("F")){rb5F.setChecked(true);}
        if(valorRgCinco.equals("O")){rb5O.setChecked(true);}

        if(valorRgSeis.equals("A")){rb6A.setChecked(true);}
        if(valorRgSeis.equals("R")){rb6R.setChecked(true);}
        if(valorRgSeis.equals("X")){rb6X.setChecked(true);}
        if(valorRgSeis.equals("F")){rb6F.setChecked(true);}
        if(valorRgSeis.equals("O")){rb6O.setChecked(true);}

        if(valorRgSete.equals("A")){rb7A.setChecked(true);}
        if(valorRgSete.equals("R")){rb7R.setChecked(true);}
        if(valorRgSete.equals("X")){rb7X.setChecked(true);}
        if(valorRgSete.equals("F")){rb7F.setChecked(true);}
        if(valorRgSete.equals("O")){rb7O.setChecked(true);}


        if(valorRgOito.equals("A")){rb8A.setChecked(true);}
        if(valorRgOito.equals("R")){rb8R.setChecked(true);}
        if(valorRgOito.equals("X")){rb8X.setChecked(true);}
        if(valorRgOito.equals("F")){rb8F.setChecked(true);}
        if(valorRgOito.equals("O")){rb8O.setChecked(true);}

        if(valorRgNove.equals("A")){rb9A.setChecked(true);}
        if(valorRgNove.equals("R")){rb9R.setChecked(true);}
        if(valorRgNove.equals("X")){rb9X.setChecked(true);}
        if(valorRgNove.equals("F")){rb9F.setChecked(true);}
        if(valorRgNove.equals("O")){rb9O.setChecked(true);}

        if(valorRgDez.equals("A")){rb10A.setChecked(true);}
        if(valorRgDez.equals("R")){rb10R.setChecked(true);}
        if(valorRgDez.equals("X")){rb10X.setChecked(true);}
        if(valorRgDez.equals("F")){rb10F.setChecked(true);}
        if(valorRgDez.equals("O")){rb10O.setChecked(true);}

        if(valorRgOnze.equals("A")){rb11A.setChecked(true);}
        if(valorRgOnze.equals("R")){rb11R.setChecked(true);}
        if(valorRgOnze.equals("X")){rb11X.setChecked(true);}
        if(valorRgOnze.equals("F")){rb11F.setChecked(true);}
        if(valorRgOnze.equals("O")){rb11O.setChecked(true);}

        if(valorRgDoze.equals("A")){rb12A.setChecked(true);}
        if(valorRgDoze.equals("R")){rb12R.setChecked(true);}
        if(valorRgDoze.equals("X")){rb12X.setChecked(true);}
        if(valorRgDoze.equals("F")){rb12F.setChecked(true);}
        if(valorRgDoze.equals("O")){rb12O.setChecked(true);}



        //Observações radio
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "porta_escada_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("porta_escada_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "porta_escada_obs", obs);
                            dialog.dismiss();
                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "pilot_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("pilot_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "pilot_obs", obs);
                            dialog.dismiss();
                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "para_brisas_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("para_brisas_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "para_brisas_obs", obs);
                            dialog.dismiss();
                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "para_sol_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("para_sol_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "para_sol_obs", obs);
                            dialog.dismiss();
                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "radone_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("radone_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "radone_obs", obs);
                            dialog.dismiss();
                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "trem_principal_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("trem_principal_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "trem_principal_obs", obs);
                            dialog.dismiss();

                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "bordos_ataque_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("bordos_ataque_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "bordos_ataque_obs", obs);
                            dialog.dismiss();

                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        rg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "descarregadores_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("descarregadores_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "descarregadores_obs", obs);
                            dialog.dismiss();

                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        rg9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "flaps_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("flaps_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "flaps_obs", obs);
                            dialog.dismiss();

                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        rg10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "motores_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("motores_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "motores_obs", obs);
                            dialog.dismiss();

                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });



        rg11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "helices_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("helices_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "helices_obs", obs);
                            dialog.dismiss();

                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });




            rg12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "farois_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("farois_obs"));
                    if (obstext.equals("0")) {
                    } else {

                        edit.setText(obstext);
                    }
                } finally {
                    if (cucon != null) {
                        cucon.close();
                    }
                }
                Button button = (Button) dialog.findViewById(R.id.bt_dialog_gravar);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String obs = edit.getText().toString();
                        if (TextUtils.isEmpty(obs)) {
                            Toast toast44 = Toast.makeText(CkUmActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "farois_obs", obs);
                            dialog.dismiss();

                        }
                    }
                });
                Button dialogButtoncancccv = (Button) dialog.findViewById(R.id.bt_dialog_cancelar);
                // if button is clicked, close the custom dialog
                dialogButtoncancccv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        tv_ck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg1.clearCheck();
            }
        });
        tv_ck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg2.clearCheck();
            }
        });
        tv_ck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg3.clearCheck();
            }
        });
        tv_ck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg4.clearCheck();
            }
        });
        tv_ck5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg5.clearCheck();
            }
        });
        tv_ck6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg6.clearCheck();
            }
        });
        tv_ck7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg7.clearCheck();
            }
        });
        tv_ck8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg8.clearCheck();
            }
        });
        tv_ck9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg9.clearCheck();
            }
        });
        tv_ck10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg10.clearCheck();
            }
        });
        tv_ck11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg11.clearCheck();
            }
        });
        tv_ck12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg12.clearCheck();
            }
        });


        //Clique em outra aba
        //ll_serv_ckdois
        linearLayout = (LinearLayout) findViewById(R.id.ll_ckum_ckdois);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                grava();

                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);
                params.putString("prefixo", prefixo);
                params.putString("modelo", modelo);
                params.putString("cliente", cliente);
                params.putString("setor", setor);
                params.putString("dt_inicio", dt_inicio);
                params.putString("dt_termino", dt_termino);
                params.putString("hr_inicio", hr_inicio);
                params.putString("hr_termino", hr_termino);
                params.putString("obsObs", obsObs);

                Intent intent2 = new Intent(CkUmActivity.this, CkDoisActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.ll_ckum_serv);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);
                params.putString("prefixo", prefixo);
                params.putString("modelo", modelo);
                params.putString("cliente", cliente);
                params.putString("setor", setor);
                params.putString("dt_inicio", dt_inicio);
                params.putString("dt_termino", dt_termino);
                params.putString("hr_inicio", hr_inicio);
                params.putString("hr_termino", hr_termino);
                params.putString("obsObs", obsObs);




                grava();

                Intent intent2 = new Intent(CkUmActivity.this, CadastroServicoActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });




        //Cuidando da lengenda

        ImageView legenda = (ImageView) findViewById(R.id.iv_ck1_legenda);
        legenda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(CkUmActivity.this);
                dialog.setContentView(R.layout.dialog_legenda);
                dialog.setTitle("Legenda");


                dialog.show();

            }

        });


        //Checando se a foto já foi tirada
        ImageView img = (ImageView) findViewById(R.id.iv_eye);

        //Hack para habilitar formulario mesmo sem tirar a foto
        String semFoto = "sim";




        //File file = new File(Environment.getExternalStorageDirectory(), "CF/Entrada/" + nrFicha + ".jpg");
        File file = new File(Environment.getExternalStorageDirectory(), "/entrada_"+nrFicha+".jpg");
        //if (file.exists()) {
        if (semFoto.equals("sim")) {

            //Do something


            img.setImageResource(R.drawable.eye);
            img.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Bundle params = new Bundle();
                    params.putString("nrFicha", nrFicha);

                    //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(CkUmActivity.this, FotoEntradaActivity.class);
                    i.putExtras(params);
                    startActivity(i);


                }
            });


            //Habilito fomulario
            for (int i = 0; i < rg1.getChildCount(); i++) {
                rg1.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg2.getChildCount(); i++) {
                rg2.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg3.getChildCount(); i++) {
                rg3.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg4.getChildCount(); i++) {
                rg4.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg5.getChildCount(); i++) {
                rg5.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg6.getChildCount(); i++) {
                rg6.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg7.getChildCount(); i++) {
                rg7.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg8.getChildCount(); i++) {
                rg8.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg9.getChildCount(); i++) {
                rg9.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg10.getChildCount(); i++) {
                rg10.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg11.getChildCount(); i++) {
                rg11.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg12.getChildCount(); i++) {
                rg12.getChildAt(i).setEnabled(true);
            }


        } else {
            //Nothing

            //9A9EA1

            img.setImageResource(R.drawable.eyeb);
            tv_visualizar.setTextColor(Color.parseColor("#9A9EA1"));

            //Desabilito fomulario
            for (int i = 0; i < rg1.getChildCount(); i++) {
                rg1.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg2.getChildCount(); i++) {
                rg2.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg3.getChildCount(); i++) {
                rg3.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg4.getChildCount(); i++) {
                rg4.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg5.getChildCount(); i++) {
                rg5.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg6.getChildCount(); i++) {
                rg6.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg7.getChildCount(); i++) {
                rg7.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg8.getChildCount(); i++) {
                rg8.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg9.getChildCount(); i++) {
                rg9.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg10.getChildCount(); i++) {
                rg10.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg11.getChildCount(); i++) {
                rg11.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg12.getChildCount(); i++) {
                rg12.getChildAt(i).setEnabled(false);
            }


        }


    }


    public void tirarFoto(View view) {


        Bundle params = getIntent().getExtras();
        String nrFicha = params.getString("nrFicha");
        //String hora_inicio = params.getString("hora_inicio");
        //Toast.makeText(LocalDemolidoActivity.this,"ID: " + nro_form, Toast.LENGTH_LONG).show();

        flag = "1";

        /*

        //Toast.makeText(LocalDemolidoActivity.this,"FLAG: " + flag, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = new File(Environment.getExternalStorageDirectory(), "CF/Entrada/" + nrFicha + ".jpg");

        outPutfileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, TAKE_PIC);

*/
        String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/entrada_"+nrFicha+".jpg";
        File imageFile = new File(imageFilePath);

       ;
       // Uri imageFileUri = Uri.fromFile(imageFile); // convert path to Uri

        Uri imageFileUri = FileProvider.getUriForFile(
                CkUmActivity.this,
                "com.example.myapp.fileprovider",
                imageFile);

        Intent it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(it, TAKE_PIC);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg_um);
        final RadioGroup rg2 = (RadioGroup) findViewById(R.id.rg_dois);
        final RadioGroup rg3 = (RadioGroup) findViewById(R.id.rg_tres);
        final RadioGroup rg4 = (RadioGroup) findViewById(R.id.rg_quatro);
        final RadioGroup rg5 = (RadioGroup) findViewById(R.id.rg_cinco);
        final RadioGroup rg6 = (RadioGroup) findViewById(R.id.rg_seis);
        final RadioGroup rg7 = (RadioGroup) findViewById(R.id.rg_sete);
        final RadioGroup rg8 = (RadioGroup) findViewById(R.id.rg_oito);
        final RadioGroup rg9 = (RadioGroup) findViewById(R.id.rg_nove);
        final RadioGroup rg10 = (RadioGroup) findViewById(R.id.rg_dez);
        final RadioGroup rg11 = (RadioGroup) findViewById(R.id.rg_onze);
        final RadioGroup rg12 = (RadioGroup) findViewById(R.id.rg_doze);


        final BancoController crud = new BancoController(getBaseContext());
        Bundle params = getIntent().getExtras();

        final String nrFicha = params.getString("nrFicha");
        //final String hora_inicio = params.getString("hora_inicio");

        //DbHelper helper = new DbHelper(FotoActivity.this);
        //SQLiteDatabase db = helper.getWritableDatabase();
        //final DataBaseManager manager = new DataBaseManager(FotoActivity.this);

        if (requestCode == TAKE_PIC && resultCode == RESULT_OK) {
            //Toast.makeText(this, outPutfileUri.toString(), Toast.LENGTH_LONG).show();
            //manager.atualizaFoto(id_pre_reg_cadastro, "1");
            flag = "2";
            ImageView img = (ImageView) findViewById(R.id.iv_eye);
            img.setImageResource(R.drawable.eye);
            tv_visualizar.setTextColor(Color.parseColor("#ffffff"));


            // String imgFile = Environment.getExternalStorageDirectory() + "/Apple.jpg";

            File imageFile = new File(Environment.getExternalStorageDirectory(), "/entrada_"+nrFicha+".jpg");

            if (imageFile.exists()) {

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                final String dataInicio = df.format(c.getTime());

                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                final String horaInicio = dft.format(c.getTime());

                tv_dt_inicio.setText(dataInicio);
                tv_hr_inicio.setText(horaInicio);

             //   crud.atualizaFotoInicio(nrFicha, dataInicio, horaInicio);


                img.setImageResource(R.drawable.eye);
                img.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        Bundle params = new Bundle();
                        params.putString("nrFicha", nrFicha);

                        //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(CkUmActivity.this, FotoEntradaActivity.class);
                        i.putExtras(params);
                        startActivity(i);


                    }
                });

                //Habilito fomulario
                for (int i = 0; i < rg1.getChildCount(); i++) {
                    rg1.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg2.getChildCount(); i++) {
                    rg2.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg3.getChildCount(); i++) {
                    rg3.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg4.getChildCount(); i++) {
                    rg4.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg5.getChildCount(); i++) {
                    rg5.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg6.getChildCount(); i++) {
                    rg6.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg7.getChildCount(); i++) {
                    rg7.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg8.getChildCount(); i++) {
                    rg8.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg9.getChildCount(); i++) {
                    rg9.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg10.getChildCount(); i++) {
                    rg10.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg11.getChildCount(); i++) {
                    rg11.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg12.getChildCount(); i++) {
                    rg12.getChildAt(i).setEnabled(true);
                }


                //Log.d("LOG ", "FOTO EXISTE !!!");

            } else {

                img.setImageResource(R.drawable.eyeb);
                tv_visualizar.setTextColor(Color.parseColor("#9A9EA1"));

                //Desabilito fomulario
                for (int i = 0; i < rg1.getChildCount(); i++) {
                    rg1.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg2.getChildCount(); i++) {
                    rg2.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg3.getChildCount(); i++) {
                    rg3.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg4.getChildCount(); i++) {
                    rg4.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg5.getChildCount(); i++) {
                    rg5.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg6.getChildCount(); i++) {
                    rg6.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg7.getChildCount(); i++) {
                    rg7.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg8.getChildCount(); i++) {
                    rg8.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg9.getChildCount(); i++) {
                    rg9.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg10.getChildCount(); i++) {
                    rg10.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg11.getChildCount(); i++) {
                    rg11.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg12.getChildCount(); i++) {
                    rg12.getChildAt(i).setEnabled(false);
                }


            }


        }/// FIM DO RESULT OK.


    }


    private void grava() {

        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg_um);
        final RadioGroup rg2 = (RadioGroup) findViewById(R.id.rg_dois);
        final RadioGroup rg3 = (RadioGroup) findViewById(R.id.rg_tres);
        final RadioGroup rg4 = (RadioGroup) findViewById(R.id.rg_quatro);
        final RadioGroup rg5 = (RadioGroup) findViewById(R.id.rg_cinco);
        final RadioGroup rg6 = (RadioGroup) findViewById(R.id.rg_seis);
        final RadioGroup rg7 = (RadioGroup) findViewById(R.id.rg_sete);
        final RadioGroup rg8 = (RadioGroup) findViewById(R.id.rg_oito);
        final RadioGroup rg9 = (RadioGroup) findViewById(R.id.rg_nove);
        final RadioGroup rg10 = (RadioGroup) findViewById(R.id.rg_dez);
        final RadioGroup rg11 = (RadioGroup) findViewById(R.id.rg_onze);
        final RadioGroup rg12 = (RadioGroup) findViewById(R.id.rg_doze);

        if (rg1.getCheckedRadioButtonId() == -1) {
            valorRgUm = "0";
        } else {
            int selectedIdRg1 = rg1.getCheckedRadioButtonId();
            radioButtonUm = (RadioButton) findViewById(selectedIdRg1);
            valorRgUm = String.valueOf(radioButtonUm.getText());
        }

        if (rg2.getCheckedRadioButtonId() == -1) {
            valorRgDois = "0";
        } else {
            int selectedIdRg2 = rg2.getCheckedRadioButtonId();
            radioButtonDois = (RadioButton) findViewById(selectedIdRg2);
            valorRgDois = String.valueOf(radioButtonDois.getText());
        }

        if (rg3.getCheckedRadioButtonId() == -1) {
            valorRgTres = "0";
        } else {
            int selectedIdRg3 = rg3.getCheckedRadioButtonId();
            radioButtonTres = (RadioButton) findViewById(selectedIdRg3);
            valorRgTres = String.valueOf(radioButtonTres.getText());
        }

        if (rg4.getCheckedRadioButtonId() == -1) {
            valorRgQuatro = "0";
        } else {
            int selectedIdRg4 = rg4.getCheckedRadioButtonId();
            radioButtonQuatro = (RadioButton) findViewById(selectedIdRg4);
            valorRgQuatro = String.valueOf(radioButtonQuatro.getText());
        }

        if (rg5.getCheckedRadioButtonId() == -1) {
            valorRgCinco = "0";
        } else {
            int selectedIdRg5 = rg5.getCheckedRadioButtonId();
            radioButtonCinco = (RadioButton) findViewById(selectedIdRg5);
            valorRgCinco = String.valueOf(radioButtonCinco.getText());
        }

        if (rg6.getCheckedRadioButtonId() == -1) {
            valorRgSeis = "0";
        } else {
            int selectedIdRg6 = rg6.getCheckedRadioButtonId();
            radioButtonSeis = (RadioButton) findViewById(selectedIdRg6);
            valorRgSeis = String.valueOf(radioButtonSeis.getText());
        }

        if (rg7.getCheckedRadioButtonId() == -1) {
            valorRgSete = "0";
        } else {
            int selectedIdRg7 = rg7.getCheckedRadioButtonId();
            radioButtonSete = (RadioButton) findViewById(selectedIdRg7);
            valorRgSete = String.valueOf(radioButtonSete.getText());
        }

        if (rg8.getCheckedRadioButtonId() == -1) {
            valorRgOito = "0";
        } else {
            int selectedIdRg8 = rg8.getCheckedRadioButtonId();
            radioButtonOito = (RadioButton) findViewById(selectedIdRg8);
            valorRgOito = String.valueOf(radioButtonOito.getText());
        }

        if (rg9.getCheckedRadioButtonId() == -1) {
            valorRgNove = "0";
        } else {

            int selectedIdRg9 = rg9.getCheckedRadioButtonId();
            radioButtonNove = (RadioButton) findViewById(selectedIdRg9);
            valorRgNove = String.valueOf(radioButtonNove.getText());

        }

        if (rg10.getCheckedRadioButtonId() == -1) {
            valorRgDez = "0";
        } else {
            int selectedIdRg10 = rg10.getCheckedRadioButtonId();
            radioButtonDez = (RadioButton) findViewById(selectedIdRg10);
            valorRgDez = String.valueOf(radioButtonDez.getText());
        }

        if (rg11.getCheckedRadioButtonId() == -1) {
            valorRgOnze = "0";
        } else {
            int selectedIdRg11 = rg11.getCheckedRadioButtonId();
            radioButtonOnze = (RadioButton) findViewById(selectedIdRg11);
            valorRgOnze = String.valueOf(radioButtonOnze.getText());
        }

        if (rg12.getCheckedRadioButtonId() == -1) {
            valorRgDoze = "0";
        } else {
            int selectedIdRg12 = rg12.getCheckedRadioButtonId();
            radioButtonDoze = (RadioButton) findViewById(selectedIdRg12);
            valorRgDoze = String.valueOf(radioButtonDoze.getText());
        }

        //Faz as validações
        final BancoController crud = new BancoController(getBaseContext());

        Cursor cu = crud.buscarTodasObs(nrFicha);
        try {

            obsUm = cu.getString(cu.getColumnIndex("porta_escada_obs"));
            obsDois = cu.getString(cu.getColumnIndex("pilot_obs"));
            obsTres = cu.getString(cu.getColumnIndex("para_brisas_obs"));
            obsQuatro = cu.getString(cu.getColumnIndex("para_sol_obs"));
            obsCinco = cu.getString(cu.getColumnIndex("radone_obs"));
            obsSeis = cu.getString(cu.getColumnIndex("trem_principal_obs"));
            obsSete = cu.getString(cu.getColumnIndex("bordos_ataque_obs"));
            obsOito = cu.getString(cu.getColumnIndex("descarregadores_obs"));
            obsNove = cu.getString(cu.getColumnIndex("flaps_obs"));
            obsDez = cu.getString(cu.getColumnIndex("motores_obs"));
            obsOnze = cu.getString(cu.getColumnIndex("helices_obs"));
            obsDoze = cu.getString(cu.getColumnIndex("farois_obs"));


        } finally {
            if (cu != null) {
                cu.close();
            }
        }

        if (obsUm.equals("0")) {
            valorRgUm = "0";
        }
        if (obsDois.equals("0")) {
            valorRgDois = "0";
        }
        if (obsTres.equals("0")) {
            valorRgTres = "0";
        }
        if (obsQuatro.equals("0")) {
            valorRgQuatro = "0";
        }
        if (obsCinco.equals("0")) {
            valorRgCinco = "0";
        }
        if (obsSeis.equals("0")) {
            valorRgSeis = "0";
        }
        if (obsSete.equals("0")) {
            valorRgSete = "0";
        }
        if (obsOito.equals("0")) {
            valorRgOito = "0";
        }
        if (obsNove.equals("0")) {
            valorRgNove = "0";
        }
        if (obsDez.equals("0")) {
            valorRgDez = "0";
        }
        if (obsOnze.equals("0")) {
            valorRgOnze = "0";
        }
        if (obsDoze.equals("0")) {
            valorRgDoze = "0";
        }


        //Grava dados no banco
        //BancoController crud = new BancoController(getBaseContext());
        crud.atualizaDados(nrFicha, valorRgUm, valorRgDois, valorRgTres, valorRgQuatro, valorRgCinco, valorRgSeis, valorRgSete, valorRgOito, valorRgNove, valorRgDez, valorRgOnze, valorRgDoze);


    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
               // Log.v("TAG","Permission is granted");
                return true;
            } else {

               // Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //Log.v("TAG","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //Log.v("TAG","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }



}
