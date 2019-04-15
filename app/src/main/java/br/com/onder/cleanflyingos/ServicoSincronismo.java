package br.com.onder.cleanflyingos;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicoSincronismo extends Service {

    //public DataBaseManager manager;
    public List<Worker> threads = new ArrayList<Worker>();

    JSONArray contacts = null;
    JSONArray jMod = null;
    JSONArray jCat = null;
    JSONArray jCli = null;
    JSONArray jSet = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.i("Script", "onStartCommand()");
        Worker w = new Worker(startId);
        w.start();
        threads.add(w);


        return (super.onStartCommand(intent, flags, startId));
    }

    class Worker extends Thread {
        public int count = 0;
        public int startId;
        public boolean ativo = true;

        public Worker(int startId) {
            this.startId = startId;
        }

        public void run() {

            if (isOnline()) {
                //Log.d("Sincronismo", "esta on line");

                BancoController crud = new BancoController(getBaseContext());

                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ServicoSincronismo.this);
                final String servidor = "cleanflying.onder.com.br";

                //AERONAVES
                String url5 = "http://"+servidor+"/os/wbs/envia_aeronaves.php";
                Log.d("JSON", "URL: "+url5);
                // Creating JSON Parser instance
                JSONParser jParser = new JSONParser();

                // getting JSON string from URL
                JSONObject json = jParser.getJSONFromUrl(url5);

                try {
                    // Getting Array of dados
                    contacts = json.getJSONArray("dados");

                    // looping dados
                    for(int i = 0; i < contacts.length(); i++){
                        JSONObject c = contacts.getJSONObject(i);

                        // Storing each json item in variable
                        String id_servidor = c.getString("id");
                        String prefixo = c.getString("prefixo");
                        //Log.d("JSON", "PREFIXO: "+prefixo);
                        String modelo = c.getString("modelo");
                        String categoria = c.getString("categoria");
                        String setor = c.getString("setor");
                        String cliente = c.getString("cliente");

                        Cursor jaTemAeronave = crud.buscarSeJaTemAeronave(id_servidor);

                        try {
                            if (jaTemAeronave.getCount() >= 1) {
                               // Log.d("Sincronismo", "JA TEM AERONAVE, ATUALIZOU BD");
                                crud.atualizaAeronave(id_servidor, prefixo, modelo, categoria, setor, cliente);

                            } else {

                                long resultado;
                                resultado = crud.inserirAeronaveAero(id_servidor, prefixo, modelo, categoria, setor, cliente);
                                //Log.d("Sincronismo", "NAO TEM AERONAVE, INSERIU NO BD");
                            }
                        } finally {
                            if (jaTemAeronave != null) {
                                jaTemAeronave.close();
                            }
                        }
                        jaTemAeronave.close();
                        //Log.d("JSON", "URL2: "+strURL2);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //FIM AERONAVE

                //MODELOS
                String urlmod = "http://"+servidor+"/os/wbs/envia_modelos.php";
                //Log.d("JSON", "URL: "+url2);
                // Creating JSON Parser instance
                JSONParser jParsermod = new JSONParser();

                // getting JSON string from URL
                JSONObject jsonMod = jParsermod.getJSONFromUrl(urlmod);

                try {
                    // Getting Array of dados
                    jMod = jsonMod.getJSONArray("dados");

                    // looping dados
                    for(int i = 0; i < jMod.length(); i++){
                        JSONObject cuMod = jMod.getJSONObject(i);

                        // Storing each json item in variable
                        String id_servidorMod = cuMod.getString("id");
                        //Log.d("JSON", "ID MNODELO "+id_servidorMod);
                        String modeloMod = cuMod.getString("modelo");
                        //Log.d("JSON", "MODELO: "+modeloMod);


                        Cursor jaTemModelo = crud.buscarSeJaTemModelo(id_servidorMod);

                        try {
                            if (jaTemModelo.getCount() >= 1) {
                                //Log.d("Sincronismo", "NUCLEO JA EXISTE NO TABLET");
                                crud.atualizaModelo(id_servidorMod, modeloMod);

                            } else {

                                long resultado;
                                resultado = crud.inserirModelo(id_servidorMod, modeloMod);
                                //Log.d("Sincronismo", "NÃO ACHOU NUCLEO GRAVOU NO BD");
                            }
                        } finally {
                            if (jaTemModelo != null) {
                                jaTemModelo.close();
                            }
                        }
                        jaTemModelo.close();
                        //Log.d("JSON", "URL2: "+strURL2);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //FIM Modelo


                //CATEGORIA
                String urlcat = "http://"+servidor+"/os/wbs/envia_categorias.php";
                //Log.d("JSON", "URL: "+url2);
                // Creating JSON Parser instance
                JSONParser jParsercat = new JSONParser();

                // getting JSON string from URL
                JSONObject jsonCat = jParsermod.getJSONFromUrl(urlcat);

                try {
                    // Getting Array of dados
                    jCat = jsonCat.getJSONArray("dados");

                    // looping dados
                    for(int i = 0; i < jCat.length(); i++){
                        JSONObject cuCat = jCat.getJSONObject(i);

                        // Storing each json item in variable
                        String id_servidorCat = cuCat.getString("id");
                        String categoriaCat = cuCat.getString("categoria");


                        Cursor jaTemCategoria = crud.buscarSeJaTemCategoria(id_servidorCat);

                        try {
                            if (jaTemCategoria.getCount() >= 1) {
                                //Log.d("Sincronismo", "NUCLEO JA EXISTE NO TABLET");
                                crud.atualizaCategoria(id_servidorCat, categoriaCat);

                            } else {

                                long resultado;
                                resultado = crud.inserirCategoria(id_servidorCat, categoriaCat);
                                //Log.d("Sincronismo", "NÃO ACHOU NUCLEO GRAVOU NO BD");
                            }
                        } finally {
                            if (jaTemCategoria != null) {
                                jaTemCategoria.close();
                            }
                        }
                        jaTemCategoria.close();
                        //Log.d("JSON", "URL2: "+strURL2);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //FIM Categoria



                //Cliente
                String urlcli = "http://"+servidor+"/os/wbs/envia_clientes.php";
                //Log.d("JSON", "URL: "+url2);
                // Creating JSON Parser instance
                JSONParser jParsercli = new JSONParser();

                // getting JSON string from URL
                JSONObject jsonCli = jParsercli.getJSONFromUrl(urlcli);

                try {
                    // Getting Array of dados
                    jCli = jsonCli.getJSONArray("dados");

                    // looping dados
                    for(int i = 0; i < jCli.length(); i++){
                        JSONObject cuCli = jCli.getJSONObject(i);

                        // Storing each json item in variable
                        String id_servidorCli = cuCli.getString("id");
                        String clienteCli = cuCli.getString("cliente");


                        Cursor jaTemCliente = crud.buscarSeJaTemCliente(id_servidorCli);

                        try {
                            if (jaTemCliente.getCount() >= 1) {
                                //Log.d("Sincronismo", "NUCLEO JA EXISTE NO TABLET");
                                crud.atualizaCliente(id_servidorCli, clienteCli);

                            } else {

                                long resultado;
                                resultado = crud.inserirCliente(id_servidorCli, clienteCli);
                                //Log.d("Sincronismo", "NÃO ACHOU NUCLEO GRAVOU NO BD");
                            }
                        } finally {
                            if (jaTemCliente != null) {
                                jaTemCliente.close();
                            }
                        }
                        jaTemCliente.close();
                        //Log.d("JSON", "URL2: "+strURL2);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //FIM cliente


                //Setor

                String urlset = "http://"+servidor+"/os/wbs/envia_setores.php";
                //Log.d("JSON", "URL: "+url2);
                // Creating JSON Parser instance
                JSONParser jParserset = new JSONParser();

                // getting JSON string from URL
                JSONObject jsonSet = jParserset.getJSONFromUrl(urlset);

                try {
                    // Getting Array of dados
                    jSet = jsonSet.getJSONArray("dados");

                    // looping dados
                    for(int i = 0; i < jSet.length(); i++){
                        JSONObject cuSet = jSet.getJSONObject(i);

                        // Storing each json item in variable
                        String id_servidorSet = cuSet.getString("id");
                        String setorSet = cuSet.getString("setor");


                        Cursor jaTemSetor = crud.buscarSeJaTemSetor(id_servidorSet);

                        try {
                            if (jaTemSetor.getCount() >= 1) {
                                //Log.d("Sincronismo", "NUCLEO JA EXISTE NO TABLET");
                                crud.atualizaSetor(id_servidorSet, setorSet);

                            } else {

                                long resultado;
                                resultado = crud.inserirSetor(id_servidorSet, setorSet);
                                //Log.d("Sincronismo", "NÃO ACHOU NUCLEO GRAVOU NO BD");
                            }
                        } finally {
                            if (jaTemSetor != null) {
                                jaTemSetor.close();
                            }
                        }
                        jaTemSetor.close();
                        //Log.d("JSON", "URL2: "+strURL2);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //FIM setor


            }else{

                //Log.d("Sincronismo", "esta OFF line");
            }


        }
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    public void onDestroy(){
        super.onDestroy();

        for(int i = 0, tam = threads.size(); i < tam; i++){
            threads.get(i).ativo = false;
        }


    }


}
