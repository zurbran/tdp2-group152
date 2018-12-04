package com.tdp2.group152.controllers;

import com.tdp2.group152.DTOs.AvailabilityDTO;
import com.tdp2.group152.models.Journey;
import com.tdp2.group152.security.SecuredController;
import com.tdp2.group152.services.PassengerService;
import com.tdp2.group152.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import javax.ws.rs.HeaderParam;
import java.util.Date;
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
            @RequestParam("from_date") String fromDate,
            @HeaderParam("passengerId") Long passengerId,
            @HeaderParam("authToken") String token
    ) throws AuthenticationException {

        this.authenticateRequest(passengerId, token, this.passengerService);
        LOGGER.info("Searching availability for parameters " + "FROM:" + from + " TO:" + to + " fromDate:" + fromDate);

        List<Journey> availableJourneys = reservationService.getAvailableJourneys(from, to, new Date(fromDate));

        return new AvailabilityDTO(availableJourneys);
    }

    @GetMapping(value = "/healthcheck")
    @ResponseBody
    public String check() {
        LOGGER.info("Resolving healthcheck (sending OK)");
        return "OK!";
    }

    @GetMapping(value = "/signin")
    @ResponseBody
    public boolean signInPassenger(
            @HeaderParam("email") String email,
            @HeaderParam("password") String password
    ) {

    }
}
