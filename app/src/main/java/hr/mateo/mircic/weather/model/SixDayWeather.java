package hr.mateo.mircic.weather.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class SixDayWeather {

    @SerializedName("consolidated_weather")
    private List<ConsolidatedWeather> consolidatedWeather = null;
    @SerializedName("time")
    private String time;
    @SerializedName("sun_rise")
    private String sunRise;
    @SerializedName("sun_set")
    private String sunSet;
    @SerializedName("timezone_name")
    private String timezoneName;
    @SerializedName("parent")
    private Parent parent;
    @SerializedName("sources")
    private List<Source> sources = null;
    @SerializedName("title")
    private String title;
    @SerializedName("location_type")
    private String locationType;
    @SerializedName("woeid")
    private Integer woeid;
    @SerializedName("latt_long")
    private String lattLong;
    @SerializedName("timezone")
    private String timezone;

    public List<ConsolidatedWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }

    public void setConsolidatedWeather(List<ConsolidatedWeather> consolidatedWeather) {
        this.consolidatedWeather = consolidatedWeather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public String getLattLong() {
        return lattLong;
    }

    public void setLattLong(String lattLong) {
        this.lattLong = lattLong;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


    public class ConsolidatedWeather implements Serializable {

        @SerializedName("id")
        private BigInteger id;
        @SerializedName("weather_state_name")
        private String weatherStateName;
        @SerializedName("weather_state_abbr")
        private String weatherStateAbbr;
        @SerializedName("wind_direction_compass")
        private String windDirectionCompass;
        @SerializedName("created")
        private String created;
        @SerializedName("applicable_date")
        private String applicableDate;
        @SerializedName("min_temp")
        private Double minTemp;
        @SerializedName("max_temp")
        private Double maxTemp;
        @SerializedName("the_temp")
        private Double theTemp;
        @SerializedName("wind_speed")
        private Double windSpeed;
        @SerializedName("wind_direction")
        private Double windDirection;
        @SerializedName("air_pressure")
        private Double airPressure;
        @SerializedName("humidity")
        private Integer humidity;
        @SerializedName("visibility")
        private Double visibility;
        @SerializedName("predictability")
        private Integer predictability;

        public BigInteger getId() {
            return id;
        }

        public void setId(BigInteger id) {
            this.id = id;
        }

        public String getWeatherStateName() {
            return weatherStateName;
        }

        public void setWeatherStateName(String weatherStateName) {
            this.weatherStateName = weatherStateName;
        }

        public String getWeatherStateAbbr() {
            return weatherStateAbbr;
        }

        public void setWeatherStateAbbr(String weatherStateAbbr) {
            this.weatherStateAbbr = weatherStateAbbr;
        }

        public String getWindDirectionCompass() {
            return windDirectionCompass;
        }

        public void setWindDirectionCompass(String windDirectionCompass) {
            this.windDirectionCompass = windDirectionCompass;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getApplicableDate() {
            return applicableDate;
        }

        public void setApplicableDate(String applicableDate) {
            this.applicableDate = applicableDate;
        }

        public Double getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(Double minTemp) {
            this.minTemp = minTemp;
        }

        public Double getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(Double maxTemp) {
            this.maxTemp = maxTemp;
        }

        public Double getTheTemp() {
            return theTemp;
        }

        public void setTheTemp(Double theTemp) {
            this.theTemp = theTemp;
        }

        public Double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(Double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public Double getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(Double windDirection) {
            this.windDirection = windDirection;
        }

        public Double getAirPressure() {
            return airPressure;
        }

        public void setAirPressure(Double airPressure) {
            this.airPressure = airPressure;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }

        public Double getVisibility() {
            return visibility;
        }

        public void setVisibility(Double visibility) {
            this.visibility = visibility;
        }

        public Integer getPredictability() {
            return predictability;
        }

        public void setPredictability(Integer predictability) {
            this.predictability = predictability;
        }

    }
    public class Parent {

        @SerializedName("title")
        private String title;
        @SerializedName("location_type")
        private String locationType;
        @SerializedName("woeid")
        private Integer woeid;
        @SerializedName("latt_long")
        private String lattLong;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLocationType() {
            return locationType;
        }

        public void setLocationType(String locationType) {
            this.locationType = locationType;
        }

        public Integer getWoeid() {
            return woeid;
        }

        public void setWoeid(Integer woeid) {
            this.woeid = woeid;
        }

        public String getLattLong() {
            return lattLong;
        }

        public void setLattLong(String lattLong) {
            this.lattLong = lattLong;
        }

    }

    public class Source {

        @SerializedName("title")
        private String title;
        @SerializedName("slug")
        private String slug;
        @SerializedName("url")
        private String url;
        @SerializedName("crawl_rate")
        private Integer crawlRate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getCrawlRate() {
            return crawlRate;
        }

        public void setCrawlRate(Integer crawlRate) {
            this.crawlRate = crawlRate;
        }

    }
}

