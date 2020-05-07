package com.example.sqliteandroid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AdaptadorEstudiante extends CursorAdapter {

    public AdaptadorEstudiante(Context context, Cursor cursor) {
        super(context, cursor, 1);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.itemestudiante, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tnombre = (TextView) view.findViewById(R.id.lnombre);
        TextView tapellido = (TextView) view.findViewById(R.id.lapellido);
        TextView tcodigo = (TextView) view.findViewById(R.id.lcodigo);
        TextView thora=(TextView) view.findViewById(R.id.lhora);
        TextView testudio=(TextView) view.findViewById(R.id.lestudio);
        // Extract properties from cursor
       String nombre  = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
        String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"));
        String codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
       String hora = cursor.getString(cursor.getColumnIndexOrThrow("hora"));
       String estudio = cursor.getString(cursor.getColumnIndexOrThrow("estudio"));
        // Populate fields with extracted properties

        tnombre.setText(nombre);
        tcodigo.setText(codigo);
        tapellido.setText(apellido);
        thora.setText(hora);
        testudio.setText(estudio);
    }
}
