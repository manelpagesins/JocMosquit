package cat.dam.andy.jocmosquit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static java.util.concurrent.TimeUnit.SECONDS;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.view.View;
import android.graphics.drawable.AnimationDrawable;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    Button btn_start;
    ImageView iv_mosquit;
    AnimationDrawable mosquit_animat;
    ConstraintLayout main_screen;
    Chronometer tv_temps;
    TextView tv_punts;

    static  boolean correr = false;
    static boolean  mosquit_mort = false;
    static boolean  jocIniciat = false;
    static int puntuacio;

    /**
     * Funcio en la que posarem el comptador operatiu en el layout
     */
    private void Temps(Chronometer tv_temps, TextView tv_punts) {

        if (!correr) {
            tv_temps.setBase(SystemClock.elapsedRealtime());
            tv_temps.start();
            correr = true;
        }
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() { //Handler per controlar la reparicio del mosquit
            @Override
            public void run() {
                correr = false;
                startActivity(new Intent(MainActivity.this, Puntuacio.class));
                puntuacio = Integer.parseInt(tv_punts.getText().toString());
                finish();
            }
        }, 30000);

    }

    /**
     * Funcio per generar un mosquit
     */
    public void GenerarMosquit(ConstraintLayout main_screen){

        Random rand = new Random();

        mosquit_mort = false;

        int X,Y;

        iv_mosquit.setBackgroundResource(R.drawable.mosquit_animat);
        // Obté el fons que ha estat compilat amb un objecte AnimationDrawable
        mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();

        // Comença l'animació (per defecte repetició de cicle).
        mosquit_animat.start();

        X = rand.nextInt(600);

        Y = rand.nextInt(1200);

        iv_mosquit.setX(X);

        iv_mosquit.setY(Y);

        if(iv_mosquit.getParent() != null){
            ((ViewGroup)iv_mosquit.getParent()).removeView(iv_mosquit);
        }

        main_screen.addView(iv_mosquit);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mosquit_animat = new AnimationDrawable();
        // Carrega el ImageView que contindrà l'animació i actualiza el fons d'imatge amb el recurs XML on es defineix les imatges
        // i temps d'animació del mosquit
        iv_mosquit = (ImageView) findViewById(R.id.iv_mosquit);
        tv_temps = (Chronometer) findViewById(R.id.tv_temps);
        btn_start = (Button) findViewById(R.id.btn_start);
        tv_punts = (TextView) findViewById(R.id.tv_punts);
        main_screen = (ConstraintLayout) findViewById(R.id.main_screen);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Temps(tv_temps, tv_punts);
                GenerarMosquit(main_screen);
                btn_start.setVisibility(View.GONE);
                jocIniciat = true;

            }
        });

        iv_mosquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int punt = 1;
                int sumaPunts = 0;
                String punts = tv_punts.getText().toString();

                if (punts.equals("")) {
                    sumaPunts = punt;
                    tv_punts.setText(Integer.toString(sumaPunts));

                } else {
                    sumaPunts = Integer.parseInt(punts) + punt;
                    tv_punts.setText(Integer.toString(sumaPunts));
                }
                boolean animacioRealitzada = true;

                // En cas de que es cliqui el mosquit actualiza el fons d'imatge amb el recurs XML on es defineix les imatges
                // i temps d'animació de la taca de sang
                iv_mosquit.setBackgroundResource(R.drawable.sang_animat);
                mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
                // Fes l'animació (només un cicle).
                mosquit_animat.start();
                mosquit_mort = true;

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() { //Handler per controlar la reparicio del mosquit
                    @Override
                    public void run() {
                        GenerarMosquit(main_screen);
                    }
                }, 500);
            }
        });
    }
}