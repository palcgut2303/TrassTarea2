package com.example.trasstarea2.datos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Tarea {

    private String titulo, descripcion;
    private int progreso;
    private boolean prioritaria;
   private String fechaCreacion, fechaObjetivo;
    private long diasRestantes2;
    private int foto;

    public Tarea(String titulo, String descripcion,int progreso, boolean prioritaria,String fechaCreacion,String fechaObjetivo,int foto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.progreso = progreso;
        this.diasRestantes2 = calcularDiasRestantes(fechaCreacion,fechaObjetivo);
        this.fechaCreacion = fechaCreacion;
        this.fechaObjetivo = fechaObjetivo;
        this.prioritaria = prioritaria;
        this.foto = foto;
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




}
