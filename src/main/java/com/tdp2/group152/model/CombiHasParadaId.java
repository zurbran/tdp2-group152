package com.tdp2.group152.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class CombiHasParadaId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "combi_id_combi")
    private Minibus minibus;

    @ManyToOne
    @JoinColumn(name = "parada_id_parada")
    private MinibusStop minibusStop;

    @ManyToOne
    @JoinColumn(name = "viaje_id_viaje")
    private Journey journey;

    @Column(name = "hora")
    private Date pickUpTime;

    public CombiHasParadaId() {
    }

    public CombiHasParadaId(Minibus minibus, MinibusStop MinibusStop) {
        this.minibus = minibus;
        this.minibusStop = MinibusStop;
    }

    public Minibus getMinibus() {
        return minibus;
    }

    public void setMinibus(Minibus minibus) {
        this.minibus = minibus;
    }

    public MinibusStop getMinibusStop() {
        return this.minibusStop;
    }

    public void setMinibusStop(MinibusStop minibusStop) {
        this.minibusStop = minibusStop;
    }

    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CombiHasParadaId that = (CombiHasParadaId) o;

        if (minibus != null ? !minibus.equals(that.minibus) : that.minibus != null) return false;
        if (minibusStop != null ? !minibusStop.equals(that.minibusStop) : that.minibusStop != null) return false;
        if (journey != null ? !journey.equals(that.journey) : that.journey != null) return false;
        if (journey != null ? !journey.equals(that.journey) : that.journey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minibus, minibusStop, journey, pickUpTime);
    }
}
