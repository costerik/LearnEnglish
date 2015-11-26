package com.example.ingerikahumada.aprendiendoingles;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SignUp extends AppCompatActivity {
    private EditText name,lastName,email,password;
    private Spinner roleSpinner;
    private Button go;
    private String role;
    protected ParseObject user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=(EditText)findViewById(R.id.name_sign_up);
        lastName=(EditText)findViewById(R.id.lastname_sign_up);
        email=(EditText)findViewById(R.id.email_sign_up);
        password=(EditText)findViewById(R.id.password_sign_up);
        roleSpinner=(Spinner)findViewById(R.id.role_sign_up);
        go=(Button)findViewById(R.id.button_sign_up_done);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, R.layout.dropdown_item_for_spinner);

        // Set the Adapter for the spinner
        roleSpinner.setAdapter(adapter);
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) {
                    role = "professor";
                } else {
                    role = "student";
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendData(name.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString(),role).execute();
            }
        });

    }

    /*
       Parse Classes
    */
    private class SendData extends AsyncTask<Void,Void,Void> {
        private String name,lastName,email,password,role;
        public SendData(String name,String lastName,String email,String password,String role){
            this.name=name;
            this.lastName=lastName;
            this.email=email;
            this.password=password;
            this.role=role;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Log.d("CREATE GROUP", this.name + " " + this.nameGroup + " " + this.initialLetter);
            ParseObject testObject=new ParseObject("User");
            testObject.put("name",this.name);
            testObject.put("lastname", this.lastName);
            testObject.put("email",this.email);
            testObject.put("password",this.password);
            testObject.put("role",this.role);
            testObject.saveInBackground();
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            finish();
        }
    }
}
