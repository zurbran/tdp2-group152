package com.tdp2.group152.controllers;

import com.tdp2.group152.DTOs.AvailabilityDTO;
import com.tdp2.group152.DTOs.ReservationDTO;
import com.tdp2.group152.DTOs.TicketValidationDTO;
import com.tdp2.group152.models.Journey;
import com.tdp2.group152.models.MinibusStop;
import com.tdp2.group152.models.Passenger;
import com.tdp2.group152.models.Ticket;
import com.tdp2.group152.security.AuthorizationToken;
import com.tdp2.group152.security.SecuredController;
import com.tdp2.group152.services.PassengerService;
import com.tdp2.group152.services.ReservationService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


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

    @CrossOrigin
    @PostMapping(value = "/signin")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void signInPassenger(
            @RequestHeader("email") String email,
            @RequestHeader("password") String password,
            HttpServletResponse response
    ) throws AuthenticationNotSupportedException {

        if (email == null || password == null) {
            throw new AuthenticationNotSupportedException();
        } else {
            AuthorizationToken token = this.passengerService.signIn(email, password);
            if (token == null) {
                throw new AuthenticationNotSupportedException();
            } else {
                Passenger passenger = this.passengerService.getPassengerByEmail(token.getEmail());
                response.addCookie(new Cookie("__sessionId", Long.toString(passenger.getPassengerId())));
                response.addCookie(new Cookie("__sessionToken", token.getToken()));
                response.addCookie(new Cookie("email", token.getEmail()));

            }
        }
    }

    @PostMapping(value = "/journey/{journeyId}/stop/{stopId}/")
    @ResponseBody
    public ReservationDTO reserveTicket(
            @RequestHeader("passengerId") Long passengerId,
            @RequestHeader("authToken") String token,
            @PathVariable("journeyId") Long journeyId,
            @PathVariable("stopId") Long stopId
    ) throws Exception {

        this.authenticateRequest(passengerId, token, this.passengerService);
        MinibusStop minibusStop = this.reservationService.getMinibusStopById(stopId);
        Journey journey = this.reservationService.getJourneyById(journeyId);
        Passenger passenger = this.passengerService.getPassengerById(passengerId);

        Optional<Ticket> opt = this.reservationService.reserveTicket(journey, minibusStop, passenger);
        if(opt.isPresent()) {
            Ticket ticket = opt.get();
            LocalTime pickUpTime = this.reservationService.getPickupTime(journey, minibusStop);
            ReservationDTO dto = new ReservationDTO(journey.getOrigin(), journey.getDestiny(), minibusStop, ticket.getTicketId()
                    , pickUpTime, journey.getDepartureTime(), journey.getJourneyId());
            return dto;

        } else {
            throw new NotFoundException("Ticket was not generated");
        }
    }

    @PostMapping(value = "/ticket/{ticketId}/journey/{journeyId}/check")
    @ResponseBody
    public TicketValidationDTO validateTicket(
            @RequestHeader("passengerId") Long passengerId,
            @RequestHeader("authToken") String token,
            @PathVariable("journeyId") Long journeyId,
            @PathVariable("ticketId") Long ticketId
    ) throws AuthenticationException {

        this.authenticateRequestForTerminal(passengerId, token, this.passengerService);
        Ticket ticket = this.reservationService.getTicketById(ticketId);
        if (!(ticket.isUsed()) && (ticket.getJourney().getJourneyId() == journeyId)) {
            ticket.setUsed(true);
            this.reservationService.update(ticket);
            TicketValidationDTO dto = new TicketValidationDTO("OK", journeyId, ticketId);
            return dto;
        } else if (!(ticket.isUsed())){
            TicketValidationDTO dto = new TicketValidationDTO("INVALID", journeyId, ticketId);
            return dto;
        } else {
            TicketValidationDTO dto = new TicketValidationDTO("USED", journeyId, ticketId);
            return dto;
        }
    }
}

