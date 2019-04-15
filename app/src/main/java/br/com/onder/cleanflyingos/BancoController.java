package br.com.onder.cleanflyingos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static br.com.onder.cleanflyingos.CriaBanco.*;

/**
 * Created by marco on 13/02/18.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }


    //Splash
    public void apagalogin(){
        db = banco.getReadableDatabase();
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }

    public Cursor buscartodoslogin(){
        Cursor cursor;

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_LOGIN,null,null,null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }


    //Cadastro Senha


    public long inserirlogin(String usuario, String data){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CN_USUARIO, usuario);
        valores.put(CN_DATA, data);

        resultado = db.insert(TABLE_LOGIN, null, valores);
        db.close();

        return resultado;

    }


    public List<ClasseFichas> getDataFromDB(){

        List<ClasseFichas> modelList = new ArrayList<ClasseFichas>();
        String query = "select * from "+TABLE_FICHAS;

        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                ClasseFichas model = new ClasseFichas();
                model.setNrFicha_c(cursor.getString(1));
                model.setPrefixo_c(cursor.getString(2));
                model.setModelo_categoria_c(cursor.getString(3));
                model.setDataInicio_c(cursor.getString(4));
                model.setDataTermino_c(cursor.getString(5));
                model.setHoraInicio_c(cursor.getString(6));
                model.setHoraTermino_c(cursor.getString(7));
                model.setModelo_categoria_c(cursor.getString(3));
                model.setCliente_c(cursor.getString(8));
                model.setSetor_c(cursor.getString(9));
                model.setAtendimento_c(cursor.getString(11));
                model.setManutencao_c(cursor.getString(12));
                model.setAvulso_c(cursor.getString(13));
                model.setAsp_c(cursor.getString(14));
                model.setCom_c(cursor.getString(15));
                model.setLav_c(cursor.getString(16));
                model.setLas_c(cursor.getString(17));
                model.setLab_c(cursor.getString(18));
                model.setPol_c(cursor.getString(19));
                model.setMet_c(cursor.getString(20));
                model.setCar_c(cursor.getString(21));
                model.setEst_c(cursor.getString(22));
                model.setQtu_c(cursor.getString(23));
                model.setBol_c(cursor.getString(24));
                model.setOtr_c(cursor.getString(25));
                model.setAsp_obs_c(cursor.getString(26));
                model.setCom_obs_c(cursor.getString(27));
                model.setLav_obs_c(cursor.getString(28));
                model.setLas_obs_c(cursor.getString(29));
                model.setLab_obs_c(cursor.getString(30));
                model.setPol_obs_c(cursor.getString(31));
                model.setMet_obs_c(cursor.getString(32));
                model.setCar_obs_c(cursor.getString(33));
                model.setEst_obs_c(cursor.getString(34));
                model.setQtu_obs_c(cursor.getString(35));
                model.setBol_obs_c(cursor.getString(36));
                model.setOtr_obs_c(cursor.getString(37));
                model.setObs_obs_c(cursor.getString(97));
                model.setStatus_c(cursor.getString(98));
                modelList.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();

        db.close();
        return modelList;
    }



    //Cadastro Aeronave
    public long inserirAeronaveFicha(String nrFicha, String prefixo, String modelo, String categoria, String cliente, String setor){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CN_FICHANR, nrFicha);
        valores.put(CN_PREFIXO, prefixo);
        valores.put(CN_MODELO, modelo);
        valores.put(CN_CATEGORIA, categoria);
        valores.put(CN_MODELO_CATEGORIA, modelo);
        valores.put(CN_CLIENTE, cliente);
        valores.put(CN_SETOR,setor);

        resultado = db.insert(TABLE_FICHAS, null, valores);
        db.close();

        return resultado;

    }


    //Cadastro Serviço

    public void atualizaServicoCk(String fichaNr, String s_ck_serv_1, String s_ck_serv_2, String s_ck_serv_3, String s_ck_serv_4, String s_ck_serv_5, String s_ck_serv_6, String s_ck_serv_7, String s_ck_serv_8, String s_ck_serv_9, String s_ck_serv_10, String s_ck_serv_11, String s_ck_serv_12){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_ASP, s_ck_serv_1);
        valores.put(CN_COM, s_ck_serv_2);
        valores.put(CN_LAV, s_ck_serv_3);
        valores.put(CN_LAS, s_ck_serv_4);
        valores.put(CN_LAB, s_ck_serv_5);
        valores.put(CN_POL, s_ck_serv_6);
        valores.put(CN_MET, s_ck_serv_7);
        valores.put(CN_CAR, s_ck_serv_8);
        valores.put(CN_EST, s_ck_serv_9);
        valores.put(CN_QTU, s_ck_serv_10);
        valores.put(CN_BOL, s_ck_serv_11);
        valores.put(CN_OTR, s_ck_serv_12);

        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();
    }


    public void atualizaTipoServicoCk(String fichaNr, String ck_atendimento, String ck_manutencao, String ck_avulso){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_ATENDIMENTO, ck_atendimento);
        valores.put(CN_MANUTENCAO, ck_manutencao);
        valores.put(CN_AVULSO, ck_avulso);


        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();
    }


    public void atualizaServicosObs(String fichaNr, String obs1, String obs2, String obs3, String obs4, String obs5, String obs6, String obs7, String obs8, String obs9, String obs10, String obs11, String obs12){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_ASP_OBS, obs1);
        valores.put(CN_COM_OBS, obs2);
        valores.put(CN_LAV_OBS, obs3);
        valores.put(CN_LAS_OBS, obs4);
        valores.put(CN_LAB_OBS, obs5);
        valores.put(CN_POL_OBS, obs6);
        valores.put(CN_MET_OBS, obs7);
        valores.put(CN_CAR_OBS, obs8);
        valores.put(CN_EST_OBS, obs9);
        valores.put(CN_QTU_OBS, obs10);
        valores.put(CN_BOL_OBS, obs11);
        valores.put(CN_OTR_OBS, obs12);


        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();
    }

    public void atualizaDados(String fichaNr, String um, String dois, String tres, String quatro, String cinco, String seis, String sete, String oito, String nove, String dez, String onze, String doze){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_PORTA_ESCADA, um);
        valores.put(CN_TUBOS_PILOT, dois);
        valores.put(CN_PARA_BRISAS, tres);
        valores.put(CN_PARASOL, quatro);
        valores.put(CN_RADONE, cinco);
        valores.put(CN_TREM_PRINCIPAL, seis);
        valores.put(CN_BORDOS_ATAQUE, sete);
        valores.put(CN_DESCARREGADORES, oito);
        valores.put(CN_FLAPS, nove);
        valores.put(CN_MOTORES , dez);
        valores.put(CN_HELICES, onze);
        valores.put(CN_FAROIS, doze);


        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();


    }

    public void atualizaDadosDois(String fichaNr, String um, String dois, String tres, String quatro, String cinco, String seis, String sete, String oito, String nove, String dez, String onze, String doze){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_ANTENAS, um);
        valores.put(CN_PINTURA, dois);
        valores.put(CN_PAINEIS, tres);
        valores.put(CN_GALLEY, quatro);
        valores.put(CN_MESAS, cinco);
        valores.put(CN_BANCOS, seis);
        valores.put(CN_JANELAS, sete);
        valores.put(CN_CARPETES, oito);
        valores.put(CN_BANHEIRO, nove);
        valores.put(CN_CLQTU , dez);
        valores.put(CN_PERTENCES, onze);
        valores.put(CN_OUTRO, doze);


        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();


    }

    public void atualizaFotoInicio(String fichaNr, String dataInicio, String horaInicio){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_DATA_INICIO, dataInicio);
        valores.put(CN_HORA_INICIO, horaInicio);



        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();


    }

    public void atualizaFotoFinal(String fichaNr, String dataTermino, String horaTermino){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_DATA_TERMINO, dataTermino);
        valores.put(CN_HORA_TERMINO, horaTermino);



        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();


    }


    public void atualizaObs(String fichaNr, String campo, String obs){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(campo, obs);

        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();


    }

    public Cursor buscarObs(String nrFicha, String campo){
        Cursor cursor;
        String[] campos =  {campo};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_FICHAS,campos,CN_FICHANR + "=?", new String[]{nrFicha}, null, null, null);

        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }


    public Cursor buscarTodasObs(String nrForm){
        Cursor cursor;
        String[] campos =  {CN_PORTA_ESCADA_OBS, CN_TUBOS_PILOT_OBS, CN_PARA_BRISAS_OBS, CN_PARASOL_OBS, CN_RADONE_OBS, CN_TREM_PRINCIPAL_OBS, CN_BORDOS_ATAQUE_OBS, CN_DESCARREGADORES_OBS, CN_FLAPS_OBS, CN_MOTORES_OBS, CN_HELICES_OBS, CN_FAROIS_OBS, CN_ANTENAS_OBS, CN_PINTURA_OBS, CN_PAINEIS_OBS, CN_GALLEY_OBS, CN_MESAS_OBS, CN_BANCOS_OBS, CN_JANEJAS_OBS, CN_CARPETES_OBS, CN_BANHEIRO_OBS, CN_CLQTU_OBS, CN_PERTENCES_OBS, CN_OUTRO_OBS};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_FICHAS,campos,CN_FICHANR + "=?", new String[]{nrForm}, null, null, null);

        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }

    public Cursor buscarValoresCklUm(String nrForm){
        Cursor cursor;
        String[] campos =  {CN_PORTA_ESCADA, CN_TUBOS_PILOT, CN_PARA_BRISAS, CN_PARASOL, CN_RADONE, CN_TREM_PRINCIPAL, CN_BORDOS_ATAQUE, CN_DESCARREGADORES, CN_FLAPS, CN_MOTORES, CN_HELICES, CN_FAROIS, CN_ANTENAS, CN_PINTURA, CN_PAINEIS, CN_GALLEY, CN_MESAS, CN_BANCOS, CN_JANELAS, CN_CARPETES, CN_BANHEIRO, CN_CLQTU, CN_PERTENCES, CN_OUTRO};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_FICHAS,campos,CN_FICHANR + "=?", new String[]{nrForm}, null, null, null);

        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }


    public Cursor buscarValoresCklDois(String nrForm){
        Cursor cursor;
        String[] campos =  {CN_ANTENAS, CN_PINTURA, CN_PAINEIS, CN_GALLEY, CN_MESAS, CN_BANCOS, CN_JANELAS, CN_CARPETES, CN_BANHEIRO, CN_CLQTU, CN_PERTENCES, CN_OUTRO, CN_OUTRO_DESCR};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_FICHAS,campos,CN_FICHANR + "=?", new String[]{nrForm}, null, null, null);

        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }


    public void atualizaObsObs(String fichaNr, String obs){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_OBS_OBS, obs);


        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();


    }


    public Cursor buscarObsObs(String nrForm){
        Cursor cursor;
        String[] campos =  {CN_OBS_OBS};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_FICHAS,campos,CN_FICHANR + "=?", new String[]{nrForm}, null, null, null);

        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }


    public void atualizaResp(String fichaNr, String ent_resp, String ent_atend, String saida_insp, String saida_resp){
        ContentValues valores;
        //String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_ENTRADA_RESP, ent_resp);
        valores.put(CN_ENTRADA_ATENDENTE, ent_atend);
        valores.put(CN_SAIDA_INSP, saida_insp);
        valores.put(CN_SAIDA_RESP, saida_resp);


        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();
    }

    public Cursor buscarResp(String nrForm){
        Cursor cursor;
        String[] campos =  {CN_ENTRADA_RESP, CN_ENTRADA_ATENDENTE, CN_SAIDA_INSP, CN_SAIDA_RESP};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_FICHAS,campos,CN_FICHANR + "=?", new String[]{nrForm}, null, null, null);

        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }


    public Cursor buscarSeJaTemAeronave(String id_servidor){
        Cursor cursor;
        String[] campos =  {CN_ID_SERVIDOR};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_AERONAVES,campos,CN_ID_SERVIDOR + "=?", new String[]{id_servidor}, null, null, null);


        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }

    public long inserirAeronaveAero(String id_servidor, String prefixo, String modelo, String categoria, String setor, String cliente){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CN_ID_SERVIDOR, id_servidor);
        valores.put(CN_PREFIXO, prefixo);
        valores.put(CN_MODELO, modelo);
        valores.put(CN_CATEGORIA, categoria);
        valores.put(CN_SETOR, setor);
        valores.put(CN_CLIENTE, cliente);

        resultado = db.insert(TABLE_AERONAVES, null, valores);
        db.close();

        return resultado;

    }

    public void atualizaAeronave(String id_servidor, String prefixo, String modelo, String categoria, String setor, String cliente){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CN_PREFIXO, prefixo);
        valores.put(CN_MODELO, modelo);
        valores.put(CN_CATEGORIA, categoria);
        valores.put(CN_SETOR, setor);
        valores.put(CN_CLIENTE, cliente);

        db.update(TABLE_AERONAVES,valores,CN_ID_SERVIDOR + "=?",new String[]{id_servidor});
        db.close();
    }


    public Cursor buscarSeJaTemModelo(String id_servidor){
        Cursor cursor;
        String[] campos =  {CN_ID_SERVIDOR};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_MODELOS,campos,CN_ID_SERVIDOR + "=?", new String[]{id_servidor}, null, null, null);


        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }

    public long inserirModelo(String id_servidor, String modelo){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CN_ID_SERVIDOR, id_servidor);
        valores.put(CN_MODELO, modelo);


        resultado = db.insert(TABLE_MODELOS, null, valores);
        db.close();

        return resultado;

    }

    public void atualizaModelo(String id_servidor, String modelo){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_MODELO, modelo);

        db.update(TABLE_MODELOS,valores,CN_ID_SERVIDOR + "=?",new String[]{id_servidor});
        db.close();
    }



    /////


    public Cursor buscarSeJaTemCategoria(String id_servidor){
        Cursor cursor;
        String[] campos =  {CN_ID_SERVIDOR};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_CATEGORIAS,campos,CN_ID_SERVIDOR + "=?", new String[]{id_servidor}, null, null, null);


        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }

    public long inserirCategoria(String id_servidor, String categoria){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CN_ID_SERVIDOR, id_servidor);
        valores.put(CN_CATEGORIA, categoria);


        resultado = db.insert(TABLE_CATEGORIAS, null, valores);
        db.close();

        return resultado;

    }

    public void atualizaCategoria(String id_servidor, String categoria){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_CATEGORIA, categoria);

        db.update(TABLE_CATEGORIAS,valores,CN_ID_SERVIDOR + "=?",new String[]{id_servidor});
        db.close();
    }

    ///////////


    public Cursor buscarSeJaTemCliente(String id_servidor){
        Cursor cursor;
        String[] campos =  {CN_ID_SERVIDOR};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_CLIENTES,campos,CN_ID_SERVIDOR + "=?", new String[]{id_servidor}, null, null, null);


        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }

    public long inserirCliente(String id_servidor, String cliente){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CN_ID_SERVIDOR, id_servidor);
        valores.put(CN_CLIENTE, cliente);


        resultado = db.insert(TABLE_CLIENTES, null, valores);
        db.close();

        return resultado;

    }

    public void atualizaCliente(String id_servidor, String cliente){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_CLIENTE, cliente);

        db.update(TABLE_CLIENTES,valores,CN_ID_SERVIDOR + "=?",new String[]{id_servidor});
        db.close();
    }


    //////


    public Cursor buscarSeJaTemSetor(String id_servidor){
        Cursor cursor;
        String[] campos =  {CN_ID_SERVIDOR};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_SETORES,campos,CN_ID_SERVIDOR + "=?", new String[]{id_servidor}, null, null, null);


        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }

    public long inserirSetor(String id_servidor, String setor){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CN_ID_SERVIDOR, id_servidor);
        valores.put(CN_SETOR, setor);


        resultado = db.insert(TABLE_SETORES, null, valores);
        db.close();

        return resultado;

    }

    public void atualizaSetor(String id_servidor, String setor){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_SETOR, setor);

        db.update(TABLE_SETORES,valores,CN_ID_SERVIDOR + "=?",new String[]{id_servidor});
        db.close();
    }

    public Cursor buscarprefixo(String prefixo){
        Cursor cursor;
        String[] campos =  {CN_MODELO, CN_CATEGORIA, CN_SETOR, CN_CLIENTE};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_AERONAVES,campos,CN_PREFIXO  + "=?", new String[]{prefixo}, null, null, null);

        if(cursor!=null){
            cursor.moveToNext();
        }
        db.close();
        return cursor;
    }


    public Cursor buscarmodelo(String codmodelo){
        Cursor cursor;
        String[] campos =  {CN_MODELO};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_MODELOS,campos,CN_ID_SERVIDOR  + "=?", new String[]{codmodelo}, null, null, null);

        if(cursor!=null){
            cursor.moveToNext();
        }
        db.close();
        return cursor;
    }


    public Cursor buscarcategoria(String codcategoria){
        Cursor cursor;
        String[] campos =  {CN_CATEGORIA};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_CATEGORIAS,campos,CN_ID_SERVIDOR  + "=?", new String[]{codcategoria}, null, null, null);

        if(cursor!=null){
            cursor.moveToNext();
        }
        db.close();
        return cursor;
    }

    public Cursor buscarsetor(String codsetor){
        Cursor cursor;
        String[] campos =  {CN_SETOR};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_SETORES,campos,CN_ID_SERVIDOR  + "=?", new String[]{codsetor}, null, null, null);

        if(cursor!=null){
            cursor.moveToNext();
        }
        db.close();
        return cursor;
    }

    public Cursor buscarcliente(String codcliente){
        Cursor cursor;
        String[] campos =  {CN_CLIENTE};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_CLIENTES,campos,CN_ID_SERVIDOR  + "=?", new String[]{codcliente}, null, null, null);

        if(cursor!=null){
            cursor.moveToNext();
        }
        db.close();
        return cursor;
    }

    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CLIENTES;

        //SQLiteDatabase db = this.getReadableDatabase();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(2));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


    public void atualizaStatusFicha(String fichaNr, String status){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CN_ENVIADO, status);
        db.update(TABLE_FICHAS,valores,CN_FICHANR + "=?",new String[]{fichaNr});
        db.close();


    }


    public Cursor buscarSetores(){
        Cursor cursor;

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_SETORES,null,null,null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }


    public void apagaFicha(String id){
        db = banco.getReadableDatabase();
        db.delete(TABLE_FICHAS, CN_ID+ "=?", new String[]{id});
        db.close();
    }

    public Cursor buscarStatusFicha(String fichaNr){
        Cursor cursor;
        String[] campos =  {CN_ENVIADO};

        db = banco.getReadableDatabase();
        cursor = db.query(TABLE_FICHAS,campos,CN_FICHANR + "=?", null, null, null, null);

        if(cursor!=null){
            cursor.moveToLast();
        }
        db.close();
        return cursor;
    }

}
