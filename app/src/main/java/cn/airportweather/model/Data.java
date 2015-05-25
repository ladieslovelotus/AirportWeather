package cn.airportweather.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Data {

    @Expose
    private List<String> temperature = new ArrayList<String>();
    @Expose
    private List<Object> pop = new ArrayList<Object>();
    @Expose
    private List<String> weather = new ArrayList<String>();
    @Expose
    private List<String> iconLink = new ArrayList<String>();
    @Expose
    private List<String> hazard = new ArrayList<String>();
    @Expose
    private List<String> hazardUrl = new ArrayList<String>();
    @Expose
    private List<String> text = new ArrayList<String>();

    /**
     *
     * @return
     * The temperature
     */
    public List<String> getTemperature() {
        return temperature;
    }

    /**
     *
     * @param temperature
     * The temperature
     */
    public void setTemperature(List<String> temperature) {
        this.temperature = temperature;
    }

    /**
     *
     * @return
     * The pop
     */
    public List<Object> getPop() {
        return pop;
    }

    /**
     *
     * @param pop
     * The pop
     */
    public void setPop(List<Object> pop) {
        this.pop = pop;
    }

    /**
     *
     * @return
     * The weather
     */
    public List<String> getWeather() {
        return weather;
    }

    /**
     *
     * @param weather
     * The weather
     */
    public void setWeather(List<String> weather) {
        this.weather = weather;
    }

    /**
     *
     * @return
     * The iconLink
     */
    public List<String> getIconLink() {
        return iconLink;
    }

    /**
     *
     * @param iconLink
     * The iconLink
     */
    public void setIconLink(List<String> iconLink) {
        this.iconLink = iconLink;
    }

    /**
     *
     * @return
     * The hazard
     */
    public List<String> getHazard() {
        return hazard;
    }

    /**
     *
     * @param hazard
     * The hazard
     */
    public void setHazard(List<String> hazard) {
        this.hazard = hazard;
    }

    /**
     *
     * @return
     * The hazardUrl
     */
    public List<String> getHazardUrl() {
        return hazardUrl;
    }

    /**
     *
     * @param hazardUrl
     * The hazardUrl
     */
    public void setHazardUrl(List<String> hazardUrl) {
        this.hazardUrl = hazardUrl;
    }

    /**
     *
     * @return
     * The text
     */
    public List<String> getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(List<String> text) {
        this.text = text;
    }
}