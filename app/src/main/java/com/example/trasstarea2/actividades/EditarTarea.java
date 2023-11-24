package com.example.trasstarea2.actividades;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.trasstarea2.R;
import com.example.trasstarea2.datos.Tarea;
import com.example.trasstarea2.fragmentos.Datos_Cuarto_Fragment;
import com.example.trasstarea2.fragmentos.Datos_Tercer_Fragmento;
import com.example.trasstarea2.viewModel.TareaViewModel;

public class EditarTarea  extends AppCompatActivity implements Datos_Cuarto_Fragment.ComunicacionEditarTarea {
    TareaViewModel tareaViewModel;

    Datos_Tercer_Fragmento tercerFragmento;

    private String nombreTarea;
    private String fechaCreacion;
    private String fechaObjetivo;
    private boolean esPrioritaria;
    private String progresoSp;

    private String descripcion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.editar_tarea);
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);


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


        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,tercerFragmento).addToBackStack(null).commit();






    }

    @Override
    public void onBotonGuardarTareaEditada() {
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
            if (esPrioritaria) {
                miTarea = new Tarea(idRandom, nombreTarea, descripcion, numProgreso, esPrioritaria, fechaCreacion, fechaObjetivo, R.drawable.baseline_star_24);
            } else {
                miTarea = new Tarea(idRandom, nombreTarea, descripcion, numProgreso, esPrioritaria, fechaCreacion, fechaObjetivo, R.drawable.baseline_star_border_24);
            }
        }

        Intent intentVolver = new Intent();
        intentVolver.putExtra("TareaDevueltaEditada",miTarea);
        setResult(RESULT_OK, intentVolver);
        finish();
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
