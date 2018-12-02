package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.Journey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AvailabilityDTO {
    private String from;
    private String to;
    private List<JourneyDTO> journeysDTO;
    private String date;

    public AvailabilityDTO(List<Journey> journeys) {
        for (Journey j : journeys) {
            this.journeysDTO.add(new JourneyDTO(j));
        }
        this.from = journeys.get(0).getOrigin();
        this.to = journeys.get(0).getDestiny();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        this.date = dateFormat.format(journeys.get(0).getDepartureTime());
    }

}
