package br.com.onder.cleanflyingos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ServidorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        final EditText etservidor = (EditText) findViewById(R.id.et_servidor);

        final String servidore = prefs.getString("servidor", null);
        etservidor.setText(servidore);

        final Button b2 = (Button) findViewById(R.id.bt_gravar_servidor);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String servidor = etservidor.getText().toString();

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("servidor", servidor);
                editor.commit();

                //vai para a tela de login
                Intent intent2 = new Intent(ServidorActivity.this, LoginActivity.class);

                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);
                finish();

            }
        });




    }
}
