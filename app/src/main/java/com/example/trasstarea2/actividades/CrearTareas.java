package com.example.trasstarea2.actividades;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.trasstarea2.R;
import com.example.trasstarea2.datos.Tarea;
import com.example.trasstarea2.fragmentos.Datos_Primer_Fragmento;
import com.example.trasstarea2.fragmentos.Datos_Segundo_Fragmento;
import com.example.trasstarea2.viewModel.TareaViewModel;

import java.util.ArrayList;

public class CrearTareas extends AppCompatActivity implements Datos_Segundo_Fragmento.ComunicacionCrearTarea {

    Datos_Primer_Fragmento fragmentoUno;

    //Datos_Segundo_Fragmento fragmentoDos;

    TareaViewModel tareaViewModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_tarea);
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);

        fragmentoUno = new Datos_Primer_Fragmento();

        //fragmentoDos = new Datos_Segundo_Fragmento();

        getSupportFragmentManager().beginTransaction().replace(R.id.primer_fragment,fragmentoUno).addToBackStack(null).commit();

        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.primer_fragment, new Datos_Primer_Fragmento());
        fragmentTransaction.add(R.id.segundo_fragment, new Datos_Segundo_Fragmento());
        fragmentTransaction.commit();*/



    }

    @Override
    public void onBotonAgregarTarea(Tarea tarea) {

        tareaViewModel.agregarTarea(tarea);


    }


    public ArrayList<Tarea> onListarTareas() {
        return tareaViewModel.getListaTareas();
    }


}
