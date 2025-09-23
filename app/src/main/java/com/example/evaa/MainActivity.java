package com.example.evaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView navView = findViewById(R.id.nav_view);




// marcar inicio como seleccionado
        navView.setSelectedItemId(R.id.navigation_inicio);

        navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    return true; // ya estamos aquí

                case R.id.navigation_agregar:
                    startActivity(new Intent(MainActivity.this, Agregar.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.navigation_ver:
                    startActivity(new Intent(MainActivity.this, Ver.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });



        Button botonVer = findViewById(R.id.btnVer); //creamos el obj para poder usarlo a nuestro gusto
        Button botonIngreso = findViewById(R.id.btnIngresar);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(10, Color.BLUE); // Seteamos el color de los bordes ah y grosor que va siendo 4
        drawable.setCornerRadius(20); // Con esto hacemos que los bordes sean redondos

       // Aqui seteamos los botones para que redireccionen
        botonVer.setOnClickListener(view -> {
            Intent intent2 = new Intent(MainActivity.this,Ver.class);
            startActivity(intent2);
        });
        botonIngreso.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Agregar.class);
            startActivity(intent);
        });
        }



    }

