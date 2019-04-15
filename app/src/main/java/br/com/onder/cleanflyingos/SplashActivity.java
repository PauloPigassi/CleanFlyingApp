package br.com.onder.cleanflyingos;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 6000;
    protected static final int TIMER_RUNTIME = 10000;
    protected boolean mbActive;
    protected ProgressBar mProgressBar;
    public String gestor;
    public String re;
    ImageView b_config;

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        File foldercf = new File(Environment.getExternalStorageDirectory() + "/CF");
        boolean successcf = true;
        if (!foldercf.exists()) {

            successcf = foldercf.mkdir();

        }
        File foldercfentrada = new File(Environment.getExternalStorageDirectory() + "/CF/Entrada");
        boolean successentrada = true;
        if (!foldercfentrada.exists()) {
            successentrada = foldercfentrada.mkdir();
        }

        File foldercfsaida = new File(Environment.getExternalStorageDirectory() + "/CF/Saida");
        boolean successsaida = true;
        if (!foldercfsaida.exists()) {
            successsaida = foldercfsaida.mkdir();
        }

        File folderass = new File(Environment.getExternalStorageDirectory() + "/CF/Assinatura");
        boolean success = true;
        if (!folderass.exists()) {
            success = folderass.mkdir();
        }


        b_config = (ImageView) findViewById(R.id.img_config);

        b_config.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                //vai para a tela de login
                Intent intent3 = new Intent(SplashActivity.this, ServidorActivity.class);

                //intent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent3);
                finish();


            }
        });


        boolean alarmeativo = (PendingIntent.getService(SplashActivity.this, 0, new Intent(SplashActivity.this, ServicoEnviaDados.class), PendingIntent.FLAG_NO_CREATE) == null);

        if (alarmeativo){
            //Log.i("Script", "Novo Alarme");
            Toast.makeText(SplashActivity.this, "ATIVANDO SINCRONISMO !", Toast.LENGTH_LONG).show();
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 400 milliseconds
            v.vibrate(400);
            //Intent alarmIntent = new Intent(SplashActivity.this, ServicoEnviaDados.class);
            //pendingIntent = PendingIntent.getBroadcast(SplashActivity.this, 0, alarmIntent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 460);

            //AlarmManager almanager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            //almanager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 1000 * 60 * 5, pendingIntent);

            Intent intent = new Intent(SplashActivity.this, ServicoEnviaDados.class);
            PendingIntent pintent = PendingIntent.getService(SplashActivity.this, 0, intent, 0);
            AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5*60*1000, pintent);



        } else {
            //Log.i("Script", "Alarme já ativo");
            Toast.makeText(SplashActivity.this, "SINCRONISMO JÁ ESTÁ ATIVO !", Toast.LENGTH_LONG).show();
        }


        //DbHelper helper = new DbHelper(SplashActivity.this);
        //SQLiteDatabase db = helper.getWritableDatabase();
        //final DataBaseManager manager = new DataBaseManager(this);






        mProgressBar = (ProgressBar)findViewById(R.id.barra);

        final Thread timerThread = new Thread(){
            @Override
            public void run(){
                mbActive = true;
                try {
                    int waited = 0;
                    while (mbActive && (waited < TIMER_RUNTIME)){
                        sleep(200);
                        if(mbActive){
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                } catch (InterruptedException e){
                    //caso erro
                } finally {
                    onContinue();
                }

            }
        };
        timerThread.start();


        final TextView txtCount = (TextView) findViewById(R.id.msg);

        final int secs = 8;
        new CountDownTimer((secs +1) * 1000, 1000) // Wait 5 secs, tick every 1 sec
        {
            @Override
            public final void onTick(final long millisUntilFinished)
            {
                txtCount.setText("Aguarde... " + (int) (millisUntilFinished * .001f));
            }
            @Override
            public final void onFinish()
            {
                txtCount.setText("Tudo ok!");
            }
        }.start();


    }


    public void  updateProgress(final int timePassed){
        if(null != mProgressBar){
            final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
            mProgressBar.setProgress(progress);
        }

    }

    public void onContinue(){


        // DbHelper helper = new DbHelper(SplashActivity.this);
        // SQLiteDatabase db = helper.getWritableDatabase();
        //final DataBaseManager manager = new DataBaseManager(this);
        //Intent i = new Intent(SplashActivity.this, MainActivity.class);
        //Verifica se já existe login anterior


                //Não tem vai para a tela de cadastro
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                // close this activity
                finish();



    }

        /*
            @Override
            public void onDestroy(){
                super.onDestroy();
                db.close();
            }
        */
}
