package com.example.universityfinddemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class University_List extends AppCompatActivity {
    String country1= "";
    String json="";
    JSONArray jarr;
    JSONObject jObj;
    TextView textView;
    ListView UniversityListView;
    ArrayList<String> UniversityArrayList=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_list);

        country1 = getIntent().getStringExtra("getCountryName");
        textView = (TextView) findViewById(R.id.textView2);
        textView.setText("Top Universities in " + country1);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
        UniversityListView=(ListView)findViewById(R.id.listView1);

        loadJSONFromAsset();
        JsonToListView();

    }

    public String loadJSONFromAsset() {
        // String json = null;
        try {
            InputStream is =getAssets().open("India.json"); //
            Log.i("msg","json file read");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void JsonToListView(){
        try {
            jarr=new JSONArray(loadJSONFromAsset());
            for(int i=0;i<jarr.length();i++){
                jObj=jarr.getJSONObject(i);
                UniversityArrayList.add(jObj.getString("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,UniversityArrayList);
        UniversityListView.setAdapter(arrayAdapter);
        UniversityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Intent intent=new Intent(getApplicationContext(),DetailsPage.class);
                try {
                    intent.putExtra("College name",jarr.getJSONObject(i).getString("name"));
                    intent.putExtra("Alpha_two_code",jarr.getJSONObject(i).getString("alpha_two_code"));
                    intent.putExtra("state-province",jarr.getJSONObject(i).getString("state-province"));
                    intent.putExtra("country",jarr.getJSONObject(i).getString("country"));
                    intent.putExtra("domains",jarr.getJSONObject(i).getString("domains"));
                    intent.putExtra("web_pages",jarr.getJSONObject(i).getString("web_pages"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });



    }



}