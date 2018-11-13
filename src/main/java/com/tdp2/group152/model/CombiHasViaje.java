package com.tdp2.group152.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//@Entity(name = "CombiHasViaje")
//@Table(name = "combi_has_viaje")
//@AssociationOverrides({
//        @AssociationOverride(name = "id.minibus",
//                joinColumns = @JoinColumn(name = "combi_id_combi")),
//        @AssociationOverride(name = "id.journey",
//                joinColumns = @JoinColumn(name = "viaje_id_viaje")) })
public class CombiHasViaje {

//    @EmbeddedId
//    private CombiHasViajeId id;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "fecha", nullable = false)
//    private Date journeyDate;
//
//    public CombiHasViaje() {
//        this.id = new CombiHasViajeId();
//    }
//
//    public CombiHasViaje(Minibus minibus, Journey journey) {
//        this.id = new CombiHasViajeId();
//    }
//
//    public CombiHasViaje(CombiHasViajeId id, Minibus minibus, Journey journey, Date journeyDate) {
//        this.id = id;
//        this.journeyDate = journeyDate;
//    }
//
//    public CombiHasViajeId getId() {
//        return id;
//    }
//
//    public void setId(CombiHasViajeId id) {
//        this.id = id;
//    }
//
//    @Transient
//    public Minibus getMinibus() {
//        return this.id.getMinibus();
//    }
//
//    public void setMinibus(Minibus minibus) {
//        this.id.setMinibus(minibus);
//    }
//
//    @Transient
//    public Journey getJourney() {
//        return this.id.getJourney();
//    }
//
//    public void setJourney(Journey journey) {
//        this.id.setJourney(journey);
//    }
//
//    public Date getJourneyDate() {
//        return journeyDate;
//    }
//
//    public void setJourneyDate(Date journeyDate) {
//        this.journeyDate = journeyDate;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        CombiHasViaje that = (CombiHasViaje) o;
//        if (getId() != null ? !getId().equals(that.getId())
//                : that.getId() != null)
//            return false;
//
//        return true;
//    }
//
//    public int hashCode() {
//        return (getId() != null ? getId().hashCode() : 0);
//    }
}
