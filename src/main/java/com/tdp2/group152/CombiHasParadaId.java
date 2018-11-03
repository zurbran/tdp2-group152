package com.tdp2.group152;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CombiHasParadaId implements Serializable {

    @Column(name = "combi_id_combi")
    private Long minibusId;

    @Column(name = "parada_id_parada")
    private Long minibusStopId;

    public CombiHasParadaId() {
    }

    public CombiHasParadaId(Long minibusId, Long minibusStopId) {
        this.minibusId = minibusId;
        this.minibusStopId = minibusStopId;
    }

    public Long getMinibusId() {
        return minibusId;
    }

    public void setMinibusId(Long minibusId) {
        minibusId = minibusId;
    }

    public Long getminibusStopId() {
        return minibusStopId;
    }

    public void setminibusStopId(Long minibusStopId) {
        minibusStopId = minibusStopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CombiHasParadaId that = (CombiHasParadaId) o;
        return Objects.equals(minibusId, that.minibusId) &&
                Objects.equals(minibusStopId, that.minibusStopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minibusId, minibusStopId);
    }
}
