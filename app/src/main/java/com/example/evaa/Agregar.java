package com.example.evaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import models.*;

public class Agregar extends AppCompatActivity {
    private Gestion gestion;
    private Spinner spinnerTipoProducto;
    private TableLayout tableProductos;
    private EditText editNombre, editCantidad, editPrecio;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        gestion = DataHolder.getGestion();
        spinnerTipoProducto = findViewById(R.id.spinnerTipoProducto);

        // Spinner con estilo propio
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Cigarro", "Encendedor", "Enroladora", "Papel", "Tabaco"}
        ) {
            // Método que controla cómo se ve el Spinner cuando está cerrado
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextSize(20);
                tv.setTextColor(Color.BLACK);
                tv.setBackgroundColor(Color.parseColor("#e0e0e0"));
                return tv;
            }

            // Método que controla cómo se ve cada opción en el menú desplegable
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
                tv.setTextSize(18);
                tv.setTextColor(Color.BLACK);
                tv.setBackgroundColor(Color.parseColor("#eeeeee"));
                return tv;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoProducto.setAdapter(adapter);


        BottomNavigationView navView = findViewById(R.id.nav_view);

// marcar agregar como seleccionado
        navView.setSelectedItemId(R.id.navigation_agregar);

        navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    startActivity(new Intent(Agregar.this, MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.navigation_agregar:
                    return true; // ya estamos aquí

                case R.id.navigation_ver:
                    startActivity(new Intent(Agregar.this, Ver.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });


        // Texto superior
        TextView txt = findViewById(R.id.txtAgregar);
        txt.setTextSize(26);
        txt.setTypeface(null, Typeface.BOLD);
        txt.setTextColor(Color.parseColor("#424242"));

        // Caja de texto nombre con bordes redondeados
        editNombre = findViewById(R.id.editNombre);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(4, Color.GRAY);
        drawable.setCornerRadius(60f);
        drawable.setColor(Color.WHITE);

        editNombre.setBackground(drawable);
        editNombre.setHint("   Ingrese Nombre producto");

        // Labels
        TextView categoria = findViewById(R.id.txtCategoria);
        categoria.setTextSize(20);
        categoria.setTextColor(Color.BLACK);

        TextView nombre = findViewById(R.id.txtnombrepr);
        nombre.setTextSize(20);
        nombre.setTextColor(Color.BLACK);

        TextView cantidad = findViewById(R.id.txtCantidad);
        cantidad.setTextSize(20);
        cantidad.setTextColor(Color.BLACK);

        TextView precio = findViewById(R.id.txtprecio);
        precio.setTextSize(20);
        precio.setTextColor(Color.BLACK);

        // Inputs


        editCantidad = findViewById(R.id.editCantidad);
        editCantidad.setHint(" Ingresa cantidad");
        editCantidad.setBackground(drawable);

        EditText editPrecio = findViewById(R.id.editPrecio);
        editPrecio.setHint(" Ingrese valor");
        editPrecio.setBackground(drawable);

        Button button = findViewById(R.id.btnAgregar);
        tableProductos = findViewById(R.id.tableProductos);
        //mostrarProductosEnTabla();

        // Botón agregar producto
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editNombre.getText().toString();
                String cantidad = editCantidad.getText().toString();
                String precio = editPrecio.getText().toString();

                if (nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty()) {
                    Toast.makeText(Agregar.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int ocantidad = Integer.parseInt(cantidad);
                double Oprecio = Double.parseDouble(precio);

                if (Oprecio < 100f) {
                    Toast.makeText(Agregar.this, "Ingresa valores mayores a 100", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ocantidad < 0) {
                    Toast.makeText(Agregar.this, "Por favor mayor a 0", Toast.LENGTH_LONG).show();
                    return;
                }

                // Crear ID incremental
                int id = gestion.obtenerTodos().size() + 1;
                String tipoSeleccionado = spinnerTipoProducto.getSelectedItem().toString();

                Producto nuevo;

                // Switch de tipos de producto
                switch (tipoSeleccionado) {
                    case "Tabaco":
                        nuevo = new Tabaco(id, nombre, ocantidad, Oprecio);
                        break;
                    case "Encendedor":
                        nuevo = new Encendedor(id, Oprecio, nombre, ocantidad);
                        break;
                    case "Cigarro":
                        nuevo = new Cigarro(id, nombre, ocantidad, Oprecio);
                        break;
                    case "Enroladora":
                        nuevo = new Enroladora(id, Oprecio, nombre, ocantidad);
                        break;
                    case "Papel":
                        nuevo = new Papel(id, nombre, ocantidad, Oprecio);
                        break;
                    default:
                        Toast.makeText(Agregar.this, "Tipo no reconocido", Toast.LENGTH_SHORT).show();
                        return;
                }

                // Guardar en la gestión
                gestion.agregar(nuevo);

                // Crear fila nueva en la tabla
                TableRow fila = new TableRow(Agregar.this);
                TableLayout.LayoutParams tableRowParams =
                        new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.WRAP_CONTENT);
                fila.setLayoutParams(tableRowParams);


                TextView tvId = new TextView(Agregar.this);
                tvId.setText(String.valueOf(nuevo.getId()));
                tvId.setPadding(8, 8, 8, 8);
                tvId.setTextColor(Color.BLACK);

                TextView tvNombre = new TextView(Agregar.this);
                tvNombre.setText(nuevo.getMarca());
                tvNombre.setPadding(8, 8, 8, 8);
                tvNombre.setTextColor(Color.BLACK);

                TextView tvCantidad = new TextView(Agregar.this);
                tvCantidad.setText(String.valueOf(nuevo.getCantidad()));
                tvCantidad.setPadding(8, 8, 8, 8);
                tvCantidad.setTextColor(Color.BLACK);

                TextView tvPrecio = new TextView(Agregar.this);
                tvPrecio.setText("$" + nuevo.getPrecio()); // corregido
                tvPrecio.setPadding(8, 8, 8, 8);
                tvPrecio.setTextColor(Color.BLACK);

                // Botón eliminar
                ImageButton btnDelete = new ImageButton(Agregar.this);
                btnDelete.setImageResource(android.R.drawable.ic_menu_delete);
                btnDelete.setBackgroundColor(Color.TRANSPARENT);
                btnDelete.setColorFilter(Color.RED);
                TableRow.LayoutParams params = new TableRow.LayoutParams(100, 100);
                params.setMargins(8, 4, 8, 4);
                btnDelete.setLayoutParams(params);

                btnDelete.setOnClickListener(v -> {
                    gestion.eliminar(nuevo.getId());
                    tableProductos.removeView(fila);
                    Toast.makeText(Agregar.this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                });

                // Botón actualizar
                ImageButton btnUpdate = new ImageButton(Agregar.this);
                btnUpdate.setImageResource(android.R.drawable.ic_menu_edit);
                btnUpdate.setBackgroundColor(Color.TRANSPARENT);
                btnUpdate.setColorFilter(Color.BLUE);
                btnUpdate.setLayoutParams(params);

                btnUpdate.setOnClickListener(v -> {
                    editNombre.setText(nuevo.getMarca());
                    editCantidad.setText(String.valueOf(nuevo.getCantidad()));
                    editPrecio.setText(String.valueOf(nuevo.getPrecio()));
                    button.setText("Guardar cambios");

                    button.setOnClickListener(v2 -> {
                        nuevo.setMarca(editNombre.getText().toString());
                        nuevo.setCantidad(Integer.parseInt(editCantidad.getText().toString()));
                        nuevo.setPrecio(Double.parseDouble(editPrecio.getText().toString()));

                        tvNombre.setText(nuevo.getMarca());
                        tvCantidad.setText(String.valueOf(nuevo.getCantidad()));
                        tvPrecio.setText("$" + nuevo.getPrecio());

                        editNombre.setText("");
                        editCantidad.setText("");
                        editPrecio.setText("");
                        button.setText("Agregar");
                        button.setOnClickListener(this::onClick);
                        Toast.makeText(Agregar.this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                    });
                });

                // Agregar todo a la fila
                fila.addView(tvId);
                fila.addView(tvNombre);
                fila.addView(tvCantidad);
                fila.addView(tvPrecio);
                fila.addView(btnUpdate);
                fila.addView(btnDelete);

                // Agregar fila a la tabla
                tableProductos.addView(fila);

                Toast.makeText(Agregar.this, "Producto Agregado " + nombre + " , Cantidad:" + cantidad + ", Precio:" + precio, Toast.LENGTH_SHORT).show();

                // Limpiar campos
                editNombre.setText("");
                editCantidad.setText("");
                editPrecio.setText("");
                editNombre.requestFocus();
            }
        });
    }
}
