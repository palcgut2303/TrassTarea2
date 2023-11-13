package com.example.trasstarea2.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trasstarea2.R;

public class MainActivity extends AppCompatActivity {

    Button bt_empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_empezar = findViewById(R.id.bt_Empezar);
        bt_empezar.setOnClickListener(this::ir);
    }

    public void ir(View view){
        Intent intentEmpezar = new Intent(this, ListadoTareasActivity.class);
        startActivity(intentEmpezar);
    }


}