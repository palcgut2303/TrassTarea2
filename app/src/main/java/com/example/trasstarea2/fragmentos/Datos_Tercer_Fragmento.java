package com.example.trasstarea2.fragmentos;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

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

import com.example.trasstarea2.R;
import com.example.trasstarea2.datos.Tarea;
import com.example.trasstarea2.viewModel.TareaViewModel;

import java.util.ArrayList;


public class Datos_Tercer_Fragmento extends Fragment {


    private Button bt_siguiente;
    private TextView tv_nombreTarea,tv_fechaCreacion,tv_fechaObjetivo;
    private Spinner sp_progreso;
    private CheckBox checkBox;
    private TareaViewModel compartirViewModel;
    private ArrayList<String> progreso = new ArrayList<>();
    private Tarea tarea;

    public static Datos_Tercer_Fragmento newInstance(Tarea tarea) {
        Datos_Tercer_Fragmento fragmento = new Datos_Tercer_Fragmento();
        Bundle bundle = new Bundle();
        bundle.putParcelable("tareaEditable", tarea);
        fragmento.setArguments(bundle);
        return fragmento;
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

        View main = inflater.inflate(R.layout.fragment_tercer_fragmento, container, false);

        tv_nombreTarea = main.findViewById(R.id.tv_nombreTareaa2);

        tv_fechaCreacion = main.findViewById(R.id.tv_fechaCreacion2);

        tv_fechaCreacion.setOnClickListener(this::fechaCreacion);

        tv_fechaObjetivo = main.findViewById(R.id.tv_fechaObjetivo2);
        tv_fechaObjetivo.setOnClickListener(this::fechaObjetivo);
        bt_siguiente = main.findViewById(R.id.bt_siguiente2);
        sp_progreso = main.findViewById(R.id.sp_progreso2);
        checkBox = main.findViewById(R.id.checkBox2);
        main.findViewById(R.id.bt_cerrar2).setOnClickListener(v -> getActivity().finish());

        bt_siguiente.setOnClickListener(this::siguiente);


        ArrayAdapter<String> adaptadorProg = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item,progreso);
        adaptadorProg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_progreso.setAdapter(adaptadorProg);

       // tarea = (Tarea) getArguments().getString("tareaEditable");


       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_nombreTarea.setText(tarea.getTitulo());
            tv_fechaCreacion.setText(tarea.getFechaCreacion());
            tv_fechaObjetivo.setText(tarea.getFechaObjetivo());
            int numeroProgreso =  tarea.getProgreso();
            sp_progreso.setSelection(numeroProgreso);
            checkBox.setChecked(Boolean.TRUE.equals(tarea.isPrioritaria()));
        }*/

       /* tv_nombreTarea.setText(compartirViewModel.getTituloTarea().getValue());
        tv_fechaCreacion.setText(compartirViewModel.getFechaCreacion().getValue());
        tv_fechaObjetivo.setText(compartirViewModel.getFechaObjetivo().getValue());
        int index = 0;
        if(compartirViewModel.getProgreso().getValue() != null){
            if(compartirViewModel.getProgreso().getValue().equals("No iniciada")){
                index = 0;
            }else if (compartirViewModel.getProgreso().getValue().equals("Iniciada")){
                index = 1;
            }else if (compartirViewModel.getProgreso().getValue().equals("Avanzada")){
                index = 2;
            }else if(compartirViewModel.getProgreso().getValue().equals("Casi Finalizada")){
                index = 3;
            }else{
                index = 4;
            }
        }
        sp_progreso.setSelection(index);
        checkBox.setChecked(Boolean.TRUE.equals(compartirViewModel.getPrioritaria().getValue()));*/
        return  main;
    }

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


        /*compartirViewModel.setTituloTarea(tv_nombreTarea.getText().toString());
        compartirViewModel.setFechaCreacion(tv_fechaCreacion.getText().toString());
        compartirViewModel.setFechaObjetivo(tv_fechaObjetivo.getText().toString());
        compartirViewModel.setPrioritaria(checkBox.isChecked());
        compartirViewModel.setProgreso(sp_progreso.getSelectedItem().toString());*/

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cuarto_fragment,new Datos_Cuarto_Fragment()).addToBackStack(null).commit();

        View fragmentContainer1 = requireActivity().findViewById(R.id.tercer_fragment);
        View fragmentContainer2 = requireActivity().findViewById(R.id.cuarto_fragment);

        fragmentContainer1.setVisibility(View.GONE);
        fragmentContainer2.setVisibility(View.VISIBLE);


    }

}