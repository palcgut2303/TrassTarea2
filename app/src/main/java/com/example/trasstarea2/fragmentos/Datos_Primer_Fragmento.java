package com.example.trasstarea2.fragmentos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trasstarea2.R;
import com.example.trasstarea2.viewModel.TareaViewModel;

import java.util.ArrayList;

public class Datos_Primer_Fragmento extends Fragment {

    private Button bt_siguiente;
    private TextView tv_nombreTarea,tv_fechaCreacion,tv_fechaObjetivo;
    private Spinner sp_progreso;
    private CheckBox checkBox;
    private TareaViewModel compartirViewModel;
    private ArrayList<String> progreso = new ArrayList<>();

    private String nombreTarea;



    public Datos_Primer_Fragmento() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgresoSpinner();

        compartirViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View main = inflater.inflate(R.layout.fragment_primer_fragmento, container, false);

        tv_nombreTarea = main.findViewById(R.id.tv_nombreTareaa);
        tv_fechaCreacion = main.findViewById(R.id.tv_fechaCreacion);
        tv_fechaCreacion.setOnClickListener(this::fechaCreacion);
        tv_fechaObjetivo = main.findViewById(R.id.tv_fechaObjetivo);
        tv_fechaObjetivo.setOnClickListener(this::fechaObjetivo);
        bt_siguiente = main.findViewById(R.id.bt_siguiente);
        sp_progreso = main.findViewById(R.id.sp_progreso);
        checkBox = main.findViewById(R.id.checkBox);
        main.findViewById(R.id.bt_cerrar).setOnClickListener(v -> getActivity().finish());
        bt_siguiente.setOnClickListener(this::siguiente);


        ArrayAdapter<String> adaptadorProg = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item,progreso);
        adaptadorProg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_progreso.setAdapter(adaptadorProg);


            /*tv_nombreTarea.setText(savedInstanceState.getString("nombreTarea"));
            tv_fechaCreacion.setText(savedInstanceState.getString("fechaCreacion"));
            tv_fechaObjetivo.setText(savedInstanceState.getString("fechaObjetivo"));
            String progreso = savedInstanceState.getString("progreso");
            int index = 0;
            switch (progreso){
                case "No iniciada":
                    index = 0;
                    break;
                case "Iniciada":
                    index = 1;
                    break;
                case "Avanzada":
                    index = 2;
                    break;
                case "Casi Finalizada":
                    index = 3;
                    break;
                case "Finalizada:":
                    index = 4;
                    break;
            }
            checkBox.setChecked(savedInstanceState.getBoolean("prioritaria"));*/

            tv_nombreTarea.setText(compartirViewModel.getTituloTarea().getValue());
            tv_fechaCreacion.setText(compartirViewModel.getFechaCreacion().getValue());
            tv_fechaObjetivo.setText(compartirViewModel.getFechaObjetivo().getValue());
            int index = 0;
            if (compartirViewModel.getProgreso().getValue() != null) {
                if (compartirViewModel.getProgreso().getValue().equals("No iniciada")) {
                    index = 0;
                } else if (compartirViewModel.getProgreso().getValue().equals("Iniciada")) {
                    index = 1;
                } else if (compartirViewModel.getProgreso().getValue().equals("Avanzada")) {
                    index = 2;
                } else if (compartirViewModel.getProgreso().getValue().equals("Casi Finalizada")) {
                    index = 3;
                } else {
                    index = 4;
                }
            }

            sp_progreso.setSelection(index);
            checkBox.setChecked(Boolean.TRUE.equals(compartirViewModel.getPrioritaria().getValue()));

           if(savedInstanceState != null){
                String nombreTarea = savedInstanceState.getString("nombreTarea");
                String fechaCreacion = savedInstanceState.getString("fechaCreacion");
                String fechaObjetivo = savedInstanceState.getString("fechaObjetivo");
                String progreso2 = savedInstanceState.getString("progreso");
                boolean prioritaria = savedInstanceState.getBoolean("prioritaria");

                tv_nombreTarea.setText(nombreTarea);
                tv_fechaCreacion.setText(fechaCreacion);
                tv_fechaObjetivo.setText(fechaObjetivo);
                index = 0;
                switch (progreso2){
                    case "No iniciada":
                        index = 0;
                        break;
                    case "Iniciada":
                        index = 1;
                        break;
                    case "Avanzada":
                        index = 2;
                        break;
                    case "Casi Finalizada":
                        index = 3;
                        break;
                    case "Finalizada:":
                        index = 4;
                        break;
                }
                sp_progreso.setSelection(index);
                checkBox.setChecked(prioritaria);
            }


        return  main;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        compartirViewModel.setTituloTarea(tv_nombreTarea.getText().toString());
        compartirViewModel.setFechaCreacion(tv_fechaCreacion.getText().toString());
        compartirViewModel.setFechaObjetivo(tv_fechaObjetivo.getText().toString());
        compartirViewModel.setProgreso(sp_progreso.getSelectedItem().toString());
        compartirViewModel.setPrioritaria(checkBox.isChecked());

        outState.putString("nombreTarea",tv_nombreTarea.getText().toString());
        outState.putString("fechaCreacion",tv_fechaCreacion.getText().toString());
        outState.putString("fechaObjetivo",tv_fechaObjetivo.getText().toString());
        outState.putString("progreso",sp_progreso.getSelectedItem().toString());
        outState.putBoolean("prioritaria", checkBox.isChecked());


    }

   /* @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            tv_nombreTarea.setText(savedInstanceState.getString("nombreTarea"));
            tv_fechaCreacion.setText(savedInstanceState.getString("fechaCreacion"));
            tv_fechaObjetivo.setText(savedInstanceState.getString("fechaObjetivo"));
            String progreso = savedInstanceState.getString("progreso");
            int index = 0;
            switch (progreso){
                case "No iniciada":
                    index = 0;
                    break;
                case "Iniciada":
                    index = 1;
                    break;
                case "Avanzada":
                    index = 2;
                    break;
                case "Casi Finalizada":
                    index = 3;
                    break;
                case "Finalizada:":
                    index = 4;
                    break;
            }
            checkBox.setChecked(savedInstanceState.getBoolean("prioritaria"));
        }


    }*/

    private void fechaCreacion(View view) {
            if(view.getId()==R.id.tv_fechaCreacion){
                showDatePickerDialogFechaCrea();
            }
    }

    private void fechaObjetivo(View view) {
        if(view.getId()==R.id.tv_fechaObjetivo){
            showDatePickerDialogFechaObj();
        }
    }
    private void showDatePickerDialogFechaCrea() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = year + " / " + (month+1) + " / " + day;
                tv_fechaCreacion.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void showDatePickerDialogFechaObj() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = year + " / " + (month+1) + " / " + day;
                tv_fechaObjetivo.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void initProgresoSpinner() {

        progreso.add("No iniciada");
        progreso.add("Iniciada");
        progreso.add("Avanzada");
        progreso.add("Casi Finalizada");
        progreso.add("Finalizada");

    }

    public void siguiente(View view){


        compartirViewModel.setTituloTarea(tv_nombreTarea.getText().toString());
        compartirViewModel.setFechaCreacion(tv_fechaCreacion.getText().toString());
        compartirViewModel.setFechaObjetivo(tv_fechaObjetivo.getText().toString());
        compartirViewModel.setPrioritaria(checkBox.isChecked());
        compartirViewModel.setProgreso(sp_progreso.getSelectedItem().toString());




        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor,new Datos_Segundo_Fragmento())
                .addToBackStack(null)
                .commit();

        /*View fragmentContainer1 = requireActivity().findViewById(R.id.primer_fragment);
        View fragmentContainer2 = requireActivity().findViewById(R.id.segundo_fragment);

        fragmentContainer1.setVisibility(View.GONE);
        fragmentContainer2.setVisibility(View.VISIBLE);*/






    }


}