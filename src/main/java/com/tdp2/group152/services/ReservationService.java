package com.tdp2.group152.services;

import com.tdp2.group152.DAOs.ReservationDAO;
import com.tdp2.group152.models.Journey;
import com.tdp2.group152.models.MinibusStop;
import com.tdp2.group152.models.Passenger;
import com.tdp2.group152.models.Ticket;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    private ReservationDAO reservationDao;


    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(ReservationDAO reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Transactional
    public Optional<Ticket> reserveTicket(Journey journey, MinibusStop minibusStop, Passenger passenger) throws Exception {
        Ticket ticket = new Ticket();
        try {
            if (journey.hasAvailability()) {
                ticket.setJourney(journey);
                ticket.setMinibusStop(minibusStop);
                ticket.setUsed(false);
                ticket.setPassenger(passenger);
                journey.addTicket(ticket);
                this.reservationDao.saveOrUpdate(journey);
            } else {
                LOGGER.error("The journey it's no longer available.");
            }
        } catch (Exception e) {
            LOGGER.error("Error while saving in the db.");
            throw new NotFoundException("Error when trying to reserve ticket.");
        }

        Optional<Ticket> opt = Optional.ofNullable(ticket);

        return opt;
    }

    @Transactional
    public List<Journey> getAvailableJourneys(String from, String to, LocalDate date) {
        List<Journey> journeys = this.reservationDao.getJourneysForDate(from, to, date);
        List<Journey> availableJourneys = new ArrayList<>();
        for (Journey j : journeys) {
            if (j.hasAvailability()) {
                availableJourneys.add(j);
            }
        }

        return availableJourneys;
    }

    @Transactional
    public Journey getJourneyById(Long id) {
        return this.reservationDao.getJourneyById(id);
    }

    @Transactional
    public MinibusStop getMinibusStopById(Long id ) {
        return this.reservationDao.getMinibusStopById(id);
    }

    @Transactional
    public LocalTime getPickupTime(Journey journey, MinibusStop stop) {
        return this.reservationDao.getPickUpTime(journey, stop);
    }

    @Transactional
    public Ticket getTicketById(Long id) {
        Ticket ticket = this.reservationDao.getTicketById(id);

        return ticket;
    }

    @Transactional
    public void update(Ticket ticket) {
        this.reservationDao.saveOrUpdate(ticket);
    }
}
