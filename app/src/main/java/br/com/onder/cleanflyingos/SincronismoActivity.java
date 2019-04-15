package br.com.onder.cleanflyingos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SincronismoActivity extends AppCompatActivity {

    //private static int SPLASH_TIME_OUT = 100000;
    private static int SPLASH_TIME_OUT = 10000;
    protected static final int TIMER_RUNTIME = 10000;
    //protected static final int TIMER_RUNTIME = 100000;
    protected boolean mbActive;
    protected ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronismo);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mProgressBar = (ProgressBar)findViewById(R.id.sincBar);
        this.setTitle("POR FAVOR AGUARDE...");


        Intent serviceIntent = new Intent(SincronismoActivity.this, ServicoSincronismo.class);
        startService(serviceIntent);


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


        final TextView txtCount = (TextView) findViewById(R.id.tv_percent);

        final int secs = 100;
        //new CountDownTimer((secs +1) * 1000, 1000) // Wait 5 secs, tick every 1 sec
        new CountDownTimer((secs +1) * 100, 100) // Wait 5 secs, tick every 1 sec
        {
            @Override
            public final void onTick(final long millisUntilFinished)
            {
                txtCount.setText("Aguarde... " + (int) + (millisUntilFinished * .001f));
            }
            @Override
            public final void onFinish()
            {
                txtCount.setText("Sincronismo Efetuado!");
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





        Intent i = new Intent(SincronismoActivity.this, FichasActivity.class);
        startActivity(i);
        // close this activity
        finish();


    }
}
