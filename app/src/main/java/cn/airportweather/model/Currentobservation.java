package cn.airportweather.model;

import com.google.gson.annotations.Expose;

public class Currentobservation {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String elev;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @Expose
    private String Date;
    @Expose
    private String Temp;
    @Expose
    private String Dewp;
    @Expose
    private String Relh;
    @Expose
    private String Winds;
    @Expose
    private String Windd;
    @Expose
    private String Gust;
    @Expose
    private String Weather;
    @Expose
    private String Weatherimage;
    @Expose
    private String Visibility;
    @Expose
    private String Altimeter;
    @Expose
    private String SLP;
    @Expose
    private String timezone;
    @Expose
    private String state;
    @Expose
    private String WindChill;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The elev
     */
    public String getElev() {
        return elev;
    }

    /**
     *
     * @param elev
     * The elev
     */
    public void setElev(String elev) {
        this.elev = elev;
    }

    /**
     *
     * @return
     * The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The Date
     */
    public String getDate() {
        return Date;
    }

    /**
     *
     * @param Date
     * The Date
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     *
     * @return
     * The Temp
     */
    public String getTemp() {
        return Temp;
    }

    /**
     *
     * @param Temp
     * The Temp
     */
    public void setTemp(String Temp) {
        this.Temp = Temp;
    }

    /**
     *
     * @return
     * The Dewp
     */
    public String getDewp() {
        return Dewp;
    }

    /**
     *
     * @param Dewp
     * The Dewp
     */
    public void setDewp(String Dewp) {
        this.Dewp = Dewp;
    }

    /**
     *
     * @return
     * The Relh
     */
    public String getRelh() {
        return Relh;
    }

    /**
     *
     * @param Relh
     * The Relh
     */
    public void setRelh(String Relh) {
        this.Relh = Relh;
    }

    /**
     *
     * @return
     * The Winds
     */
    public String getWinds() {
        return Winds;
    }

    /**
     *
     * @param Winds
     * The Winds
     */
    public void setWinds(String Winds) {
        this.Winds = Winds;
    }

    /**
     *
     * @return
     * The Windd
     */
    public String getWindd() {
        return Windd;
    }

    /**
     *
     * @param Windd
     * The Windd
     */
    public void setWindd(String Windd) {
        this.Windd = Windd;
    }

    /**
     *
     * @return
     * The Gust
     */
    public String getGust() {
        return Gust;
    }

    /**
     *
     * @param Gust
     * The Gust
     */
    public void setGust(String Gust) {
        this.Gust = Gust;
    }

    /**
     *
     * @return
     * The Weather
     */
    public String getWeather() {
        return Weather;
    }

    /**
     *
     * @param Weather
     * The Weather
     */
    public void setWeather(String Weather) {
        this.Weather = Weather;
    }

    /**
     *
     * @return
     * The Weatherimage
     */
    public String getWeatherimage() {
        return Weatherimage;
    }

    /**
     *
     * @param Weatherimage
     * The Weatherimage
     */
    public void setWeatherimage(String Weatherimage) {
        this.Weatherimage = Weatherimage;
    }

    /**
     *
     * @return
     * The Visibility
     */
    public String getVisibility() {
        return Visibility;
    }

    /**
     *
     * @param Visibility
     * The Visibility
     */
    public void setVisibility(String Visibility) {
        this.Visibility = Visibility;
    }

    /**
     *
     * @return
     * The Altimeter
     */
    public String getAltimeter() {
        return Altimeter;
    }

    /**
     *
     * @param Altimeter
     * The Altimeter
     */
    public void setAltimeter(String Altimeter) {
        this.Altimeter = Altimeter;
    }

    /**
     *
     * @return
     * The SLP
     */
    public String getSLP() {
        return SLP;
    }

    /**
     *
     * @param SLP
     * The SLP
     */
    public void setSLP(String SLP) {
        this.SLP = SLP;
    }

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The WindChill
     */
    public String getWindChill() {
        return WindChill;
    }

    /**
     *
     * @param WindChill
     * The WindChill
     */
    public void setWindChill(String WindChill) {
        this.WindChill = WindChill;
    }
}