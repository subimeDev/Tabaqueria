package com.example.evaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import models.DataHolder;
import models.Gestion;
import models.Producto;
import models.Tabaco;

public class Ver extends AppCompatActivity {

    private TableLayout tableProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);



        BottomNavigationView navView = findViewById(R.id.nav_view);

// marcar agregar como seleccionado
        navView.setSelectedItemId(R.id.navigation_ver);

        navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    startActivity(new Intent(Ver.this, MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.navigation_agregar:
                    startActivity(new Intent(Ver.this, Ver.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.navigation_ver:
                    return  true;
            }
            return false;
        });

        tableProductos = findViewById(R.id.tableProductosVer);

        Gestion<Producto> gestion = DataHolder.getGestion();

        for (Producto p : gestion.obtenerTodos()) {
            TableRow fila = new TableRow(this);

            TextView tvId = new TextView(this);
            tvId.setText(String.valueOf(p.getId()));

            TextView tvNombre = new TextView(this);
            tvNombre.setText(p.getMarca());

            TextView tvCantidad = new TextView(this);
            tvCantidad.setText(String.valueOf(p.getCantidad()));

            TextView tvPrecio = new TextView(this);
            tvPrecio.setText("$" + p.getPrecio());


            tvId.setPadding(8, 8, 8, 8);
            tvNombre.setPadding(8, 8, 8, 8);
            tvCantidad.setPadding(8, 8, 8, 8);
            tvPrecio.setPadding(8, 8, 8, 8);

            tvId.setTextColor(Color.BLACK);
            tvNombre.setTextColor(Color.BLACK);
            tvCantidad.setTextColor(Color.BLACK);
            tvPrecio.setTextColor(Color.BLACK);

            fila.addView(tvId);
            fila.addView(tvNombre);
            fila.addView(tvCantidad);
            fila.addView(tvPrecio);

            tableProductos.addView(fila);
        }

    }
}
