package com.example.trasstarea2.viewModel;

import android.os.Build;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.trasstarea2.datos.Tarea;

import java.util.ArrayList;
import java.util.List;

public class TareaViewModel extends ViewModel {

    private MutableLiveData<String> tituloTarea,fechaCreacion,fechaObjetivo,descripcion;
    private MutableLiveData<String> progreso;
    private MutableLiveData<Boolean> prioritaria;

    private ArrayList<Tarea> listaTareas = new ArrayList<>();

    public MutableLiveData<String> getTituloTarea() {
        if(tituloTarea == null){
            tituloTarea = new MutableLiveData<>();
        }
        return tituloTarea;
    }

    public MutableLiveData<String> getDescripcion() {
        if(descripcion == null){
            descripcion = new MutableLiveData<>();
        }
        return descripcion;
    }

    public void setDescripcion(MutableLiveData<String> descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Tarea> getListaTareas(){
        if(listaTareas == null){
            listaTareas = new ArrayList<>();
        }

        return listaTareas;
    }

    public void setListaTareas(ArrayList<Tarea> listaTareas) {
        this.listaTareas = getListaTareas();
    }

    public void agregarTarea(Tarea nuevaTarea){
        listaTareas.add(nuevaTarea);
    }

    public void setTituloTarea(String tituloTar) {
        tituloTarea.setValue(tituloTar);
    }

    public MutableLiveData<String> getFechaCreacion() {
        if(fechaCreacion == null){
            fechaCreacion = new MutableLiveData<>();
        }
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCre) {
        fechaCreacion.setValue(fechaCre);
    }

    public MutableLiveData<String> getFechaObjetivo() {
        if(fechaObjetivo == null){
            fechaObjetivo = new MutableLiveData<>();
        }
        return fechaObjetivo;
    }

    public void setFechaObjetivo(String fechaObj) {
        fechaObjetivo.setValue(fechaObj);
    }

    public MutableLiveData<String> getProgreso() {
        if(progreso == null){
            progreso = new MutableLiveData<>();
        }
        return progreso;
    }

    public void setProgreso(String progre) {
        progreso.setValue(progre);
    }

    public MutableLiveData<Boolean> getPrioritaria() {
        if(prioritaria == null){
            prioritaria = new MutableLiveData<>();
        }
        return prioritaria;
    }

    public void setPrioritaria(boolean priori) {
        prioritaria.setValue(priori);
    }

}