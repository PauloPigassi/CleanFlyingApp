package br.com.onder.cleanflyingos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class PesquisaAeronaveActivity extends AppCompatActivity {

    String prefixo, modelo, cliente, setor, nrFicha, nomemodelo, nomecategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_aeronave);

        final BancoController crud = new BancoController(getBaseContext());

        final TextView tvModelo = (TextView) findViewById(R.id.tv_pa_modelo);
        final TextView tvCategoria = (TextView) findViewById(R.id.tv_pa_categoria);
        final TextView tvSetor = (TextView) findViewById(R.id.tv_pa_setor);
        final TextView tvCliente = (TextView) findViewById(R.id.tv_pa_cliente);

        final EditText etPrefixo = (EditText) findViewById(R.id.et_p_prefixo);
        //EditdText etCPF = /* sua inicialização aqui */;
        etPrefixo.addTextChangedListener(Mask.insert(Mask.PREFIXO_MASK, etPrefixo));

        final Button btNovaAeronave = (Button) findViewById(R.id.bt_pa_nova_aeronave);
        final Button btNovaFicha = (Button) findViewById(R.id.bt_pa_nova_ficha);
        btNovaAeronave.setEnabled(false);
        btNovaFicha.setEnabled(false);

        final Button bp = (Button) findViewById(R.id.bt_pa_pesquisa_aeronave);
        bp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //String ck = "0";
                closeKeyboard();
                tvModelo.setText("-");
                tvCategoria.setText("-");
                tvSetor.setText("-");
                tvCliente.setText("-");

                final EditText etprefixo = (EditText) findViewById(R.id.et_p_prefixo);
                prefixo = etprefixo.getText().toString();

                if(etprefixo.getText().length() == 0){//como o tamanho é zero é nulla aresposta

                    etprefixo.setError("Campo vazio");

                }else if (etprefixo.getText().length() < 6){

                    etprefixo.setError("Minimo 6 letras");

                }else{

                    //ck = "1";
                    //Log.d("Sincronismo", "NUCLEO JA EXISTE NO TABLET");

                    //Pesquisa prefixo
                    Cursor cu = crud.buscarprefixo(prefixo);
                    try {
                        if (cu.getCount() >= 1) {
                            btNovaFicha.setEnabled(true);
                            btNovaAeronave.setEnabled(false);
                            cu.moveToLast();
                            //Log.d("CAD", "ACHOU AERONAVE");
                            String codmodelo = cu.getString(cu.getColumnIndex("modelo"));
                            String codcategoria = cu.getString(cu.getColumnIndex("categoria"));
                            String codcliente = cu.getString(cu.getColumnIndex("cliente"));
                            String codsetor = cu.getString(cu.getColumnIndex("setor"));
                            nomemodelo = "";
                            nomecategoria = "";
                            String nomesetor = "";
                            String nomecliente = "";

                            Cursor cumodelo = crud.buscarmodelo(codmodelo);
                            if (cumodelo.getCount() >= 1) {
                                cumodelo.moveToLast();
                                nomemodelo = cumodelo.getString(cumodelo.getColumnIndex("modelo"));
                            }
                            if (cumodelo != null) {
                                cumodelo.close();
                            }
                            Cursor cucategoria = crud.buscarcategoria(codcategoria);
                            if (cucategoria.getCount() >= 1) {
                                cucategoria.moveToLast();
                                nomecategoria = cucategoria.getString(cucategoria.getColumnIndex("categoria"));
                            }
                            if (cucategoria != null) {
                                cucategoria.close();
                            }
                            Cursor cusetor = crud.buscarsetor(codsetor);
                            if (cusetor.getCount() >= 1) {
                                cusetor.moveToLast();
                                nomesetor = cusetor.getString(cusetor.getColumnIndex("setor"));
                            }
                            if (cusetor != null) {
                                cusetor.close();
                            }
                            Cursor cucliente = crud.buscarcliente(codcliente);
                            if (cucliente.getCount() >= 1) {
                                cucliente.moveToLast();
                                nomecliente = cucliente.getString(cucliente.getColumnIndex("cliente"));
                            }
                            if (cucliente != null) {
                                cucliente.close();
                            }



                            //String jmodelo = nomemodelo+"/"+nomecategoria;



                            // etprefixo.setText(prefixo);
                            tvModelo.setText(nomemodelo);
                            tvCategoria.setText(nomecategoria);
                            tvSetor.setText(nomesetor);
                            tvCliente.setText(nomecliente);



                        }else{

                            btNovaAeronave.setEnabled(true);
                            btNovaFicha.setEnabled(false);

                            //Log.d("CAD", "NÃO ACHOU AERONAVE");
                        }
                    } finally {
                        if (cu != null) {
                            cu.close();
                        }

                    }
                    cu.close();


                }





            }
        });


        final Button bvoltar = (Button) findViewById(R.id.bt_pa_voltar);
        bvoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent intent2 = new Intent(PesquisaAeronaveActivity.this, FichasActivity.class);

                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();

            }
        });

        final Button bnovaaeronave = (Button) findViewById(R.id.bt_pa_nova_aeronave);
        bnovaaeronave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent intent3 = new Intent(PesquisaAeronaveActivity.this, CadastrarNovaAeronaveActivity.class);

                intent3.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent3);

                finish();

            }
        });



        final Button bnovaficha = (Button) findViewById(R.id.bt_pa_nova_ficha);
        bnovaficha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //prefixo = etprefixo.getText().toString();


                //modelo = etmodelo.getText().toString();


               // cliente = etcliente.getText().toString();


                //setor = etsetor.getText().toString();

                long resultado;


                //Criando o nr da ficha
                int n = new Random().nextInt(999999);
                String rand = String.valueOf(n);

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyhhmmss");
                final String data = df.format(c.getTime());

                nrFicha = rand+""+data;


                resultado =  crud.inserirAeronaveFicha(nrFicha,prefixo,nomemodelo, nomecategoria, cliente,setor);
                Intent intent2 = new Intent(PesquisaAeronaveActivity.this, FichasActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);
                finish();

            }
        });



    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
    }

}
