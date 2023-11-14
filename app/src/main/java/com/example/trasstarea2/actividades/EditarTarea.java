package com.example.trasstarea2.actividades;

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



        savedInstanceState = getIntent().getExtras();
        Tarea tareaEditable = savedInstanceState.getParcelable("tareaEditable");
        tercerFragmento = Datos_Tercer_Fragmento.newInstance(tareaEditable);

        tercerFragmento.setArguments(savedInstanceState);


        getSupportFragmentManager().beginTransaction().replace(R.id.tercer_fragment,tercerFragmento).addToBackStack(null).commit();






    }
}
