package hr.mateo.mircic.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hr.mateo.mircic.weather.model.SixDayWeather;


public class SixDayWeathersAdapter extends RecyclerView.Adapter<SixDayWeathersAdapter.ViewHolder>{

    private List<SixDayWeather.ConsolidatedWeather> weatherList;
    private String locationTitle;
    private Context context;

    public SixDayWeathersAdapter(List<SixDayWeather.ConsolidatedWeather> weatherList, Context context, String locationTitle) {
        this.weatherList = weatherList;
        this.context = context;
        this.locationTitle = locationTitle;
    }

    @NonNull
    @Override
    public SixDayWeathersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_weathers, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SixDayWeathersAdapter.ViewHolder holder, int position) {
        SixDayWeather.ConsolidatedWeather weather = weatherList.get(position);
        holder.weatherDayTextView.setText(GetDateString(weather.getApplicableDate()));
        holder.maxTempTextView.setText("Maksimalna temperatura: " + weather.getMaxTemp().intValue() + "°C");
        holder.minTempTextView.setText("Minimalna temperatura: " + weather.getMinTemp().intValue() + "°C");
        holder.windSpeedTextView.setText(GetWindSpeed(weather.getWindSpeed()) + " km/h");
        String weatherStateImageUrl = "https://www.metaweather.com/static/img/weather/ico/" + weather.getWeatherStateAbbr() + ".ico";
        Glide.with(context).load(weatherStateImageUrl).into(holder.weatherStateImageView);
        int imageId = GetWindDirectionImageId(weather.getWindDirectionCompass());
        Glide.with(context).load(imageId).into(holder.windDirectionImageView);
        holder.weatherCardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    v.setBackgroundColor(Color.parseColor("#c9d8ef"));

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                    v.setBackgroundColor(Color.parseColor("#ffffff"));
                return false;
            }
        });
        holder.weatherCardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, WeatherDetailsActivity.class);
                myIntent.putExtra("LocationTitle", locationTitle);
                myIntent.putExtra("Weather", weather);
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weatherDayTextView;
        public TextView maxTempTextView;
        public TextView minTempTextView;
        public TextView windSpeedTextView;
        public ImageView weatherStateImageView;
        public ImageView windDirectionImageView;
        public CardView weatherCardView;

        public ViewHolder(View itemView) {
            super(itemView);

            weatherDayTextView = (TextView) itemView.findViewById(R.id.weather_day_textview);
            maxTempTextView = (TextView) itemView.findViewById(R.id.max_temp_textview);
            minTempTextView = (TextView) itemView.findViewById(R.id.min_temp_textview);
            windSpeedTextView = (TextView) itemView.findViewById(R.id.wind_speed_textview);
            weatherStateImageView = (ImageView) itemView.findViewById(R.id.weather_state_imageview);
            windDirectionImageView = (ImageView) itemView.findViewById(R.id.wind_direction_imageview);
            weatherCardView = (CardView) itemView.findViewById(R.id.weather_cardview);
        }
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

    private int GetWindSpeed(Double speedInMph){
        int speedInKmh;
        speedInKmh = (int)(speedInMph * 1.6);
        return speedInKmh;
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
