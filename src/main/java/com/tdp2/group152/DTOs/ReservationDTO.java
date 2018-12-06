package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.MinibusStop;
import com.tdp2.group152.models.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {
    private String from;
    private String to;
    private MinibusStop stop;
    private Long ticketId;
    private String pickUpDate;
    private String date;

    public ReservationDTO(String from, String to, MinibusStop stop, Long ticketId, LocalTime pickUpDate, LocalDate date) {
        this.from = from;
        this.to = to;
        this.stop = stop;
        this.ticketId = ticketId;
        this.pickUpDate = pickUpDate.toString();
        this.date = date.toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MinibusStop getStop() {
        return stop;
    }

    public void setStop(MinibusStop stop) {
        this.stop = stop;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
