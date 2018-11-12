package com.tdp2.group152;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.mindrot.jbcrypt.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
@Rollback
public class PassengerDAOTestCase {

    @Autowired
    private PassengerDAO passengerFactory;

    @Autowired
    private ReservationDAO reservationDAO;

    private Minibus minibus;

    private Ticket ticket;

    private MinibusStop minibusStop;

    private Journey journey;

    @Before
    @Transactional
    public void initTest() {
        Journey journey = new Journey();
        journey.setDestiny("Buenos Aires");
        journey.setOrigin("La Plata");
        this.reservationDAO.saveOrUpdate(journey);
        this.journey = journey;

        Minibus minibus = new Minibus();
        minibus.setLicensePlate("DTK-177");
        minibus.setBrand("Peugeot");
        minibus.setModel("Boxer");
        minibus.setTotalSeats(40);
        this.reservationDAO.saveOrUpdate(minibus);
        this.minibus = minibus;

        MinibusStop minibusStop = new MinibusStop();
        minibusStop.setCity("La Plata");
        minibusStop.setStreet("Av 7");
        minibusStop.setStreetNumber("1100");
        minibusStop.setCity("La Plata");
        this.reservationDAO.saveOrUpdate(minibusStop);
        this.minibusStop = minibusStop;
    }

    @Test
    @Transactional
    public void testSavePassenger() throws NoSuchAlgorithmException {
        Passenger passenger = new Passenger();
        passenger.setDni("38468109");
        String password="asdasd";
        passenger.setName("Brandon");
        passenger.setSurname("Russell");
        passenger.setEmail("asd@asd.com");
        String salt = BCrypt.gensalt();
        passenger.setSalt(salt);
        String passwordHash = BCrypt.hashpw(password,salt);
        passenger.setPasswordHash(passwordHash);

        Ticket ticket = new Ticket();
        ticket.setTicketSchedule(new Date());
        ticket.setUsed(false);
        ticket.setJourney(this.journey);
        ticket.setMinibusStop(this.minibusStop);
        ticket.setPassenger(passenger);

        passenger.addTicket(ticket);
        this.passengerFactory.saveOrUpdate(passenger);

        assertNotNull(passenger.getPassengerId());

        CombiHasParada combiHasParada = new CombiHasParada();
        combiHasParada.setMinibusStop(this.minibusStop);
        combiHasParada.setMinibus(this.minibus);
        combiHasParada.setJourney(this.journey);
        combiHasParada.setPickUpTime(new Date());

        this.minibus.addStop(combiHasParada);

        this.reservationDAO.saveOrUpdate(minibus);

        Minibus retrievedMinibus = this.reservationDAO.getMinibusById(this.minibus.getMinibusId());
        assertEquals(this.journey.getJourneyId(),retrievedMinibus.getStops().get(0).getJourney().getJourneyId());
    }

    @Test
    @Transactional
    public void testDeletePassenger() throws NoSuchAlgorithmException {
        Passenger passenger = new Passenger();
        passenger.setDni("38468109");
        String password="asdasd";
        passenger.setName("Brandon");
        passenger.setSurname("Russell");
        passenger.setEmail("asd@asd.com");
        String salt = BCrypt.gensalt();
        passenger.setSalt(salt);
        String passwordHash = BCrypt.hashpw(password,salt);
        passenger.setPasswordHash(passwordHash);

        this.passengerFactory.saveOrUpdate(passenger);

        this.passengerFactory.delete(passenger);

        assertNull(this.passengerFactory.getPassengerById(passenger.getPassengerId()));
    }

}
