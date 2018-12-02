package com.tdp2.group152.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parada")
@NaturalIdCache
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class MinibusStop {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id_parada")
    private Long stopId;

    @Column(name = "calle")
    private String street;

    @Column(name = "numero")
    private String streetNumber;

    @Column(name = "ciudad")
    private String city;

    public MinibusStop() {
    }

    public MinibusStop(String street, String streetNumber, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
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

    @Override
    public boolean equals(Object object) {
        if (object instanceof MinibusStop) {
            MinibusStop minibusStop = (MinibusStop) object;
            return stopId == minibusStop.getStopId();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId);
    }
}
