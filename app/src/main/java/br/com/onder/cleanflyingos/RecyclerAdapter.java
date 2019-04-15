package br.com.onder.cleanflyingos;

/**
 * Created by marco on 25/02/18.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    static List<ClasseFichas> dbList;

    public void setGreen(boolean green) {
        isGreen = green;
    }

    static boolean isGreen = false;
    static Context context;
    RecyclerAdapter(Context context, List<ClasseFichas> dbList ){
        this.dbList = new ArrayList<ClasseFichas>();
        this.context = context;
        this.dbList = dbList;

    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_fichas, null);

        // cria o ViewHolder

        ViewHolder viewHolder = null;
        try {
            viewHolder = new ViewHolder(itemLayoutView);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        setGreen(false);

        holder.prefixo.setText(dbList.get(position).getPrefixo_c());
        holder.modelo.setText(dbList.get(position).getModelo_categoria_c());
        holder.cliente.setText(dbList.get(position).getCliente_c());

        String statusnculeopre = dbList.get(position).getStatus_c();

        //Log.d("DEBUG", "pre: "+statusnculeopre);

        switch (statusnculeopre) {
            case "0":
                holder.ll_statusnucleo.setBackgroundColor(Color.RED);

                break;
            case "1":
                holder.ll_statusnucleo.setBackgroundColor(Color.GREEN);
                setGreen(true);
                break;

            default:
                holder.ll_statusnucleo.setBackgroundColor(Color.GRAY);

        }

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public String dataInicio, horaInicio, dataTermino, horaTermino;
        public TextView prefixo,modelo,cliente,tv_dt_inicio, tv_dt_termino, tv_hr_inicio, tv_hr_termino;
        public LinearLayout ll_statusnucleo;
        public ImageView iv_cadeado;

        public ViewHolder(View itemLayoutView) throws URISyntaxException {
            super(itemLayoutView);
            prefixo = (TextView) itemLayoutView.findViewById(R.id.tv_row_prefixo);
            modelo = (TextView) itemLayoutView.findViewById(R.id.tv_row_modelo);
          //  tv_dt_inicio = (TextView) itemLayoutView.findViewById(R.id.tv_row_data_inicio);
           // tv_dt_termino = (TextView) itemLayoutView.findViewById(R.id.tv_row_data_termino);
            //tv_hr_inicio = (TextView) itemLayoutView.findViewById(R.id.tv_row_hora_inicio);
           // tv_hr_termino = (TextView) itemLayoutView.findViewById(R.id.tv_row_hora_termino);
            cliente = (TextView) itemLayoutView.findViewById(R.id.tv_row_cliente);
            ll_statusnucleo = (LinearLayout) itemLayoutView.findViewById(R.id.ll_satusnucleo);

            itemLayoutView.setOnClickListener(this);


//            Intent intent = getIntent("");
//            dataInicio = intent.getStringExtra("dt_inicio");
//            horaInicio = intent.getStringExtra("hr_inicio");
//            dataTermino = intent.getStringExtra("dt_termino");
//            horaTermino = intent.getStringExtra("hr_termino");
//
//                Date dataHoraAtual = new Date();
//                dataInicio = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
//                horaInicio = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
//                dataTermino = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
//                horaTermino = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
//
//               tv_dt_inicio.setText(dataInicio);
//                tv_hr_inicio.setText(horaInicio);
//
//                tv_dt_termino.setText(dataTermino);
//                tv_hr_termino.setText(horaTermino);
//

//            Bundle bundle = intent.getExtras();
//
//            Bundle params = getIntent("dt_inicio").getExtras();
//            dataInicio = params.getString("dt_inicio");

//            if (bundle != null) {
//                dataInicio = bundle.getString("nrFicha");
//                //Log.i("Script", "nr ficha: "+nrFicha);
//
//            }
            // if (dataInicio.equals(null) && dataInicio.equals(null) ) {
//               Calendar c = Calendar.getInstance();
//               System.out.println("Current time => " + c.getTime());


//               dataInicio = df.format(c.getTime());


//               SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
//               horaInicio = dft.format(c.getTime());

//               tv_dt_inicio.setText(dataInicio);
//               tv_hr_inicio.setText(horaInicio);

            //}
//            Intent intent = getIntent();
//            Bundle dados = intent.getExtras();
//
//            dataInicio = dados.getString("data_inicio").toString();
//            horaInicio = dados.getString("hora_inicio").toString();
//
//            tv_dt_inicio.setText(dataInicio);
//            tv_hr_inicio.setText(horaInicio);

        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,CadastroServicoActivity.class);

            Bundle extras = new Bundle();
            extras.putInt("position",getAdapterPosition());

            intent.putExtras(extras);

            /*x
            int i=getAdapterPosition();
            intent.putExtra("position", getAdapterPosition());*/

            if(isGreen == true){

//                    Toast toast = Toast.makeText(context.getApplicationContext(), "Ficha já executada!", Toast.LENGTH_LONG);
//                    toast.show();

            }
            else if (isGreen == false){
                context.startActivity(intent);
                //Toast.makeText(RecyclerAdapter.context, "Linha clicada " + getAdapterPosition(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
