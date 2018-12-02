package com.tdp2.group152.model;

import com.tdp2.group152.model.Journey;
import com.tdp2.group152.model.MinibusStop;
import com.tdp2.group152.model.Passenger;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pasaje")
public class Ticket {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id_pasaje")
    private Long ticketId;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parada_id_parada", nullable = false)
    private MinibusStop minibusStop;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "viaje_id_viaje", nullable = false)
    private Journey journey;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pasajero_id_pasajero")
    private Passenger passenger;

    @Column(name = "used")
    private boolean isUsed;

    public Ticket() {
    }

    public Ticket(Date ticketSchedule, MinibusStop minibusStop, Journey journey, Passenger passenger, boolean isUsed) {
        this.minibusStop = minibusStop;
        this.journey = journey;
        this.passenger = passenger;
        this.isUsed = isUsed;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public MinibusStop getMinibusStop() {
        return minibusStop;
    }

    public void setMinibusStop(MinibusStop minibusStop) {
        this.minibusStop = minibusStop;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

}
