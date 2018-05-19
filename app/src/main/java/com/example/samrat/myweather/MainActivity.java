package com.example.samrat.myweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String currentDateTimeString,me;
    SharedPreferences sharedPreferences;
    int a,b,c,d;
    String month;

    private  String WEATHER_URL;//="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22kolkata%2C%20in%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        WEATHER_URL = sharedPreferences.getString("URL","https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22kolkata%2C%20in%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        String place = sharedPreferences.getString("PLACE","kolkata");
        

        TextView textView = (TextView)findViewById(R.id.textView2);

        textView.setText(""+place);


        ImageView imageView = (ImageView)findViewById(R.id.imageview);

        WeatherAssyntusk weatherAssyntusk = new WeatherAssyntusk();
        weatherAssyntusk.execute(WEATHER_URL);

        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        Calendar time = Calendar.getInstance();

        a = time.get(Calendar.HOUR_OF_DAY);
        b=time.get(Calendar.DATE);
        c=time.get(Calendar.MONTH);
        int e=c+1;
        d=time.get(Calendar.YEAR);

        switch(e){

            case 1: month = "January";
                break;
            case 2: month = "February";
                break;
            case 3: month = "March";
                break;
            case 4: month = "April";
                break;
            case 5: month = "May";
                break;
            case 6: month = "June";
                break;
            case 7: month = "July";
                break;
            case 8: month = "August";
                break;
            case 9: month = "September";
                break;
            case 10: month = "October";
                break;
            case 11: month = "November";
                break;
            case 12: month = "December";
                break;
        }

        if(a>4 && a<18){

            imageView.setImageResource(R.drawable.day4);

        }else{
            imageView.setImageResource(R.drawable.night2);
        }

        TextView button = (TextView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Forcast.class);
                intent.putExtra("THIS",WEATHER_URL);
                startActivity(intent);
            }
        });

        TextView button1 = (TextView) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Search.class);
                startActivity(intent);
            }
        });
    }

    private void UpdateUI(Event event){

        TextView date = (TextView)findViewById(R.id.date1);
        date.setText(""+""+b+""+month+","+""+d);

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

    private class WeatherAssyntusk extends AsyncTask<String,Void,Event>{

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
