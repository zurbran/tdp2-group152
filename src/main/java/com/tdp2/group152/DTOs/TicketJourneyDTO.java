package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.MinibusStop;
import com.tdp2.group152.models.Ticket;

public class TicketJourneyDTO {
    private MinibusStop address;
    private String origin;
    private String destiny;
    private String date;
    private String id;
    private String time;

    public TicketJourneyDTO() {
    }

    public TicketJourneyDTO(Ticket ticket) {
        this.address = ticket.getMinibusStop();
        this.origin = ticket.getJourney().getOrigin();
        this.destiny = ticket.getJourney().getDestiny();
        this.date = ticket.getJourney().getDepartureTime().toString();
        this.id = ticket.getJourney().getJourneyId().toString();
        this.time = ticket.getJourney().getStopById(ticket.getMinibusStop().getStopId()).toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MinibusStop getAddress() {
        return address;
    }

    public void setAddress(MinibusStop address) {
        this.address = address;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
