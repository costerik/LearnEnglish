package com.example.ingerikahumada.aprendiendoingles;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ProfessorView extends AppCompatActivity{

    private RecyclerView recyclerLista;
    private MyGroupAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textNameProfessor;
    private Button buttonCreateGroup;
    private ProgressDialog pDialog;
    private List<ParseObject> ob;
    protected ArrayList<ParseObject> values;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_view);
        textNameProfessor =(TextView)findViewById(R.id.text_name_profesor);
        recyclerLista =(RecyclerView)findViewById(R.id.lista_recycle);
        buttonCreateGroup=(Button)findViewById(R.id.button_create_group);
        recyclerLista.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerLista.setLayoutManager(mLayoutManager);
        //mAdapter.setRecyclerClickListener(this);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            id=extras.getString(MainActivity.ID);
            String name=extras.getString(MainActivity.NAME);
            String lastName=extras.getString(MainActivity.LAST_NAME);
            Log.d("EXTRAS", id + " " + name + " " + lastName);
            textNameProfessor.setText(name + " " + lastName);
        }

        buttonCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ProfessorView.this,CreateGroup.class);
                i.putExtra(MainActivity.ID,id);
                startActivity(i);
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        new GetData(id).execute();
        Log.d("DATA",id);
    }

    private class GetData extends AsyncTask<Void,Void,Void> {
        private String id;
        public GetData(String id){
            this.id=id;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog=new ProgressDialog(ProfessorView.this);
            pDialog.setTitle("Learn English");
            pDialog.setMessage("Checking...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            values= new ArrayList<ParseObject>();
            try{
                ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Group");
                ob=query.find();
                for (ParseObject dato : ob){
                    Log.d("RESULT",this.id+" "+dato.getObjectId());
                    if(dato.getString("user_id").compareTo(this.id)==0){
                        Log.d("MATCH","GROUP");
                        values.add(dato);
                    }
                }
                Log.d("GETDATA", "" + query.count());
            } catch (com.parse.ParseException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            mAdapter=new MyGroupAdapter(values);
            if(values.isEmpty()){
                Bitmap bm=splash_screen.decodeSampledBitmapFromResource(getResources(),R.drawable.empty_state,400,400);
                BitmapDrawable b=new BitmapDrawable(getResources(),bm);
                recyclerLista.setBackground(b);
            }else {
                recyclerLista.setBackground(null);
                recyclerLista.setAdapter(mAdapter);
            }
            pDialog.dismiss();
        }
    }


}
