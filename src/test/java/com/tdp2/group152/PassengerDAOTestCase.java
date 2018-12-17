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

import javax.transaction.Transactional;
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
        journey.setDepartureTime(LocalDate.now().plusDays(1));
        journey.setMinibus(minibus);
        this.reservationDAO.saveOrUpdate(journey);
        this.journey = journey;

        Journey journey2 = new Journey();
        journey2.setDestiny("Buenos Aires");
        journey2.setOrigin("La Plata");
        journey2.setDepartureTime(LocalDate.now().plusDays(2));
        journey2.setMinibus(minibus2);
        this.reservationDAO.saveOrUpdate(journey2);
        this.journey2 = journey2;

        Journey journey3 = new Journey();
        journey3.setDestiny("Buenos Aires");
        journey3.setOrigin("La Plata");
        journey3.setDepartureTime(LocalDate.now().plusDays(3));
        journey3.setMinibus(minibus3);
        this.reservationDAO.saveOrUpdate(journey3);
        this.journey3 = journey3;

        Journey journey4 = new Journey();
        journey4.setOrigin("Buenos Aires");
        journey4.setDestiny("La Plata");
        journey4.setDepartureTime(LocalDate.now().plusDays(4));
        journey4.setMinibus(minibus);
        this.reservationDAO.saveOrUpdate(journey4);
        this.journey4 = journey4;

        MinibusStop minibusStop = new MinibusStop();
        minibusStop.setCity("La Plata");
        minibusStop.setStreet("Av 7");
        minibusStop.setStreetNumber("1100");
        this.reservationDAO.saveOrUpdate(minibusStop);
        this.minibusStop = minibusStop;

        MinibusStop minibusStop2 = new MinibusStop();
        minibusStop2.setCity("La Plata");
        minibusStop2.setStreet("Av 13");
        minibusStop2.setStreetNumber("1100");
        this.reservationDAO.saveOrUpdate(minibusStop2);
        this.minibusStop2 = minibusStop2;

        MinibusStop minibusStop3 = new MinibusStop();
        minibusStop3.setCity("Buenos Aires");
        minibusStop3.setStreet("Av 9 de Julio");
        minibusStop3.setStreetNumber("0");
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
        passenger.setTerminal(false);

        Passenger passenger2 = new Passenger();
        passenger2.setDni("0");
        String password2 = "asdasd";
        passenger2.setName("RaspBerry Pi");
        passenger2.setSurname("3 Model B");
        passenger2.setEmail("terminalone@combiLP.com");
        String salt2 = BCrypt.gensalt();
        passenger2.setSalt(salt2);
        String passwordHash2 = BCrypt.hashpw(password2, salt2);
        passenger2.setPasswordHash(passwordHash2);
        passenger2.setTerminal(true);

        this.passengerFactory.saveOrUpdate(passenger);
        this.passengerFactory.saveOrUpdate(passenger2);

        Ticket ticket = new Ticket();
        ticket.setUsed(false);
        ticket.setJourney(this.journey);
        ticket.setMinibusStop(this.minibusStop);
        ticket.setPassenger(passenger);
        this.journey.addTicket(ticket);
        this.journey.addStop(this.minibusStop, LocalTime.now());
        this.journey.addStop(this.minibusStop2, LocalTime.now().plusHours(1));
        this.journey.addStop(this.minibusStop3, LocalTime.now().plusHours(2));
        this.reservationDAO.saveOrUpdate(journey);

        this.journey2.addStop(this.minibusStop, LocalTime.now().plusHours(4));
        this.journey2.addStop(this.minibusStop2, LocalTime.now().plusHours(5));
        this.reservationDAO.saveOrUpdate(journey2);

        this.journey3.addStop(this.minibusStop2, LocalTime.now().plusHours(10));
        this.journey3.addStop(this.minibusStop3, LocalTime.now().plusHours(11));
        this.reservationDAO.saveOrUpdate(journey3);

        this.journey4.addStop(this.minibusStop3, LocalTime.now().plusHours(14));
        this.journey4.addStop(this.minibusStop2, LocalTime.now().plusHours(15));
        this.journey4.addStop(this.minibusStop, LocalTime.now().plusHours(16));
        this.reservationDAO.saveOrUpdate(journey4);

        this.reservationDAO.getJourneyById(journey.getJourneyId());

        assertNotNull(passenger.getPassengerId());


    }

    @Test
    @Transactional
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
