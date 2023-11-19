package com.example.trasstarea2.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trasstarea2.R;
import com.example.trasstarea2.datos.Tarea;

import java.util.ArrayList;
import java.util.List;

public class Adaptador  extends RecyclerView.Adapter<Adaptador.TareaViewHolder>{

    private ArrayList<Tarea> listaTareas;
    Context contexto;
    private Tarea tareaSeleccionada;
    public Adaptador(Context contexto,ArrayList<Tarea> listaTareas){
        this.contexto=contexto;
        this.listaTareas = listaTareas;
    }

    public void actualizarTareas(ArrayList<Tarea> nuevasTareas) {
        this.listaTareas = nuevasTareas;
        notifyDataSetChanged(); // Notifica al RecyclerView que los datos han cambiado
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view,parent,false);
        TareaViewHolder tareaViewHolder = new TareaViewHolder(view);
        return tareaViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.posicion = position;
        tareaSeleccionada = listaTareas.get(position);
        holder.bindTarea(tareaSeleccionada);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuInflater inflater = new MenuInflater(contexto);
                inflater.inflate(R.menu.menu_contextual,menu);
                holder.posicion = position;
                tareaSeleccionada = listaTareas.get(position);
                holder.bindTarea(tareaSeleccionada);
            }
        });
    }



    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public Tarea getTareaSeleccionada(){
        return tareaSeleccionada;
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder  {

        ImageView foto;
        TextView tv_NombreTarea, tv_formato,tv_dias_restantes;
        ProgressBar barra_progreso;
        int posicion;

        //Método constructor
        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.idImagen);
            tv_NombreTarea = itemView.findViewById(R.id.tv_NombreTarea);
            tv_formato = itemView.findViewById(R.id.tv_formato);
            tv_dias_restantes = itemView.findViewById(R.id.tv_dias_restantes);
            barra_progreso = itemView.findViewById(R.id.barra_progreso);

        }

        //Método que nos permitirá dar valores a cada campo del objeto ViewHolder y que
        //el mismo pueda ser mostrado en el RecyclerView
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bindTarea(Tarea t) {


            tv_dias_restantes.setText(String.valueOf(t.getDiasRestantes2()));
            tv_NombreTarea.setText(t.getTitulo());

            if(t.getDiasRestantes2() == 0){
                tv_NombreTarea.setPaintFlags(tv_NombreTarea.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tv_dias_restantes.setTextColor(itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
            }else{
                tv_NombreTarea.setPaintFlags(tv_NombreTarea.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

            barra_progreso.setProgress(t.getProgreso());
            tv_formato.setText(t.getFechaObjetivo());
            foto.setImageResource(t.getFoto());
        }

    }
}
