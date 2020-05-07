package com.example.sqliteandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListadoEstudiantes extends AppCompatActivity {
    EstudianteController c;
    ListView listado;
    ArrayList<Estudiante> arreglo = new ArrayList<Estudiante>();
    Estudiante e;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_estudiantes);
        listado = findViewById(R.id.lstestudiante);
        c = new EstudianteController(getApplicationContext());
        Cursor cur = c.allEstudiantes1();
        while (cur.moveToNext()){
           e = new Estudiante(cur.getString(0),cur.getString(1),cur.getString(2 ),cur.getString(3), cur.getString(4));
           arreglo.add(e);
        }
        AdaptadorEstudiante adaptador = new AdaptadorEstudiante(this, cur);
        registerForContextMenu(listado);
        listado.setAdapter(adaptador);
        adaptador.changeCursor(cur);
        adaptador.notifyDataSetChanged();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)menuInfo;

        menu.setHeaderTitle(
                listado.getAdapter().getItem(info.position).toString());

        inflater.inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itEliminar:
                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                borrar(arreglo.get(info.position));
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }
    private void borrar(Estudiante e){
        c = new EstudianteController(getApplicationContext());
        c.delete(e);
        Toast.makeText(getApplicationContext(), "Persona Borrado", Toast.LENGTH_SHORT).show();

    }

}
