package hr.mateo.mircic.weather;

import java.util.List;

import hr.mateo.mircic.weather.model.SixDayWeather;
import hr.mateo.mircic.weather.model.Location;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("search")
    Call<List<Location>> getLocations(@Query("query") String locationTitle);

    @GET("{locationId}")
    Call<SixDayWeather> getSixDayWeather(@Path("locationId") String locationId);
}
