package com.example.samrat.myweather;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Search extends AppCompatActivity {

    int a,b,c,d;
    String month,city,country;
    TextView textView;
    EditText editText,editText1;

    private  String  WEATHER_URL;//="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22atlanta%2C%20us%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        textView = (TextView)findViewById(R.id.textView2);
        editText = (EditText)findViewById(R.id.editText);
        editText1 = (EditText)findViewById(R.id.editText2);



        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                city = editText.getText().toString();
                country = editText1.getText().toString();

                textView.setText(""+city+", "+country);

                WEATHER_URL="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%2C%20"+country+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

                WeatherAssyntusk weatherAssyntusk = new WeatherAssyntusk();
                weatherAssyntusk.execute(WEATHER_URL);
            }
        });

    }

    private void UpdateUI(Event event){

        TextView temp = (TextView)findViewById(R.id.temp);
        String hightemp = event.getTemp();
        int far = Integer.parseInt(hightemp);
        float cel = 5*(far-32)/9;
        temp.setText(""+cel+"°C");

        TextView cond = (TextView)findViewById(R.id.cond);
        cond.setText(event.getText());

        TextView sunrise=(TextView)findViewById(R.id.sr);
        sunrise.setText(event.getSunrise());

        TextView sunset=(TextView)findViewById(R.id.ss);
        sunset.setText(event.getSunset());

        TextView humidity=(TextView)findViewById(R.id.humidity);
        humidity.setText(event.getHumidity());

        TextView pressure=(TextView)findViewById(R.id.pressure);
        pressure.setText(event.getPressure()+"in");

    }

    private class WeatherAssyntusk extends AsyncTask<String,Void,Event> {

        @Override
        protected Event doInBackground(String... strings) {

            Event result = Utils.fetchWeatherData(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(Event result) {

            UpdateUI(result);
        }
    }
}
