package com.tdp2.group152.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ViajeHasParadaId implements Serializable {

    @Column(name = "viaje_id_viaje")
    private Long journeyId;

    @Column(name = "parada_id_parada")
    private Long minibusStopId;

    public ViajeHasParadaId() {
    }

    public ViajeHasParadaId(Long journeyId, Long minibusStopId) {
        this.journeyId = journeyId;
        this.minibusStopId = minibusStopId;
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public Long getMinibusStopId() {
        return minibusStopId;
    }

    public void setMinibusStopId(Long minibusStopId) {
        this.minibusStopId = minibusStopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViajeHasParadaId that = (ViajeHasParadaId) o;
        return Objects.equals(journeyId, that.journeyId) && Objects.equals(minibusStopId, that.minibusStopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(journeyId, minibusStopId);
    }

}
