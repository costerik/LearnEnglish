package com.example.ingerikahumada.aprendiendoingles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btn_log_in;
    private EditText edt_email,edt_password;
    private RecyclerView recycle_vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_email=(EditText)findViewById(R.id.email);
        edt_password=(EditText)findViewById(R.id.password);
        btn_log_in=(Button)findViewById(R.id.log_in);

        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProfessorView.class);
                startActivity(i);
            }
        });
    }
}
