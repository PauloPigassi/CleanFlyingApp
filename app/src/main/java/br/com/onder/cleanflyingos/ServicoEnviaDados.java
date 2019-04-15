package br.com.onder.cleanflyingos;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 13/02/18.
 */

public class ServicoEnviaDados extends Service {

    String id, id_servidor, fichanr, prefixo, modelo, categoria, data_inicio, data_termino, hora_inicio, hora_termino, cliente, setor, servicos, atendimento, manutencao, avulso;
    String asp, com, lav, las, lab, pol, met, car, est, qtu,bol, otr;
    String asp_obs, com_obs, lav_obs, las_obs, lab_obs, pol_obs, met_obs, car_obs, est_obs, qtu_obs,bol_obs, otr_obs;
    String porta_escada, pilot, para_brisas, para_sol, radone, trem_principal, bordos_ataque, descarregadores, flaps, motores, helices, farois, antenas, pintura, paineis;
    String porta_escada_obs, pilot_obs, para_brisas_obs, para_sol_obs, radone_obs, trem_principal_obs, bordos_ataque_obs, descarregadores_obs, flaps_obs, motores_obs, helices_obs, farois_obs, antenas_obs, pintura_obs, paineis_obs;
    String galley, mesas, bancos, janelas, carpetes, banheiro, clqtu, pertences, outro_descr, outro;
    String galley_obs, mesas_obs, bancos_obs, janelas_obs, carpetes_obs, banheiro_obs, clqtu_obs, pertences_obs, outro_descr_obs, outro_obs, obs_obs;
    String entrada_resp, entrada_atendente, saida_insp,saida_resp, okficha, entrada_resp_ass, entrada_atendente_ass, saida_insp_ass, saida_resp_ass, foto_antes, foto_depois;


    int serverResponseCode = 0;

    //public DataBaseManager manager;
    public List<Worker> threads = new ArrayList<Worker>();


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //manager = new DataBaseManager(this);
        //Log.i("Script", "onCreate()");
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

            BancoController crud = new BancoController(getBaseContext());

            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ServicoEnviaDados.this);
            final String servidor = prefs.getString("servidor", null);

            final String uploadFilePathIn = Environment.getExternalStorageDirectory() + "CF/Entrada/";
            final String uploadFilePathOut = Environment.getExternalStorageDirectory() + "CF/Saida/";
            //File imageFile = new File(Environment.getExternalStorageDirectory(),"CF/Entrada/"+nrFicha +".jpg");

            if (isOnline()) {

                okficha = "N";

                SQLiteDatabase db = openOrCreateDatabase("cf.db", Context.MODE_PRIVATE, null);

                //Cursor cursor = db.rawQuery("SELECT * FROM fichas", null);
                //int totalDB = cursor.getCount();
                //int totalReplicado = 0;

                Cursor curetorno = db.rawQuery("SELECT * FROM fichas WHERE enviado = ?", new String[]{"1"});

                try {
                    if (curetorno.getCount() >= 1) {

                        while (curetorno.moveToNext()) {

                            id = curetorno.getString(curetorno.getColumnIndex("_id"));
                            prefixo = curetorno.getString(curetorno.getColumnIndex("prefixo"));
                            fichanr = curetorno.getString(curetorno.getColumnIndex("fichanr"));
                            modelo = curetorno.getString(curetorno.getColumnIndex("modelo"));
                            categoria = curetorno.getString(curetorno.getColumnIndex("categoria"));
                            data_inicio = curetorno.getString(curetorno.getColumnIndex("data_inicio"));
                            data_termino = curetorno.getString(curetorno.getColumnIndex("data_termino"));
                            hora_inicio = curetorno.getString(curetorno.getColumnIndex("hora_inicio"));
                            hora_termino = curetorno.getString(curetorno.getColumnIndex("hora_termino"));
                            cliente = curetorno.getString(curetorno.getColumnIndex("cliente"));
                            setor = curetorno.getString(curetorno.getColumnIndex("setor"));
                            servicos = curetorno.getString(curetorno.getColumnIndex("servicos"));
                            atendimento = curetorno.getString(curetorno.getColumnIndex("atendimento"));
                            manutencao = curetorno.getString(curetorno.getColumnIndex("manutencao"));
                            avulso = curetorno.getString(curetorno.getColumnIndex("avulso"));
                            asp = curetorno.getString(curetorno.getColumnIndex("asp"));
                            com = curetorno.getString(curetorno.getColumnIndex("com"));
                            lav = curetorno.getString(curetorno.getColumnIndex("lav"));
                            las = curetorno.getString(curetorno.getColumnIndex("las"));
                            lab = curetorno.getString(curetorno.getColumnIndex("lab"));
                            pol = curetorno.getString(curetorno.getColumnIndex("pol"));
                            met = curetorno.getString(curetorno.getColumnIndex("met"));
                            car = curetorno.getString(curetorno.getColumnIndex("car"));
                            est = curetorno.getString(curetorno.getColumnIndex("est"));
                            qtu = curetorno.getString(curetorno.getColumnIndex("qtu"));
                            bol = curetorno.getString(curetorno.getColumnIndex("bol"));
                            otr = curetorno.getString(curetorno.getColumnIndex("otr"));

                            asp_obs = curetorno.getString(curetorno.getColumnIndex("asp_obs"));
                            com_obs = curetorno.getString(curetorno.getColumnIndex("com_obs"));
                            lav_obs = curetorno.getString(curetorno.getColumnIndex("lav_obs"));
                            las_obs = curetorno.getString(curetorno.getColumnIndex("las_obs"));
                            lab_obs = curetorno.getString(curetorno.getColumnIndex("lab_obs"));
                            pol_obs = curetorno.getString(curetorno.getColumnIndex("pol_obs"));
                            met_obs = curetorno.getString(curetorno.getColumnIndex("met_obs"));
                            car_obs = curetorno.getString(curetorno.getColumnIndex("car_obs"));
                            est_obs = curetorno.getString(curetorno.getColumnIndex("est_obs"));
                            qtu_obs = curetorno.getString(curetorno.getColumnIndex("qtu_obs"));
                            bol_obs = curetorno.getString(curetorno.getColumnIndex("bol_obs"));
                            otr_obs = curetorno.getString(curetorno.getColumnIndex("otr_obs"));

                            porta_escada = curetorno.getString(curetorno.getColumnIndex("porta_escada"));
                            pilot = curetorno.getString(curetorno.getColumnIndex("pilot"));
                            para_brisas = curetorno.getString(curetorno.getColumnIndex("para_brisas"));
                            para_sol = curetorno.getString(curetorno.getColumnIndex("para_sol"));
                            radone = curetorno.getString(curetorno.getColumnIndex("radone"));
                            trem_principal = curetorno.getString(curetorno.getColumnIndex("trem_principal"));
                            bordos_ataque = curetorno.getString(curetorno.getColumnIndex("bordos_ataque"));
                            descarregadores = curetorno.getString(curetorno.getColumnIndex("descarregadores"));
                            flaps = curetorno.getString(curetorno.getColumnIndex("flaps"));
                            motores = curetorno.getString(curetorno.getColumnIndex("motores"));
                            helices = curetorno.getString(curetorno.getColumnIndex("helices"));
                            farois = curetorno.getString(curetorno.getColumnIndex("farois"));
                            antenas = curetorno.getString(curetorno.getColumnIndex("antenas"));
                            pintura = curetorno.getString(curetorno.getColumnIndex("pintura"));
                            paineis = curetorno.getString(curetorno.getColumnIndex("paineis"));
                            galley = curetorno.getString(curetorno.getColumnIndex("galley"));
                            mesas = curetorno.getString(curetorno.getColumnIndex("mesas"));
                            bancos = curetorno.getString(curetorno.getColumnIndex("bancos"));
                            janelas = curetorno.getString(curetorno.getColumnIndex("janelas"));
                            carpetes = curetorno.getString(curetorno.getColumnIndex("carpetes"));
                            banheiro = curetorno.getString(curetorno.getColumnIndex("banheiro"));
                            clqtu = curetorno.getString(curetorno.getColumnIndex("clqtu"));
                            pertences = curetorno.getString(curetorno.getColumnIndex("pertences"));
                            outro_descr = curetorno.getString(curetorno.getColumnIndex("outro_descr"));
                            outro = curetorno.getString(curetorno.getColumnIndex("outro"));


                            porta_escada_obs = curetorno.getString(curetorno.getColumnIndex("porta_escada_obs"));
                            pilot_obs = curetorno.getString(curetorno.getColumnIndex("pilot_obs"));
                            para_brisas_obs = curetorno.getString(curetorno.getColumnIndex("para_brisas_obs"));
                            para_sol_obs = curetorno.getString(curetorno.getColumnIndex("para_sol_obs"));
                            radone_obs = curetorno.getString(curetorno.getColumnIndex("radone_obs"));
                            trem_principal_obs = curetorno.getString(curetorno.getColumnIndex("trem_principal_obs"));
                            bordos_ataque_obs = curetorno.getString(curetorno.getColumnIndex("bordos_ataque_obs"));
                            descarregadores_obs = curetorno.getString(curetorno.getColumnIndex("descarregadores_obs"));
                            flaps_obs = curetorno.getString(curetorno.getColumnIndex("flaps_obs"));
                            motores_obs = curetorno.getString(curetorno.getColumnIndex("motores_obs"));
                            helices_obs = curetorno.getString(curetorno.getColumnIndex("helices_obs"));
                            farois_obs = curetorno.getString(curetorno.getColumnIndex("farois_obs"));
                            antenas_obs = curetorno.getString(curetorno.getColumnIndex("antenas_obs"));
                            pintura_obs = curetorno.getString(curetorno.getColumnIndex("pintura_obs"));
                            paineis_obs = curetorno.getString(curetorno.getColumnIndex("paineis_obs"));
                            galley_obs = curetorno.getString(curetorno.getColumnIndex("galley_obs"));
                            mesas_obs = curetorno.getString(curetorno.getColumnIndex("mesas_obs"));
                            bancos_obs = curetorno.getString(curetorno.getColumnIndex("bancos_obs"));
                            janelas_obs = curetorno.getString(curetorno.getColumnIndex("janelas_obs"));
                            carpetes_obs = curetorno.getString(curetorno.getColumnIndex("carpetes_obs"));
                            banheiro_obs = curetorno.getString(curetorno.getColumnIndex("banheiro_obs"));
                            clqtu_obs = curetorno.getString(curetorno.getColumnIndex("clqtu_obs"));
                            pertences_obs = curetorno.getString(curetorno.getColumnIndex("pertences_obs"));
                            //outro_descr = curetorno.getString(curetorno.getColumnIndex("outro_descr"));
                            //outro = curetorno.getString(curetorno.getColumnIndex("outro_obs"));

                            obs_obs = curetorno.getString(curetorno.getColumnIndex("obs_obs"));
                            //id_servidor = curetorno.getString(curetorno.getColumnIndex("id_servidor"));

                            entrada_resp = curetorno.getString(curetorno.getColumnIndex("entrada_resp"));
                            entrada_resp_ass = curetorno.getString(curetorno.getColumnIndex("entrada_resp_ass"));
                            entrada_atendente = curetorno.getString(curetorno.getColumnIndex("entrada_atendente"));
                            entrada_atendente_ass = curetorno.getString(curetorno.getColumnIndex("entrada_atendente_ass"));
                            saida_insp = curetorno.getString(curetorno.getColumnIndex("saida_insp"));
                            saida_insp_ass = curetorno.getString(curetorno.getColumnIndex("saida_insp_ass"));
                            saida_resp = curetorno.getString(curetorno.getColumnIndex("saida_resp"));
                            saida_resp_ass = curetorno.getString(curetorno.getColumnIndex("saida_resp_ass"));
                            foto_antes = curetorno.getString(curetorno.getColumnIndex("foto_antes"));
                            foto_depois = curetorno.getString(curetorno.getColumnIndex("foto_depois"));



                            StringBuilder strURL = new StringBuilder();
                            strURL.append("http://"+servidor+"/os/wbs/nova_ficha.php?fichanr=");
                            strURL.append(fichanr);

                            //Log.d("URL ENVIA", strURL.toString());

                            try {

                                URL url = new URL(strURL.toString());
                                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                url = uri.toURL();

                                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                                BufferedReader line = new BufferedReader(ips);


                                String linhaRetorno = line.readLine();
                                http.disconnect();

                                //Log.d("Exportar","retorno:"+linhaRetorno);

                                if(linhaRetorno.equals("Y")){


                                okficha = "S";





                                    //fim

                                }

                            } catch (Exception ex) {
                                //Log.d("EnviaDados", ex.getMessage());

                            }


                            if(okficha.equals("S")) {
                                //Faz a atualização das demais

                                StringBuilder strURLcab = new StringBuilder();
                                strURLcab.append("http://" + servidor + "/os/wbs/cab_ficha.php?fichanr=");
                                strURLcab.append(fichanr);
                                strURLcab.append("&modelo=");
                                strURLcab.append(modelo);
                                strURLcab.append("&prefixo=");
                                strURLcab.append(prefixo);
                                strURLcab.append("&categoria=");
                                strURLcab.append(categoria);
                                strURLcab.append("&data_inicio=");
                                strURLcab.append(data_inicio);
                                strURLcab.append("&data_termino=");
                                strURLcab.append(data_termino);
                                strURLcab.append("&hora_inicio=");
                                strURLcab.append(hora_inicio);
                                strURLcab.append("&hora_termino=");
                                strURLcab.append(hora_termino);
                                strURLcab.append("&cliente=");
                                strURLcab.append(cliente);
                                strURLcab.append("&setor=");
                                strURLcab.append(setor);
                                strURLcab.append("&servicos=");
                                strURLcab.append(servicos);
                                strURLcab.append("&manutencao=");
                                strURLcab.append(manutencao);
                                strURLcab.append("&atendimento=");
                                strURLcab.append(atendimento);
                                strURLcab.append("&avulso=");
                                strURLcab.append(avulso);

                               // Log.d("URL ENVIA CAB", strURLcab.toString());

                                try {

                                    URL url = new URL(strURLcab.toString());
                                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                    url = uri.toURL();

                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                    InputStreamReader ips = new InputStreamReader(http.getInputStream());
                                    BufferedReader line = new BufferedReader(ips);


                                    String linhaRetorno = line.readLine();
                                    http.disconnect();

                                    //Log.d("Exportar","retorno:"+linhaRetorno);

                                    if(linhaRetorno.equals("Y")){


                                        //okficha = "S";


                                        //fim

                                    }

                                } catch (Exception ex) {

                                    //Log.d("EnviaDados", ex.getMessage());

                                }

                                //servicos
                                StringBuilder strURLserv = new StringBuilder();
                                strURLserv.append("http://" + servidor + "/os/wbs/serv_ficha.php?fichanr=");
                                strURLserv.append(fichanr);
                                strURLserv.append("&asp=");
                                strURLserv.append(asp);
                                strURLserv.append("&com=");
                                strURLserv.append(com);
                                strURLserv.append("&lav=");
                                strURLserv.append(lav);
                                strURLserv.append("&las=");
                                strURLserv.append(las);
                                strURLserv.append("&lab=");
                                strURLserv.append(lab);
                                strURLserv.append("&pol=");
                                strURLserv.append(pol);
                                strURLserv.append("&met=");
                                strURLserv.append(met);
                                strURLserv.append("&car=");
                                strURLserv.append(car);
                                strURLserv.append("&est=");
                                strURLserv.append(est);
                                strURLserv.append("&qtu=");
                                strURLserv.append(qtu);
                                strURLserv.append("&bol=");
                                strURLserv.append(bol);
                                strURLserv.append("&otr=");
                                strURLserv.append(otr);
                                strURLserv.append("&asp_obs=");
                                strURLserv.append(asp_obs);
                                strURLserv.append("&com_obs=");
                                strURLserv.append(com_obs);
                                strURLserv.append("&lav_obs=");
                                strURLserv.append(lav_obs);
                                strURLserv.append("&las_obs=");
                                strURLserv.append(las_obs);
                                strURLserv.append("&lab_obs=");
                                strURLserv.append(lab_obs);
                                strURLserv.append("&pol_obs=");
                                strURLserv.append(pol_obs);
                                strURLserv.append("&met_obs=");
                                strURLserv.append(met_obs);
                                strURLserv.append("&car_obs=");
                                strURLserv.append(car_obs);
                                strURLserv.append("&est_obs=");
                                strURLserv.append(est_obs);
                                strURLserv.append("&qtu_obs=");
                                strURLserv.append(qtu_obs);
                                strURLserv.append("&bol_obs=");
                                strURLserv.append(bol_obs);
                                strURLserv.append("&otr_obs=");
                                strURLserv.append(otr_obs);

                                //Log.d("URL ENVIA SERV ", strURLserv.toString());
                                try {

                                    URL url = new URL(strURLserv.toString());
                                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                    url = uri.toURL();

                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                    InputStreamReader ips = new InputStreamReader(http.getInputStream());
                                    BufferedReader line = new BufferedReader(ips);


                                    String linhaRetorno = line.readLine();
                                    http.disconnect();

                                    //Log.d("Exportar","retorno:"+linhaRetorno);

                                    if(linhaRetorno.equals("Y")){


                                        //okficha = "S";


                                        //fim

                                    }

                                } catch (Exception ex) {

                                    //Log.d("EnviaDados", ex.getMessage());

                                }


                                //checklist um
                                StringBuilder strURLckum = new StringBuilder();
                                strURLckum.append("http://" + servidor + "/os/wbs/ckum_ficha.php?fichanr=");
                                strURLckum.append(fichanr);
                                strURLckum.append("&porta_escada=");
                                strURLckum.append(porta_escada);
                                strURLckum.append("&pilot=");
                                strURLckum.append(pilot);
                                strURLckum.append("&para_brisas=");
                                strURLckum.append(para_brisas);
                                strURLckum.append("&para_sol=");
                                strURLckum.append(para_sol);
                                strURLckum.append("&radone=");
                                strURLckum.append(radone);
                                strURLckum.append("&trem_principal=");
                                strURLckum.append(trem_principal);
                                strURLckum.append("&bordos_ataque=");
                                strURLckum.append(bordos_ataque);
                                strURLckum.append("&descarregadores=");
                                strURLckum.append(descarregadores);
                                strURLckum.append("&flaps=");
                                strURLckum.append(flaps);
                                strURLckum.append("&motores=");
                                strURLckum.append(motores);
                                strURLckum.append("&helices=");
                                strURLckum.append(helices);
                                strURLckum.append("&farois=");
                                strURLckum.append(farois);

                                strURLckum.append("&porta_escada_obs=");
                                strURLckum.append(porta_escada_obs);
                                strURLckum.append("&pilot_obs=");
                                strURLckum.append(pilot_obs);
                                strURLckum.append("&para_brisas_obs=");
                                strURLckum.append(para_brisas_obs);
                                strURLckum.append("&para_sol_obs=");
                                strURLckum.append(para_sol_obs);
                                strURLckum.append("&radone_obs=");
                                strURLckum.append(radone_obs);
                                strURLckum.append("&trem_principal_obs=");
                                strURLckum.append(trem_principal_obs);
                                strURLckum.append("&bordos_ataque_obs=");
                                strURLckum.append(bordos_ataque_obs);
                                strURLckum.append("&descarregadores_obs=");
                                strURLckum.append(descarregadores_obs);
                                strURLckum.append("&flaps_obs=");
                                strURLckum.append(flaps_obs);
                                strURLckum.append("&motores_obs=");
                                strURLckum.append(motores_obs);
                                strURLckum.append("&helices_obs=");
                                strURLckum.append(helices_obs);
                                strURLckum.append("&farois_obs=");
                                strURLckum.append(farois_obs);

                                //Log.d("URL ENVIA ckum", strURLckum.toString());

                                try {

                                    URL url = new URL(strURLckum.toString());
                                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                    url = uri.toURL();

                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                    InputStreamReader ips = new InputStreamReader(http.getInputStream());
                                    BufferedReader line = new BufferedReader(ips);


                                    String linhaRetorno = line.readLine();
                                    http.disconnect();

                                   // Log.d("Exportar","retorno:"+linhaRetorno);

                                    if(linhaRetorno.equals("Y")){


                                        //okficha = "S";


                                        //fim

                                    }

                                } catch (Exception ex) {

                                    //Log.d("EnviaDados", ex.getMessage());

                                }


                                //checklist dois
                                StringBuilder strURLckdois = new StringBuilder();

                                strURLckdois.append("http://" + servidor + "/os/wbs/ckdois_ficha.php?fichanr=");
                                strURLckdois.append(fichanr);
                                strURLckdois.append("&antenas=");
                                strURLckdois.append(antenas);
                                strURLckdois.append("&pintura=");
                                strURLckdois.append(pintura);
                                strURLckdois.append("&paineis=");
                                strURLckdois.append(paineis);
                                strURLckdois.append("&galley=");
                                strURLckdois.append(galley);
                                strURLckdois.append("&mesas=");
                                strURLckdois.append(mesas);
                                strURLckdois.append("&bancos=");
                                strURLckdois.append(bancos);
                                strURLckdois.append("&janelas=");
                                strURLckdois.append(janelas);
                                strURLckdois.append("&carpetes=");
                                strURLckdois.append(carpetes);
                                strURLckdois.append("&banheiro=");
                                strURLckdois.append(banheiro);
                                strURLckdois.append("&clqtu=");
                                strURLckdois.append(clqtu);
                                strURLckdois.append("&pertences=");
                                strURLckdois.append(pertences);
                                strURLckdois.append("&outro_descr=");
                                strURLckdois.append(outro_descr);
                                strURLckdois.append("&antenas_obs=");
                                strURLckdois.append(antenas_obs);
                                strURLckdois.append("&pintura_obs=");
                                strURLckdois.append(pintura_obs);
                                strURLckdois.append("&paineis_obs=");
                                strURLckdois.append(paineis_obs);
                                strURLckdois.append("&galley_obs=");
                                strURLckdois.append(galley_obs);
                                strURLckdois.append("&mesas_obs=");
                                strURLckdois.append(mesas_obs);
                                strURLckdois.append("&bancos_obs=");
                                strURLckdois.append(bancos_obs);
                                strURLckdois.append("&janelas_obs=");
                                strURLckdois.append(janelas_obs);
                                strURLckdois.append("&carpetes_obs=");
                                strURLckdois.append(carpetes_obs);
                                strURLckdois.append("&banheiro_obs=");
                                strURLckdois.append(banheiro_obs);
                                strURLckdois.append("&clqtu_obs=");
                                strURLckdois.append(clqtu_obs);
                                strURLckdois.append("&pertences_obs=");
                                strURLckdois.append(pertences_obs);
                                strURLckdois.append("&outro_descr=");
                                strURLckdois.append(outro_descr);
                                strURLckdois.append("&outro=");
                                strURLckdois.append(outro);

                                Log.d("URL ENVIA ckdois ", strURLckdois.toString());

                                try {

                                    URL url = new URL(strURLckdois.toString());
                                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                    url = uri.toURL();

                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                    InputStreamReader ips = new InputStreamReader(http.getInputStream());
                                    BufferedReader line = new BufferedReader(ips);


                                    String linhaRetorno = line.readLine();
                                    http.disconnect();

                                    //Log.d("Exportar","retorno:"+linhaRetorno);

                                    if(linhaRetorno.equals("Y")){


                                        //okficha = "S";


                                        //fim

                                    }

                                } catch (Exception ex) {

                                    //Log.d("EnviaDados", ex.getMessage());

                                }


                                //rodape
                                StringBuilder strURLrod = new StringBuilder();
                                strURLrod.append("http://" + servidor + "/os/wbs/rod_ficha.php?fichanr=");
                                strURLrod.append(fichanr);
                                strURLrod.append("&obs_obs=");
                                strURLrod.append(obs_obs);
                                strURLrod.append("&entrada_resp=");
                                strURLrod.append(entrada_resp);
                                strURLrod.append("&entrada_atendente=");
                                strURLrod.append(entrada_atendente);
                                strURLrod.append("&saida_insp=");
                                strURLrod.append(saida_insp);
                                strURLrod.append("&saida_resp=");
                                strURLrod.append(saida_resp);

                               // Log.d("URL ENVIA ROD", strURLrod.toString());

                                try {

                                    URL url = new URL(strURLrod.toString());
                                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                    url = uri.toURL();

                                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                    InputStreamReader ips = new InputStreamReader(http.getInputStream());
                                    BufferedReader line = new BufferedReader(ips);


                                    String linhaRetorno = line.readLine();
                                    http.disconnect();

                                    //Log.d("Exportar","retorno:"+linhaRetorno);

                                    if(linhaRetorno.equals("Y")){


                                        //okficha = "S";


                                        //fim

                                    }

                                } catch (Exception ex) {

                                    //Log.d("EnviaDados", ex.getMessage());

                                }


                                /*
                                    String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+nrFicha+".jpg";
                                    File imageFile = new File(imageFilePath);

                                   ;
                                   // Uri imageFileUri = Uri.fromFile(imageFile); // convert path to Uri

                                    Uri imageFileUri = FileProvider.getUriForFile(
                                            CkUmActivity.this,
                                            "com.example.myapp.fileprovider",
                                            imageFile);


                                 */





                                //Envio foto entrada
                                File imageFileIn = new File(Environment.getExternalStorageDirectory(),"/entrada_"+fichanr +".jpg");

                                if(imageFileIn.exists()){


                                    //final String pastafotoIn = Environment.getExternalStorageDirectory().getAbsolutePath() + "CF/Entrada/";
                                    final String pastafotoIn = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
                                    final String arquivofotoIn = "entrada_" +fichanr + ".jpg";
                                    final String upfotoIn = pastafotoIn + arquivofotoIn;
                                    String usiteIn = "http://" + servidor + "/os/fotos/uploadfotoentrada.php";
                                   // Log.d("Exportar","url entrada:"+usiteIn);
                                   // Log.d("Exportar","foto:"+upfotoIn);

                                    int resposta_entrada = uploadFile(upfotoIn, usiteIn);

                                   // Log.d("Exportar","Envio foto entrafa");

                                }else{

                                   // Log.d("Exportar","Envio foto entrada nao existye");

                                }

                                //Envio foto Saida
                                File imageFileOut = new File(Environment.getExternalStorageDirectory(),"/saida_"+fichanr +".jpg");

                                if(imageFileOut.exists()){


                                    final String pastafotoOut = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
                                    final String arquivofotoOut = "saida_" +fichanr + ".jpg";
                                    final String upfotoout = pastafotoOut + arquivofotoOut;
                                    String usiteout = "http://" + servidor + "/os/fotos/uploadfotosaida.php";
                                    int resposta_saida = uploadFile(upfotoout, usiteout);

                                    //Log.d("Exportar","Envio foto saida");

                                }


                                //Envio Ass Um
                                String uploadFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CF/Assinatura/";
                                String uploadFileName = fichanr + "um.png";
                                String filename = uploadFilePath + uploadFileName;



                               // File imageFileAssUm = new File(Environment.getExternalStorageDirectory(),"/CF/Assinatura/"+fichanr +"um.jpg");
                                File imageFileAssUm = new File(filename);

                                if(imageFileAssUm.exists()){


                                    final String pastaAssUm = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CF/Assinatura/";
                                    final String arquivoAssUm = fichanr + "um.png";
                                    final String upAssUm = pastaAssUm + arquivoAssUm;
                                    String usiteout = "http://" + servidor + "/os/fotos/uploadassinatura.php";
                                    int respostaAssUm = uploadFile(upAssUm, usiteout);

                                    //Log.d("Exportar","Envio ass um "+respostaAssUm);

                                }else{

                                   // Log.d("Exportar","Envio ass um, não exesita ");
                                }


                                //Envio Ass Dois
                                File imageFileAssDois = new File(Environment.getExternalStorageDirectory(),"/CF/Assinatura/"+fichanr +"dois.png");

                                if(imageFileAssDois.exists()){


                                    final String pastaAssDois = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CF/Assinatura/";
                                    final String arquivoAssDois = fichanr + "dois.png";
                                    final String upAssDois = pastaAssDois + arquivoAssDois;
                                    String usiteout = "http://" + servidor + "/os/fotos/uploadassinatura.php";
                                    int respostaAssDois = uploadFile(upAssDois, usiteout);

                                    //Log.d("Exportar","Envioass dois "+respostaAssDois);

                                }


                                //Envio Ass Tres
                                File imageFileAssTres = new File(Environment.getExternalStorageDirectory(),"/CF/Assinatura/"+fichanr +"tres.png");

                                if(imageFileAssTres.exists()){


                                    final String pastaAssTres = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CF/Assinatura/";
                                    final String arquivoAssTres = fichanr + "tres.png";
                                    final String upAssTres = pastaAssTres + arquivoAssTres;
                                    String usiteout = "http://" + servidor + "/os/fotos/uploadassinatura.php";
                                    int respostaAssTres = uploadFile(upAssTres, usiteout);

                                    //Log.d("Exportar","Envio Ass Tres "+respostaAssTres);

                                }


                                //Envio Ass Quatro
                                File imageFileAssQuatro = new File(Environment.getExternalStorageDirectory(),"/CF/Assinatura/"+fichanr +"quatro.png");

                                if(imageFileAssQuatro.exists()){


                                    final String pastaAssQuatro = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CF/Assinatura/";
                                    final String arquivoAssQuatro = fichanr + "quatro.png";
                                    final String upAssQuatro = pastaAssQuatro + arquivoAssQuatro;
                                    String usiteout = "http://" + servidor + "/os/fotos/uploadassinatura.php";
                                    int respostaAssQuatro = uploadFile(upAssQuatro, usiteout);

                                    //Log.d("Exportar","Envio Ass Quatro "+respostaAssQuatro);

                                }



                            }//fim nr ficha enviada


                        }//fim while cursor

                    }

                    crud.apagaFicha(id);

                } finally {
                    if (curetorno != null) {
                        curetorno.close();
                    }
                }


            }





        }

        private int uploadFile(String sourceFileUri, String wbs) {

            String fileName = sourceFileUri;
            String upLoadServerUri = wbs;

            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            File sourceFile = new File(sourceFileUri);

            if (!sourceFile.isFile()) {


               // Log.d("UPLOAD", "ARQUIVO NÂO EXISTE");

                return 0;

            } else {
                try {

                    // open a URL connection to the Servlet
                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(upLoadServerUri);

                    // Open a HTTP  connection to  the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("uploaded_file", fileName);

                    dos = new DataOutputStream(conn.getOutputStream());

                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=" + fileName + "" + lineEnd);

                    dos.writeBytes(lineEnd);

                    // create a buffer of  maximum size
                    bytesAvailable = fileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // read file and write it into form...
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {

                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    }

                    // send multipart form data necesssary after file data...
                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Responses from the server (code and message)
                    serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();

                   // Log.i("uploadFile", "HTTP Response is : "
                    //        + serverResponseMessage + ": " + serverResponseCode);

                    if (serverResponseCode == 200) {
                        //Log.d("UPLOAD", "servidor respondeu ok");


                    }

                    //close the streams //
                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                } catch (MalformedURLException ex) {


                    ex.printStackTrace();
                    //Log.d("UPLOAD", "erro url");

                    //Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
                } catch (Exception e) {


                    e.printStackTrace();
                   // Log.d("UPLOAD", "erro servidor");

                  //  Log.e("Upload fieption", "Exception : " + e.getMessage(), e);
                }

                return serverResponseCode;

            } // End else block
        }

        public boolean isOnline() {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }


        public void onDestroy() {
            ServicoEnviaDados.super.onDestroy();

            for (int i = 0, tam = threads.size(); i < tam; i++) {
                threads.get(i).ativo = false;
            }

            //if (manager != null) {
            //    manager.close();
            //}

        }


    }
}

