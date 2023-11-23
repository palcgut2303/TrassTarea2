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
    private MutableLiveData<Integer> id;



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

    public void setDescripcion(String descripcioon) {
        if(descripcion == null){
            descripcion = new MutableLiveData<>();
        }
        this.descripcion.setValue(descripcioon);
    }

    public MutableLiveData<Integer> getId(){
        if(id == null){
            id = new MutableLiveData<>();
        }
        return id;
    }



    public void setId(int id){

        this.id.setValue(id);
    }

    public void setTituloTarea(String tituloTar) {
        if(tituloTarea == null){
            tituloTarea = new MutableLiveData<>();
        }
        tituloTarea.setValue(tituloTar);
    }

    public MutableLiveData<String> getFechaCreacion() {
        if(fechaCreacion == null){
            fechaCreacion = new MutableLiveData<>();
        }
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCre) {
        if(fechaCreacion == null){
            fechaCreacion = new MutableLiveData<>();
        }
        fechaCreacion.setValue(fechaCre);
    }

    public MutableLiveData<String> getFechaObjetivo() {
        if(fechaObjetivo == null){
            fechaObjetivo = new MutableLiveData<>();
        }
        return fechaObjetivo;
    }

    public void setFechaObjetivo(String fechaObj) {
        if(fechaObjetivo == null){
            fechaObjetivo = new MutableLiveData<>();
        }
        fechaObjetivo.setValue(fechaObj);
    }

    public MutableLiveData<String> getProgreso() {
        if(progreso == null){
            progreso = new MutableLiveData<>();
        }
        return progreso;
    }

    public void setProgreso(String progre) {
        if(progreso == null){
            progreso = new MutableLiveData<>();
        }
        progreso.setValue(progre);
    }

    public MutableLiveData<Boolean> getPrioritaria() {
        if(prioritaria == null){
            prioritaria = new MutableLiveData<>();
        }
        return prioritaria;
    }

    public void setPrioritaria(boolean priori) {
        if(prioritaria == null){
            prioritaria = new MutableLiveData<>();
        }
        prioritaria.setValue(priori);
    }

}
