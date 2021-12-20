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


public class Puntuacio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puntuacio);

        TextView puntuacio = (TextView) findViewById(R.id.PuntsTotals);
        Button btn_tornar = (Button) findViewById(R.id.btn_tornar);

        String puntsJugador = Integer.toString(MainActivity.puntuacio);

        puntuacio.setText(puntsJugador);

        btn_tornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Puntuacio.this, MainActivity.class));
            }
        });
    }
}