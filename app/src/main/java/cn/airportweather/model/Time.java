package cn.airportweather.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Time {

    @Expose
    private String layoutKey;
    @Expose
    private List<String> startPeriodName = new ArrayList<String>();
    @Expose
    private List<String> startValidTime = new ArrayList<String>();
    @Expose
    private List<String> tempLabel = new ArrayList<String>();

    /**
     *
     * @return
     * The layoutKey
     */
    public String getLayoutKey() {
        return layoutKey;
    }

    /**
     *
     * @param layoutKey
     * The layoutKey
     */
    public void setLayoutKey(String layoutKey) {
        this.layoutKey = layoutKey;
    }

    /**
     *
     * @return
     * The startPeriodName
     */
    public List<String> getStartPeriodName() {
        return startPeriodName;
    }

    /**
     *
     * @param startPeriodName
     * The startPeriodName
     */
    public void setStartPeriodName(List<String> startPeriodName) {
        this.startPeriodName = startPeriodName;
    }

    /**
     *
     * @return
     * The startValidTime
     */
    public List<String> getStartValidTime() {
        return startValidTime;
    }

    /**
     *
     * @param startValidTime
     * The startValidTime
     */
    public void setStartValidTime(List<String> startValidTime) {
        this.startValidTime = startValidTime;
    }

    /**
     *
     * @return
     * The tempLabel
     */
    public List<String> getTempLabel() {
        return tempLabel;
    }

    /**
     *
     * @param tempLabel
     * The tempLabel
     */
    public void setTempLabel(List<String> tempLabel) {
        this.tempLabel = tempLabel;
    }
}