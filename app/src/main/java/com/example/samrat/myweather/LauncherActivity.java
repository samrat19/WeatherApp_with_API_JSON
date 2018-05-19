package com.example.samrat.myweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LauncherActivity extends AppCompatActivity {

    private String url,place,country,userlocation;
    EditText editText,editText1;
    Button button;;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        editText = (EditText)findViewById(R.id.editText3);
        editText1 = (EditText)findViewById(R.id.editText4);
        button = (Button)findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                place = editText.getText().toString();
                country = editText1.getText().toString();
                userlocation = (""+place+","+country);
                url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+place+"%2C%20"+country+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

                editor = sharedPreferences.edit();

                editor.putString("URL", url);
                editor.putString("PLACE",userlocation);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
