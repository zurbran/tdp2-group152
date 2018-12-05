package com.tdp2.group152.controllers;

import com.tdp2.group152.DTOs.AvailabilityDTO;
import com.tdp2.group152.models.Journey;
import com.tdp2.group152.security.SecuredController;
import com.tdp2.group152.services.PassengerService;
import com.tdp2.group152.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import javax.ws.rs.HeaderParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class PublicController extends SecuredController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);
    private ReservationService reservationService;
    private PassengerService passengerService;

    public PublicController() {
    }

    public PublicController(ReservationService reservationService, PassengerService passengerService) {
        this.reservationService = reservationService;
        this.passengerService = passengerService;
    }

    @GetMapping(value = "/availability")
    @ResponseBody
    public AvailabilityDTO elaborateAvailabilityResponse(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("from_date") String fromDate

    ) {

        //this.authenticateRequest(passengerId, token, this.passengerService);
        LOGGER.info("Searching availability for parameters " + "FROM:" + from + " TO:" + to + " fromDate:" + fromDate);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fromDate, df);

        List<Journey> availableJourneys = reservationService.getAvailableJourneys(from, to, date);

        AvailabilityDTO response = new AvailabilityDTO(availableJourneys);

        return response;
    }

    @GetMapping(value = "/healthcheck")
    @ResponseStatus(value = HttpStatus.OK)
    public void check() {
        LOGGER.info("Resolving healthcheck (sending OK)");
    }

    @GetMapping(value = "/signin")
    @ResponseBody
    public boolean signInPassenger(
            @HeaderParam("email") String email,
            @HeaderParam("password") String password
    ) {
        return true;
    }
}
