package com.tdp2.group152;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CombiHasViajeId implements Serializable {

//    @ManyToOne
//    private Minibus minibus;
//
//    @ManyToOne
//    private Journey journey;
//
//    public CombiHasViajeId() {
//    }
//
//    public CombiHasViajeId(Minibus minibus, Journey journey) {
//        this.minibus = minibus;
//        this.journey = journey;
//    }
//
//    public Minibus getMinibus() {
//        return minibus;
//    }
//
//    public void setMinibus(Minibus minibus) {
//        this.minibus = minibus;
//    }
//
//    public Journey getJourney() {
//        return this.journey;
//    }
//
//    public void setJourney(Journey journey) {
//        this.journey = journey;
//    }
//
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        CombiHasViajeId that = (CombiHasViajeId) o;
//
//        if (minibus != null ? !minibus.equals(that.minibus) : that.minibus != null) return false;
//        if (journey != null ? !journey.equals(that.journey) : that.journey != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(minibus, journey);
//    }
}