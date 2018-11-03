package com.tdp2.group152;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "CombiHasParada")
@Table(name = "combi_has_parada")
public class CombiHasParada {

    @EmbeddedId
    private CombiHasParadaId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("minibusId")
    private Minibus minibus;

    @ManyToOne(fetch =  FetchType.EAGER)
    @MapsId("minibusStopId")
    private MinibusStop minibusStop;

    @Column(name = "hora")
    private Date pickUpTime;

    public CombiHasParada() {
    }

    public CombiHasParada(Minibus minibus, MinibusStop minibusStop) {
        this.minibus = minibus;
        this.minibusStop = minibusStop;
    }

    public CombiHasParada(CombiHasParadaId id, Minibus minibus, MinibusStop minibusStop, Date pickUpTime) {
        this.id = id;
        this.minibus = minibus;
        this.minibusStop = minibusStop;
        this.pickUpTime = pickUpTime;
    }

    public CombiHasParadaId getId() {
        return id;
    }

    public void setId(CombiHasParadaId id) {
        this.id = id;
    }

    public Minibus getMinibus() {
        return minibus;
    }

    public void setMinibus(Minibus minibus) {
        this.minibus = minibus;
    }

    public MinibusStop getMinibusStop() {
        return minibusStop;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CombiHasParada that = (CombiHasParada) o;
        return Objects.equals(minibus, that.minibus) &&
                Objects.equals(minibusStop, that.minibusStop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minibus, minibusStop);
    }
}
