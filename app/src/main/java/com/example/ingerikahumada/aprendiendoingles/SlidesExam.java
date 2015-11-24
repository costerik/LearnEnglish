package com.example.ingerikahumada.aprendiendoingles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SlidesExam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ViewPager viewPager=(ViewPager)findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Picture(getResources().getColor(R.color.accent)), "CAT");
        adapter.addFrag(new Picture(getResources().getColor(R.color.primary)), "DOG");
        adapter.addFrag(new Picture(getResources().getColor(R.color.primary_light)), "MOUSE");
        viewPager.setAdapter(adapter);
    }

}
