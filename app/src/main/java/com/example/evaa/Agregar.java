package com.example.evaa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import models.*;

public class Agregar extends AppCompatActivity {
    private  Gestion gestion;
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_producto,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoProducto.setAdapter(adapter);


        TextView txt = findViewById(R.id.txtAgregar);
        txt.setTextSize(26);
        txt.setTypeface(null, Typeface.BOLD);
        txt.setTextColor(Color.parseColor("#424242"));

        EditText editNombre = findViewById(R.id.editNombre);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(4,Color.GRAY);
        drawable.setCornerRadius(60f);
        drawable.setColor(Color.WHITE);

        editNombre.setBackground(drawable);

        editNombre.setHint("   Ingrese Nombre producto");

        TextView nombre = findViewById(R.id.txtnombrepr);
         nombre.setTextSize(20);
         nombre.setTextColor(Color.BLACK);
         TextView cantidad = findViewById(R.id.txtCantidad);
         cantidad.setTextSize(20);
         cantidad.setTextColor(Color.BLACK);
         TextView precio = findViewById(R.id.txtprecio);
         precio.setTextSize(20);
         precio.setTextColor(Color.BLACK);

        EditText editcantidad = findViewById(R.id.editCantidad);
        editcantidad.setHint(" Ingresa cantidad");
        editcantidad.setBackground(drawable);

        EditText editPrecio = findViewById(R.id.editPrecio);
        editPrecio.setHint(" Ingrese valor");
        editPrecio.setBackground(drawable);

        Button button = findViewById(R.id.btnAgregar);
        tableProductos = findViewById(R.id.tableProductos);
        //mostrarProductosEnTabla();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editNombre.getText().toString();
                String cantidad = editcantidad.getText().toString();
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

                int id = gestion.obtenerTodos().size() + 1;
                String tipoSeleccionado = spinnerTipoProducto.getSelectedItem().toString();
                Producto nuevo;

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

                gestion.agregar(nuevo);

                TableRow fila = new TableRow(Agregar.this);

                TextView tvId = new TextView(Agregar.this);
                tvId.setText(String.valueOf(nuevo.getId()));
                tvId.setPadding(8, 8, 8, 8);

                TextView tvNombre = new TextView(Agregar.this);
                tvNombre.setText(nuevo.getMarca());
                tvNombre.setPadding(8, 8, 8, 8);

                TextView tvCantidad = new TextView(Agregar.this);
                tvCantidad.setText(String.valueOf(nuevo.getCantidad()));
                tvCantidad.setPadding(8, 8, 8, 8);

                TextView tvPrecio = new TextView(Agregar.this);
                tvPrecio.setText(String.valueOf(nuevo.getPrecio()));
                tvPrecio.setPadding(8, 8, 8, 8);

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

                ImageButton btnUpdate = new ImageButton(Agregar.this);
                btnUpdate.setImageResource(android.R.drawable.ic_menu_edit);
                btnUpdate.setBackgroundColor(Color.TRANSPARENT);
                btnUpdate.setColorFilter(Color.BLUE);
                btnUpdate.setLayoutParams(params);

                btnUpdate.setOnClickListener(v -> {
                    editNombre.setText(nuevo.getMarca());
                    editcantidad.setText(String.valueOf(nuevo.getCantidad()));
                    editPrecio.setText(String.valueOf(nuevo.getPrecio()));
                    button.setText("Guardar cambios");

                    button.setOnClickListener(v2 -> {
                        nuevo.setMarca(editNombre.getText().toString());
                        nuevo.setCantidad(Integer.parseInt(editcantidad.getText().toString()));
                        nuevo.setPrecio(Double.parseDouble(editPrecio.getText().toString()));

                        tvNombre.setText(nuevo.getMarca());
                        tvCantidad.setText(String.valueOf(nuevo.getCantidad()));
                        tvPrecio.setText(String.valueOf(nuevo.getPrecio()));

                        editNombre.setText("");
                        editcantidad.setText("");
                        editPrecio.setText("");
                        button.setText("Agregar");
                        button.setOnClickListener(this::onClick);
                        Toast.makeText(Agregar.this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                    });
                });

                fila.addView(tvId);
                fila.addView(tvNombre);
                fila.addView(tvCantidad);
                fila.addView(tvPrecio);
                fila.addView(btnUpdate);
                fila.addView(btnDelete);
                tableProductos.addView(fila);

                Toast.makeText(Agregar.this, "Producto Agregado " + nombre + " , Cantidad:" + cantidad + ", Precio:" + precio, Toast.LENGTH_SHORT).show();
                editNombre.setText("");
                editcantidad.setText("");
                editPrecio.setText("");
                editNombre.requestFocus();
            }
        });





    }
}


