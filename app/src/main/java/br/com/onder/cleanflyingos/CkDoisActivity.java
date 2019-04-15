package br.com.onder.cleanflyingos;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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

public class CkDoisActivity extends AppCompatActivity {


    String nrFicha, prefixo, modelo, cliente, setor, dt_inicio, dt_termino, hr_inicio, hr_termino, obsObs;
    //String valorRgTreze, valorRgQuatorze, valorRgQuinze, valorRgDezeseis, valorRgDezesete, valorRgDezoito, valorRgDezenove, valorRgVinte, valorRgVinteum, valorRgVintedois, valorRgVintetres, valorRgVintequatro;
    String valorRgTreze, valorRgQuatorze, valorRgQuinze, valorRgDezeseis, valorRgDezesete, valorRgDezoito, valorRgDezenove, valorRgVinte, valorRgVinteum, valorRgVintedois, valorRgVintetres, valorRgVintequatro;
    TextView tv_visualizar, tv_prefixo, tv_modelo, tv_cliente, tv_setor, tv_dt_inicio, tv_dt_termino, tv_hr_inicio, tv_hr_termino;
    //TextView tv_ck13, tv_ck2, tv_ck3, tv_ck4, tv_ck5, tv_ck6, tv_ck7, tv_ck8, tv_ck9, tv_ck10, tv_ck11, tv_ck12;
    TextView tv_ck13, tv_ck14, tv_ck15, tv_ck16, tv_ck17, tv_ck18, tv_ck19, tv_ck20, tv_ck21, tv_ck22, tv_ck23, tv_ck24;
    String flag;
    //String obsTreze, obsQuatorze, obsQuinze, obsDezeseis, obsDezesete, obsDezoito, obsDezenove, obsVinte, obsVinteUm, obsVinteDois, obsVinteTres, obsVinteQuatro;
    String obsTreze, obsQuatorze, obsQuinze, obsDezeseis, obsDezesete, obsDezoito, obsDezenove, obsVinte, obsVinteUm, obsVinteDois, obsVinteTres, obsVinteQuatro;
    static int TAKE_PIC = 1;
    Uri outPutfileUri;
    ImageView imgClick;
    LinearLayout linearLayout;
    private RadioButton radioButtonUm, radioButtonDois, radioButtonTres, radioButtonQuatro, radioButtonCinco, radioButtonSeis, radioButtonSete, radioButtonOito, radioButtonNove, radioButtonDez, radioButtonOnze, radioButtonDoze;
    private RadioButton radioButtonTreze, radioButtonQuatorze, radioButtonQuinze, radioButtonDezeseis, radioButtonDezesete, radioButtonDezoito, radioButtonDezenove, radioButtonVinte, radioButtonVinteum, radioButtonVintedois, radioButtonVintetres, radioButtonVintequatro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_dois);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            nrFicha = bundle.getString("nrFicha");
            prefixo = bundle.getString("prefixo");
            modelo = bundle.getString("modelo");
            cliente = bundle.getString("cliente");
            setor = bundle.getString("setor");
            dt_inicio = bundle.getString("dt_inicio");
            hr_inicio = bundle.getString("hr_inicio");
            dt_termino = bundle.getString("dt_termino");
            hr_termino = bundle.getString("hr_termino");
            obsObs = bundle.getString("obsObs");

            //Log.i("Script", "nrFicha: "+nrFicha);
            //Log.i("Script", "prefixo: "+prefixo);

        }




        final BancoController crud = new BancoController(getBaseContext());

        tv_prefixo = (TextView) findViewById(R.id.tv_ck2_prefixo);
        tv_modelo = (TextView) findViewById(R.id.tv_ck2_modelo);
        tv_cliente = (TextView) findViewById(R.id.tv_ck2_cliente);
        tv_setor = (TextView) findViewById(R.id.tv_ck2_setor);
//        tv_dt_inicio = (TextView) findViewById(R.id.tv_ck2_data_inicio);
//        tv_dt_termino = (TextView) findViewById(R.id.tv_ck2_data_termino);
//        tv_hr_inicio = (TextView) findViewById(R.id.tv_ck2_hora_inicio);
//        tv_hr_termino = (TextView) findViewById(R.id.tv_ck2_hora_termino);
        tv_visualizar = (TextView) findViewById(R.id.tv_ck2_visualizar);

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

        File foldercfentrada = new File(Environment.getExternalStorageDirectory() + "/CF/Entrada");
        boolean successentrada = true;
        if (!foldercfentrada.exists()) {
            successentrada = foldercfentrada.mkdir();
        }

        final RadioGroup rg13 = (RadioGroup) findViewById(R.id.rg_treze);
        final RadioGroup rg14 = (RadioGroup) findViewById(R.id.rg_quatorze);
        final RadioGroup rg15 = (RadioGroup) findViewById(R.id.rg_quinze);
        final RadioGroup rg16 = (RadioGroup) findViewById(R.id.rg_dezeseis);
        final RadioGroup rg17 = (RadioGroup) findViewById(R.id.rg_dezesete);
        final RadioGroup rg18 = (RadioGroup) findViewById(R.id.rg_dezoito);
        final RadioGroup rg19 = (RadioGroup) findViewById(R.id.rg_dezenove);
        final RadioGroup rg20 = (RadioGroup) findViewById(R.id.rg_vinte);
        final RadioGroup rg21 = (RadioGroup) findViewById(R.id.rg_vinteum);
        final RadioGroup rg22 = (RadioGroup) findViewById(R.id.rg_vintedois);
        final RadioGroup rg23 = (RadioGroup) findViewById(R.id.rg_vintetres);
        final RadioGroup rg24 = (RadioGroup) findViewById(R.id.rg_vintequatro);

        //apagar radio group
        tv_ck13 = (TextView) findViewById(R.id.tv_ck13);
        tv_ck14 = (TextView) findViewById(R.id.tv_ck14);
        tv_ck15 = (TextView) findViewById(R.id.tv_ck15);
        tv_ck16 = (TextView) findViewById(R.id.tv_ck16);
        tv_ck17 = (TextView) findViewById(R.id.tv_ck17);
        tv_ck18 = (TextView) findViewById(R.id.tv_ck18);
        tv_ck19 = (TextView) findViewById(R.id.tv_ck19);
        tv_ck20 = (TextView) findViewById(R.id.tv_ck20);
        tv_ck21 = (TextView) findViewById(R.id.tv_ck21);
        tv_ck22 = (TextView) findViewById(R.id.tv_ck22);
        tv_ck23 = (TextView) findViewById(R.id.tv_ck23);
        tv_ck24 = (TextView) findViewById(R.id.tv_ck24);

        //Recuperando valores dos radios
        Cursor cur = crud.buscarValoresCklDois(nrFicha);
        try {
            valorRgTreze = cur.getString(cur.getColumnIndex("antenas"));
            valorRgQuatorze = cur.getString(cur.getColumnIndex("pintura"));
            valorRgQuinze = cur.getString(cur.getColumnIndex("paineis"));
            valorRgDezeseis = cur.getString(cur.getColumnIndex("galley"));
            valorRgDezesete = cur.getString(cur.getColumnIndex("mesas"));
            valorRgDezoito = cur.getString(cur.getColumnIndex("bancos"));
            valorRgDezenove = cur.getString(cur.getColumnIndex("janelas"));
            valorRgVinte = cur.getString(cur.getColumnIndex("carpetes"));
            valorRgVinteum = cur.getString(cur.getColumnIndex("banheiro"));
            valorRgVintedois = cur.getString(cur.getColumnIndex("clqtu"));
            valorRgVintetres = cur.getString(cur.getColumnIndex("pertences"));
            valorRgVintequatro = cur.getString(cur.getColumnIndex("outro"));

        } finally {
            if (cur != null) {
                cur.close();
            }
        }




        RadioButton rb13A =(RadioButton)findViewById(R.id.radioButton13A);
        RadioButton rb13R =(RadioButton)findViewById(R.id.radioButton13R);
        RadioButton rb13X =(RadioButton)findViewById(R.id.radioButton13X);
        RadioButton rb13F =(RadioButton)findViewById(R.id.radioButton13F);
        RadioButton rb13O =(RadioButton)findViewById(R.id.radioButton13O);

        RadioButton rb14A =(RadioButton)findViewById(R.id.radioButton14A);
        RadioButton rb14R =(RadioButton)findViewById(R.id.radioButton14R);
        RadioButton rb14X =(RadioButton)findViewById(R.id.radioButton14X);
        RadioButton rb14F =(RadioButton)findViewById(R.id.radioButton14F);
        RadioButton rb14O =(RadioButton)findViewById(R.id.radioButton14O);

        RadioButton rb15A =(RadioButton)findViewById(R.id.radioButton15A);
        RadioButton rb15R =(RadioButton)findViewById(R.id.radioButton15R);
        RadioButton rb15X =(RadioButton)findViewById(R.id.radioButton15X);
        RadioButton rb15F =(RadioButton)findViewById(R.id.radioButton15F);
        RadioButton rb15O =(RadioButton)findViewById(R.id.radioButton15O);

        RadioButton rb16A =(RadioButton)findViewById(R.id.radioButton16A);
        RadioButton rb16R =(RadioButton)findViewById(R.id.radioButton16R);
        RadioButton rb16X =(RadioButton)findViewById(R.id.radioButton16X);
        RadioButton rb16F =(RadioButton)findViewById(R.id.radioButton16F);
        RadioButton rb16O =(RadioButton)findViewById(R.id.radioButton16O);

        RadioButton rb17A =(RadioButton)findViewById(R.id.radioButton17A);
        RadioButton rb17R =(RadioButton)findViewById(R.id.radioButton17R);
        RadioButton rb17X =(RadioButton)findViewById(R.id.radioButton17X);
        RadioButton rb17F =(RadioButton)findViewById(R.id.radioButton17F);
        RadioButton rb17O =(RadioButton)findViewById(R.id.radioButton17O);

        RadioButton rb18A =(RadioButton)findViewById(R.id.radioButton18A);
        RadioButton rb18R =(RadioButton)findViewById(R.id.radioButton18R);
        RadioButton rb18X =(RadioButton)findViewById(R.id.radioButton18X);
        RadioButton rb18F =(RadioButton)findViewById(R.id.radioButton18F);
        RadioButton rb18O =(RadioButton)findViewById(R.id.radioButton18O);

        RadioButton rb19A =(RadioButton)findViewById(R.id.radioButton19A);
        RadioButton rb19R =(RadioButton)findViewById(R.id.radioButton19R);
        RadioButton rb19X =(RadioButton)findViewById(R.id.radioButton19X);
        RadioButton rb19F =(RadioButton)findViewById(R.id.radioButton19F);
        RadioButton rb19O =(RadioButton)findViewById(R.id.radioButton19O);

        RadioButton rb20A =(RadioButton)findViewById(R.id.radioButton20A);
        RadioButton rb20R =(RadioButton)findViewById(R.id.radioButton20R);
        RadioButton rb20X =(RadioButton)findViewById(R.id.radioButton20X);
        RadioButton rb20F =(RadioButton)findViewById(R.id.radioButton20F);
        RadioButton rb20O =(RadioButton)findViewById(R.id.radioButton20O);

        RadioButton rb21A =(RadioButton)findViewById(R.id.radioButton21A);
        RadioButton rb21R =(RadioButton)findViewById(R.id.radioButton21R);
        RadioButton rb21X =(RadioButton)findViewById(R.id.radioButton21X);
        RadioButton rb21F =(RadioButton)findViewById(R.id.radioButton21F);
        RadioButton rb21O =(RadioButton)findViewById(R.id.radioButton21O);

        RadioButton rb22A =(RadioButton)findViewById(R.id.radioButton22A);
        RadioButton rb22R =(RadioButton)findViewById(R.id.radioButton22R);
        RadioButton rb22X =(RadioButton)findViewById(R.id.radioButton22X);
        RadioButton rb22F =(RadioButton)findViewById(R.id.radioButton22F);
        RadioButton rb22O =(RadioButton)findViewById(R.id.radioButton22O);

        RadioButton rb23A =(RadioButton)findViewById(R.id.radioButton23A);
        RadioButton rb23R =(RadioButton)findViewById(R.id.radioButton23R);
        RadioButton rb23X =(RadioButton)findViewById(R.id.radioButton23X);
        RadioButton rb23F =(RadioButton)findViewById(R.id.radioButton23F);
        RadioButton rb23O =(RadioButton)findViewById(R.id.radioButton23O);

        RadioButton rb24A =(RadioButton)findViewById(R.id.radioButton24A);
        RadioButton rb24R =(RadioButton)findViewById(R.id.radioButton24R);
        RadioButton rb24X =(RadioButton)findViewById(R.id.radioButton24X);
        RadioButton rb24F =(RadioButton)findViewById(R.id.radioButton24F);
        RadioButton rb24O =(RadioButton)findViewById(R.id.radioButton24O);


        if(valorRgTreze.equals("A")){rb13A.setChecked(true);}
        if(valorRgTreze.equals("R")){rb13R.setChecked(true);}
        if(valorRgTreze.equals("X")){rb13X.setChecked(true);}
        if(valorRgTreze.equals("F")){rb13F.setChecked(true);}
        if(valorRgTreze.equals("O")){rb13O.setChecked(true);}

        if(valorRgQuatorze.equals("A")){rb14A.setChecked(true);}
        if(valorRgQuatorze.equals("R")){rb14R.setChecked(true);}
        if(valorRgQuatorze.equals("X")){rb14X.setChecked(true);}
        if(valorRgQuatorze.equals("F")){rb14F.setChecked(true);}
        if(valorRgQuatorze.equals("O")){rb14O.setChecked(true);}

        if(valorRgQuinze.equals("A")){rb15A.setChecked(true);}
        if(valorRgQuinze.equals("R")){rb15R.setChecked(true);}
        if(valorRgQuinze.equals("X")){rb15X.setChecked(true);}
        if(valorRgQuinze.equals("F")){rb15F.setChecked(true);}
        if(valorRgQuinze.equals("O")){rb15O.setChecked(true);}

        if(valorRgDezeseis.equals("A")){rb16A.setChecked(true);}
        if(valorRgDezeseis.equals("R")){rb16R.setChecked(true);}
        if(valorRgDezeseis.equals("X")){rb16X.setChecked(true);}
        if(valorRgDezeseis.equals("F")){rb16F.setChecked(true);}
        if(valorRgDezeseis.equals("O")){rb16O.setChecked(true);}

        if(valorRgDezesete.equals("A")){rb17A.setChecked(true);}
        if(valorRgDezesete.equals("R")){rb17R.setChecked(true);}
        if(valorRgDezesete.equals("X")){rb17X.setChecked(true);}
        if(valorRgDezesete.equals("F")){rb17F.setChecked(true);}
        if(valorRgDezesete.equals("O")){rb17O.setChecked(true);}

        if(valorRgDezoito.equals("A")){rb18A.setChecked(true);}
        if(valorRgDezoito.equals("R")){rb18R.setChecked(true);}
        if(valorRgDezoito.equals("X")){rb18X.setChecked(true);}
        if(valorRgDezoito.equals("F")){rb18F.setChecked(true);}
        if(valorRgDezoito.equals("O")){rb18O.setChecked(true);}

        if(valorRgDezenove.equals("A")){rb19A.setChecked(true);}
        if(valorRgDezenove.equals("R")){rb19R.setChecked(true);}
        if(valorRgDezenove.equals("X")){rb19X.setChecked(true);}
        if(valorRgDezenove.equals("F")){rb19F.setChecked(true);}
        if(valorRgDezenove.equals("O")){rb19O.setChecked(true);}


        if(valorRgVinte.equals("A")){rb20A.setChecked(true);}
        if(valorRgVinte.equals("R")){rb20R.setChecked(true);}
        if(valorRgVinte.equals("X")){rb20X.setChecked(true);}
        if(valorRgVinte.equals("F")){rb20F.setChecked(true);}
        if(valorRgVinte.equals("O")){rb20O.setChecked(true);}

        if(valorRgVinteum.equals("A")){rb21A.setChecked(true);}
        if(valorRgVinteum.equals("R")){rb21R.setChecked(true);}
        if(valorRgVinteum.equals("X")){rb21X.setChecked(true);}
        if(valorRgVinteum.equals("F")){rb21F.setChecked(true);}
        if(valorRgVinteum.equals("O")){rb21O.setChecked(true);}

        if(valorRgVintedois.equals("A")){rb22A.setChecked(true);}
        if(valorRgVintedois.equals("R")){rb22R.setChecked(true);}
        if(valorRgVintedois.equals("X")){rb22X.setChecked(true);}
        if(valorRgVintedois.equals("F")){rb22F.setChecked(true);}
        if(valorRgVintedois.equals("O")){rb22O.setChecked(true);}

        if(valorRgVintetres.equals("A")){rb23A.setChecked(true);}
        if(valorRgVintetres.equals("R")){rb23R.setChecked(true);}
        if(valorRgVintetres.equals("X")){rb23X.setChecked(true);}
        if(valorRgVintetres.equals("F")){rb23F.setChecked(true);}
        if(valorRgVintetres.equals("O")){rb23O.setChecked(true);}

        if(valorRgVintequatro.equals("A")){rb24A.setChecked(true);}
        if(valorRgVintequatro.equals("R")){rb24R.setChecked(true);}
        if(valorRgVintequatro.equals("X")){rb24X.setChecked(true);}
        if(valorRgVintequatro.equals("F")){rb24F.setChecked(true);}
        if(valorRgVintequatro.equals("O")){rb24O.setChecked(true);}



        //Observações radio
        rg13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "antenas_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("antenas_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "antenas_obs", obs);
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

        rg14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "pintura_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("pintura_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "pintura_obs", obs);
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

        rg15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "paineis_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("paineis_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "paineis_obs", obs);
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

        rg16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "galley_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("galley_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "galley_obs", obs);
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
        rg17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "mesas_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("mesas_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "mesas_obs", obs);
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
        rg18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "bancos_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("bancos_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "bancos_obs", obs);
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
        rg19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "janelas_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("janelas_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "janelas_obs", obs);
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
        rg20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "carpetes_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("carpetes_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "carpetes_obs", obs);
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
        rg21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "banheiro_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("banheiro_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "banheiro_obs", obs);
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
        rg22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "clqtu_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("clqtu_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "clqtu_obs", obs);
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
        rg23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "pertences_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("pertences_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "pertences_obs", obs);
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
        rg24.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_obs);
                dialog.setTitle("Observações");
                final EditText edit = (EditText) dialog.findViewById(R.id.et_dialog_obs);
                //busca observação gravada
                Cursor cucon = crud.buscarObs(nrFicha, "outro_obs");
                try {
                    String obstext = cucon.getString(cucon.getColumnIndex("outro_obs"));
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
                            Toast toast44 = Toast.makeText(CkDoisActivity.this, "     Observação vazia!     ", Toast.LENGTH_LONG);
                            View view44 = toast44.getView();
                            TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                            toast44.setGravity(Gravity.CENTER, 0, 0);
                            toast44.show();
                        } else {

                            crud.atualizaObs(nrFicha, "outro_obs", obs);
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


        tv_ck13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg13.clearCheck();
            }
        });
        tv_ck14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg14.clearCheck();
            }
        });
        tv_ck15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg15.clearCheck();
            }
        });
        tv_ck16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg16.clearCheck();
            }
        });
        tv_ck17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg17.clearCheck();
            }
        });
        tv_ck18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg18.clearCheck();
            }
        });
        tv_ck19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg19.clearCheck();
            }
        });
        tv_ck20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg20.clearCheck();
            }
        });
        tv_ck21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg21.clearCheck();
            }
        });
        tv_ck22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg22.clearCheck();
            }
        });
        tv_ck23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg23.clearCheck();
            }
        });
        tv_ck24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg24.clearCheck();
            }
        });


        //Clique em outra aba
        //ll_serv_ckdois
        linearLayout = (LinearLayout) findViewById(R.id.ll_ckldois_ckum);
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

                Intent intent2 = new Intent(CkDoisActivity.this, CkUmActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.ll_ckldois_obs);
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

                Intent intent2 = new Intent(CkDoisActivity.this, ObsActivity.class);
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

                final Dialog dialog = new Dialog(CkDoisActivity.this);
                dialog.setContentView(R.layout.dialog_legenda);
                dialog.setTitle("Legenda");


                dialog.show();

            }

        });

        //Hack para habilitar formulario mesmo sem tirar a foto
        String semFoto = "sim";

        //Checando se a foto já foi tirada
        ImageView img = (ImageView) findViewById(R.id.iv_eye);
        File file  = new File(Environment.getExternalStorageDirectory(), "/entrada_"+nrFicha+".jpg");
        //File file = new File(Environment.getExternalStorageDirectory(), "CF/Entrada/" + nrFicha + ".jpg");
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
                    Intent i = new Intent(CkDoisActivity.this, FotoEntradaActivity.class);
                    i.putExtras(params);
                    startActivity(i);


                }
            });


            //Habilito fomulario
            for (int i = 0; i < rg13.getChildCount(); i++) {
                rg13.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg14.getChildCount(); i++) {
                rg14.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg15.getChildCount(); i++) {
                rg15.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg16.getChildCount(); i++) {
                rg16.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg17.getChildCount(); i++) {
                rg17.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg18.getChildCount(); i++) {
                rg18.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg19.getChildCount(); i++) {
                rg19.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg20.getChildCount(); i++) {
                rg20.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg21.getChildCount(); i++) {
                rg21.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg22.getChildCount(); i++) {
                rg22.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg23.getChildCount(); i++) {
                rg23.getChildAt(i).setEnabled(true);
            }
            for (int i = 0; i < rg24.getChildCount(); i++) {
                rg24.getChildAt(i).setEnabled(true);
            }


        } else {
            //Nothing

            //9A9EA1

            img.setImageResource(R.drawable.eyeb);
            tv_visualizar.setTextColor(Color.parseColor("#9A9EA1"));

            //Desabilito fomulario
            for (int i = 0; i < rg13.getChildCount(); i++) {
                rg13.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg14.getChildCount(); i++) {
                rg14.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg15.getChildCount(); i++) {
                rg15.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg16.getChildCount(); i++) {
                rg16.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg17.getChildCount(); i++) {
                rg17.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg18.getChildCount(); i++) {
                rg18.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg19.getChildCount(); i++) {
                rg19.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg20.getChildCount(); i++) {
                rg20.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg21.getChildCount(); i++) {
                rg21.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg22.getChildCount(); i++) {
                rg22.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg23.getChildCount(); i++) {
                rg23.getChildAt(i).setEnabled(false);
            }
            for (int i = 0; i < rg24.getChildCount(); i++) {
                rg24.getChildAt(i).setEnabled(false);
            }


        }


    }


    public void tirarFoto(View view) {


        Bundle params = getIntent().getExtras();
        String nrFicha = params.getString("nrFicha");
        //String hora_inicio = params.getString("hora_inicio");
        //Toast.makeText(LocalDemolidoActivity.this,"ID: " + nro_form, Toast.LENGTH_LONG).show();

        flag = "1";

        //Toast.makeText(LocalDemolidoActivity.this,"FLAG: " + flag, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = new File(Environment.getExternalStorageDirectory(), "CF/Entrada/" + nrFicha + ".jpg");

        outPutfileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, TAKE_PIC);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        final RadioGroup rg13 = (RadioGroup) findViewById(R.id.rg_treze);
        final RadioGroup rg14 = (RadioGroup) findViewById(R.id.rg_quatorze);
        final RadioGroup rg15 = (RadioGroup) findViewById(R.id.rg_quinze);
        final RadioGroup rg16 = (RadioGroup) findViewById(R.id.rg_dezeseis);
        final RadioGroup rg17 = (RadioGroup) findViewById(R.id.rg_dezesete);
        final RadioGroup rg18 = (RadioGroup) findViewById(R.id.rg_dezoito);
        final RadioGroup rg19 = (RadioGroup) findViewById(R.id.rg_dezenove);
        final RadioGroup rg20 = (RadioGroup) findViewById(R.id.rg_vinte);
        final RadioGroup rg21 = (RadioGroup) findViewById(R.id.rg_vinteum);
        final RadioGroup rg22 = (RadioGroup) findViewById(R.id.rg_vintedois);
        final RadioGroup rg23 = (RadioGroup) findViewById(R.id.rg_vintetres);
        final RadioGroup rg24 = (RadioGroup) findViewById(R.id.rg_vintequatro);


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
            File imageFile  = new File(Environment.getExternalStorageDirectory(), "/entrada_"+nrFicha+".jpg");
            //File imageFile = new File(Environment.getExternalStorageDirectory(), "CF/Entrada/" + nrFicha + ".jpg");

            if (imageFile.exists()) {

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                final String dataInicio = df.format(c.getTime());

                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                final String horaInicio = dft.format(c.getTime());

                tv_dt_inicio.setText(dataInicio);
                tv_hr_inicio.setText(horaInicio);

           //     crud.atualizaFotoInicio(nrFicha, dataInicio, horaInicio);

                img.setImageResource(R.drawable.eye);
                img.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        Bundle params = new Bundle();
                        params.putString("nrFicha", nrFicha);

                        //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(CkDoisActivity.this, FotoEntradaActivity.class);
                        i.putExtras(params);
                        startActivity(i);


                    }
                });

                //Habilito fomulario
                for (int i = 0; i < rg13.getChildCount(); i++) {
                    rg13.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg14.getChildCount(); i++) {
                    rg14.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg15.getChildCount(); i++) {
                    rg15.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg16.getChildCount(); i++) {
                    rg16.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg17.getChildCount(); i++) {
                    rg17.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg18.getChildCount(); i++) {
                    rg18.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg19.getChildCount(); i++) {
                    rg19.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg20.getChildCount(); i++) {
                    rg20.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg21.getChildCount(); i++) {
                    rg21.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg22.getChildCount(); i++) {
                    rg22.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg23.getChildCount(); i++) {
                    rg23.getChildAt(i).setEnabled(true);
                }
                for (int i = 0; i < rg24.getChildCount(); i++) {
                    rg24.getChildAt(i).setEnabled(true);
                }


                //Log.d("LOG ", "FOTO EXISTE !!!");

            } else {

                img.setImageResource(R.drawable.eyeb);
                tv_visualizar.setTextColor(Color.parseColor("#9A9EA1"));

                //Desabilito fomulario
                for (int i = 0; i < rg13.getChildCount(); i++) {
                    rg13.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg14.getChildCount(); i++) {
                    rg14.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg15.getChildCount(); i++) {
                    rg15.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg16.getChildCount(); i++) {
                    rg16.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg17.getChildCount(); i++) {
                    rg17.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg18.getChildCount(); i++) {
                    rg18.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg19.getChildCount(); i++) {
                    rg19.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg20.getChildCount(); i++) {
                    rg20.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg21.getChildCount(); i++) {
                    rg21.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg22.getChildCount(); i++) {
                    rg22.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg23.getChildCount(); i++) {
                    rg23.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rg24.getChildCount(); i++) {
                    rg24.getChildAt(i).setEnabled(false);
                }


            }


        }/// FIM DO RESULT OK.


    }


    private void grava() {

        final RadioGroup rg13 = (RadioGroup) findViewById(R.id.rg_treze);
        final RadioGroup rg14 = (RadioGroup) findViewById(R.id.rg_quatorze);
        final RadioGroup rg15 = (RadioGroup) findViewById(R.id.rg_quinze);
        final RadioGroup rg16 = (RadioGroup) findViewById(R.id.rg_dezeseis);
        final RadioGroup rg17 = (RadioGroup) findViewById(R.id.rg_dezesete);
        final RadioGroup rg18 = (RadioGroup) findViewById(R.id.rg_dezoito);
        final RadioGroup rg19 = (RadioGroup) findViewById(R.id.rg_dezenove);
        final RadioGroup rg20 = (RadioGroup) findViewById(R.id.rg_vinte);
        final RadioGroup rg21 = (RadioGroup) findViewById(R.id.rg_vinteum);
        final RadioGroup rg22 = (RadioGroup) findViewById(R.id.rg_vintedois);
        final RadioGroup rg23 = (RadioGroup) findViewById(R.id.rg_vintetres);
        final RadioGroup rg24 = (RadioGroup) findViewById(R.id.rg_vintequatro);

        if (rg13.getCheckedRadioButtonId() == -1) {
            valorRgTreze = "0";
        } else {
            int selectedIdRg1 = rg13.getCheckedRadioButtonId();
            radioButtonTreze = (RadioButton) findViewById(selectedIdRg1);
            valorRgTreze = String.valueOf(radioButtonTreze.getText());
        }

        if (rg14.getCheckedRadioButtonId() == -1) {
            valorRgQuatorze = "0";
        } else {
            int selectedIdRg2 = rg14.getCheckedRadioButtonId();
            radioButtonQuatorze = (RadioButton) findViewById(selectedIdRg2);
            valorRgQuatorze = String.valueOf(radioButtonQuatorze.getText());
        }

        if (rg15.getCheckedRadioButtonId() == -1) {
            valorRgQuinze = "0";
        } else {
            int selectedIdRg3 = rg15.getCheckedRadioButtonId();
            radioButtonQuinze = (RadioButton) findViewById(selectedIdRg3);
            valorRgQuinze = String.valueOf(radioButtonQuinze.getText());
        }

        if (rg16.getCheckedRadioButtonId() == -1) {
            valorRgDezeseis = "0";
        } else {
            int selectedIdRg4 = rg16.getCheckedRadioButtonId();
            radioButtonDezeseis = (RadioButton) findViewById(selectedIdRg4);
            valorRgDezeseis = String.valueOf(radioButtonDezeseis.getText());
        }

        if (rg17.getCheckedRadioButtonId() == -1) {
            valorRgDezesete = "0";
        } else {
            int selectedIdRg5 = rg17.getCheckedRadioButtonId();
            radioButtonDezesete = (RadioButton) findViewById(selectedIdRg5);
            valorRgDezesete = String.valueOf(radioButtonDezesete.getText());
        }

        if (rg18.getCheckedRadioButtonId() == -1) {
            valorRgDezoito = "0";
        } else {
            int selectedIdRg6 = rg18.getCheckedRadioButtonId();
            radioButtonDezoito = (RadioButton) findViewById(selectedIdRg6);
            valorRgDezoito = String.valueOf(radioButtonDezoito.getText());
        }

        if (rg19.getCheckedRadioButtonId() == -1) {
            valorRgDezenove = "0";
        } else {
            int selectedIdRg7 = rg19.getCheckedRadioButtonId();
            radioButtonDezenove = (RadioButton) findViewById(selectedIdRg7);
            valorRgDezenove = String.valueOf(radioButtonDezenove.getText());
        }

        if (rg20.getCheckedRadioButtonId() == -1) {
            valorRgVinte = "0";
        } else {
            int selectedIdRg8 = rg20.getCheckedRadioButtonId();
            radioButtonVinte = (RadioButton) findViewById(selectedIdRg8);
            valorRgVinte = String.valueOf(radioButtonVinte.getText());
        }

        if (rg21.getCheckedRadioButtonId() == -1) {
            valorRgVinteum = "0";
        } else {

            int selectedIdRg9 = rg21.getCheckedRadioButtonId();
            radioButtonVinteum = (RadioButton) findViewById(selectedIdRg9);
            valorRgVinteum = String.valueOf(radioButtonVinteum.getText());

        }

        if (rg22.getCheckedRadioButtonId() == -1) {
            valorRgVintedois = "0";
        } else {
            int selectedIdRg10 = rg22.getCheckedRadioButtonId();
            radioButtonVintedois = (RadioButton) findViewById(selectedIdRg10);
            valorRgVintedois = String.valueOf(radioButtonVintedois.getText());
        }

        if (rg23.getCheckedRadioButtonId() == -1) {
            valorRgVintetres = "0";
        } else {
            int selectedIdRg11 = rg23.getCheckedRadioButtonId();
            radioButtonVintetres = (RadioButton) findViewById(selectedIdRg11);
            valorRgVintetres = String.valueOf(radioButtonVintetres.getText());
        }

        if (rg24.getCheckedRadioButtonId() == -1) {
            valorRgVintequatro = "0";
        } else {
            int selectedIdRg12 = rg24.getCheckedRadioButtonId();
            radioButtonVintequatro = (RadioButton) findViewById(selectedIdRg12);
            valorRgVintequatro = String.valueOf(radioButtonVintequatro.getText());
        }

        //Faz as validações
        final BancoController crud = new BancoController(getBaseContext());

        Cursor cu = crud.buscarTodasObs(nrFicha);
        try {

            obsTreze = cu.getString(cu.getColumnIndex("antenas_obs"));
            obsQuatorze = cu.getString(cu.getColumnIndex("pintura_obs"));
            obsQuinze = cu.getString(cu.getColumnIndex("paineis_obs"));
            obsDezeseis = cu.getString(cu.getColumnIndex("galley_obs"));
            obsDezesete = cu.getString(cu.getColumnIndex("mesas_obs"));
            obsDezoito = cu.getString(cu.getColumnIndex("bancos_obs"));
            obsDezenove = cu.getString(cu.getColumnIndex("janelas_obs"));
            obsVinte = cu.getString(cu.getColumnIndex("carpetes_obs"));
            obsVinteUm = cu.getString(cu.getColumnIndex("banheiro_obs"));
            obsVinteDois = cu.getString(cu.getColumnIndex("clqtu_obs"));
            obsVinteTres = cu.getString(cu.getColumnIndex("pertences_obs"));
            obsVinteQuatro = cu.getString(cu.getColumnIndex("outro_obs"));


        } finally {
            if (cu != null) {
                cu.close();
            }
        }

        if (obsTreze.equals("0")) {
            valorRgTreze = "0";
        }
        if (obsQuatorze.equals("0")) {
            valorRgQuatorze = "0";
        }
        if (obsQuinze.equals("0")) {
            valorRgQuinze = "0";
        }
        if (obsDezeseis.equals("0")) {
            valorRgDezeseis = "0";
        }
        if (obsDezesete.equals("0")) {
            valorRgDezesete = "0";
        }
        if (obsDezoito.equals("0")) {
            valorRgDezoito = "0";
        }
        if (obsDezenove.equals("0")) {
            valorRgDezenove = "0";
        }
        if (obsVinte.equals("0")) {
            valorRgVinte = "0";
        }
        if (obsVinteUm.equals("0")) {
            valorRgVinteum = "0";
        }
        if (obsVinteDois.equals("0")) {
            valorRgVintedois = "0";
        }
        if (obsVinteTres.equals("0")) {
            valorRgVintetres = "0";
        }
        if (obsVinteQuatro.equals("0")) {
            valorRgVintequatro = "0";
        }


        //Grava dados no banco
        //BancoController crud = new BancoController(getBaseContext());
        crud.atualizaDadosDois(nrFicha, valorRgTreze, valorRgQuatorze, valorRgQuinze, valorRgDezeseis, valorRgDezesete, valorRgDezoito, valorRgDezenove, valorRgVinte, valorRgVinteum, valorRgVintedois, valorRgVintetres, valorRgVintequatro);


    }




}
