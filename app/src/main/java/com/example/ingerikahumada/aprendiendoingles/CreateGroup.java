package com.example.ingerikahumada.aprendiendoingles;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

public class CreateGroup extends AppCompatActivity {
    private EditText groupName;
    private EditText initialLetter;
    private Button buttonCreate;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        groupName =(EditText)findViewById(R.id.group_name);
        initialLetter =(EditText)findViewById(R.id.initial_letter);
        buttonCreate=(Button)findViewById(R.id.button_create);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            id=extras.getString(MainActivity.ID);
        }

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendData(id,groupName.getText().toString(),initialLetter.getText().toString()).execute();
            }
        });
    }

    /*
        Parse Classes
     */
    private class SendData extends AsyncTask<Void,Void,Void> {
        private String id,nameGroup,initialLetter;
        public SendData(String id,String nameGroup,String initialLetter){
            this.id=id;
            this.nameGroup=nameGroup;
            this.initialLetter=initialLetter;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("CREATE GROUP",this.id+" "+this.nameGroup+" "+this.initialLetter);
            ParseObject testObject=new ParseObject("Group");
            testObject.put("name",this.nameGroup);
            testObject.put("user_id",this.id);
            testObject.put("letters",this.initialLetter);
            testObject.saveInBackground();
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            finish();
        }
    }
}
