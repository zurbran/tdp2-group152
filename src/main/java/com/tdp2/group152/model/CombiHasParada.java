package com.tdp2.group152.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "CombiHasParada")
@Table(name = "combi_has_parada")
@AssociationOverrides({
        @AssociationOverride(name = "id.minibus",
                joinColumns = @JoinColumn(name = "combi_id_combi")),
        @AssociationOverride(name = "id.minibusStop",
                joinColumns = @JoinColumn(name = "parada_id_parada")),
        @AssociationOverride(name = "id.journey",
                joinColumns = @JoinColumn(name = "viaje_id_viaje"))})
public class CombiHasParada {

    @EmbeddedId
    private CombiHasParadaId id;

    public CombiHasParada() {
        this.id = new CombiHasParadaId();
    }

    public CombiHasParada(Minibus minibus, MinibusStop minibusStop) {
        this.id = new CombiHasParadaId();
    }

    public CombiHasParada(CombiHasParadaId id, Journey journey) {
        this.id = id;
    }

    public CombiHasParadaId getId() {
        return id;
    }

    public void setId(CombiHasParadaId id) {
        this.id = id;
    }

    @Transient
    public Minibus getMinibus() {
        return this.id.getMinibus();
    }

    public void setMinibus(Minibus minibus) {
        this.id.setMinibus(minibus);
    }

    @Transient
    public MinibusStop getMinibusStop() {
        return this.id.getMinibusStop();
    }

    public void setMinibusStop(MinibusStop minibusStop) {
        this.id.setMinibusStop(minibusStop);
    }

    public Date getPickUpTime() {
        return this.id.getPickUpTime();
    }

    public void setPickUpTime(Date pickUpTime) {
        this.id.setPickUpTime(pickUpTime);
    }

    public Journey getJourney() {
        return this.id.getJourney();
    }

    public void setJourney(Journey journey) {
        this.id.setJourney(journey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CombiHasParada that = (CombiHasParada) o;
        if (getId() != null ? !getId().equals(that.getId())
                : that.getId() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }
}
