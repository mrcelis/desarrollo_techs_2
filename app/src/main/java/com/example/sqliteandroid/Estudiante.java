package com.example.sqliteandroid;

import android.os.Parcel;
import android.os.Parcelable;

public class Estudiante implements Parcelable {

    public String nombre;
    public String apellido;
    public String codigo;
    public String hora;
    public String estudio;




    public Estudiante(String nombre, String apellido, String codigo,String hora,String estudio) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
        this.hora=hora;
        this.estudio=estudio;

    }

    protected Estudiante(Parcel in) {

        this.nombre = in.readString();
        this.apellido = in.readString();
        this.codigo = in.readString();
        this.hora=in.readString();
        this.estudio=in.readString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHora(){return hora;}

    public void  setHora(String hora){this.hora=hora;}

    public  String getEstudio(){return estudio;}

    public void setEstudio(String estudio){this.estudio=estudio;}

    @Override
    public String toString() {
        return "Estudiante{" +

                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", codigo='" + codigo + '\'' +
                ", hora='"+ hora+ '\'' +
                ", estudio='"+ estudio+ '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeString(this.codigo);
        dest.writeString(this.hora);
        dest.writeString(this.estudio);
    }

    public static final Creator<Estudiante> CREATOR = new Creator<Estudiante>() {
        public Estudiante createFromParcel(Parcel source) {
            return new Estudiante(source);
        }
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };
}
