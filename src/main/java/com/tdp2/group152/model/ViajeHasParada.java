package com.tdp2.group152.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "viaje_has_parada")
public class ViajeHasParada {

    @EmbeddedId
    private ViajeHasParadaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viaje_id_viaje")
    @MapsId("journeyId")
    private Journey journey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parada_id_parada")
    @MapsId("minibusStopId")
    private MinibusStop stop;

    @Column(name = "hora")
    private LocalTime pickUpTime;

    public ViajeHasParada() {
        this.id = new ViajeHasParadaId();
    }

    public ViajeHasParada(Journey journey, MinibusStop minibusStop, LocalTime pickUpTime) {
        this.journey = journey;
        this.stop = minibusStop;
        this.pickUpTime = pickUpTime;
        this.id = new ViajeHasParadaId(journey.getJourneyId(), minibusStop.getStopId());
    }

    public ViajeHasParadaId getId() {
        return id;
    }

    public void setId(ViajeHasParadaId id) {
        this.id = id;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ViajeHasParada that = (ViajeHasParada) o;
        return Objects.equals(journey, that.journey) && Objects.equals(stop, that.stop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(journey, stop);
    }
}
