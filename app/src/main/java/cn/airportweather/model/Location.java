package cn.airportweather.model;

import com.google.gson.annotations.Expose;

public class Location {

    @Expose
    private String region;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @Expose
    private String elevation;
    @Expose
    private String wfo;
    @Expose
    private String timezone;
    @Expose
    private String areaDescription;
    @Expose
    private String radar;
    @Expose
    private String zone;
    @Expose
    private String county;
    @Expose
    private String firezone;
    @Expose
    private String metar;

    /**
     *
     * @return
     * The region
     */
    public String getRegion() {
        return region;
    }

    /**
     *
     * @param region
     * The region
     */
    public void setRegion(String region) {
        this.region = region;
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
     * The elevation
     */
    public String getElevation() {
        return elevation;
    }

    /**
     *
     * @param elevation
     * The elevation
     */
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    /**
     *
     * @return
     * The wfo
     */
    public String getWfo() {
        return wfo;
    }

    /**
     *
     * @param wfo
     * The wfo
     */
    public void setWfo(String wfo) {
        this.wfo = wfo;
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
     * The areaDescription
     */
    public String getAreaDescription() {
        return areaDescription;
    }

    /**
     *
     * @param areaDescription
     * The areaDescription
     */
    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    /**
     *
     * @return
     * The radar
     */
    public String getRadar() {
        return radar;
    }

    /**
     *
     * @param radar
     * The radar
     */
    public void setRadar(String radar) {
        this.radar = radar;
    }

    /**
     *
     * @return
     * The zone
     */
    public String getZone() {
        return zone;
    }

    /**
     *
     * @param zone
     * The zone
     */
    public void setZone(String zone) {
        this.zone = zone;
    }

    /**
     *
     * @return
     * The county
     */
    public String getCounty() {
        return county;
    }

    /**
     *
     * @param county
     * The county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     *
     * @return
     * The firezone
     */
    public String getFirezone() {
        return firezone;
    }

    /**
     *
     * @param firezone
     * The firezone
     */
    public void setFirezone(String firezone) {
        this.firezone = firezone;
    }

    /**
     *
     * @return
     * The metar
     */
    public String getMetar() {
        return metar;
    }

    /**
     *
     * @param metar
     * The metar
     */
    public void setMetar(String metar) {
        this.metar = metar;
    }

}