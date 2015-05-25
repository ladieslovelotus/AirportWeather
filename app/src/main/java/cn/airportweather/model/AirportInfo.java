package cn.airportweather.model;

import com.google.gson.annotations.Expose;

public class AirportInfo {

    @Expose
    private String operationalMode;
    @Expose
    private String srsName;
    @Expose
    private String creationDate;
    @Expose
    private String creationDateLocal;
    @Expose
    private String productionCenter;
    @Expose
    private String credit;
    @Expose
    private String moreInformation;
    @Expose
    private Location location;
    @Expose
    private Time time;
    @Expose
    private Data data;
    @Expose
    private Currentobservation currentobservation;

    /**
     *
     * @return
     * The operationalMode
     */
    public String getOperationalMode() {
        return operationalMode;
    }

    /**
     *
     * @param operationalMode
     * The operationalMode
     */
    public void setOperationalMode(String operationalMode) {
        this.operationalMode = operationalMode;
    }

    /**
     *
     * @return
     * The srsName
     */
    public String getSrsName() {
        return srsName;
    }

    /**
     *
     * @param srsName
     * The srsName
     */
    public void setSrsName(String srsName) {
        this.srsName = srsName;
    }

    /**
     *
     * @return
     * The creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     * The creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return
     * The creationDateLocal
     */
    public String getCreationDateLocal() {
        return creationDateLocal;
    }

    /**
     *
     * @param creationDateLocal
     * The creationDateLocal
     */
    public void setCreationDateLocal(String creationDateLocal) {
        this.creationDateLocal = creationDateLocal;
    }

    /**
     *
     * @return
     * The productionCenter
     */
    public String getProductionCenter() {
        return productionCenter;
    }

    /**
     *
     * @param productionCenter
     * The productionCenter
     */
    public void setProductionCenter(String productionCenter) {
        this.productionCenter = productionCenter;
    }

    /**
     *
     * @return
     * The credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     *
     * @param credit
     * The credit
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    /**
     *
     * @return
     * The moreInformation
     */
    public String getMoreInformation() {
        return moreInformation;
    }

    /**
     *
     * @param moreInformation
     * The moreInformation
     */
    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The time
     */
    public Time getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The currentobservation
     */
    public Currentobservation getCurrentobservation() {
        return currentobservation;
    }

    /**
     *
     * @param currentobservation
     * The currentobservation
     */
    public void setCurrentobservation(Currentobservation currentobservation) {
        this.currentobservation = currentobservation;
    }

    public String GenerateInfoString(){
        return new String("Name: " + this.getCurrentobservation().getName() +
                "\nID: " + this.getCurrentobservation().getId() +
                "\nWeather: " + this.getCurrentobservation().getWeather() +
                "\nTempature: " + this.getCurrentobservation().getTemp() + "\0");
    }
}