package com.example.evaa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import models.DataHolder;
import models.Gestion;
import models.Producto;

public class Agregar extends AppCompatActivity {
    private  Gestion gestion;
    private TableLayout tableProductos;
    private EditText editNombre, editCantidad, editPrecio;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        gestion = DataHolder.getGestion();  // Aqui importe el singleton para poder traspasar los datos que agrego aca a ver
       // gestion = new Gestion(new ArrayList<>());


        gestion.agregar(new Producto(1,"pall man",10,2500));
        gestion.agregar(new Producto(2,"Tabaco",10,4000));
        gestion.agregar(new Producto(3,"Encendedor",5,400));





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
                // Con esto obtenemos los datos de los 3 parametros nombre,cantidad y precio y lo guardamos en la variable
                String nombre = editNombre.getText().toString();
                String cantidad = editcantidad.getText().toString();
                String precio = editPrecio.getText().toString();

                if(nombre.isEmpty()|| cantidad.isEmpty() || precio.isEmpty() || precio.isEmpty() ){
                    Toast.makeText(Agregar.this,"Completa los campos ", Toast.LENGTH_SHORT).show();
                    return;
                }
                int ocantidad = Integer.parseInt(cantidad);
                Double Oprecio = Double.parseDouble(precio);
                if(Oprecio < 100f){
                    Toast.makeText(Agregar.this,"Ingresa valores mayores a 100",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ocantidad <0){
                    Toast.makeText(Agregar.this,"Porfavor mayor a 0",Toast.LENGTH_LONG).show();
                    return;
                }

                //Crear producto y agregamos a la lista gestion

                int id = gestion.obtenerTodos().size()+1; // esto hace que sea auto increment las id como en bd
                Producto nuevo = new Producto(id,nombre,ocantidad,Oprecio); // aqui es oprecio o ocantidad porque transformamos denante
                gestion.agregar(nuevo);

                // ahora vamos con crear filas en la tabla
                TextView tvId = new TextView(Agregar.this);
                TableRow fila = new TableRow(Agregar.this);
                tvId.setText(String.valueOf(nuevo.getId()));
                tvId.setPadding(8,8,8,8);

                TextView tvNombre = new TextView(Agregar.this);
                tvNombre.setText(nuevo.getNombre());
                tvNombre.setPadding(8,8,8,8);

                TextView tvCantidad = new TextView(Agregar.this);
                tvCantidad.setText(String.valueOf(nuevo.getCantidad()));
                tvCantidad.setPadding(8,8,8,8);


                TextView  tvPrecio = new TextView(Agregar.this);
                tvPrecio.setText(String.valueOf(nuevo.getPrecio()));
                tvCantidad.setPadding(8,8,8,8);



                // Botón Eliminar (ícono)
                ImageButton btnDelete = new ImageButton(Agregar.this);
                btnDelete.setImageResource(android.R.drawable.ic_menu_delete); // icono basurero
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
                btnUpdate.setImageResource(android.R.drawable.ic_menu_edit); // icono lápiz
                btnUpdate.setBackgroundColor(Color.TRANSPARENT);
                btnUpdate.setColorFilter(Color.BLUE);
                btnUpdate.setLayoutParams(params);

                btnUpdate.setOnClickListener(v -> {
                    editNombre.setText(nuevo.getNombre());
                    editcantidad.setText(String.valueOf(nuevo.getCantidad()));
                    editPrecio.setText(String.valueOf(nuevo.getPrecio()));
                    editNombre.setText(nuevo.getNombre());
                    editcantidad.setText(String.valueOf(nuevo.getCantidad()));
                    editPrecio.setText(String.valueOf(nuevo.getPrecio()));
                    btnUpdate.setPadding(16, 8, 16, 8);
                    //cambiamos el comportamiento del boton pricapal a guard
                    button.setText("Guarda cambios");
                    button.setOnClickListener(v2 ->{
//actualizamos datos
                        nuevo.setNombre(editNombre.getText().toString());
                        nuevo.setCantidad(Integer.parseInt(editcantidad.getText().toString()));
                        nuevo.setPrecio(Double.parseDouble(editPrecio.getText().toString()));

                   tvNombre.setText(nuevo.getNombre());
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



                // Mostramos los textos obtenidos en un mensaje volante llamado toast o notificacion quien sabe xd
                Toast.makeText(Agregar.this ,"Producto Agregado " + nombre + " , Cantidad:" + cantidad + ",Precio:" + precio, Toast.LENGTH_SHORT).show();
                editNombre.setText("");
                editcantidad.setText("");
                editPrecio.setText("");
                editNombre.requestFocus(); // con esto una vez complete el ciclo se enfoque en el primer campo osea editNombre
            }


        });


        //Esto nos permite cambiar el fondo de un mainactivity en especifico
       // View root = findViewById(android.R.id.content);
        //root.setBackgroundColor(Color.parseColor("#B71C1C"));




    }

    /*private void mostrarProductosEnTabla(){
        for (Producto p : gestion.obtenerProductos()){
            TableRow fila = new TableRow(this);

            TextView tvId = new TextView(this);
            tvId.setText(String.valueOf(p.getId()));
            tvId.setPadding(8,8,8,8);

            TextView tvNombre = new TextView(this);
            tvNombre.setText(p.getNombre());
            tvNombre.setPadding(8, 8, 8, 8);

            // Cantidad
            TextView tvCantidad = new TextView(this);
            tvCantidad.setText(String.valueOf(p.getCantidad()));
            tvCantidad.setPadding(8, 8, 8, 8);

            // Precio
            TextView tvPrecio = new TextView(this);
            tvPrecio.setText(String.valueOf(p.getPrecio()));
            tvPrecio.setPadding(8, 8, 8, 8);

            ImageButton btnDelete = new ImageButton(this);
            btnDelete.setImageResource(android.R.drawable.ic_menu_delete);
            btnDelete.setBackgroundColor(Color.TRANSPARENT);
            btnDelete.setColorFilter(Color.RED);

            TableRow.LayoutParams params = new TableRow.LayoutParams(100, 100);
            params.setMargins(8, 4, 8, 4);
            btnDelete.setLayoutParams(params);

            btnDelete.setOnClickListener(v -> {
                gestion.eliminarProducto(p.getId());
                tableProductos.removeView(fila);
                Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show();


            });





            fila.addView(tvId);
            fila.addView(tvNombre);
            fila.addView(tvCantidad);
            fila.addView(tvPrecio);
            tableProductos.addView(fila);
        }

    }

     */


}


