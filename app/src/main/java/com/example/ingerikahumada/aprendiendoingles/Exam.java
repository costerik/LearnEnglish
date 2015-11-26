package com.example.ingerikahumada.aprendiendoingles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Exam extends AppCompatActivity {
    private ImageView picture;
    private TextView word,wordDos,wordTres,wordCuatro;
    private List<ParseObject> ob;
    private ProgressDialog pDialog;
    private ParseObject user;
    private String letters,studentId,studentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            letters=extras.getString(SlidesExam.LETTERS);
            studentId=extras.getString(SlidesExam.STUDENT_ID);
            studentGroup=extras.getString(SlidesExam.GROUP_ID);
            Log.d("EXTRASEXAM",letters+" "+studentId+" "+studentGroup);
        }
    }

    private class GetData extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog=new ProgressDialog(Exam.this);
            pDialog.setTitle("Learn English");
            pDialog.setMessage("Checking...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //values= new ArrayList<String>();
            try{
                ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("User");
                ob = query.find();
                Log.d("GETDATA", "" + query.count());
            } catch (com.parse.ParseException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            pDialog.dismiss();
        }
    }
}
