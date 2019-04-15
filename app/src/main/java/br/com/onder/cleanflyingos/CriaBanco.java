package br.com.onder.cleanflyingos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marco on 13/02/18.
 */

public class CriaBanco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "cf.db";
    private static final int VERSAO =12;


    public static final String TABLE_LOGIN = "login";
    public static final String TABLE_FICHAS = "fichas";
    public static final String TABLE_AERONAVES = "aeronaves";
    public static final String TABLE_MODELOS = "modelos";
    public static final String TABLE_CATEGORIAS = "categorias";
    public static final String TABLE_SETORES = "setores";
    public static final String TABLE_CLIENTES = "clientes";

    //colunas login
    public static final String CN_ID = "_id";
    public static final String CN_USUARIO = "usuario";
    public static final String CN_DATA = "data";



    //colunas tablea fichas

    public static final String CN_PREFIXO = "prefixo";
    public static final String CN_FICHANR = "fichanr";
    public static final String CN_MODELO_CATEGORIA = "modelo_categoria";
    public static final String CN_DATA_INICIO = "data_inicio";
    public static final String CN_DATA_TERMINO = "data_termino";
    public static final String CN_HORA_INICIO = "hora_inicio";
    public static final String CN_HORA_TERMINO = "hora_termino";
    public static final String CN_CLIENTE = "cliente";
    public static final String CN_SETOR = "setor";
    public static final String CN_SERVICOS = "servicos";
    public static final String CN_ATENDIMENTO = "atendimento";
    public static final String CN_MANUTENCAO = "manutencao";
    public static final String CN_AVULSO = "avulso";
    public static final String CN_ASP = "asp";
    public static final String CN_COM = "com";
    public static final String CN_LAV = "lav";
    public static final String CN_LAS = "las";
    public static final String CN_LAB = "lab";
    public static final String CN_POL = "pol";
    public static final String CN_MET = "met";
    public static final String CN_CAR = "car";
    public static final String CN_EST = "est";
    public static final String CN_QTU = "qtu";
    public static final String CN_BOL = "bol";
    public static final String CN_OTR = "otr";
    public static final String CN_ASP_OBS = "asp_obs";
    public static final String CN_COM_OBS = "com_obs";
    public static final String CN_LAV_OBS = "lav_obs";
    public static final String CN_LAS_OBS = "las_obs";
    public static final String CN_LAB_OBS = "lab_obs";
    public static final String CN_POL_OBS = "pol_obs";
    public static final String CN_MET_OBS = "met_obs";
    public static final String CN_CAR_OBS = "car_obs";
    public static final String CN_EST_OBS = "est_obs";
    public static final String CN_QTU_OBS = "qtu_obs";
    public static final String CN_BOL_OBS = "bol_obs";
    public static final String CN_OTR_OBS = "otr_obs";
    public static final String CN_PORTA_ESCADA = "porta_escada";
    public static final String CN_TUBOS_PILOT = "pilot";
    public static final String CN_PARA_BRISAS = "para_brisas";
    public static final String CN_PARASOL = "para_sol";
    public static final String CN_RADONE = "radone";
    public static final String CN_TREM_PRINCIPAL = "trem_principal";
    public static final String CN_BORDOS_ATAQUE = "bordos_ataque";
    public static final String CN_DESCARREGADORES = "descarregadores";
    public static final String CN_FLAPS = "flaps";
    public static final String CN_MOTORES = "motores";
    public static final String CN_HELICES = "helices";
    public static final String CN_FAROIS = "farois";
    public static final String CN_ANTENAS = "antenas";
    public static final String CN_PINTURA = "pintura";
    public static final String CN_PAINEIS = "paineis";
    public static final String CN_GALLEY = "galley";
    public static final String CN_MESAS = "mesas";
    public static final String CN_BANCOS = "bancos";
    public static final String CN_JANELAS = "janelas";
    public static final String CN_CARPETES = "carpetes";
    public static final String CN_BANHEIRO = "banheiro";
    public static final String CN_CLQTU = "clqtu";
    public static final String CN_PERTENCES = "pertences";
    public static final String CN_OUTRO_DESCR = "outro_descr";
    public static final String CN_OUTRO = "outro";
    public static final String CN_ENTRADA_RESP = "entrada_resp";
    public static final String CN_ENTRADA_RESP_ASS = "entrada_resp_ass";
    public static final String CN_ENTRADA_ATENDENTE = "entrada_atendente";
    public static final String CN_ENTRADA_ATENDENTE_ASS = "entrada_atendente_ass";
    public static final String CN_SAIDA_INSP = "saida_insp";
    public static final String CN_SAIDA_INSP_ASS = "saida_insp_ass";
    public static final String CN_SAIDA_RESP = "saida_resp";
    public static final String CN_SAIDA_RESP_ASS = "saida_resp_ass";
    public static final String CN_FOTO_ANTES = "foto_antes";
    public static final String CN_FOTO_DEPOIS = "foto_depois";
    public static final String CN_PORTA_ESCADA_OBS = "porta_escada_obs";
    public static final String CN_TUBOS_PILOT_OBS = "pilot_obs";
    public static final String CN_PARA_BRISAS_OBS = "para_brisas_obs";
    public static final String CN_PARASOL_OBS = "para_sol_obs";
    public static final String CN_RADONE_OBS = "radone_obs";
    public static final String CN_TREM_PRINCIPAL_OBS = "trem_principal_obs";
    public static final String CN_BORDOS_ATAQUE_OBS = "bordos_ataque_obs";
    public static final String CN_DESCARREGADORES_OBS = "descarregadores_obs";
    public static final String CN_FLAPS_OBS = "flaps_obs";
    public static final String CN_MOTORES_OBS = "motores_obs";
    public static final String CN_HELICES_OBS = "helices_obs";
    public static final String CN_FAROIS_OBS = "farois_obs";
    public static final String CN_ANTENAS_OBS = "antenas_obs";
    public static final String CN_PINTURA_OBS = "pintura_obs";
    public static final String CN_PAINEIS_OBS = "paineis_obs";
    public static final String CN_GALLEY_OBS = "galley_obs";
    public static final String CN_MESAS_OBS = "mesas_obs";
    public static final String CN_BANCOS_OBS = "bancos_obs";
    public static final String CN_JANEJAS_OBS = "janelas_obs";
    public static final String CN_CARPETES_OBS = "carpetes_obs";
    public static final String CN_BANHEIRO_OBS = "banheiro_obs";
    public static final String CN_CLQTU_OBS = "clqtu_obs";
    public static final String CN_PERTENCES_OBS = "pertences_obs";
    public static final String CN_OUTRO_OBS = "outro_obs";
    public static final String CN_ENVIADO = "enviado";
    public static final String CN_OBS_OBS = "obs_obs";


    public static final String CN_ID_SERVIDOR = "id_servidor";
    public static final String CN_MODELO = "modelo";
    public static final String CN_CATEGORIA = "categoria";
    //public static final String CN_SETOR = "";
    public static final String CN_ID_CLIENTE = "id_cliente";







    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN = " create table " +TABLE_LOGIN+ " ("
                + CN_ID + " integer primary key autoincrement,"
                + CN_USUARIO + " text,"
                + CN_DATA + " text);";


        String CREATE_FICHAS = " create table " +TABLE_FICHAS+ " ("
                + CN_ID + " integer primary key autoincrement,"
                + CN_FICHANR + " text,"
                + CN_PREFIXO + " text,"
                + CN_MODELO_CATEGORIA + " text,"
                + CN_DATA_INICIO + " text,"
                + CN_DATA_TERMINO + " text,"
                + CN_HORA_INICIO + " text,"
                + CN_HORA_TERMINO + " text,"
                + CN_CLIENTE + " text,"
                + CN_SETOR + " text,"
                + CN_SERVICOS + " text,"
                + CN_ATENDIMENTO + " text DEFAULT 0,"
                + CN_MANUTENCAO + " text DEFAULT 0,"
                + CN_AVULSO + " text DEFAULT 0,"
                + CN_ASP + " text DEFAULT 0,"
                + CN_COM + " text DEFAULT 0,"
                + CN_LAV + " text DEFAULT 0,"
                + CN_LAS + " text DEFAULT 0,"
                + CN_LAB + " text DEFAULT 0,"
                + CN_POL + " text DEFAULT 0,"
                + CN_MET + " text DEFAULT 0,"
                + CN_CAR + " text DEFAULT 0,"
                + CN_EST + " text DEFAULT 0,"
                + CN_QTU + " text DEFAULT 0,"
                + CN_BOL + " text DEFAULT 0,"
                + CN_OTR + " text DEFAULT 0,"
                + CN_ASP_OBS + " text DEFAULT 0,"
                + CN_COM_OBS + " text DEFAULT 0,"
                + CN_LAV_OBS + " text DEFAULT 0,"
                + CN_LAS_OBS + " text DEFAULT 0,"
                + CN_LAB_OBS + " text DEFAULT 0,"
                + CN_POL_OBS + " text DEFAULT 0,"
                + CN_MET_OBS + " text DEFAULT 0,"
                + CN_CAR_OBS + " text DEFAULT 0,"
                + CN_EST_OBS + " text DEFAULT 0,"
                + CN_QTU_OBS + " text DEFAULT 0,"
                + CN_BOL_OBS + " text DEFAULT 0,"
                + CN_OTR_OBS + " text DEFAULT 0,"
                + CN_PORTA_ESCADA + " text DEFAULT 0,"
                + CN_TUBOS_PILOT + " text DEFAULT 0,"
                + CN_PARA_BRISAS + " text DEFAULT 0,"
                + CN_PARASOL + " text DEFAULT 0,"
                + CN_RADONE + " text DEFAULT 0,"
                + CN_TREM_PRINCIPAL + " text DEFAULT 0,"
                + CN_BORDOS_ATAQUE + " text DEFAULT 0,"
                + CN_DESCARREGADORES + " text DEFAULT 0,"
                + CN_FLAPS + " text DEFAULT 0,"
                + CN_MOTORES + " text DEFAULT 0,"
                + CN_HELICES + " text DEFAULT 0,"
                + CN_FAROIS + " text DEFAULT 0,"
                + CN_ANTENAS + " text DEFAULT 0,"
                + CN_PINTURA + " text DEFAULT 0,"
                + CN_PAINEIS + " text DEFAULT 0,"
                + CN_GALLEY + " text DEFAULT 0,"
                + CN_MESAS + " text DEFAULT 0,"
                + CN_BANCOS + " text DEFAULT 0,"
                + CN_JANELAS + " text DEFAULT 0,"
                + CN_CARPETES + " text DEFAULT 0,"
                + CN_BANHEIRO + " text DEFAULT 0,"
                + CN_CLQTU + " text DEFAULT 0,"
                + CN_PERTENCES+ " text DEFAULT 0,"
                + CN_OUTRO + " text DEFAULT 0,"
                + CN_OUTRO_DESCR + " text,"
                + CN_ENTRADA_RESP + " text,"
                + CN_ENTRADA_RESP_ASS + " text,"
                + CN_ENTRADA_ATENDENTE + " text,"
                + CN_ENTRADA_ATENDENTE_ASS + " text,"
                + CN_SAIDA_INSP + " text,"
                + CN_SAIDA_INSP_ASS + " text,"
                + CN_SAIDA_RESP + " text,"
                + CN_SAIDA_RESP_ASS + " text,"
                + CN_FOTO_ANTES + " text,"
                + CN_FOTO_DEPOIS + " text,"
                + CN_PORTA_ESCADA_OBS + " text DEFAULT 0,"
                + CN_TUBOS_PILOT_OBS + " text DEFAULT 0,"
                + CN_PARA_BRISAS_OBS + " text DEFAULT 0,"
                + CN_PARASOL_OBS + " text DEFAULT 0,"
                + CN_RADONE_OBS + " text DEFAULT 0,"
                + CN_TREM_PRINCIPAL_OBS + " text DEFAULT 0,"
                + CN_BORDOS_ATAQUE_OBS + " text DEFAULT 0,"
                + CN_DESCARREGADORES_OBS + " text DEFAULT 0,"
                + CN_FLAPS_OBS + " text DEFAULT 0,"
                + CN_MOTORES_OBS + " text DEFAULT 0,"
                + CN_HELICES_OBS + " text DEFAULT 0,"
                + CN_FAROIS_OBS + " text DEFAULT 0,"
                + CN_ANTENAS_OBS + " text DEFAULT 0,"
                + CN_PINTURA_OBS + " text DEFAULT 0,"
                + CN_PAINEIS_OBS + " text DEFAULT 0,"
                + CN_GALLEY_OBS + " text DEFAULT 0,"
                + CN_MESAS_OBS + " text DEFAULT 0,"
                + CN_BANCOS_OBS + " text DEFAULT 0,"
                + CN_JANEJAS_OBS + " text DEFAULT 0,"
                + CN_CARPETES_OBS + " text DEFAULT 0,"
                + CN_BANHEIRO_OBS + " text DEFAULT 0,"
                + CN_CLQTU_OBS + " text DEFAULT 0,"
                + CN_PERTENCES_OBS + " text DEFAULT 0,"
                + CN_OUTRO_OBS + " text DEFAULT 0,"
                + CN_OBS_OBS + " text DEFAULT 0," //38
                + CN_ENVIADO + " text DEFAULT 0,"
                + CN_MODELO + " text DEFAULT 0,"
                + CN_CATEGORIA + " text DEFAULT 0);";


        String CREATE_AERONAVES = " create table " +TABLE_AERONAVES+ " ("
                + CN_ID + " integer primary key autoincrement,"
                + CN_ID_SERVIDOR + " text,"
                + CN_PREFIXO + " text,"
                + CN_MODELO + " text,"
                + CN_CATEGORIA + " text,"
                + CN_SETOR + " text,"
                + CN_CLIENTE + " text);";

        String CREATE_MODELOS = " create table " +TABLE_MODELOS+ " ("
                + CN_ID + " integer primary key autoincrement,"
                + CN_ID_SERVIDOR + " text,"
                + CN_MODELO + " text);";

        String CREATE_CATEGORIAS = " create table " +TABLE_CATEGORIAS+ " ("
                + CN_ID + " integer primary key autoincrement,"
                + CN_ID_SERVIDOR + " text,"
                + CN_CATEGORIA + " text);";

        String CREATE_CLIENTES = " create table " +TABLE_CLIENTES+ " ("
                + CN_ID + " integer primary key autoincrement,"
                + CN_ID_SERVIDOR + " text,"
                + CN_CLIENTE + " text);";

        String CREATE_SETORES = " create table " +TABLE_SETORES+ " ("
                + CN_ID + " integer primary key autoincrement,"
                + CN_ID_SERVIDOR + " text,"
                + CN_SETOR + " text);";

        db.execSQL(CREATE_LOGIN);
        db.execSQL(CREATE_FICHAS);
        db.execSQL(CREATE_AERONAVES);
        db.execSQL(CREATE_MODELOS);
        db.execSQL(CREATE_CATEGORIAS);
        db.execSQL(CREATE_CLIENTES);
        db.execSQL(CREATE_SETORES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS login");
        db.execSQL("DROP TABLE IF EXISTS fichas");
        db.execSQL("DROP TABLE IF EXISTS aeronaves");
        db.execSQL("DROP TABLE IF EXISTS modelos");
        db.execSQL("DROP TABLE IF EXISTS categorias");
        db.execSQL("DROP TABLE IF EXISTS clientes");
        db.execSQL("DROP TABLE IF EXISTS setores");

        onCreate(db);
    }
}