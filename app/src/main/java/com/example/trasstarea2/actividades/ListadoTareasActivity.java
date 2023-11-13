package com.example.trasstarea2.actividades;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trasstarea2.R;
import com.example.trasstarea2.adaptadores.Adaptador;
import com.example.trasstarea2.datos.Tarea;
import com.example.trasstarea2.fragmentos.Datos_Segundo_Fragmento;
import com.example.trasstarea2.viewModel.TareaViewModel;

import java.util.ArrayList;

public class ListadoTareasActivity extends AppCompatActivity{

    private TextView tv_NoTareas, tv_NoTarPri;
    private ArrayList<Tarea> listaTareas = new ArrayList<>();

    public ArrayList<Tarea> getListaTareaTodas() {
        return listaTareas;
    }

    public void setListaTareaTodas(ArrayList<Tarea> listaTareaTodas) {
        this.listaTareas = listaTareaTodas;
    }

    public ArrayList<Tarea> getListaTareasPrio() {
        return listaTareasPrio;
    }

    public void setListaTareasPrio(ArrayList<Tarea> listaTareasPrio) {
        this.listaTareasPrio = listaTareasPrio;
    }

    public TareaViewModel tareaViewModel;
    private ArrayList<Tarea> listaTareasPrio = new ArrayList<>();

    private RecyclerView recyclerTareas;

    private Boolean esPriori = false;

    private Adaptador adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.listado_tareas);
            tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);

            tv_NoTareas = findViewById(R.id.tv_Tareas);
            tv_NoTarPri = findViewById(R.id.tv_noTarPri);
            tv_NoTarPri.setVisibility(View.INVISIBLE);

            llenarTareas();


           listaTareas = (ArrayList<Tarea>) tareaViewModel.getListaTareas();
            listaTareasPrio = TareasPri();


            if(listaTareas.size() == 0){
                tv_NoTareas.setVisibility(View.VISIBLE);
            }else{
                tv_NoTareas.setVisibility(View.INVISIBLE);
            }

            recyclerTareas=findViewById(R.id.rcView_Tareas);
            adapter = new Adaptador(this, listaTareas);

            recyclerTareas.setAdapter(adapter);
            recyclerTareas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



        }catch (Exception ex){
            ex.getMessage();
        }

       registerForContextMenu(recyclerTareas);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        menu.setGroupVisible(R.id.it_group_gestion_noticias,true);
        return super.onCreateOptionsMenu(menu);

    }

    public Adaptador getAdapter() {
        return adapter;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.it_acercade){
            mostrarAcercaDe();
            Toast.makeText(this, "Pantalla AcercaDe", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.it_salir){
            finishAffinity();
        }else if(id == R.id.it_mostrarPri){

            recyclerTareas=findViewById(R.id.rcView_Tareas);
            esPriori = !esPriori;

            if(esPriori){
                Adaptador adapter = new Adaptador(this, listaTareasPrio);
                recyclerTareas.setAdapter(adapter);
                item.setIcon(R.drawable.baseline_star_border_24);
            }else{
                Adaptador adapter = new Adaptador(this, listaTareas);
                recyclerTareas.setAdapter(adapter);
                item.setIcon(R.drawable.baseline_star_24);
            }


            recyclerTareas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Mostar Prioritarias ", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.it_añadirTarea) {
            Intent intentCrearTarea = new Intent(this, CrearTareas.class);
            startActivity(intentCrearTarea);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        boolean b = true;

        Tarea tareSeleccionada = adapter.getTareaSeleccionada();
        int posicion = listaTareas.indexOf(tareSeleccionada);

        if(itemId == R.id.it_descripcion){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mostrarDescripcion(listaTareas.get(posicion).getDescripcion());
            }
            Toast.makeText(this, "Descripción", Toast.LENGTH_SHORT).show();

            return true;
        } else if (itemId == R.id.it_editar) {
            Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();
            return true;
        }else {

            Tarea miTareaBorrar = listaTareas.get(posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(miTareaBorrar.isPrioritaria()){
                    listaTareasPrio.remove(miTareaBorrar);
                    adapter.notifyDataSetChanged();
                }
            }
            listaTareas.remove(miTareaBorrar);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Borrar", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void llenarTareas() {
        try {

            tareaViewModel.agregarTarea(new Tarea("TareaUT1","Holaa esta es mi descripcion Tarea 1",50,true,"2023 / 12 / 12","2024 / 02 / 20",R.drawable.baseline_star_24));
            tareaViewModel.agregarTarea(new Tarea("TareaUT2","Hola esta es mi descripcion Tarea 2",0,false,"2023 / 02 / 12","2023 / 05 / 12",R.drawable.baseline_star_border_24));
            tareaViewModel.agregarTarea(new Tarea("TareaUT3","Hola esta es mi descripcion Tarea 3",50,true,"2023 / 09 / 02","2023 / 10 / 02",R.drawable.baseline_star_24));
            tareaViewModel.agregarTarea(new Tarea("TareaUT4","Hola esta es mi descripcion Tarea 4",50,true,"2023 / 03 / 23","2023 / 05 / 12",R.drawable.baseline_star_24));
            tareaViewModel.agregarTarea(new Tarea("TareaUT5","Hola esta es mi descripcion Tarea 5",75,false,"2023 / 05 / 31","2023 / 05 / 31",R.drawable.baseline_star_border_24));


        }catch (Exception ex){
             ex.getMessage();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<Tarea>  TareasPri(){
        ArrayList<Tarea> listaPri = new ArrayList<>();
        for(Tarea misTareas : listaTareas){
            if(misTareas.isPrioritaria()){

                listaPri.add(misTareas);
            }
        }
        return listaPri;
    }

    private void mostrarDescripcion(String descripcion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Descripción");

        // Configura el contenido del cuadro de diálogo con un ScrollView y un TextView
        ScrollView scrollView = new ScrollView(this);
        TextView descripcionTextView = new TextView(this);
        descripcionTextView.setText(descripcion);
        scrollView.addView(descripcionTextView);
        builder.setView(scrollView);

        // Agrega un botón de cierre
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Muestra el cuadro de diálogo
        builder.show();
    }

    public TareaViewModel getTareaViewModel() {
        return tareaViewModel;
    }

    @SuppressLint("SetTextI18n")
    private void mostrarAcercaDe() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acerca De");

        // Configura el contenido del cuadro de diálogo con un ScrollView y un TextView
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_app_trasstarea2);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
        layout.addView(imageView);


        TextView acercaDe = new TextView(this);
        acercaDe.setText("\tPABLO ALCUDIA GUTIÉRREZ.\n \tIES TRASSIERRA.\n \t2023.");
        layout.addView(acercaDe);

        scrollView.addView(layout);
        builder.setView(scrollView);


        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Muestra el cuadro de diálogo
        builder.show();
    }



}
