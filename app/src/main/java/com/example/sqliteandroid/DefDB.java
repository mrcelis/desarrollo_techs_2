package com.example.sqliteandroid;

public class DefDB {

    public static final String nameDb = "Universidad";
    public static final String tabla_est = "Estudiante";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_apellido = "apellido";
    public static final String col_hora= "hora";
    public static final String col_estudio="estudio";


    public static final String create_tabla_est = "CREATE TABLE IF NOT EXISTS Estudiante(" +
            "  codigo text primary key," +
            "  nombre text," +
            "  apellido text," +
            "  hora text," +
            "  estudio text" +
            ");";

    }
