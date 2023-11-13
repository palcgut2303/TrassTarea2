package com.example.trasstarea2.datos;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Tarea implements Parcelable {

    private String titulo, descripcion;
    private int progreso;
    private boolean prioritaria;
   private String fechaCreacion, fechaObjetivo;
    private long diasRestantes2;
    private int foto;

    private int id;

    public Tarea(int id,String titulo, String descripcion,int progreso, boolean prioritaria,String fechaCreacion,String fechaObjetivo,int foto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.progreso = progreso;
        this.diasRestantes2 = calcularDiasRestantes(fechaCreacion,fechaObjetivo);
        this.fechaCreacion = fechaCreacion;
        this.fechaObjetivo = fechaObjetivo;
        this.prioritaria = prioritaria;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public boolean isPrioritaria() {
        return prioritaria;
    }

    public void setPrioritaria(boolean prioritaria) {
        this.prioritaria = prioritaria;
    }


    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaObjetivo() {
        return fechaObjetivo;
    }

    public void setFechaObjetivo(String fechaObjetivo) {
        this.fechaObjetivo = fechaObjetivo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long calcularDiasRestantes(String fechaCreacion, String fechaObjetivo) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy / MM / dd");
        DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("yyyy / MM / d");
        // Parsea las fechas a objetos LocalDate
        LocalDate fechaInicial;
        try {
            fechaInicial = LocalDate.parse(fechaCreacion, formato);
        }catch (Exception io){
            fechaInicial = LocalDate.parse(fechaCreacion, formato2);
        }

        LocalDate fechaFinal;
        try{
            fechaFinal = LocalDate.parse(fechaObjetivo, formato);
        }catch (Exception io){
            fechaFinal = LocalDate.parse(fechaObjetivo, formato2);
        }

        // Calcula la diferencia en días
        long diasRestantes = ChronoUnit.DAYS.between(fechaInicial, fechaFinal);

        return diasRestantes;
    }

    public long getDiasRestantes2() {

        return diasRestantes2;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.titulo);
        dest.writeString(this.descripcion);
        dest.writeInt(this.progreso);
        dest.writeByte(this.prioritaria ? (byte) 1 : (byte) 0);
        dest.writeString(this.fechaCreacion);
        dest.writeString(this.fechaObjetivo);
        dest.writeLong(this.diasRestantes2);
        dest.writeInt(this.foto);
    }

    public void readFromParcel(Parcel source) {
        this.titulo = source.readString();
        this.descripcion = source.readString();
        this.progreso = source.readInt();
        this.prioritaria = source.readByte() != 0;
        this.fechaCreacion = source.readString();
        this.fechaObjetivo = source.readString();
        this.diasRestantes2 = source.readLong();
        this.foto = source.readInt();
    }

    protected Tarea(Parcel in) {
        this.titulo = in.readString();
        this.descripcion = in.readString();
        this.progreso = in.readInt();
        this.prioritaria = in.readByte() != 0;
        this.fechaCreacion = in.readString();
        this.fechaObjetivo = in.readString();
        this.diasRestantes2 = in.readLong();
        this.foto = in.readInt();
    }

    public static final Parcelable.Creator<Tarea> CREATOR = new Parcelable.Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel source) {
            return new Tarea(source);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarea)) return false;
        Tarea tarea = (Tarea) o;
        return id == tarea.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
