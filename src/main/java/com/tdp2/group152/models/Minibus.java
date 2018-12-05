package com.tdp2.group152.models;

import javax.persistence.*;

@Entity
@Table(name = "combi")
public class Minibus {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id_combi")
    private Long minibusId;

    @Column(name = "patente")
    private String licensePlate;

    @Column(name = "modelo")
    private String model;

    @Column(name = "marca")
    private String brand;

    @Column(name = "asientos")
    private int totalSeats;

    public Minibus() {}

    public Minibus(String licensePlate, String model, String brand, int totalSeats) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.totalSeats = totalSeats;
    }

    public Long getMinibusId() {
        return minibusId;
    }

    public void setMinibusId(Long minibusId) {
        this.minibusId = minibusId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
//
//    public List<CombiHasParada> getStops() {
//        return stops;
//    }
//
//    public void setStops(List<CombiHasParada> stops) {
//        this.stops = stops;
//    }
//
//    public void addStop(CombiHasParada minibusHasStop) {
//        this.stops.add(minibusHasStop);
//    }


}
