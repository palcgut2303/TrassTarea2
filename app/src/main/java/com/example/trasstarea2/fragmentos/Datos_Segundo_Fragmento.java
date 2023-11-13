package com.example.trasstarea2.fragmentos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trasstarea2.R;
import com.example.trasstarea2.actividades.CrearTareas;
import com.example.trasstarea2.actividades.ListadoTareasActivity;
import com.example.trasstarea2.adaptadores.Adaptador;
import com.example.trasstarea2.datos.Tarea;
import com.example.trasstarea2.viewModel.TareaViewModel;

import java.util.ArrayList;
import java.util.List;


public class Datos_Segundo_Fragmento extends Fragment {

    private Button bt_volver,bt_guardar;
    private TextView tv_nombreTarea,tv_fechaCreacion,tv_fechaObjetivo,tv_descripcion;
    private CheckBox prioritaria;
    private Spinner progreso;
    private TareaViewModel compartirViewModel;
    private Observer<String> observadorTitulo;
    private Observer<String> observadorFechaCreacion;
    private Observer<String> observadorFechaObjetivo;
    private Observer<String> observadorProgreso;
    private Observer<Boolean> observadorPrioritaria;
    private Observer<String> observadorDescripcion;

    private String nombreTarea;
    private String fechaCreacion;
    private String fechaObjetivo;
    private boolean esPrioritaria;
    private String progresoSp;
    //int intProgreso = numProgreso(progresoSp)
    private String descripcion;



   public interface ComunicacionCrearTarea{
        void onBotonAgregarTarea();

    }

    private ComunicacionCrearTarea comunicador2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ComunicacionCrearTarea) {  //Si la Actividad implementa la interfaz de comunicación
            comunicador2 = (ComunicacionCrearTarea) context; //la Actividad se convierte en comunicador
        } else {
            throw new ClassCastException(context + " debe implementar interfaz de comunicación con el 1º fragmento");
        }
    }

    public Datos_Segundo_Fragmento() {
        // Required empty public constructor
    }

    public static Datos_Segundo_Fragmento newInstance(String param1, String param2) {
        Datos_Segundo_Fragmento fragment = new Datos_Segundo_Fragmento();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compartirViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);

        observadorTitulo = new Observer<String>() {
            @Override
            public void onChanged(String text) {
                nombreTarea = text;
            }
        };

        observadorFechaCreacion = new Observer<String>() {
            @Override
            public void onChanged(String text) {
                fechaCreacion = text;
            }
        };

        observadorFechaObjetivo = new Observer<String>() {
            @Override
            public void onChanged(String text) {
                fechaObjetivo = text;
            }
        };

        observadorProgreso = new Observer<String>() {
            @Override
            public void onChanged(String text) {
                progresoSp = text;
            }
        };

        observadorPrioritaria = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                esPrioritaria = aBoolean;
            }
        };

        observadorDescripcion = new Observer<String>() {
            @Override
            public void onChanged(String text) {
                descripcion = text;
            }
        };


        compartirViewModel.getTituloTarea().observe(this,observadorTitulo);
        compartirViewModel.getFechaCreacion().observe(this,observadorFechaCreacion);
        compartirViewModel.getFechaObjetivo().observe(this,observadorFechaObjetivo);
        compartirViewModel.getProgreso().observe(this,observadorProgreso);
        compartirViewModel.getPrioritaria().observe(this,observadorPrioritaria);
        compartirViewModel.getDescripcion().observe(this,observadorDescripcion);
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View main =  inflater.inflate(R.layout.fragment_segundo_fragmento, container, false);

       bt_volver = main.findViewById(R.id.bt_Volver);
       bt_volver.setOnClickListener(this::volver);
       bt_guardar = main.findViewById(R.id.bt_Guardar);
       bt_guardar.setOnClickListener(this::guardar);
       tv_descripcion = main.findViewById(R.id.tvMulti_Descripcion);
       return main;



    }



    private void guardar(View view) {

        /*nombreTarea = compartirViewModel.getTituloTarea().getValue();
        fechaCreacion = compartirViewModel.getFechaCreacion().getValue();
        fechaObjetivo = compartirViewModel.getFechaObjetivo().getValue();
        progresoSp = compartirViewModel.getProgreso().getValue();
        esPrioritaria = Boolean.TRUE.equals(compartirViewModel.getPrioritaria().getValue());
        descripcion = tv_descripcion.getText().toString();

        int numProgreso = numProgreso(progresoSp);


        Tarea miTarea = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
           if(esPrioritaria){
               miTarea = new Tarea(nombreTarea,descripcion,numProgreso,esPrioritaria,fechaCreacion,fechaObjetivo,R.drawable.baseline_star_24);
           }else{
               miTarea = new Tarea(nombreTarea,descripcion,numProgreso,esPrioritaria,fechaCreacion,fechaObjetivo,R.drawable.baseline_star_border_24);
           }


        }*/


        comunicador2.onBotonAgregarTarea();
        //comunicador2.onBotonAgregarTarea(miTarea);

        getActivity().finish();
    }



    public void volver(View view){
        requireActivity().getSupportFragmentManager().popBackStack();

        View fragmentContainer1 = requireActivity().findViewById(R.id.primer_fragment);
        View fragmentContainer2 = requireActivity().findViewById(R.id.segundo_fragment);

        fragmentContainer1.setVisibility(View.VISIBLE);
        fragmentContainer2.setVisibility(View.GONE);
    }



}