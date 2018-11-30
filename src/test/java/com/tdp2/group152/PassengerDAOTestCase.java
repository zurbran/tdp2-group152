package com.tdp2.group152;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.tdp2.group152.DAOs.PassengerDAO;
import com.tdp2.group152.DAOs.ReservationDAO;
import com.tdp2.group152.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private Minibus minibus2;
    private Minibus minibus3;

    private Ticket ticket;

    private MinibusStop minibusStop;
    private MinibusStop minibusStop2;
    private MinibusStop minibusStop3;


    private Journey journey;
    private Journey journey2;
    private Journey journey3;
    private Journey journey4;
    private Journey journey5;

    @Before
    @Transactional
    public void initTest() {
        Journey journey = new Journey();
        journey.setDestiny("Buenos Aires");
        journey.setOrigin("La Plata");
        journey.setDepartureTime(LocalTime.now());
        this.reservationDAO.saveOrUpdate(journey);
        this.journey = journey;

        Journey journey2 = new Journey();
        journey2.setDestiny("Buenos Aires");
        journey2.setOrigin("La Plata");
        journey2.setDepartureTime(LocalTime.now().plusHours(3));
        this.reservationDAO.saveOrUpdate(journey2);
        this.journey2 = journey2;

        Journey journey3 = new Journey();
        journey3.setDestiny("Buenos Aires");
        journey3.setOrigin("La Plata");
        journey3.setDepartureTime(LocalTime.now().plusHours(2));
        this.reservationDAO.saveOrUpdate(journey3);
        this.journey3 = journey3;

        Journey journey4 = new Journey();
        journey4.setDestiny("Buenos Aires");
        journey4.setOrigin("La Plata");
        journey4.setDepartureTime(LocalTime.now().plusHours(1));
        this.reservationDAO.saveOrUpdate(journey4);
        this.journey4 = journey4;

        Journey journey5 = new Journey();
        journey5.setDestiny("Buenos Aires");
        journey5.setOrigin("La Plata");
        journey5.setDepartureTime(LocalTime.now().plusHours(4));
        this.reservationDAO.saveOrUpdate(journey5);
        this.journey5 = journey5;

        Minibus minibus = new Minibus();
        minibus.setLicensePlate("DTK-177");
        minibus.setBrand("Peugeot");
        minibus.setModel("Boxer");
        minibus.setTotalSeats(40);
        this.reservationDAO.saveOrUpdate(minibus);
        this.minibus = minibus;

        Minibus minibus2 = new Minibus();
        minibus2.setLicensePlate("DTK-200");
        minibus2.setBrand("Renault");
        minibus2.setModel("Boxer");
        minibus2.setTotalSeats(40);
        this.reservationDAO.saveOrUpdate(minibus2);
        this.minibus2 = minibus2;

        Minibus minibus3 = new Minibus();
        minibus3.setLicensePlate("DTK-300");
        minibus3.setBrand("Citroen");
        minibus3.setModel("Boxer");
        minibus3.setTotalSeats(40);
        this.reservationDAO.saveOrUpdate(minibus3);
        this.minibus3 = minibus3;

        MinibusStop minibusStop = new MinibusStop();
        minibusStop.setCity("La Plata");
        minibusStop.setStreet("Av 7");
        minibusStop.setStreetNumber("1100");
        minibusStop.setCity("La Plata");
        this.reservationDAO.saveOrUpdate(minibusStop);
        this.minibusStop = minibusStop;

        MinibusStop minibusStop2 = new MinibusStop();
        minibusStop2.setCity("La Plata");
        minibusStop2.setStreet("Av 13");
        minibusStop2.setStreetNumber("1100");
        minibusStop2.setCity("La Plata");
        this.reservationDAO.saveOrUpdate(minibusStop2);
        this.minibusStop2 = minibusStop2;

        MinibusStop minibusStop3 = new MinibusStop();
        minibusStop3.setCity("La Plata");
        minibusStop3.setStreet("Av 19");
        minibusStop3.setStreetNumber("1100");
        minibusStop3.setCity("La Plata");
        this.reservationDAO.saveOrUpdate(minibusStop3);
        this.minibusStop3 = minibusStop3;
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

        CombiHasParada combiHasParada2 = new CombiHasParada();
        combiHasParada2.setMinibusStop(this.minibusStop2);
        combiHasParada2.setMinibus(this.minibus);
        combiHasParada2.setJourney(this.journey);
        combiHasParada2.setPickUpTime(new Date());

        CombiHasParada combiHasParada3 = new CombiHasParada();
        combiHasParada3.setMinibusStop(this.minibusStop3);
        combiHasParada3.setMinibus(this.minibus);
        combiHasParada3.setJourney(this.journey);
        combiHasParada3.setPickUpTime(new Date());

        this.minibus.addStop(combiHasParada);
        this.minibus.addStop(combiHasParada2);
        this.minibus.addStop(combiHasParada3);

        CombiHasParada combiHasParada4 = new CombiHasParada();
        combiHasParada4.setMinibusStop(this.minibusStop);
        combiHasParada4.setMinibus(this.minibus2);
        combiHasParada4.setJourney(this.journey2);
        combiHasParada4.setPickUpTime(new Date());

        CombiHasParada combiHasParada5 = new CombiHasParada();
        combiHasParada5.setMinibusStop(this.minibusStop2);
        combiHasParada5.setMinibus(this.minibus2);
        combiHasParada5.setJourney(this.journey2);
        combiHasParada5.setPickUpTime(new Date());

        CombiHasParada combiHasParada6 = new CombiHasParada();
        combiHasParada6.setMinibusStop(this.minibusStop3);
        combiHasParada6.setMinibus(this.minibus2);
        combiHasParada6.setJourney(this.journey2);
        combiHasParada6.setPickUpTime(new Date());

        this.minibus2.addStop(combiHasParada4);
        this.minibus2.addStop(combiHasParada5);
        this.minibus2.addStop(combiHasParada6);

        CombiHasParada combiHasParada7 = new CombiHasParada();
        combiHasParada7.setMinibusStop(this.minibusStop);
        combiHasParada7.setMinibus(this.minibus3);
        combiHasParada7.setJourney(this.journey3);
        combiHasParada7.setPickUpTime(new Date());

        CombiHasParada combiHasParada8 = new CombiHasParada();
        combiHasParada8.setMinibusStop(this.minibusStop2);
        combiHasParada8.setMinibus(this.minibus3);
        combiHasParada8.setJourney(this.journey3);
        combiHasParada8.setPickUpTime(new Date());

        CombiHasParada combiHasParada9 = new CombiHasParada();
        combiHasParada9.setMinibusStop(this.minibusStop3);
        combiHasParada9.setMinibus(this.minibus3);
        combiHasParada9.setJourney(this.journey3);
        combiHasParada9.setPickUpTime(new Date());

        this.minibus3.addStop(combiHasParada7);
        this.minibus3.addStop(combiHasParada8);
        this.minibus3.addStop(combiHasParada9);

        CombiHasParada combiHasParada10 = new CombiHasParada();
        combiHasParada10.setMinibusStop(this.minibusStop);
        combiHasParada10.setMinibus(this.minibus);
        combiHasParada10.setJourney(this.journey4);
        combiHasParada10.setPickUpTime(new Date());

        CombiHasParada combiHasParada11 = new CombiHasParada();
        combiHasParada11.setMinibusStop(this.minibusStop2);
        combiHasParada11.setMinibus(this.minibus);
        combiHasParada11.setJourney(this.journey4);
        combiHasParada11.setPickUpTime(new Date());

        CombiHasParada combiHasParada12 = new CombiHasParada();
        combiHasParada12.setMinibusStop(this.minibusStop3);
        combiHasParada12.setMinibus(this.minibus);
        combiHasParada12.setJourney(this.journey4);
        combiHasParada12.setPickUpTime(new Date());

        this.minibus.addStop(combiHasParada10);
        this.minibus.addStop(combiHasParada11);
        this.minibus.addStop(combiHasParada12);

        this.reservationDAO.saveOrUpdate(minibus);
        this.reservationDAO.saveOrUpdate(minibus2);
        this.reservationDAO.saveOrUpdate(minibus3);

        Minibus retrievedMinibus = this.reservationDAO.getMinibusById(this.minibus.getMinibusId());
        assertEquals(this.journey.getJourneyId(),retrievedMinibus.getStops().get(0).getJourney().getJourneyId());

        this.reservationDAO.getMinibusOfJourney(journey.getJourneyId());
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
