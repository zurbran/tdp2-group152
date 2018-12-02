package com.tdp2.group152.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "viaje")
public class Journey {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Long journeyId;

    @Column(name ="destino")
    private String destiny;

    @Column(name="origen")
    private String origin;

    @Column(name = "fecha")
    private Date departureTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combi_id_combi")
    private Minibus minibus;

    @OneToMany(mappedBy = "journey" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> reservedTickets;

    @OneToMany(mappedBy = "journey", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ViajeHasParada> stops;

    public Journey() {
        this.reservedTickets = new ArrayList<>();
        this.stops = new ArrayList<>();
    }

    public Journey(String destiny, String origin, Date departureTime) {
        this.destiny = destiny;
        this.origin = origin;
        this.departureTime = departureTime;
        this.reservedTickets = new ArrayList<>();
        this.stops = new ArrayList<>();
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<Ticket> getReservedTickets() {
        return reservedTickets;
    }

    public void setReservedTickets(List<Ticket> reservedTickets) {
        this.reservedTickets = reservedTickets;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Minibus getMinibus() {
        return minibus;
    }

    public void setMinibus(Minibus minibus) {
        this.minibus = minibus;
    }

    public List<ViajeHasParada> getStops() {
        return stops;
    }

    public void addStop(MinibusStop stop, LocalTime pickUpDate) {
        ViajeHasParada vhp = new ViajeHasParada(this, stop, pickUpDate);
        this.stops.add(vhp);
    }

    public void addTicket(Ticket t) {
        this.reservedTickets.add(t);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Journey) {
            Journey journey = (Journey) object;
            return journeyId == journey.getJourneyId();
        } else {
            return false;
        }
    }

}
