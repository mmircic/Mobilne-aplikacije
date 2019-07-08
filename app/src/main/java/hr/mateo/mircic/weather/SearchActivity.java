package hr.mateo.mircic.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import hr.mateo.mircic.weather.model.Location;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    Button search_btn;
    RecyclerView location_list;
    EditText search_text;
    TextView error_textview;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        search_btn = findViewById(R.id.search_button);
        location_list = findViewById(R.id.location_recycler);
        location_list.setHasFixedSize(true);
        location_list.setLayoutManager(new LinearLayoutManager(this));
        search_text = findViewById(R.id.search_edittext);
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SearchLocations();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        error_textview = findViewById(R.id.error_textview);


    }

    public void SearchLocations(){
        String enteredText = search_text.getText().toString();

        if (enteredText != null && !enteredText.equals(""))
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getResources().getString(R.string.api_base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WeatherAPI api = retrofit.create(WeatherAPI.class);

            api.getLocations(enteredText).enqueue(new Callback<List<Location>>() {
                @Override
                public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                    List<Location> locationList = response.body();
                    if (locationList.isEmpty()){
                        location_list.setVisibility(View.INVISIBLE);
                        error_textview.setText("Nema pronađenih rezultata");
                        error_textview.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_textview.setVisibility(View.GONE);
                        location_list.setVisibility(View.VISIBLE);
                        PopulateRecyclerView(locationList);
                    }
                }

                @Override
                public void onFailure(Call<List<Location>> call, Throwable t) {
                    location_list.setVisibility(View.INVISIBLE);
                    error_textview.setText("Greška kod dohvata podataka");
                    error_textview.setVisibility(View.VISIBLE);
                }
            });
        }
        else{
            location_list.swapAdapter(null, false);
        }
    }

    private void PopulateRecyclerView(List<Location> locationList) {
        adapter = new LocationsAdapter(locationList, this);
        location_list.setAdapter(adapter);
    }
}
