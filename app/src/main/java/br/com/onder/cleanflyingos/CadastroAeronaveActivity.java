package br.com.onder.cleanflyingos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class CadastroAeronaveActivity extends AppCompatActivity {

    String horaInicio, dataInicio, prefixo, categoria, modelo, cliente, setor, nrFicha, nomemodelo, nomecategoria, nomesetor, nomecliente;


    private Spinner spinnersetor, spinnercliente;
    private List<String> a_setor = new ArrayList<String>();
    private List<String> a_cliente = new ArrayList<String>();
    private String sSetor, sCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aeronave);

        final BancoController crud = new BancoController(getBaseContext());

        final EditText etprefixo = (EditText) findViewById(R.id.et_prefixo);
        etprefixo.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etprefixo.addTextChangedListener(Mask.insert(Mask.PREFIXO_MASK, etprefixo));

        final EditText etmodelo = (EditText) findViewById(R.id.et_modelo);
        etmodelo.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        final EditText etcliente = (EditText) findViewById(R.id.et_cliente);
        etcliente.setFilters(new InputFilter[]{new InputFilter.AllCaps()});


//        final EditText etsetor = (EditText) findViewById(R.id.et_setor);
//        etsetor.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        final EditText etcategoria = (EditText) findViewById(R.id.et_categoria);
        etcategoria.setFilters(new InputFilter[]{new InputFilter.AllCaps()});


        //bt_pesq_prefixo
        final Button bp = (Button) findViewById(R.id.bt_pesq_prefixo);
        bp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //String ck = "0";

                final EditText etprefixo = (EditText) findViewById(R.id.et_prefixo);
                prefixo = etprefixo.getText().toString();

                if (etprefixo.getText().length() == 0) {//como o tamanho é zero é nulla aresposta

                    etprefixo.setError("Campo vazio");

                } else if (etprefixo.getText().length() < 5) {

                    etprefixo.setError("Minimo 5 letras");

                } else {

                    //Mostra spinner com texto selecione

                    //a_complementoletra.add("-");

                    a_setor.add("Selecione");
                    SQLiteDatabase db = openOrCreateDatabase("cf.db", Context.MODE_PRIVATE, null);

                    Cursor curetorno = db.rawQuery("SELECT * FROM setores ORDER BY setor ASC", null);

//                    a_cliente.add("Selecione");
//
//                    Cursor cucliente = db.rawQuery("SELECT * FROM clientes ORDER BY cliente ASC", null);
//

                    int posicao = 0;
                    int posicaocliente=0;

                    try {
                        if (curetorno.getCount() >= 1 ) {

                            while (curetorno.moveToNext()) {

                                sSetor = curetorno.getString(curetorno.getColumnIndex("setor"));
                                a_setor.add(sSetor);

                            }

                        }
                    } finally {
                        if (curetorno != null) {
                            curetorno.close();
                        }
                    }

//                    try{
//                        if (cucliente.getCount() >= 1 ) {
//
//                            while (cucliente.moveToNext()) {
//
//                                sCliente = cucliente.getString(cucliente.getColumnIndex("cliente"));
//                                a_cliente.add(sCliente);
//
//                            }
//
//                        }
//                    }finally {
//                        if (cucliente != null) {
//                            cucliente.close();
//                        }
//                    }


                    spinnersetor = (Spinner) findViewById(R.id.sp_spinnersetor);
                    //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
                    ArrayAdapter<String> arrayAdaptersetor = new ArrayAdapter<String>(CadastroAeronaveActivity.this, android.R.layout.simple_spinner_dropdown_item, a_setor);
                    ArrayAdapter<String> spinnerArrayAdaptersetor = arrayAdaptersetor;
                    spinnerArrayAdaptersetor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnersetor.setAdapter(spinnerArrayAdaptersetor);
                    //Método do Spinner para capturar o item selecionado
                    spinnersetor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                            //pega nome pela posição
                            sSetor = parent.getItemAtPosition(posicao).toString();
                            if (sSetor.equals("Selecione")) {
                                sSetor = "";
                            }
                            //imprime um Toast na tela com o nome que foi selecionado
                            nomesetor = sSetor;
                            //Toast.makeText(CadastroAeronaveActivity.this, "Selecionado: " + sSetor, Toast.LENGTH_LONG).show();
                            Toast.makeText(CadastroAeronaveActivity.this, "Selecionado: " + nomesetor, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });



//                    spinnercliente = (Spinner) findViewById(R.id.sp_spinnercliente);
//                    //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
//                    ArrayAdapter<String> arrayAdapterCliente = new ArrayAdapter<String>(CadastroAeronaveActivity.this, android.R.layout.simple_spinner_dropdown_item, a_cliente);
//                    ArrayAdapter<String> spinnerArrayAdapterCliente = arrayAdapterCliente;
//                    spinnerArrayAdapterCliente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinnercliente.setAdapter(spinnerArrayAdapterCliente);
//                    //Método do Spinner para capturar o item selecionado
//                    spinnercliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View v, int posicaocliente, long idcliente) {
//                            //pega nome pela posição
//                            sCliente = parent.getItemAtPosition(posicaocliente).toString();
//                            if (sCliente.equals("Selecione")) {
//                                sCliente = "";
//                            }
//                            //imprime um Toast na tela com o nome que foi selecionado
//                            nomecliente = sCliente;
//                            //Toast.makeText(CadastroAeronaveActivity.this, "Selecionado: " + sCliente, Toast.LENGTH_LONG).show();
//                            Toast.makeText(CadastroAeronaveActivity.this, "Selecionado: " + nomecliente, Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });


                    //ck = "1";
                    //Log.d("Sincronismo", "NUCLEO JA EXISTE NO TABLET");

                    //Pesquisa prefixo
                    Cursor cu = crud.buscarprefixo(prefixo);
                    try {
                        if (cu.getCount() >= 1) {
                            cu.moveToLast();
                            //Log.d("CAD", "ACHOU AERONAVE");
                            String codmodelo = cu.getString(cu.getColumnIndex("modelo"));
                            String codcategoria = cu.getString(cu.getColumnIndex("categoria"));
                            String codcliente = cu.getString(cu.getColumnIndex("cliente"));
                            String codsetor = cu.getString(cu.getColumnIndex("setor"));
                            nomemodelo = "";
                            nomecategoria = "";
                            nomesetor = "";
                            nomecliente = "";

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
                            Cursor cucliente = crud.buscarcliente(codcliente);
                            if (cucliente.getCount() >= 1) {
                                cucliente.moveToLast();
                                nomecliente = cucliente.getString(cucliente.getColumnIndex("cliente"));
                            } else if(cucliente.getCount() == 0){
                                nomecliente = "Outros clientes";
                            }
                            if (cucliente != null) {
                                cucliente.close();
                            }
                            Cursor cusetor = crud.buscarsetor(codsetor);
                            if (cusetor.getCount() >= 1) {
                              //  while(cusetor.moveToNext()){
                                     nomesetor = cusetor.getString(cusetor.getColumnIndex("setor"));


//                                Cursor curetornop = db.rawQuery("SELECT * FROM setores", null);
//                                try {
//                                    if (curetornop.getCount() >= 1) {
//                                        int posicaop = 1;
//                                       while (curetornop.moveToNext()) {
//
//                                            String sSetorp = curetornop.getString(curetorno.getColumnIndex("setor"));
//
//
//                                            if (nomesetor.equals(sSetorp)) {
//
//                                                spinnersetor.setSelection(posicaop);
//                                            }
//
//                                            posicaop++;
//                                        }
//
//                                    }
//                                } finally {
//                                    if (curetorno != null) {
//                                        curetorno.close();
//                                    }
//                                }
//
//
//                            }
                            }
                            if (cusetor != null) {
                                cusetor.close();
                            }
//                            Cursor cuCliente = crud.buscarcliente(codcliente);
//                            if (cuCliente.getCount() >= 1) {
//                                cuCliente.moveToLast();
//                                nomecliente = cuCliente.getString(cuCliente.getColumnIndex("cliente"));
//
//                                Cursor curetornoc = db.rawQuery("SELECT * FROM clientes", null);
//                                try {
//                                    if (curetornoc.getCount() >= 1) {
//                                        int posicaoc = 1;
//                                        while (curetornoc.moveToNext()) {
//
//                                            String sClientec = curetornoc.getString(cucliente.getColumnIndex("cliente"));
//
//
//                                            if (nomecliente.equals(sClientec)) {
//
//                                                spinnercliente.setSelection(posicaoc);
//                                            }
//
//                                            posicaoc++;
//                                        }
//
//                                    }
//                                } finally {
//                                    if (cucliente != null) {
//                                        cucliente.close();
//                                    }
//                                }
//                            }
//                            if (cuCliente != null) {
//                                cuCliente.close();
//                            }


                            String jmodelo = nomemodelo + "/" + nomecategoria;


                            // etprefixo.setText(prefixo);
                            etmodelo.setText(nomemodelo);
                            etcategoria.setText(nomecategoria);
                            etcliente.setText(nomecliente);
                           //  etsetor.setText(nomesetor);


                        } else if (cu.getCount() == 0) {

                            Toast.makeText(CadastroAeronaveActivity.this, "Prefixo não encontrado", Toast.LENGTH_LONG).show();


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


        final Button b3 = (Button) findViewById(R.id.bt_gravar_aeronave);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                prefixo = etprefixo.getText().toString();

                nomemodelo = etmodelo.getText().toString();

                cliente = etcliente.getText().toString();
//                nomecategoria = etcategoria.getText().toString();

                Toast.makeText(CadastroAeronaveActivity.this, "SETOR: " + nomesetor, Toast.LENGTH_LONG).show();

                Calendar c = Calendar.getInstance();

                SimpleDateFormat dinicio = new SimpleDateFormat("dd/MM/yyyy");
                dataInicio = dinicio.format(c.getTime());

                SimpleDateFormat hinicio = new SimpleDateFormat("HH:mm");
                horaInicio = hinicio.format(c.getTime());
                Bundle params = new Bundle();

                params.putString("dt_inicio", dataInicio);
                params.putString("hr_inicio", horaInicio);

                Intent intent = new Intent(CadastroAeronaveActivity.this, RecyclerAdapter.class);
                intent.putExtras(params);



                // setor = etsetor.getText().toString();

                long resultado;


                //Criando o nr da ficha
                int n = new Random().nextInt(999999);
                String rand = String.valueOf(n);

//                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyhhmmss");
                final String data = df.format(c.getTime());

                nrFicha = rand + "" + data;
//
//                Log.d("numero ficha", nrFicha);
//                Log.d("prefixo", prefixo);
//                Log.d("nomemodelo", nomemodelo);
////                Log.d("nomecategoria", nomecategoria);
//                Log.d("nomecliente", nomecliente);
//                Log.d("nomesetor", nomesetor);
//                Log.d("horainicio", horaInicio);
//                Log.d("datainicio", dataInicio);

                final StringBuilder strURL = new StringBuilder();
                strURL.append("http://cleanflying.onder.com.br/os/wbs/nova_ficha.php?fichanr=");
                strURL.append(nrFicha);


//                final int MILISEGUNDOS = 30;
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run() {
//                        String url = strURL.toString();
//                        Intent i = new Intent(Intent.ACTION_VIEW);
//                        i.setData(Uri.parse(url));
//                        startActivity(i);
//
//                        finish();
//                    }
//                }, MILISEGUNDOS);





//                final StringBuilder strURLcab = new StringBuilder();
//                strURLcab.append("http://cleanflying.onder.com.br/os/wbs/cab_ficha.php?fichanr=");
//                strURLcab.append(nrFicha);
//                strURLcab.append("&modelo=");
//                strURLcab.append(nomemodelo);
//                strURLcab.append("&prefixo=");
//                strURLcab.append(prefixo);
//                strURLcab.append("&categoria=");
//                strURLcab.append(nomecategoria);
//                strURLcab.append("&data_inicio=");
//                strURLcab.append(dataInicio);
//                strURLcab.append("&hora_inicio=");
//                strURLcab.append(horaInicio);
//                strURLcab.append("&cliente=");
//                strURLcab.append(nomecliente);
//                strURLcab.append("&setor=");
//                strURLcab.append(nomesetor);

                Log.d("url", strURL.toString());
//                String stringUrl = strURLcab.toString();
//                // stringUrl.replace(" ", "-");
//                //stringUrl.replaceAll(Pattern.quote (" "), "-");
//                //String st= stringUrl.replaceAll(Pattern.quote ("."), "");
//                final String st= stringUrl.replaceAll(Pattern.quote (" "), "-");
//                st.trim();

//                Log.d("st1234", st);




//                try {
//                    URL urlCab = new URL(stringUrl);
//                    URI uriCab = new URI(urlCab.getProtocol(), urlCab.getUserInfo(), urlCab.getHost(), urlCab.getPort(), urlCab.getPath(), urlCab.getQuery(), urlCab.getRef());
//                    urlCab = uriCab.toURL();
//
//                    HttpURLConnection httpCab = (HttpURLConnection) urlCab.openConnection();
//                    InputStreamReader ipsCab = new InputStreamReader(httpCab.getInputStream());
//                    BufferedReader lineCab = new BufferedReader(ipsCab);
//                    //String linhaRetornoCab = lineCab.readLine();
//                    httpCab.disconnect();
//                }
//                catch (Exception ex){
//
//                }


                resultado = crud.inserirAeronaveFicha(nrFicha, prefixo, nomemodelo, nomecategoria, nomecliente, nomesetor);

                Intent intent2 = new Intent(CadastroAeronaveActivity.this, FichasActivity.class);

                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);



                finish();

            }
        });


        final Button b2 = (Button) findViewById(R.id.bt_cancelar_cadastro);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent2 = new Intent(CadastroAeronaveActivity.this, FichasActivity.class);

                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();

            }
        });

    }










}
