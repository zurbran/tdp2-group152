package com.tdp2.group152;

import com.tdp2.group152.DAOs.PassengerDAO;
import com.tdp2.group152.DAOs.ReservationDAO;
import com.tdp2.group152.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
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

    @Before
    public void initTest() {
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

        Journey journey = new Journey();
        journey.setDestiny("Buenos Aires");
        journey.setOrigin("La Plata");
        journey.setDepartureTime(LocalDate.now().plusDays(15));
        journey.setMinibus(minibus);
        this.reservationDAO.saveOrUpdate(journey);
        this.journey = journey;

        Journey journey2 = new Journey();
        journey2.setDestiny("Buenos Aires");
        journey2.setOrigin("La Plata");
        journey2.setDepartureTime(LocalDate.now().plusDays(30));
        journey2.setMinibus(minibus2);
        this.reservationDAO.saveOrUpdate(journey2);
        this.journey2 = journey2;

        Journey journey3 = new Journey();
        journey3.setDestiny("Buenos Aires");
        journey3.setOrigin("La Plata");
        journey3.setDepartureTime(LocalDate.now().plusDays(45));
        journey3.setMinibus(minibus3);
        this.reservationDAO.saveOrUpdate(journey3);
        this.journey3 = journey3;

        Journey journey4 = new Journey();
        journey4.setDestiny("Buenos Aires");
        journey4.setOrigin("La Plata");
        journey4.setDepartureTime(LocalDate.now().plusDays(60));
        journey4.setMinibus(minibus);
        this.reservationDAO.saveOrUpdate(journey4);
        this.journey4 = journey4;

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
    public void testSavePassenger() throws NoSuchAlgorithmException {
        Passenger passenger = new Passenger();
        passenger.setDni("38468109");
        String password = "asdasd";
        passenger.setName("Brandon");
        passenger.setSurname("Russell");
        passenger.setEmail("asd@asd.com");
        String salt = BCrypt.gensalt();
        passenger.setSalt(salt);
        String passwordHash = BCrypt.hashpw(password, salt);
        passenger.setPasswordHash(passwordHash);

        this.passengerFactory.saveOrUpdate(passenger);

        Ticket ticket = new Ticket();
        ticket.setUsed(false);
        ticket.setJourney(this.journey);
        ticket.setMinibusStop(this.minibusStop);
        ticket.setPassenger(passenger);
        this.journey.addTicket(ticket);
        this.journey.addStop(this.minibusStop, LocalTime.now());
        this.reservationDAO.saveOrUpdate(journey);

        this.reservationDAO.getJourneyById(journey.getJourneyId());

        assertNotNull(passenger.getPassengerId());


    }

    @Test
    public void testDeletePassenger() throws NoSuchAlgorithmException {
        Passenger passenger = new Passenger();
        passenger.setDni("38468109");
        String password = "asdasd";
        passenger.setName("Brandon");
        passenger.setSurname("Russell");
        passenger.setEmail("asd@asd.com");
        String salt = BCrypt.gensalt();
        passenger.setSalt(salt);
        String passwordHash = BCrypt.hashpw(password, salt);
        passenger.setPasswordHash(passwordHash);

        this.passengerFactory.saveOrUpdate(passenger);

        this.passengerFactory.delete(passenger);

        assertNull(this.passengerFactory.getPassengerById(passenger.getPassengerId()));
    }

}
