package com.example.universityfinddemoapp;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView textView1;
    String getCountryName=" ";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=findViewById(R.id.spinner1);
        button=(Button)findViewById(R.id.button);
        String[] countries={"--Choose your country--","India"};
        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,countries);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){

                textView1=(TextView)spinner.getSelectedView();
                getCountryName=textView1.getText().toString();
                Log.i("msg",getCountryName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),University_List.class);
                intent.putExtra("getCountryName",getCountryName);
                startActivity(intent);
            }
        });
    }
}