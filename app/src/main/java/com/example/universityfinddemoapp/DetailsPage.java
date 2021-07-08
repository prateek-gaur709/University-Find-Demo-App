package com.example.universityfinddemoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DetailsPage extends AppCompatActivity {

    String description="";
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        textView=(TextView)findViewById(R.id.textView4);

        description="University name- "+getIntent().getStringExtra("College name")+"\n\n"+"Alpha two code-"+
                getIntent().getStringExtra("Alpha_two_code")+"\n\n"+"State/province- "+
                getIntent().getStringExtra("state-province")+"\n\n"+"Country- "+
                getIntent().getStringExtra("country")+"\n\n"+"Domains- "+
                getIntent().getStringExtra("domains")+"\n\n"+"Webpages- "+
                getIntent().getStringExtra("web_pages");

        textView.setText(description);
        textView.setMovementMethod(new ScrollingMovementMethod()); //to scroll down the contents in the textview
    }
}