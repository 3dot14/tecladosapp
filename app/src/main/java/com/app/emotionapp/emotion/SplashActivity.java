package com.app.emotionapp.emotion;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by luisprieto on 18/06/15.
 */
public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";
    protected boolean _active = true;
    private SharedPreferences preferences;
    private Thread splashTread;

    /** The _splash time. */
    protected int _splashTime = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                    finish();
                } finally {
                    Intent mIntent = new Intent(SplashActivity.this,
                                    MainActivity.class);

                    startActivity(mIntent);
                    finish();

                }
            }
        };
        splashTread.start();

    }

    @Override
    public void onBackPressed() {
        splashTread.interrupt();
        super.onBackPressed();
    }
}

