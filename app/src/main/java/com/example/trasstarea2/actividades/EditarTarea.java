package com.example.trasstarea2.actividades;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.trasstarea2.R;
import com.example.trasstarea2.datos.Tarea;
import com.example.trasstarea2.fragmentos.Datos_Tercer_Fragmento;
import com.example.trasstarea2.viewModel.TareaViewModel;

public class EditarTarea  extends AppCompatActivity {
    TareaViewModel tareaViewModel;

    Datos_Tercer_Fragmento tercerFragmento;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_tarea);
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);



        /*savedInstanceState = getIntent().getExtras();
        Tarea tareaEditable = savedInstanceState.getParcelable("tareaEditable");
        tercerFragmento = Datos_Tercer_Fragmento.newInstance(tareaEditable);

        tercerFragmento.setArguments(savedInstanceState);*/

        tercerFragmento = new Datos_Tercer_Fragmento();

        Bundle bundle = getIntent().getExtras();
        Tarea tareaEditable = bundle.getParcelable("tareaEditable");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tareaViewModel.setTituloTarea(tareaEditable.getTitulo());
            tareaViewModel.setDescripcion(tareaEditable.getDescripcion());
            tareaViewModel.setFechaCreacion(tareaEditable.getFechaCreacion());
            tareaViewModel.setFechaObjetivo(tareaEditable.getFechaObjetivo());
            tareaViewModel.setPrioritaria(tareaEditable.isPrioritaria());
            String progresoString =  "";
            int progresoTarea = tareaEditable.getProgreso();
            if(progresoTarea == 0){
                progresoString = "No iniciada";
            }else if(progresoTarea == 25){
                progresoString = "Iniciada";
            }else if(progresoTarea == 50){
                progresoString = "Avanzada";
            }else if(progresoTarea == 75){
                progresoString = "Casi Finalizada";
            }else{
                progresoString = "Finalizada";
            }
            tareaViewModel.setProgreso(progresoString);

        }


        getSupportFragmentManager().beginTransaction().replace(R.id.tercer_fragment,tercerFragmento).addToBackStack(null).commit();






    }
}
