package br.com.onder.cleanflyingos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CadastroServicoActivity extends AppCompatActivity {

    CompoundButton ck_servi_1, ck_servi_2, ck_servi_3, ck_servi_4, ck_servi_5, ck_servi_6, ck_servi_7, ck_servi_8, ck_servi_9, ck_servi_10, ck_servi_11, ck_servi_12;
    CompoundButton ck_serv_atendimento, ck_serv_manutencao, ck_serv_avulso;

    LinearLayout linearLayout;
    int position;
    List<ClasseFichas> dbListc;
    String nrFicha, prefixo, modelo, cliente, setor,dt_inicio, dt_termino, hr_inicio, hr_termino;
    String atendimento, manutencao, avulso, obsObs;
    String asp, com, lav, las, lab, pol, met, car, est, qtu, bol, otr;
    String aspObs, comObs, lavObs, lasObs, labObs, polObs, metObs, carObs, estObs, qtuObs, bolObs, otrObs;
    TextView tv_prefixo, tv_modelo, tv_cliente, tv_setor, tv_dt_inicio, tv_dt_termino, tv_hr_inicio, tv_hr_termino;

    EditText etObs1, etObs2, etObs3, etObs4, etObs5, etObs6, etObs7, etObs8, etObs9, etObs10, etObs11, etObs12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);
        this.setTitle("SERVIÇOS");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle !=null) {
            // resgata o valor do status bundle
            position = bundle.getInt("position");
            //Log.i("Script", "position: "+position);

        }

        ck_serv_manutencao = (CompoundButton)findViewById(R.id.ck_serv_manutencao);
        ck_serv_atendimento = (CompoundButton)findViewById(R.id.ck_serv_atendimento);
        ck_serv_avulso = (CompoundButton)findViewById(R.id.ck_serv_avulso);

        ck_servi_1 = (CompoundButton)findViewById(R.id.ck_serv_1);
        ck_servi_2 = (CompoundButton)findViewById(R.id.ck_serv_2);
        ck_servi_3 = (CompoundButton)findViewById(R.id.ck_serv_3);
        ck_servi_4 = (CompoundButton)findViewById(R.id.ck_serv_4);
        ck_servi_5 = (CompoundButton)findViewById(R.id.ck_serv_5);
        ck_servi_6 = (CompoundButton)findViewById(R.id.ck_serv_6);
        ck_servi_7 = (CompoundButton)findViewById(R.id.ck_serv_7);
        ck_servi_8 = (CompoundButton)findViewById(R.id.ck_serv_8);
        ck_servi_9 = (CompoundButton)findViewById(R.id.ck_serv_9);
        ck_servi_10 = (CompoundButton)findViewById(R.id.ck_serv_10);
        ck_servi_11 = (CompoundButton)findViewById(R.id.ck_serv_11);
        ck_servi_12 = (CompoundButton)findViewById(R.id.ck_serv_12);

        etObs1 = (EditText) findViewById(R.id.et_obs_1);
        etObs2 = (EditText) findViewById(R.id.et_obs_2);
        etObs3 = (EditText) findViewById(R.id.et_obs_3);
        etObs4 = (EditText) findViewById(R.id.et_obs_4);
        etObs5 = (EditText) findViewById(R.id.et_obs_5);
        etObs6 = (EditText) findViewById(R.id.et_obs_6);
        etObs7 = (EditText) findViewById(R.id.et_obs_7);
        etObs8 = (EditText) findViewById(R.id.et_obs_8);
        etObs9 = (EditText) findViewById(R.id.et_obs_9);
        etObs10 = (EditText) findViewById(R.id.et_obs_10);
        etObs11 = (EditText) findViewById(R.id.et_obs_11);
        etObs12 = (EditText) findViewById(R.id.et_obs_12);

        tv_prefixo = (TextView) findViewById(R.id.tv_serv_prefixo);
        tv_modelo = (TextView) findViewById(R.id.tv_serv_modelo);
        tv_cliente = (TextView) findViewById(R.id.tv_serv_cliente);
        tv_setor = (TextView) findViewById(R.id.tv_serv_setor);
//        tv_dt_inicio = (TextView) findViewById(R.id.tv_serv_data_inicio);
//        tv_dt_termino = (TextView) findViewById(R.id.tv_serv_data_termino);
//        tv_hr_inicio = (TextView) findViewById(R.id.tv_serv_hora_inicio);
//        tv_hr_termino = (TextView) findViewById(R.id.tv_serv_hora_termino);


        final BancoController crud = new BancoController(getBaseContext());
        dbListc= new ArrayList<ClasseFichas>();
        dbListc = crud.getDataFromDB();

        if(dbListc.size()>0){
            //String name= dbListc.get(position).getRua_c();
            nrFicha= dbListc.get(position).getNrFicha_c();
            prefixo = dbListc.get(position).getPrefixo_c();
            modelo = dbListc.get(position).getModelo_categoria_c();
            cliente = dbListc.get(position).getCliente_c();
            setor = dbListc.get(position).getSetor_c();
            obsObs = dbListc.get(position).getObs_obs_c();
            dt_inicio = dbListc.get(position).getDataInicio_c();
            if (TextUtils.isEmpty(dt_inicio)) { dt_inicio="-"; }
            dt_termino = dbListc.get(position).getDataTermino_c();
            if (TextUtils.isEmpty(dt_termino)) { dt_termino="-"; }
            hr_inicio = dbListc.get(position).getHoraInicio_c();
            if (TextUtils.isEmpty(hr_inicio)) { hr_inicio="-"; }
            hr_termino = dbListc.get(position).getHoraTermino_c();
            if (TextUtils.isEmpty(hr_termino)) {hr_termino="-"; }


            tv_prefixo.setText(prefixo);
            tv_modelo.setText(modelo);
            tv_cliente.setText(cliente);
            tv_setor.setText(setor);
           // tv_dt_inicio.setText(dt_inicio);
            //tv_dt_termino.setText(dt_termino);
            //tv_hr_inicio.setText(hr_inicio);
            //tv_hr_termino.setText(hr_termino);

            atendimento= dbListc.get(position).getAtendimento_c();
            if(atendimento.equals("1")){ck_serv_atendimento.setChecked(true);}

            manutencao= dbListc.get(position).getManutencao_c();
            if(manutencao.equals("1")){ck_serv_manutencao.setChecked(true);}

            avulso= dbListc.get(position).getAvulso_c();
            if(avulso.equals("1")){ck_serv_avulso.setChecked(true);}

            asp = dbListc.get(position).getAsp_c();
            com = dbListc.get(position).getCom_c();
            lav = dbListc.get(position).getLav_c();
            las = dbListc.get(position).getLas_c();
            lab = dbListc.get(position).getLab_c();
            pol = dbListc.get(position).getPol_c();
            met = dbListc.get(position).getMet_c();
            car = dbListc.get(position).getCar_c();
            est = dbListc.get(position).getEst_c();
            qtu = dbListc.get(position).getQtu_c();
            bol = dbListc.get(position).getBol_c();
            otr = dbListc.get(position).getOtr_c();

            if(asp.equals("1")){ck_servi_1.setChecked(true);}
            if(com.equals("1")){ck_servi_2.setChecked(true);}
            if(lav.equals("1")){ck_servi_3.setChecked(true);}
            if(las.equals("1")){ck_servi_4.setChecked(true);}
            if(lab.equals("1")){ck_servi_5.setChecked(true);}
            if(pol.equals("1")){ck_servi_6.setChecked(true);}
            if(met.equals("1")){ck_servi_7.setChecked(true);}
            if(car.equals("1")){ck_servi_8.setChecked(true);}
            if(est.equals("1")){ck_servi_9.setChecked(true);}
            if(qtu.equals("1")){ck_servi_10.setChecked(true);}
            if(bol.equals("1")){ck_servi_11.setChecked(true);}
            if(otr.equals("1")){ck_servi_12.setChecked(true);}

            aspObs = dbListc.get(position).getAsp_obs_c();
            comObs = dbListc.get(position).getCom_obs_c();
            lavObs = dbListc.get(position).getLav_obs_c();
            lasObs = dbListc.get(position).getLas_obs_c();
            labObs = dbListc.get(position).getLab_obs_c();
            polObs = dbListc.get(position).getPol_obs_c();
            metObs = dbListc.get(position).getMet_obs_c();
            carObs = dbListc.get(position).getCar_obs_c();
            estObs = dbListc.get(position).getEst_obs_c();
            qtuObs = dbListc.get(position).getQtu_obs_c();
            bolObs = dbListc.get(position).getBol_obs_c();
            otrObs = dbListc.get(position).getOtr_obs_c();


            if(aspObs.equals("0")){aspObs="";}
            if(comObs.equals("0")){comObs="";}
            if(lavObs.equals("0")){lavObs="";}
            if(lasObs.equals("0")){lasObs="";}
            if(labObs.equals("0")){labObs="";}
            if(polObs.equals("0")){polObs="";}
            if(metObs.equals("0")){metObs="";}
            if(carObs.equals("0")){carObs="";}
            if(estObs.equals("0")){estObs="";}
            if(qtuObs.equals("0")){qtuObs="";}
            if(bolObs.equals("0")){bolObs="";}
            if(otrObs.equals("0")){otrObs="";}

            etObs1.setText(aspObs);
            etObs2.setText(comObs);
            etObs3.setText(lavObs);
            etObs4.setText(lasObs);
            etObs5.setText(labObs);
            etObs6.setText(polObs);
            etObs7.setText(metObs);
            etObs8.setText(carObs);
            etObs9.setText(estObs);
            etObs10.setText(qtuObs);
            etObs11.setText(bolObs);
            etObs12.setText(otrObs);





        }







/*
        final CheckBox ck_serv_1 =(CheckBox)findViewById(R.id.ck_serv_1);

        if(ck_serv_1.isChecked()) {


            Log.d("ExportarGiro DEV", "OK");

        }
        ck_serv_1.isChecked(){
            Toast toast = Toast.makeText(getApplicationContext(), "NÃO CONSEGUI LER, TENTE NOVAMENTE!", Toast.LENGTH_LONG);
            toast.show();

        }

        //checkBox.setChecked(false);


*/
        /*Linear Layout now will call the new listener*/
        linearLayout = (LinearLayout) findViewById(R.id.ll_serv_serv);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              grava();

            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.ll_serv_ckum);
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

                int iatendimento = Integer.parseInt(atendimento);
                int imanutencao = Integer.parseInt(manutencao);
                int iavulso = Integer.parseInt(avulso);
                Intent intent2 = new Intent(CadastroServicoActivity.this, CkUmActivity.class);
                intent2.putExtras(params);

                int total = iatendimento+imanutencao+iavulso;

//        if(total>1){
////
////            Toast toast = Toast.makeText(getApplicationContext(), "Escolha apenas um tipo de serviço!", Toast.LENGTH_LONG);
////            toast.show();
////
////        }
                if (total == 0) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Escolha ao menos um tipo de serviço!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(total == 1){


                    startActivity(intent2);
                    finish();

                    final BancoController crud = new BancoController(getBaseContext());
                    //faz a gravação
                //    crud.atualizaFotoInicio(nrFicha, dt_inicio, hr_inicio);

                    crud.atualizaServicoCk(nrFicha, asp, com, lav, las, lab, pol, met, car, est, qtu, bol, otr);

                    crud.atualizaTipoServicoCk(nrFicha, atendimento, manutencao, avulso);




                    crud.atualizaServicosObs(nrFicha, aspObs, comObs,lavObs,lasObs, labObs, polObs, metObs, carObs, estObs, qtuObs, bolObs,otrObs);

                }
                else if(total>1){
                    ck_serv_atendimento.setChecked(false);
                    ck_serv_manutencao.setChecked(false);
                    ck_serv_avulso.setChecked(false);
                    Toast toast = Toast.makeText(getApplicationContext(), "Escolha apenas um tipo de serviço!", Toast.LENGTH_LONG);
                    toast.show();

                }


            }
        });

    }


    private void grava() {

        //Toast toast = Toast.makeText(getApplicationContext(), "NÃO CONSEGUI LER, TENTE NOVAMENTE!", Toast.LENGTH_LONG);
        //toast.show();

        atendimento = ck_serv_atendimento.isChecked() ? "1":"0";
        manutencao = ck_serv_manutencao.isChecked() ? "1":"0";
        avulso = ck_serv_avulso.isChecked() ? "1":"0";

//        Calendar c = Calendar.getInstance();
//        System.out.println("Current time => " + c.getTime());
//
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        final String dataInicio = df.format(c.getTime());
//
//        SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
//        final String horaInicio = dft.format(c.getTime());
//
//
//        tv_dt_inicio.setText(dataInicio);
//        tv_hr_inicio.setText(horaInicio);


        asp = ck_servi_1.isChecked() ? "1":"0";
        com = ck_servi_2.isChecked() ? "1":"0";
        lav = ck_servi_3.isChecked() ? "1":"0";
        las = ck_servi_4.isChecked() ? "1":"0";
        lab = ck_servi_5.isChecked() ? "1":"0";
        pol = ck_servi_6.isChecked() ? "1":"0";
        met = ck_servi_7.isChecked() ? "1":"0";
        car = ck_servi_8.isChecked() ? "1":"0";
        est = ck_servi_9.isChecked() ? "1":"0";
        qtu = ck_servi_10.isChecked() ? "1":"0";
        bol = ck_servi_11.isChecked() ? "1":"0";
        otr = ck_servi_12.isChecked() ? "1":"0";


        etObs1 = (EditText) findViewById(R.id.et_obs_1);
        etObs2 = (EditText) findViewById(R.id.et_obs_2);
        etObs3 = (EditText) findViewById(R.id.et_obs_3);
        etObs4 = (EditText) findViewById(R.id.et_obs_4);
        etObs5 = (EditText) findViewById(R.id.et_obs_5);
        etObs6 = (EditText) findViewById(R.id.et_obs_6);
        etObs7 = (EditText) findViewById(R.id.et_obs_7);
        etObs8 = (EditText) findViewById(R.id.et_obs_8);
        etObs9 = (EditText) findViewById(R.id.et_obs_9);
        etObs10 = (EditText) findViewById(R.id.et_obs_10);
        etObs11 = (EditText) findViewById(R.id.et_obs_11);
        etObs12 = (EditText) findViewById(R.id.et_obs_12);

        aspObs = etObs1.getText().toString();
        comObs = etObs2.getText().toString();
        lavObs = etObs3.getText().toString();
        lasObs = etObs4.getText().toString();
        labObs = etObs5.getText().toString();
        polObs = etObs6.getText().toString();
        metObs = etObs7.getText().toString();
        carObs = etObs8.getText().toString();
        estObs = etObs9.getText().toString();
        qtuObs = etObs10.getText().toString();
        bolObs = etObs11.getText().toString();
        otrObs = etObs12.getText().toString();

        /*
        if(aspObs != null && !aspObs.isEmpty()){ aspObs = "0";}
        if(comObs != null && !comObs.isEmpty()){ comObs = "0";}
        if(lavObs != null && !lavObs.isEmpty()){ lavObs = "0";}
        if(lasObs != null && !lasObs.isEmpty()){ lasObs = "0";}
        if(labObs != null && !labObs.isEmpty()){ labObs = "0";}
        if(polObs != null && !polObs.isEmpty()){ polObs = "0";}
        if(metObs != null && !metObs.isEmpty()){ metObs = "0";}
        if(carObs != null && !carObs.isEmpty()){ carObs = "0";}
        if(estObs != null && !estObs.isEmpty()){ estObs = "0";}
        if(qtuObs != null && !qtuObs.isEmpty()){ qtuObs = "0";}
        if(bolObs != null && !bolObs.isEmpty()){ bolObs = "0";}
        if(otrObs != null && !otrObs.isEmpty()){ otrObs = "0";}


        if(aspObs == null && aspObs.isEmpty()){ aspObs = "0";}
        if(comObs == null && comObs.isEmpty()){ comObs = "0";}
        if(lavObs == null && lavObs.isEmpty()){ lavObs = "0";}
        if(lasObs == null && lasObs.isEmpty()){ lasObs = "0";}
        if(labObs == null && labObs.isEmpty()){ labObs = "0";}
        if(polObs == null && polObs.isEmpty()){ polObs = "0";}
        if(metObs == null && metObs.isEmpty()){ metObs = "0";}
        if(carObs == null && carObs.isEmpty()){ carObs = "0";}
        if(estObs == null && estObs.isEmpty()){ estObs = "0";}
        if(qtuObs == null && qtuObs.isEmpty()){ qtuObs = "0";}
        if(bolObs == null && bolObs.isEmpty()){ bolObs = "0";}
        if(otrObs == null && otrObs.isEmpty()){ otrObs = "0";}
*/

//        if(total==1){
//
//            final BancoController crud = new BancoController(getBaseContext());
//            //faz a gravação
//            crud.atualizaFotoInicio(nrFicha, dataInicio, horaInicio);
//
//            crud.atualizaServicoCk(nrFicha, asp, com, lav, las, lab, pol, met, car, est, qtu, bol, otr);
//
//            crud.atualizaTipoServicoCk(nrFicha, atendimento, manutencao, avulso);
//
//            crud.atualizaServicosObs(nrFicha, aspObs, comObs,lavObs,lasObs, labObs, polObs, metObs, carObs, estObs, qtuObs, bolObs,otrObs);
//
//        }


    }


}
