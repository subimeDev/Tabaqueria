package com.example.evaa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import models.*;

public class Ver extends AppCompatActivity {

    private TableLayout tableProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        tableProductos = findViewById(R.id.tableProductosVer);

        Gestion<Producto> gestion = DataHolder.getGestion();

        for (Producto p : gestion.obtenerTodos()) {
            TableRow fila = new TableRow(this);

            TextView tvId = new TextView(this);
            tvId.setText(String.valueOf(p.getId()));
            tvId.setPadding(8, 8, 8, 8);
            tvId.setTextColor(Color.BLACK);

            TextView tvNombre = new TextView(this);
            tvNombre.setText(p.getMarca());
            tvNombre.setPadding(8, 8, 8, 8);
            tvNombre.setTextColor(Color.BLACK);

            TextView tvCantidad = new TextView(this);
            tvCantidad.setText(String.valueOf(p.getCantidad()));
            tvCantidad.setPadding(8, 8, 8, 8);
            tvCantidad.setTextColor(Color.BLACK);

            TextView tvPrecio = new TextView(this);
            tvPrecio.setText(String.valueOf(p.getPrecio()));
            tvPrecio.setPadding(8, 8, 8, 8);
            tvPrecio.setTextColor(Color.BLACK);

            fila.addView(tvId);
            fila.addView(tvNombre);
            fila.addView(tvCantidad);
            fila.addView(tvPrecio);

            tableProductos.addView(fila);
        }

    }
}
