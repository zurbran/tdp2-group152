package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.Journey;
import com.tdp2.group152.models.ViajeHasParada;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class JourneyDTO {
    private Long journeyId;
    private String time;
    private List<ViajeHasParada> stops;

    public JourneyDTO(Journey j) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        this.time = timeFormat.format(j.getDepartureTime());
        this.journeyId = j.getJourneyId();
        this.stops = j.getStops();
    }
}
