package com.example.ingerikahumada.aprendiendoingles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class StudentView extends AppCompatActivity implements MyGroupAdapter.RecyclerClickListener{

    private RecyclerView recyclerListaStudent;
    private MyGroupAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog pDialog;
    private List<ParseObject> ob;
    protected ArrayList<ParseObject> values;
    static String LETTERS="letters";
    static String STUDENT_ID="studentId";
    static String GROUP_ID="groupId";
    String id,name,lastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            id=extras.getString(MainActivity.ID);
            name=extras.getString(MainActivity.NAME);
            lastName=extras.getString(MainActivity.LAST_NAME);
            Log.d("EXTRASTUDENTVIEW",id+" "+name+" "+lastName);
        }
        recyclerListaStudent =(RecyclerView)findViewById(R.id.lista_recycle_student);
        recyclerListaStudent.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerListaStudent.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onResume(){
        Log.d("STUDENT VIEW","ONRESUME");
        super.onResume();
        new GetData().execute();
    }

    @Override
    public void itemClick(ParseObject parseObjectAtPosition) {
        Log.d("VIEWHOLDER", parseObjectAtPosition.getString("name"));

        Intent i=new Intent(StudentView.this,SlidesExam.class);
        i.putExtra(LETTERS,parseObjectAtPosition.getString("letters"));
        i.putExtra(STUDENT_ID,id);
        i.putExtra(GROUP_ID,parseObjectAtPosition.getObjectId());
        startActivity(i);
    }

    private class GetData extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog=new ProgressDialog(StudentView.this);
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
                    values.add(dato);
                }

                Log.d("GETDATASTUDENTVIEW", "" + query.count());
            } catch (com.parse.ParseException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            mAdapter=new MyGroupAdapter(values);
            mAdapter.setRecyclerClickListener(StudentView.this);
            if(values.isEmpty()){
                Bitmap bm=splash_screen.decodeSampledBitmapFromResource(getResources(),R.drawable.empty_state,400,400);
                BitmapDrawable b=new BitmapDrawable(getResources(),bm);
                recyclerListaStudent.setBackground(b);
            }else {
                recyclerListaStudent.setBackground(null);
                recyclerListaStudent.setAdapter(mAdapter);
            }
            pDialog.dismiss();
        }
    }
}
