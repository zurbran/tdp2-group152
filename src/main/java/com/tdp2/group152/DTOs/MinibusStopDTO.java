package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.MinibusStop;

public class MinibusStopDTO {

    private Long stopId;

    private String street;

    private String streetNumber;

    private String city;

    private String pickUpTime;

    public MinibusStopDTO(MinibusStop minibusStop, String pickUpTime) {
        this.stopId = minibusStop.getStopId();
        this.street = minibusStop.getStreet();
        this.streetNumber = minibusStop.getStreetNumber();
        this.city = minibusStop.getCity();
        this.pickUpTime = pickUpTime;
    }

    public Long getStopId() {
        return stopId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
}
