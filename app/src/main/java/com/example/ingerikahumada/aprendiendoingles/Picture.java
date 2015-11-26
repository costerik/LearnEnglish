package com.example.ingerikahumada.aprendiendoingles;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;


public class Picture extends Fragment {
    private ImageView picture;
    private TextView word;
    private ParseObject data;
    private Bitmap bmp;

    public Picture() {
    }

    @SuppressLint("ValidFragment")
    public Picture(ParseObject data){
        this.data=data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseFile picture=data.getParseFile("File");
        try {
            bmp= BitmapFactory.decodeByteArray(picture.getData(), 0,
                    picture.getData().length);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_picture, container, false);
        picture=(ImageView)v.findViewById(R.id.picture);
        word=(TextView)v.findViewById(R.id.word);
        word.setText(data.getString("description"));
        if(bmp!=null)
            picture.setImageBitmap(bmp);

        return v;
    }
}
