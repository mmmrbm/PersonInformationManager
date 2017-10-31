package com.yukon.madhuranga.personinformationmanager.feature.startup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.yukon.madhuranga.personinformationmanager.R;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.MainActivity;

/**
 * Represents the logic governing the Splash screen of the application.
 */
public class SplashActivity extends Activity {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
