package com.example.ingerikahumada.aprendiendoingles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ProfessorView extends AppCompatActivity {

    private RecyclerView recycle_lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_view);
        recycle_lista=(RecyclerView)findViewById(R.id.lista_recycle);
    }
}
