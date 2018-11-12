package com.tdp2.group152;

import javax.persistence.*;
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

//    @OneToMany(
//            mappedBy = "id.journey",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<CombiHasViaje> minibuses;

    @OneToMany(mappedBy = "ticketId")
    private List<Ticket> reservedTickets;

    public Journey() {
//        this.minibuses = new ArrayList<>();
        this.reservedTickets = new ArrayList<>();
    }

    public Journey(String destiny, String origin) {
        this.destiny = destiny;
        this.origin = origin;
//        this.minibuses = new ArrayList<>();
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

//    public List<CombiHasViaje> getMinibuses() {
//        return minibuses;
//    }
//
//    public void setMinibuses(List<CombiHasViaje> minibuses) {
//        this.minibuses = minibuses;
//    }

    public List<Ticket> getReservedTickets() {
        return reservedTickets;
    }

    public void setReservedTickets(List<Ticket> reservedTickets) {
        this.reservedTickets = reservedTickets;
    }
}
