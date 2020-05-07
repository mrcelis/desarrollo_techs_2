package com.example.sqliteandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Spinner SptipoCarro, SpEstudio;
    EditText edtcodigo, edtapellido, etTipoVe, edthora, edtestudio;
    Button guardar, actualizar, consultar ,seleccionar,seleccionar2, buscar;
    Estudiante est;
    EstudianteController c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtcodigo = findViewById(R.id.edtcodigo);
        etTipoVe = findViewById(R.id.etTipoVe);
        edtapellido = findViewById(R.id.edtapellido);
        edthora = findViewById(R.id.edthora);
        edtestudio = findViewById(R.id.edtestudio);
        edtestudio.setKeyListener(null);
        etTipoVe.setKeyListener(null);


        guardar = findViewById(R.id.btnguardar);
        actualizar = findViewById(R.id.btnactualizar);
        consultar = findViewById(R.id.btnconsultar);
        seleccionar = findViewById(R.id.btnTipoVe);
        seleccionar2 = findViewById(R.id.btnseleccionar2);
        buscar = findViewById(R.id.btnbuscar);


        Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();
        c = new EstudianteController(getApplicationContext());
        SptipoCarro = findViewById(R.id.spinner);
        String[] opciones = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        SptipoCarro.setAdapter(adapter);

        SpEstudio = findViewById(R.id.spinner2);
        String[] opciones1 = {"Bachiller", "Pregrado", "Postgrado", "Maestria", "Doctorado"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones1);
        SpEstudio.setAdapter(adapter1);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                est = new Estudiante(etTipoVe.getText().toString(), edtapellido.getText().toString(), edtcodigo.getText().toString(), edthora.getText().toString(), edtestudio.getText().toString());
                Toast.makeText(getApplicationContext(), est.codigo, Toast.LENGTH_SHORT).show();
                if (!c.buscarEstudiante(est.codigo)) {
                    Log.d("Buscar", "No encontrado");
                    long id = c.agregarEstudiante(est);
                    Toast.makeText(getApplicationContext(), "Persona registrado", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Buscar", "encontrado");
                    Toast.makeText(getApplicationContext(), "Persona ya esta registrado", Toast.LENGTH_SHORT).show();
                }

            }


        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                est = new Estudiante(etTipoVe.getText().toString(), edtapellido.getText().toString(), edtcodigo.getText().toString(), edthora.getText().toString(), edtestudio.getText().toString());
                if (c.buscarEstudiante(est.codigo)) {
                    Log.e("Buscar", "Entro");
                    c.actualizarEstudiante(est);
                    Toast.makeText(getApplicationContext(), "Persona actualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Persona no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = c.allEstudiantes();

                String cadena = "";
                while (cur.moveToNext()) {
                    cadena = cadena + cur.getString(0) + " " + cur.getString(1) + " " + cur.getString(2) + " " + cur.getString(3) + " " + "\n";

                }
                if (cur.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No hay personas", Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ListadoEstudiantes.class);
                    startActivity(i);
                }
            }
        });
        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ValorApagar = null;
                String seleccion = SptipoCarro.getSelectedItem().toString();
                if (seleccion.equals("1")) {
                    ValorApagar = "1";
                } else if (seleccion.equals("2")) {
                    ValorApagar = "2";
                } else if (seleccion.equals("3")) {
                    ValorApagar = "3";
                } else if (seleccion.equals("4")) {
                    ValorApagar = "4";
                } else if (seleccion.equals("5")) {
                    ValorApagar = "5";
                }
                etTipoVe.setText(ValorApagar + "");
            }
        });


        seleccionar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ValorApagar = null;
                String seleccion = SpEstudio.getSelectedItem().toString();
                if (seleccion.equals("Bachiller")) {
                    ValorApagar = "Bachiller";
                } else if (seleccion.equals("Pregrado")) {
                    ValorApagar = "Pregrado";
                } else if (seleccion.equals("Postgrado")) {
                    ValorApagar = "Postgrado";
                } else if (seleccion.equals("Maestria")) {
                    ValorApagar = "Maestria";
                } else if (seleccion.equals("Doctorado")) {
                    ValorApagar = "Doctorado";
                }
                edtestudio.setText(ValorApagar + "");
            }
        });


    }

                    public void buscar(View view) {
                        BaseDatos bd = new BaseDatos(getApplicationContext());
                        SQLiteDatabase BasedeDatos = bd.getWritableDatabase();

                        String codigo = edtcodigo.getText().toString();


                            String[] args = new String[]{codigo};
                            Cursor c = BasedeDatos.query(DefDB.tabla_est, null, "codigo=?", args, null, null, null);

                            if (c.moveToFirst()) {
                                edtapellido.setText(c.getString(2));
                                etTipoVe.setText(c.getString(1));
                                edthora.setText(c.getString(3));
                                edtestudio.setText(c.getString(4));
                                BasedeDatos.close();
                                }else{
                                  Toast.makeText(this, "No existe la persona", Toast.LENGTH_SHORT).show();
                                 BasedeDatos.close();
                            }

                        }


                    }





