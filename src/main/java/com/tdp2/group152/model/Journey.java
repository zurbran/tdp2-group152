package com.tdp2.group152.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
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

    @Column(name = "horario_salida")
    private LocalTime departureTime;


    @OneToMany(mappedBy = "ticketId")
    private List<Ticket> reservedTickets;

    public Journey() {
        this.reservedTickets = new ArrayList<>();
    }

    public Journey(String destiny, String origin, LocalTime departureTime) {
        this.destiny = destiny;
        this.origin = origin;
        this.departureTime = departureTime;
        this.reservedTickets = new ArrayList<>();
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
