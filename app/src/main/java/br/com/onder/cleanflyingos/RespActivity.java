package br.com.onder.cleanflyingos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RespActivity extends AppCompatActivity {


    static int TAKE_PIC =1;
    Uri outPutfileUri;

    String nrFicha, prefixo, modelo, cliente, setor, dt_inicio, dt_termino, hr_inicio, hr_termino, obs, obsObs, s_ent_resp, s_ent_atend, s_saida_resp, s_saida_atend, s_saida_insp;
    TextView tv_visualizar, tv_prefixo, tv_modelo, tv_cliente, tv_setor, tv_dt_inicio, tv_dt_termino, tv_hr_inicio, tv_hr_termino, tv_obsObs;
    EditText et_ent_resp, et_ent_atend, et_saida_insp, et_saida_resp;
    LinearLayout linearLayout;
    String flag;
    ClasseFichas ficha = new ClasseFichas();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resp);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        tv_visualizar = (TextView) findViewById(R.id.tv_resp_visualizar);
        //img.setImageResource(R.drawable.eyeb);
        tv_visualizar.setTextColor(Color.parseColor("#9A9EA1"));

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


            tv_prefixo = (TextView) findViewById(R.id.tv_resp_prefixo);
            tv_modelo = (TextView) findViewById(R.id.tv_resp_modelo);
            tv_cliente = (TextView) findViewById(R.id.tv_resp_cliente);
            tv_setor = (TextView) findViewById(R.id.tv_resp_setor);
         //   tv_dt_inicio = (TextView) findViewById(R.id.tv_resp_data_inicio);
//            tv_dt_termino = (TextView) findViewById(R.id.tv_resp_data_termino);
           // tv_hr_inicio = (TextView) findViewById(R.id.tv_resp_hora_inicio);
//            tv_hr_termino = (TextView) findViewById(R.id.tv_resp_hora_termino);


            et_ent_resp = (EditText) findViewById(R.id.et_ent_resp);
            et_ent_atend = (EditText) findViewById(R.id.et_ent_atend);
            et_saida_insp = (EditText) findViewById(R.id.et_saida_insp);
            et_saida_resp = (EditText) findViewById(R.id.et_saida_resp);

            //tv_visualizar = (TextView) findViewById(R.id.tv_resp_visualizar);


            tv_prefixo.setText(prefixo);
            tv_modelo.setText(modelo);
            tv_cliente.setText(cliente);
            tv_setor.setText(setor);
//            tv_dt_inicio.setText(dt_inicio);
//            tv_dt_termino.setText(dt_termino);
//            tv_hr_inicio.setText(hr_inicio);
//            tv_hr_termino.setText(hr_termino);
            ///tv_obsObs.setText(obsObs);




            //Log.i("Script", "position: "+position);

        }


        //Checando se a foto já foi tirada
        ImageView img = (ImageView) findViewById(R.id.iv_eye_out);

        File file = new File(Environment.getExternalStorageDirectory(), "CF/Saida/" + nrFicha + ".jpg");
        if (file.exists()) {
            img.setImageResource(R.drawable.eye);
            img.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Bundle params = new Bundle();
                    params.putString("nrFicha", nrFicha);

                    //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RespActivity.this, FotoSaidaActivity.class);
                    i.putExtras(params);
                    startActivity(i);


                }
            });


        }


        final BancoController crud = new BancoController(getBaseContext());

        //resgata valores dos campos

        Cursor cur = crud.buscarResp(nrFicha);
        try {

            s_ent_resp = cur.getString(cur.getColumnIndex("entrada_resp"));
            s_ent_atend = cur.getString(cur.getColumnIndex("entrada_atendente"));
            s_saida_insp = cur.getString(cur.getColumnIndex("saida_insp"));
            s_saida_resp = cur.getString(cur.getColumnIndex("saida_resp"));

            et_ent_resp.setText(s_ent_resp);
            et_ent_atend.setText(s_ent_atend);
            et_saida_insp.setText(s_saida_insp);
            et_saida_resp.setText(s_saida_resp);




        } finally {
            if (cur != null) {
                cur.close();
            }
        }



        File folderassinum = new File(Environment.getExternalStorageDirectory() + "/CF/Assinatura");
        boolean successassum = true;
        if (!folderassinum.exists()) {
            successassum = folderassinum.mkdir();
        }





        //Atualizando a view da assinatura

        File imageFileUm = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "um.png");

        if (imageFileUm.exists()) {

            ImageView imageView= (ImageView) findViewById(R.id.iv_assinatura_um);
            imageView.setImageBitmap(BitmapFactory.decodeFile(imageFileUm.getAbsolutePath()));

        }

        ImageView imga = (ImageView) findViewById(R.id.iv_assinatura_um);
        //img.setImageResource(R.drawable.eye);
        imga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);

                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RespActivity.this, AssinaturaUmActivity.class);
                i.putExtras(params);
                startActivity(i);


            }
        });




        File imageFileDoisp = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "dois.png");

        if (imageFileDoisp.exists()) {

            ImageView imageViewDoisd= (ImageView) findViewById(R.id.iv_assinatura_dois);
            imageViewDoisd.setImageBitmap(BitmapFactory.decodeFile(imageFileDoisp.getAbsolutePath()));

        }

        ImageView imgdois = (ImageView) findViewById(R.id.iv_assinatura_dois);
        //img.setImageResource(R.drawable.eye);
        imgdois.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);

                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RespActivity.this, AssinaturaDoisActivity.class);
                i.putExtras(params);
                startActivity(i);


            }
        });





        File imageFileTresp = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "tres.png");

        if (imageFileTresp.exists()) {

            ImageView imageViewTresd= (ImageView) findViewById(R.id.iv_assinatura_tres);
            imageViewTresd.setImageBitmap(BitmapFactory.decodeFile(imageFileTresp.getAbsolutePath()));

        }

        ImageView imgtres = (ImageView) findViewById(R.id.iv_assinatura_tres);
        //img.setImageResource(R.drawable.eye);
        imgtres.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);

                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RespActivity.this, AssinaturaTresActivity.class);
                i.putExtras(params);
                startActivity(i);


            }
        });




        File imageFileQuatrop = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "quatro.png");

        if (imageFileQuatrop.exists()) {

            ImageView imageViewQuatrod= (ImageView) findViewById(R.id.iv_assinatura_quatro);
            imageViewQuatrod.setImageBitmap(BitmapFactory.decodeFile(imageFileQuatrop.getAbsolutePath()));

        }

        ImageView imgquatro = (ImageView) findViewById(R.id.iv_assinatura_quatro);
        //img.setImageResource(R.drawable.eye);
        imgquatro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);

                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RespActivity.this, AssinaturaQuatroActivity.class);
                i.putExtras(params);
                startActivity(i);


            }
        });







        //Clique em outra aba
        //ll_serv_ckobs
        linearLayout = (LinearLayout) findViewById(R.id.ll_resp_serv);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);
                params.putString("prefixo", prefixo);
                params.putString("modelo", modelo);
                params.putString("cliente", cliente);
                params.putString("setor", setor);
                //params.putString("dt_inicio", dt_inicio);
                params.putString("dt_termino", dt_termino);
                //params.putString("hr_inicio", hr_inicio);
                params.putString("hr_termino", hr_termino);
                params.putString("obsObs", obsObs);





                grava();

                Intent intent2 = new Intent(RespActivity.this, CadastroServicoActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.ll_resp_ckum);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);
                params.putString("prefixo", prefixo);
                params.putString("modelo", modelo);
                params.putString("cliente", cliente);
                params.putString("setor", setor);
               // params.putString("dt_inicio", dt_inicio);
                params.putString("dt_termino", dt_termino);
               // params.putString("hr_inicio", hr_inicio);
                params.putString("hr_termino", hr_termino);
                params.putString("obsObs", obsObs);



                //Toast toast44 = Toast.makeText(RespActivity.this, "     GRAVA!!!!     ", Toast.LENGTH_LONG);
               // View view44 = toast44.getView();
               // TextView text44 = (TextView) view44.findViewById(android.R.id.message);
                //toast44.setGravity(Gravity.CENTER, 0, 0);
               // toast44.show();
                grava();

                Intent intent2 = new Intent(RespActivity.this, CkUmActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.ll_resp_ckdois);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);
                params.putString("prefixo", prefixo);
                params.putString("modelo", modelo);
                params.putString("cliente", cliente);
                params.putString("setor", setor);
               // params.putString("dt_inicio", dt_inicio);
                params.putString("dt_termino", dt_termino);
               // params.putString("hr_inicio", hr_inicio);
                params.putString("hr_termino", hr_termino);
                params.putString("obsObs", obsObs);




                grava();

                Intent intent2 = new Intent(RespActivity.this, CkDoisActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.ll_resp_obs);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle params = new Bundle();
                params.putString("nrFicha", nrFicha);
                params.putString("prefixo", prefixo);
                params.putString("modelo", modelo);
                params.putString("cliente", cliente);
                params.putString("setor", setor);
               // params.putString("dt_inicio", dt_inicio);
                params.putString("dt_termino", dt_termino);
              //  params.putString("hr_inicio", hr_inicio);
                params.putString("hr_termino", hr_termino);
                params.putString("obsObs", obsObs);


                grava();

                Intent intent2 = new Intent(RespActivity.this, ObsActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.ll_resp_resp);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                grava();


            }
        });



        final Button b2 = (Button) findViewById(R.id.bt_resp_finaliza);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                grava();
                Calendar c = Calendar.getInstance();

                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                final String dataTermino = df.format(c.getTime());

                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                final String horaTermino = dft.format(c.getTime());

                crud.atualizaFotoFinal(nrFicha, dataTermino, horaTermino);




                Intent intent2 = new Intent(RespActivity.this, FichasActivity.class);
                Cursor status = crud.buscarStatusFicha(nrFicha);

                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                if (status.getColumnIndex("enviado") == 2) {
//                    crud.atualizaStatusFicha(nrFicha,"1");
//                    startActivity(intent2);
//                    finish();
//                }
//                else {
//                    Toast.makeText(RespActivity.this, "Deve-se retornar à fichas antes", Toast.LENGTH_SHORT).show();
//                }

                    startActivity(intent2);
                    finish();


            }
        });


        final Button b3 = (Button) findViewById(R.id.bt_resp_fichas);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                grava();


                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                final String dataInicio = df.format(c.getTime());

                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                final String horaInicio = dft.format(c.getTime());

                crud.atualizaFotoInicio(nrFicha, dataInicio, horaInicio);

                crud.atualizaStatusFicha(nrFicha,"1");

//                ficha.setStatus_c("2");


              //  tv_dt_inicio.setText(dataInicio);
              //  tv_hr_inicio.setText(horaInicio);

                Intent intent2 = new Intent(RespActivity.this, FichasActivity.class);

                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();



            }
        });







    }

    private void grava() {


        EditText ent_resp = (EditText) findViewById(R.id.et_ent_resp);
        s_ent_resp = ent_resp.getText().toString();
        EditText ent_atend = (EditText) findViewById(R.id.et_ent_atend);
        s_ent_atend = ent_atend.getText().toString();
        EditText saida_insp = (EditText) findViewById(R.id.et_saida_insp);
        s_saida_insp = saida_insp.getText().toString();
        EditText saida_resp = (EditText) findViewById(R.id.et_saida_resp);
        s_saida_resp = saida_resp.getText().toString();


        final BancoController crud = new BancoController(getBaseContext());

        crud.atualizaResp(nrFicha, s_ent_resp, s_ent_atend, s_saida_insp, s_saida_resp);



    }

    public void tirarFotoFim(View view) {


        Bundle params = getIntent().getExtras();
        String nrFicha = params.getString("nrFicha");
        //String hora_inicio = params.getString("hora_inicio");
        //Toast.makeText(LocalDemolidoActivity.this,"ID: " + nro_form, Toast.LENGTH_LONG).show();

        flag = "1";

        //Toast.makeText(LocalDemolidoActivity.this,"FLAG: " + flag, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

       // File file = new File(Environment.getExternalStorageDirectory(), "CF/Saida/" + nrFicha + ".jpg");

        String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/saida_"+nrFicha+".jpg";
        File imageFile = new File(imageFilePath);

        ;
        // Uri imageFileUri = Uri.fromFile(imageFile); // convert path to Uri

        Uri imageFileUri = FileProvider.getUriForFile(
                RespActivity.this,
                "com.example.myapp.fileprovider",
                imageFile);

        Intent it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(it, TAKE_PIC);




        //outPutfileUri = Uri.fromFile(file);
       // intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
       // startActivityForResult(intent, TAKE_PIC);











    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        final BancoController crud = new BancoController(getBaseContext());
        Bundle params = getIntent().getExtras();

        if (requestCode == TAKE_PIC && resultCode == RESULT_OK) {
            //Toast.makeText(this, outPutfileUri.toString(), Toast.LENGTH_LONG).show();
            //manager.atualizaFoto(id_pre_reg_cadastro, "1");
            flag = "2";
            ImageView img = (ImageView) findViewById(R.id.iv_eye_out);
            img.setImageResource(R.drawable.eye);
            tv_visualizar.setTextColor(Color.parseColor("#ffffff"));


            // String imgFile = Environment.getExternalStorageDirectory() + "/Apple.jpg";

            File imageFile = new File(Environment.getExternalStorageDirectory(), "CF/Entrada/" + nrFicha + ".jpg");

            if (imageFile.exists()) {

//                Calendar c = Calendar.getInstance();
//                System.out.println("Current time => " + c.getTime());
//
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                final String dataTermino= df.format(c.getTime());
//
//                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
//                final String horaTermino = dft.format(c.getTime());

                //tv_dt_fim.setText(dataInicio);
                //tv_hr_inicio.setText(horaInicio);

                //crud.atualizaFotoInicio(nrFicha, dataInicio, horaInicio);


                img.setImageResource(R.drawable.eye);
                img.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        Bundle params = new Bundle();
                        params.putString("nrFicha", nrFicha);

                        //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RespActivity.this, FotoSaidaActivity.class);
                        i.putExtras(params);
                        startActivity(i);


                    }
                });

            } else {

                img.setImageResource(R.drawable.eyeb);
                tv_visualizar.setTextColor(Color.parseColor("#9A9EA1"));

            }

        }


    }/// FIM DO RESULT OK.


    @Override
    protected void onResume() {
        super.onResume();

        File imageFileUm = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "um.png");

        if (imageFileUm.exists()) {

            ImageView imageView= (ImageView) findViewById(R.id.iv_assinatura_um);
            imageView.setImageBitmap(BitmapFactory.decodeFile(imageFileUm.getAbsolutePath()));

        }


        File imageFileDois = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "dois.png");

        if (imageFileDois.exists()) {

            ImageView imageViewDois= (ImageView) findViewById(R.id.iv_assinatura_dois);
            imageViewDois.setImageBitmap(BitmapFactory.decodeFile(imageFileDois.getAbsolutePath()));

        }


        File imageFileTres = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "tres.png");

        if (imageFileTres.exists()) {

            ImageView imageViewTres= (ImageView) findViewById(R.id.iv_assinatura_tres);
            imageViewTres.setImageBitmap(BitmapFactory.decodeFile(imageFileTres.getAbsolutePath()));

        }


        File imageFileQuatro = new File(Environment.getExternalStorageDirectory(), "CF/Assinatura/" + nrFicha + "quatro.png");

        if (imageFileQuatro.exists()) {

            ImageView imageViewQuatro= (ImageView) findViewById(R.id.iv_assinatura_quatro);
            imageViewQuatro.setImageBitmap(BitmapFactory.decodeFile(imageFileQuatro.getAbsolutePath()));

        }



    }


}
