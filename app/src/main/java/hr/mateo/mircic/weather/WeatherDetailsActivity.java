package hr.mateo.mircic.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hr.mateo.mircic.weather.model.SixDayWeather;

public class WeatherDetailsActivity extends AppCompatActivity {

    private TextView locationTitleTextView;
    private TextView weatherStateTextView;
    private TextView maxTempTextView;
    private TextView minTempTextView;
    private TextView windSpeedTextView;
    private TextView humidityTextView;
    private TextView visibilityTextView;
    private TextView airPresureTextView;
    private ImageView weatherImageView;
    private ImageView windDirectionImageView;
    private SixDayWeather.ConsolidatedWeather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_details_activity);

        weather = (SixDayWeather.ConsolidatedWeather)getIntent().getSerializableExtra("Weather");

        locationTitleTextView = (TextView) findViewById(R.id.location_name_textView);
        locationTitleTextView.setText(getIntent().getSerializableExtra("LocationTitle").toString() + " - " + GetDateString(weather.getApplicableDate()));

        weatherImageView = (ImageView)findViewById(R.id.weather_image_imageView);
        windDirectionImageView = (ImageView)findViewById(R.id.wind_dir_imageview);
        weatherStateTextView = (TextView)findViewById(R.id.weather_state_textview);
        maxTempTextView = (TextView)findViewById(R.id.max_tem_textview);
        minTempTextView = (TextView)findViewById(R.id.min_tem_textview);
        windSpeedTextView = (TextView)findViewById(R.id.wind_spd_textview);
        humidityTextView = (TextView)findViewById(R.id.humidity_textview);
        visibilityTextView = (TextView)findViewById(R.id.visibility_textview);
        airPresureTextView = (TextView)findViewById(R.id.air_presure_textview);

        String weatherStateImageUrl = "https://www.metaweather.com/static/img/weather/ico/" + weather.getWeatherStateAbbr() + ".ico";
        Glide.with(this).load(weatherStateImageUrl).into(weatherImageView);

        int imageId = GetWindDirectionImageId(weather.getWindDirectionCompass());
        Glide.with(this).load(imageId).into(windDirectionImageView);

        weatherStateTextView.setText(GetCroatianWeatherStateName(weather.getWeatherStateName()));
        maxTempTextView.setText("Maksimalna temperatura: " + weather.getMaxTemp().intValue() + "°C");
        minTempTextView.setText("Minimalna temperatura: " + weather.getMinTemp().intValue() + "°C");
        windSpeedTextView.setText("Vjetar: " + (int)(weather.getWindSpeed() * 1.6) + " km/h");
        humidityTextView.setText("Vlaga zraka: " + weather.getHumidity() + "%");
        visibilityTextView.setText("Vidljivost: " + (int)(weather.getVisibility()*1.6) + " km");
        airPresureTextView.setText("Tlak zraka: " + weather.getAirPressure().intValue() + " hPa");

    }

    private String GetCroatianWeatherStateName(String weatherState){
        Map<String,String> namesMap = new HashMap(){{
            put("Snow", "Snijeg");
            put("Sleet", "Susnježica");
            put("Hail", "Tuča");
            put("Thunderstorm", "Grmljavina");
            put("Heavy Rain", "Pljusak");
            put("Light Rain", "Blaga kiša");
            put("Showers", "Slaba kiša");
            put("Heavy Cloud", "Velika naoblaka");
            put("Light Cloud", "Naoblaka");
            put("Clear", "Sunčano");
        }};

        return namesMap.get(weatherState);
    }

    private int GetWindDirectionImageId(String windDirectionCompass) {
        if (windDirectionCompass.toLowerCase().contains("ne"))
            return R.drawable.ne;
        else if (windDirectionCompass.toLowerCase().contains("nw"))
            return R.drawable.nw;
        else if (windDirectionCompass.toLowerCase().contains("se"))
            return R.drawable.se;
        else if (windDirectionCompass.toLowerCase().contains("sw"))
            return R.drawable.sw;
        else if (windDirectionCompass.toLowerCase().equals("n"))
            return R.drawable.n;
        else if (windDirectionCompass.toLowerCase().equals("s"))
            return R.drawable.s;
        else if (windDirectionCompass.toLowerCase().equals("w"))
            return R.drawable.w;
        else if (windDirectionCompass.toLowerCase().equals("e"))
            return R.drawable.e;
        else
            return 0;
    }

    private String GetDateString(String dateString){
        Date date = null;
        String displaydate = "";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date  = dateFormat.parse(dateString);

            if (DateUtils.isToday(date.getTime()))
                displaydate = "Danas";
            else if (DateUtils.isToday(date.getTime() - DateUtils.DAY_IN_MILLIS))
                displaydate = "Sutra, " + date.getDate() + ". " + GetCroatianMonthName(date.getMonth());
            else
                displaydate = GetCroatianDayName(date.getDay()) + ", " + date.getDate() + ". " + GetCroatianMonthName(date.getMonth());
        }

        catch(ParseException e){
            e.printStackTrace();

        }
        return displaydate;
    }

    private String GetCroatianMonthName(int month) {
        String monthName = "";

        switch (month){
            case 0:
                monthName = "sij";
                break;
            case 1:
                monthName = "velj";
                break;
            case 2:
                monthName = "ožu";
                break;
            case 3:
                monthName = "tra";
                break;
            case 4:
                monthName = "svi";
                break;
            case 5:
                monthName = "lip";
                break;
            case 6:
                monthName = "srp";
                break;
            case 7:
                monthName = "kol";
                break;
            case 8:
                monthName = "ruj";
                break;
            case 9:
                monthName = "lis";
                break;
            case 10:
                monthName = "stu";
                break;
            case 11:
                monthName = "pro";
                break;

        }

        return monthName;
    }

    private String GetCroatianDayName(int day) {
        String dayName = "";
        switch (day){
            case 0:
                dayName = "Ned";
                break;
            case 1:
                dayName = "Pon";
                break;
            case 2:
                dayName = "Uto";
                break;
            case 3:
                dayName = "Sri";
                break;
            case 4:
                dayName = "Čet";
                break;
            case 5:
                dayName = "Pet";
                break;
            case 6:
                dayName = "Sub";
                break;
        }
        return  dayName;
    }
}
