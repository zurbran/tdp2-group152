package com.tdp2.group152.DTOs;


import com.tdp2.group152.models.Ticket;
import com.tdp2.group152.models.ViajeHasParada;

public class TicketDTO {
    private PassengerDTO user;
    private TicketJourneyDTO journeyData;

    public TicketDTO(Ticket ticket) {
        this.user = new PassengerDTO(ticket.getPassenger());
        this.journeyData = new TicketJourneyDTO(ticket);
    }

    public TicketDTO() {
    }

    public PassengerDTO getUser() {
        return user;
    }

    public void setUser(PassengerDTO user) {
        this.user = user;
    }

    public TicketJourneyDTO getJourneyData() {
        return journeyData;
    }

    public void setJourneyData(TicketJourneyDTO journeyData) {
        this.journeyData = journeyData;
    }

}
