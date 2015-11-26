package com.example.ingerikahumada.aprendiendoingles;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SlidesExam extends AppCompatActivity {

    private ProgressDialog pDialog;
    private List<ParseObject> ob;
    protected ArrayList<ParseObject> values;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        new GetData().execute();
    }

    private void setupViewPager(ViewPager viewPager,ArrayList<ParseObject> values) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        /*adapter.addFrag(new Picture(getResources().getColor(R.color.accent)), "CAT");
        adapter.addFrag(new Picture(getResources().getColor(R.color.primary)), "DOG");
        adapter.addFrag(new Picture(getResources().getColor(R.color.primary_light)), "MOUSE");*/
        for (ParseObject data: values) {
            adapter.addFrag(new Picture(data));
        }
        viewPager.setAdapter(adapter);
    }

    private class GetData extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog=new ProgressDialog(SlidesExam.this);
            pDialog.setTitle("Learn English");
            pDialog.setMessage("Loading images...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            values= new ArrayList<ParseObject>();
            try{
                ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Image");
                ob=query.find();
                for (ParseObject dato : ob){
                    values.add(dato);
                }

                Log.d("GETDATAIMAGES", "" + query.count());
            } catch (com.parse.ParseException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            setupViewPager(viewPager,values);
            pDialog.dismiss();
        }
    }

}
