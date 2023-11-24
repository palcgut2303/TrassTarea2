package com.example.trasstarea2.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trasstarea2.R;
import com.example.trasstarea2.datos.Tarea;
import com.example.trasstarea2.fragmentos.Datos_Primer_Fragmento;
import com.example.trasstarea2.fragmentos.Datos_Segundo_Fragmento;
import com.example.trasstarea2.viewModel.TareaViewModel;

public class CrearTareas extends AppCompatActivity implements Datos_Segundo_Fragmento.ComunicacionCrearTarea {

    Datos_Primer_Fragmento fragmentoUno;

    TareaViewModel tareaViewModel;

    private String nombreTarea;
    private String fechaCreacion;
    private String fechaObjetivo;
    private boolean esPrioritaria;
    private String progresoSp;

    private String descripcion;

    private int contadorId = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.crear_tarea);
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);

        if (savedInstanceState==null)
            fragmentoUno = new Datos_Primer_Fragmento();
        else
            fragmentoUno = (Datos_Primer_Fragmento) getSupportFragmentManager().getFragment(savedInstanceState, "crearplanfragment");


        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmentoUno).addToBackStack(null).commit();

    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "crearplanfragment", fragmentoUno);

    }



    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fragmentoUno = new Datos_Primer_Fragmento();

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmentoUno).addToBackStack(null).commit();
    }

    @Override
    public void onBotonAgregarTarea() {
        nombreTarea = tareaViewModel.getTituloTarea().getValue();
        fechaCreacion = tareaViewModel.getFechaCreacion().getValue();
        fechaObjetivo = tareaViewModel.getFechaObjetivo().getValue();
        progresoSp = tareaViewModel.getProgreso().getValue();
        esPrioritaria = Boolean.TRUE.equals(tareaViewModel.getPrioritaria().getValue());
        descripcion = tareaViewModel.getDescripcion().getValue();

        int numProgreso = numProgreso(progresoSp);

        int idRandom = (int) (Math.random() * ((100 - 1) + 1)) + 1;


        Tarea miTarea = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if(esPrioritaria){
                miTarea = new Tarea(idRandom,nombreTarea,descripcion,numProgreso,esPrioritaria,fechaCreacion,fechaObjetivo,R.drawable.baseline_star_24);
            }else{
                miTarea = new Tarea(idRandom,nombreTarea,descripcion,numProgreso,esPrioritaria,fechaCreacion,fechaObjetivo,R.drawable.baseline_star_border_24);
            }

            Intent intentVolver = new Intent();
            intentVolver.putExtra("TareaDevuelta",miTarea);
            setResult(RESULT_OK, intentVolver);
            finish();

        }
    }

    private int numProgreso(String progresoSp) {

        if(progresoSp.equalsIgnoreCase("No iniciada")){
            return 0;
        }else if(progresoSp.equalsIgnoreCase("Iniciada")){
            return 25;
        } else if (progresoSp.equalsIgnoreCase("Avanzada")) {
            return 50;
        } else if (progresoSp.equalsIgnoreCase("Casi Finalizada")) {
            return 75;
        }else{
            return 100;
        }

    }


}
