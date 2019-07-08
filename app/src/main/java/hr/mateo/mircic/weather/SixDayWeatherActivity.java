package hr.mateo.mircic.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hr.mateo.mircic.weather.model.SixDayWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SixDayWeatherActivity extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    RecyclerView weather_list;
    TextView locationTitleTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.six_day_weather);
        locationTitleTextView = findViewById(R.id.location_title_textView);
        weather_list = findViewById(R.id.weather_recycler);
        weather_list.setHasFixedSize(true);
        weather_list.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI api = retrofit.create(WeatherAPI.class);

        api.getSixDayWeather(getIntent().getSerializableExtra("LocationId").toString()).enqueue(new Callback<SixDayWeather>() {
            @Override
            public void onResponse(Call<SixDayWeather> call, Response<SixDayWeather> response) {
                SixDayWeather sixDayWeather = response.body();
                locationTitleTextView.setText(sixDayWeather.getTitle());
                PopulateRecyclerView(sixDayWeather.getConsolidatedWeather());
                /*if (locationList.isEmpty()){
                    location_list.setVisibility(View.INVISIBLE);
                    error_textview.setText("Nema pronađenih rezultata");
                    error_textview.setVisibility(View.VISIBLE);
                }
                else{
                    error_textview.setVisibility(View.GONE);
                    location_list.setVisibility(View.VISIBLE);
                    PopulateRecyclerView(locationList);
                }*/
            }

            @Override
            public void onFailure(Call<SixDayWeather> call, Throwable t) {
                /*location_list.setVisibility(View.INVISIBLE);
                error_textview.setText("Greška kod dohvata podataka");
                error_textview.setVisibility(View.VISIBLE);*/
            }
        });
    }
    private void PopulateRecyclerView(List<SixDayWeather.ConsolidatedWeather> weatherList) {
        adapter = new SixDayWeathersAdapter(weatherList, this, locationTitleTextView.getText().toString());
        weather_list.setAdapter(adapter);
    }
}
