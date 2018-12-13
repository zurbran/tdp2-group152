package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.Journey;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityDTO {
    private String from;
    private String to;
    private List<JourneyDTO> journeysDTO;
    private String date;

    public AvailabilityDTO(List<Journey> journeys) {
        this.journeysDTO = new ArrayList<>();
        for (Journey j : journeys) {
            this.journeysDTO.add(new JourneyDTO(j));
        }
        this.from = journeys.get(0).getOrigin();
        this.to = journeys.get(0).getDestiny();
        this.date = journeys.get(0).getDepartureTime().toString();
    }

    public AvailabilityDTO() {
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

    public List<JourneyDTO> getJourneysDTO() {
        return journeysDTO;
    }

    public void setJourneysDTO(List<JourneyDTO> journeysDTO) {
        this.journeysDTO = journeysDTO;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
