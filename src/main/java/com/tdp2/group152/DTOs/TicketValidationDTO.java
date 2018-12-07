package com.tdp2.group152.DTOs;

public class TicketValidationDTO {
    private String status;
    private Long journeyId;
    private Long ticketId;

    public TicketValidationDTO() {
    }

    public TicketValidationDTO(String status, Long journeyId, Long ticketId) {
        this.status = status;
        this.journeyId = journeyId;
        this.ticketId = ticketId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
