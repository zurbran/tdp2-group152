package com.tdp2.group152.controllers;

import com.tdp2.group152.DTOs.AvailabilityDTO;
import com.tdp2.group152.models.Journey;
import com.tdp2.group152.models.Passenger;
import com.tdp2.group152.security.AuthorizationToken;
import com.tdp2.group152.security.SecuredController;
import com.tdp2.group152.services.PassengerService;
import com.tdp2.group152.services.ReservationService;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
            @RequestHeader("passengerId") Long passengerId,
            @RequestHeader("authToken") String token
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

    @PostMapping(value = "/signin")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void signInPassenger(
            @RequestHeader("email") String email,
            @RequestHeader("password") String password,
            HttpServletResponse response
    ) throws AuthenticationNotSupportedException {

        if(email == null || password == null) {
            throw new AuthenticationNotSupportedException();
        } else {
            AuthorizationToken token = this.passengerService.signIn(email, password);
            if(token == null) {
                throw new AuthenticationNotSupportedException();
            } else {
                Passenger passenger = this.passengerService.getPassengerByEmail(token.getEmail());
                response.addCookie(new Cookie("__sessionId", Long.toString(passenger.getPassengerId())));
                response.addCookie(new Cookie("__sessionToken",token.getToken()));

            }
        }
    }
}
