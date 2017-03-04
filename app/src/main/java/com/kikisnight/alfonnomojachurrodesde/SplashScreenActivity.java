package com.kikisnight.alfonnomojachurrodesde;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.animation;

public class SplashScreenActivity extends AppCompatActivity {


    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

        //Lamada a la Animación del Logo y Texto
        getInitLogo();
        getInitTexto();
        getInitTexto2();


        //Comenzar Servicio para música de fondo
        Intent svc = new Intent(this, MusicaFondo.class);
        svc.setAction("com.kikisnight.alfonnomojachurrodesde");
        startService(svc);

    }

    //Animación del Logo
    public void getInitLogo() {
        ImageView imagen = (ImageView) findViewById(R.id.kikisnightLogo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.kikisnight);
        imagen.startAnimation(animation);
    }

    public void getInitTexto () {
        TextView texto = (TextView) findViewById(R.id.kikisnightTexto);
        Animation animationTexto = AnimationUtils.loadAnimation(this, R.anim.kikisnight);
        texto.startAnimation(animationTexto);
    }

    public void getInitTexto2 () {
        TextView texto = (TextView) findViewById(R.id.kikisnightTexto2);
        Animation animationTexto = AnimationUtils.loadAnimation(this, R.anim.kikisnight);
        texto.startAnimation(animationTexto);
    }



}