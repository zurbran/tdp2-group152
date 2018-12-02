package com.tdp2.group152.services;

import com.tdp2.group152.DAOs.ReservationDAO;
import com.tdp2.group152.model.*;
import javassist.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.JobOriginatingUserName;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {

    private ReservationDAO reservationDao;


    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(ReservationDAO reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Transactional
    public Ticket reserveTicket(Journey journey, MinibusStop minibusStop, Passenger passenger) throws Exception {
        if(this.hasAvailability(journey)) {
            Ticket ticket = new Ticket();
            ticket.setJourney(journey);
            ticket.setMinibusStop(minibusStop);
            ticket.setUsed(false);
            ticket.setPassenger(passenger);
            journey.addTicket(ticket);
            this.reservationDao.saveOrUpdate(journey);
            return ticket;
        }
        else {
            LOGGER.error("[RESERVATION_DAO] Error when trying to reserve ticket");
            throw new Exception();
        }
    }

    private boolean hasAvailability(Journey journey) {
        return journey.getReservedTickets().size() < journey.getMinibus().getTotalSeats();
    }

    @Transactional
    public Journey getAvailableJourneys(Journey journey, Date date) {
        List<Journey> journeys = this.reservationDao.getJourneysForDate(date);
        List<Journey> availableJourneys = new ArrayList<>();
        for(Journey j : journeys) {
            if(j.getReservedTickets().size() < j.getMinibus().getTotalSeats()) {
                availableJourneys.add(j);
            }
        }
    }

}
