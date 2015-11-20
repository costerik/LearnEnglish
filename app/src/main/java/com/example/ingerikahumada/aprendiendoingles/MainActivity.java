package com.example.ingerikahumada.aprendiendoingles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_log_in,btnStudent;
    private EditText edt_email,edt_password;
    private List<ParseObject> ob;
    private ProgressDialog pDialog;
    private ParseObject user;
    static String ID="id";
    static String NAME="name";
    static String LAST_NAME="lastName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_email=(EditText)findViewById(R.id.email);
        edt_password=(EditText)findViewById(R.id.password);
        btn_log_in=(Button)findViewById(R.id.log_in);
        btnStudent=(Button)findViewById(R.id.button_student);

        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetData().execute();
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent student=new Intent(MainActivity.this,StudentView.class);
                startActivity(student);
            }
        });
    }

    private class GetData extends AsyncTask<Void,Void,Void> {

        public ParseObject isProfessorOrStudent(String email,String password,ParseObject po){
            Log.d("GETDATA", email+" "+password+" "+po.getString("email")+" "+po.getString("password"));
            if(email.compareTo(po.getString("email"))==0 && password.compareTo(po.getString("password"))==0){
                Log.d("SI","Match");
                return po;
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog=new ProgressDialog(MainActivity.this);
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
                Log.d("GETDATA",""+query.count());
            } catch (com.parse.ParseException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            for (ParseObject dato : ob){
                user=isProfessorOrStudent(edt_email.getText().toString(), edt_password.getText().toString(), dato);
                if(user!=null)
                    break;
            }

            if(user!=null){
                Log.d("USER", "Entro");
                Intent i=new Intent(MainActivity.this,ProfessorView.class);
                i.putExtra(ID,user.getObjectId());
                i.putExtra(NAME,user.getString("name"));
                i.putExtra(LAST_NAME,user.getString("lastname"));
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"email or password invalid",Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }
}
