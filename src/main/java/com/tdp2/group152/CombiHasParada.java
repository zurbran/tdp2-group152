package com.tdp2.group152;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "CombiHasParada")
@Table(name = "combi_has_parada")
@AssociationOverrides({
        @AssociationOverride(name = "id.minibus",
                joinColumns = @JoinColumn(name = "combi_id_combi")),
        @AssociationOverride(name = "id.minibusStop",
                joinColumns = @JoinColumn(name = "parada_id_parada")) })
public class CombiHasParada {

    @EmbeddedId
    private CombiHasParadaId id;

    private Date pickUpTime;

    public CombiHasParada() {
    }

    public CombiHasParada(Minibus minibus, MinibusStop minibusStop) {
        this.id = new CombiHasParadaId();
    }

    public CombiHasParada(CombiHasParadaId id, Minibus minibus, MinibusStop minibusStop, Date pickUpTime) {
        this.id = id;
        this.pickUpTime = pickUpTime;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "hora", nullable = false)
    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
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
