package br.com.onder.cleanflyingos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ObsActivity extends AppCompatActivity {

    String nrFicha, prefixo, modelo, cliente, setor, dt_inicio, dt_termino, hr_inicio, hr_termino, obs, obsObs;
    TextView tv_visualizar, tv_prefixo, tv_modelo, tv_cliente, tv_setor, tv_dt_inicio, tv_dt_termino, tv_hr_inicio, tv_hr_termino, tv_obsObs;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obs);
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


            //Log.i("Script", "position: "+position);

        }


        final BancoController crud = new BancoController(getBaseContext());



        //Recuperando valores dos radios
        Cursor cur = crud.buscarObsObs(nrFicha);
        try {
            obsObs = cur.getString(cur.getColumnIndex("obs_obs"));


        } finally {
            if (cur != null) {
                cur.close();
            }
        }




        tv_prefixo = (TextView) findViewById(R.id.tv_ck2_prefixo);
        tv_modelo = (TextView) findViewById(R.id.tv_ck2_modelo);
        tv_cliente = (TextView) findViewById(R.id.tv_ck2_cliente);
        tv_setor = (TextView) findViewById(R.id.tv_ck2_setor);
//        tv_dt_inicio = (TextView) findViewById(R.id.tv_ck2_data_inicio);
//        tv_dt_termino = (TextView) findViewById(R.id.tv_ck2_data_termino);
//        tv_hr_inicio = (TextView) findViewById(R.id.tv_ck2_hora_inicio);
//        tv_hr_termino = (TextView) findViewById(R.id.tv_ck2_hora_termino);
        tv_visualizar = (TextView) findViewById(R.id.tv_ck2_visualizar);
        tv_obsObs = (TextView) findViewById(R.id.et_obs_obs);

        tv_prefixo.setText(prefixo);
        tv_modelo.setText(modelo);
        tv_cliente.setText(cliente);
        tv_setor.setText(setor);
//        tv_dt_inicio.setText(dt_inicio);
//        tv_dt_termino.setText(dt_termino);
//        tv_hr_inicio.setText(hr_inicio);
//        tv_hr_termino.setText(hr_termino);
        tv_obsObs.setText(obsObs);






        //Clique em outra aba
        //ll_serv_ckobs
        linearLayout = (LinearLayout) findViewById(R.id.ll_obs_serv);
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

                Intent intent2 = new Intent(ObsActivity.this, CkUmActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.ll_obs_ckum);
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

                Intent intent2 = new Intent(ObsActivity.this, CkUmActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.ll_obs_ckdois);
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

                Intent intent2 = new Intent(ObsActivity.this, CkDoisActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });


        linearLayout = (LinearLayout) findViewById(R.id.ll_obs_resp);
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

                Intent intent2 = new Intent(ObsActivity.this, RespActivity.class);
                intent2.putExtras(params);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();


            }
        });



    }

    private void grava() {


        EditText etobs = (EditText) findViewById(R.id.et_obs_obs);
        obs = etobs.getText().toString();

        final BancoController crud = new BancoController(getBaseContext());

        crud.atualizaObsObs(nrFicha,obs);

    }


}
