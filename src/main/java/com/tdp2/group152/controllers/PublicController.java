package com.tdp2.group152.controllers;

import com.tdp2.group152.DTOs.AvailabilityDTO;
import com.tdp2.group152.models.Journey;
import com.tdp2.group152.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class PublicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);
    private ReservationService reservationService;

    @GetMapping(value = "/availability")
    @ResponseBody
    public AvailabilityDTO elaborateAvailabilityResponse(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("from_date") String fromDate) {
        LOGGER.info("Searching availability for parameters " + "FROM:" + from + " TO:" + to + " fromDate:" + fromDate);

        List<Journey> availableJourneys = reservationService.getAvailableJourneys(from, to, new Date(fromDate));

        return new AvailabilityDTO(availableJourneys);
    }

    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/healthcheck")
    @ResponseBody
    public String check() {
        LOGGER.info("Resolving healthcheck (sending OK)");
        return "OK!";
    }
}
