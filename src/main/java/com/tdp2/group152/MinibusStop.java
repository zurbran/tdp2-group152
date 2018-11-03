package com.tdp2.group152;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "parada")
@NaturalIdCache
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class MinibusStop {

    @Id
    @GeneratedValue
    @Column(name = "id_parada")
    private int stopId;

    @Column(name = "calle")
    private String street;

    @Column(name = "numero")
    private String streetNumber;

    @NaturalId
    @Column(name = "city")
    private String city;

    @OneToMany(
            mappedBy = "parada",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CombiHasParada> stops = new ArrayList<>();

    public MinibusStop(String street, String streetNumber, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
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

    public void addStopsToMinibus(Minibus minibus){
        CombiHasParada combiHasParada = new CombiHasParada(minibus,this);
        this.stops.add(combiHasParada);
        minibus.getStops().add(combiHasParada);
    }

    public void removeStopToMinibus(Minibus minibus) {
        for(CombiHasParada c : this.stops) {
            if(c.equals(this) && c.equals(minibus)) {
                this.stops.remove(c);
                c.getMinibus().getStops().remove(c);
                c.setMinibus(null);
                c.setMinibusStop(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinibusStop tag = (MinibusStop) o;
        return Objects.equals(stopId, tag.stopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId);
    }
}
