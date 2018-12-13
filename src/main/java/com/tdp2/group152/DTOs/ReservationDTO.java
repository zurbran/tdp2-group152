package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.MinibusStop;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {
    private String from;
    private String to;
    private MinibusStop stop;
    private Long ticketId;
    private String time;
    private String date;
    private Long journeyId;

    public ReservationDTO(String from, String to, MinibusStop stop, Long ticketId, LocalTime time, LocalDate date, Long journeyId) {
        this.from = from;
        this.to = to;
        this.stop = stop;
        this.ticketId = ticketId;
        this.time = time.toString();
        this.date = date.toString();
        this.journeyId = journeyId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }
}
