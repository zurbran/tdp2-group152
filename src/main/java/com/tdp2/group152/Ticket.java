package com.tdp2.group152;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pasaje")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name = "id_pasaje")
    private Long ticketId;

    @Column(name = "horario")
    private Date ticketSchedule;

    @Column(name = "pasajero_id_pasajero")
    @ManyToOne
    private Passenger passenger;

    @Column(name = "parada_id_parada")
    @ManyToOne
    private MinibusStop minibusStop;

    @Column(name = "viaje_id_viaje")
    @ManyToOne
    private Journey journey;

    @Column(name = "used")
    private boolean isUsed;

    public Ticket() {
    }

    public Ticket(Date ticketSchedule, Passenger passenger, MinibusStop minibusStop, Journey journey, boolean isUsed) {
        this.ticketSchedule = ticketSchedule;
        this.passenger = passenger;
        this.minibusStop = minibusStop;
        this.journey = journey;
        this.isUsed = isUsed;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Date getTicketSchedule() {
        return ticketSchedule;
    }

    public void setTicketSchedule(Date ticketSchedule) {
        this.ticketSchedule = ticketSchedule;
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
