package br.com.onder.cleanflyingos;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class FichasActivity extends AppCompatActivity {

    List<ClasseFichas> dbList;
    LinearLayout linearLayout;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichas);
        this.setTitle("Fichas");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.setTitle("SOLICITAÇÕES");

        BancoController crud = new BancoController(getBaseContext());


        dbList= new ArrayList<ClasseFichas>();
        dbList = crud.getDataFromDB();

        mRecyclerView = (RecyclerView)findViewById(R.id.reciclador);

        //mRecyclerView.setHasFixedSize(true);

        // usando o linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        //lmanager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // especificando o adapter
        mAdapter = new RecyclerAdapter(this,dbList);
        mRecyclerView.setAdapter(mAdapter);


        linearLayout = (LinearLayout) findViewById(R.id.btl_linear);
   /*Rest of subviews*/


   /*Linear Layout now will call the new listener*/
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Intent intent2 = new Intent(FichasActivity.this, CadastroAeronaveActivity.class);
                //Intent intent2 = new Intent(FichasActivity.this, PesquisaAeronaveActivity.class);
                Intent intent2 = new Intent(FichasActivity.this, CadastroAeronaveActivity.class);

                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();

            }
        });



/*
        final Button b2 = (Button) findViewById(R.id.bt_cadastrar_aeronave);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent intent2 = new Intent(FichasActivity.this, CadastroAeronaveActivity.class);

                intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);

                finish();

            }
        });
*/

    }
}
