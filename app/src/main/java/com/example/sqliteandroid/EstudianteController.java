package com.example.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EstudianteController {

    private BaseDatos db;

    public EstudianteController(Context context) {
        this.db = new BaseDatos(context);
    }

    public long agregarEstudiante(Estudiante e){
       try{

           SQLiteDatabase database = db.getWritableDatabase();
           ContentValues valores = new ContentValues();
           valores.put(DefDB.col_codigo,e.codigo);
           valores.put(DefDB.col_nombre,e.nombre);
           valores.put(DefDB.col_apellido,e.apellido);
           valores.put(DefDB.col_hora,e.hora);
           valores.put(DefDB.col_estudio,e.estudio);
           long id = database.insert(DefDB.tabla_est,null,valores);
           return id;
       }
        catch (Exception ex){
           System.out.println("Error al insertar");
           return 0;
        }
    }

    public boolean buscarEstudiante(String cod){
        String[] args = new String[] {cod};
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_est, null, "codigo=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}

    }



    public long actualizarEstudiante(Estudiante e){

        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            String[] args = new String[] {e.codigo};
            valores.put(DefDB.col_estudio,e.estudio);
            valores.put(DefDB.col_hora,e.hora);
            valores.put(DefDB.col_nombre,e.nombre);
            valores.put(DefDB.col_apellido,e.apellido);
            long id = database.update(DefDB.tabla_est, valores,"codigo=?",args);
            database.close();
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }
    public Cursor allEstudiantes() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select * from estudiante", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }

    public Cursor allEstudiantes1() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select nombre, apellido, codigo as _id, hora, estudio from estudiante", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }


    public long delete(Estudiante e){

        try{
            SQLiteDatabase database = db.getWritableDatabase();
            String[] args = new String[] {e.codigo};
            long id = database.delete(DefDB.tabla_est, "codigo=?",args);
            database.close();
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al borrar");
            return 0;
        }



    }

}
