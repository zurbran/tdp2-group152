package com.tdp2.group152;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "combi")
public class Minibus {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
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

    @ManyToMany
    private List<Journey> journeys;

    @OneToMany(
            mappedBy = "id.minibus",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CombiHasParada> stops;

    public Minibus() {
        this.journeys = new ArrayList<>();
        this.stops = new ArrayList<>();
    }

    public Minibus(String licensePlate, String model, String brand, int totalSeats) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.totalSeats = totalSeats;
        this.journeys = new ArrayList<>();
        this.stops = new ArrayList<>();
    }

    public Long getMinibuId() {
        return minibusId;
    }

    public void setMinibuId(Long minibusId) {
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

    public List<CombiHasParada> getStops() {
        return stops;
    }

    public void setStops(List<CombiHasParada> stops) {
        this.stops = stops;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }
}
