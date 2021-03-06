package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.Journey;
import com.tdp2.group152.models.MinibusStop;
import com.tdp2.group152.models.ViajeHasParada;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JourneyDTO {
    private Long journeyId;
    private String time;
    private List<MinibusStopDTO> stops;

    public JourneyDTO(Journey j) {
        LocalTime datetime = LocalTime.now();
        this.journeyId = j.getJourneyId();
        this.stops = new ArrayList<>();
        if (!j.getStops().isEmpty()) {
            datetime = j.getStops().get(0).getPickUpTime();
            for (ViajeHasParada v : j.getStops()) {
                MinibusStopDTO dto = new MinibusStopDTO(v.getStop(), v.getPickUpTime().toString());
                this.stops.add(dto);
            }
        }
        this.time = datetime.toString();
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<MinibusStopDTO> getStops() {
        return stops;
    }

    public void setStops(List<MinibusStopDTO> stops) {
        this.stops = stops;
    }
}
