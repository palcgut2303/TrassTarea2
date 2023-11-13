package com.example.trasstarea2.fragmentos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
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

public class Datos_Primer_Fragmento extends Fragment {

    private Button bt_siguiente;
    private TextView tv_nombreTarea,tv_fechaCreacion,tv_fechaObjetivo;
    private Spinner sp_progreso;
    private CheckBox checkBox;
    private TareaViewModel compartirViewModel;
    private ArrayList<String> progreso = new ArrayList<>();

   /* public interface OnDatosTareaListener{
        void onDatosTareaIngresados(Tarea tarea);
    }

    private OnDatosTareaListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDatosTareaListener) {
            listener = (OnDatosTareaListener) context;
        } else {
            throw new ClassCastException(context.toString() + " debe implementar OnDatosTareaListener");
        }
    }*/


    public Datos_Primer_Fragmento() {
        // Required empty public constructor
    }

   /* public static Datos_Primer_Fragmento newInstance(String param1, String param2) {
        Datos_Primer_Fragmento fragment = new Datos_Primer_Fragmento();

        return fragment;
    }*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgresoSpinner();

        compartirViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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


        return  main;
    }



    private void cerrar(View view) {
        getActivity().finish();
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


        compartirViewModel.setTituloTarea(tv_nombreTarea.getText().toString());
        compartirViewModel.setFechaCreacion(tv_fechaCreacion.getText().toString());
        compartirViewModel.setFechaObjetivo(tv_fechaObjetivo.getText().toString());
        compartirViewModel.setPrioritaria(checkBox.isChecked());
        compartirViewModel.setProgreso(sp_progreso.getSelectedItem().toString());

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.segundo_fragment,new Datos_Segundo_Fragmento()).addToBackStack(null).commit();

        View fragmentContainer1 = requireActivity().findViewById(R.id.primer_fragment);
        View fragmentContainer2 = requireActivity().findViewById(R.id.segundo_fragment);

        fragmentContainer1.setVisibility(View.GONE);
        fragmentContainer2.setVisibility(View.VISIBLE);





    }
}