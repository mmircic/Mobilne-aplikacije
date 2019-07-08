package hr.mateo.mircic.weather.model;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("distance")
    private Integer distance;
    @SerializedName("title")
    private String title;
    @SerializedName("location_type")
    private String locationType;
    @SerializedName("woeid")
    private Integer woeid;
    @SerializedName("latt_long")
    private String lattLong;

    public Location(Integer distance, String title, String locationType, Integer woeid, String lattLong) {
        this.distance = distance;
        this.title = title;
        this.locationType = locationType;
        this.woeid = woeid;
        this.lattLong = lattLong;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

}
