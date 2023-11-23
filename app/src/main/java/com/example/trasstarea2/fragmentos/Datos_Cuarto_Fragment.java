package com.example.trasstarea2.fragmentos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.example.trasstarea2.R;
import com.example.trasstarea2.viewModel.TareaViewModel;


public class Datos_Cuarto_Fragment extends Fragment {

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



    public interface ComunicacionEditarTarea{
        void onBotonGuardarTareaEditada();


    }

    private Datos_Cuarto_Fragment.ComunicacionEditarTarea comunicador2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Datos_Cuarto_Fragment.ComunicacionEditarTarea) {  //Si la Actividad implementa la interfaz de comunicación
            comunicador2 = (Datos_Cuarto_Fragment.ComunicacionEditarTarea) context; //la Actividad se convierte en comunicador
        } else {
            throw new ClassCastException(context + " debe implementar interfaz de comunicación con el 3º fragmento");
        }
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
        compartirViewModel.getPrioritaria().observe(this,observadorPrioritaria);
        compartirViewModel.getDescripcion().observe(this,observadorDescripcion);
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View main =  inflater.inflate(R.layout.fragment_cuarto_fragment, container, false);

        bt_volver = main.findViewById(R.id.bt_Volver2);
        bt_volver.setOnClickListener(this::volver);
        bt_guardar = main.findViewById(R.id.bt_Guardar2);
        bt_guardar.setOnClickListener(this::guardar);
        tv_descripcion = main.findViewById(R.id.tvMulti_Descripcion2);

        tv_descripcion.setText(compartirViewModel.getDescripcion().getValue());



        return main;
    }



    private void guardar(View view) {

        if(compartirViewModel.getTituloTarea().getValue().length() > 0 && compartirViewModel.getFechaObjetivo().getValue().length() > 0 &&
                compartirViewModel.getFechaCreacion().getValue().length()>0 && tv_descripcion.getText().length()>0)
        {
            compartirViewModel.setDescripcion(tv_descripcion.getText().toString());
            comunicador2.onBotonGuardarTareaEditada();


            getActivity().finish();

        }else{
            Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

    }



    public void volver(View view){
        compartirViewModel.setDescripcion(tv_descripcion.getText().toString());

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor, new Datos_Tercer_Fragmento())
                .commit();

       /* View fragmentContainer1 = requireActivity().findViewById(R.id.tercer_fragment);
        View fragmentContainer2 = requireActivity().findViewById(R.id.cuarto_fragment);

        fragmentContainer1.setVisibility(View.VISIBLE);
        fragmentContainer2.setVisibility(View.GONE);*/




    }


}