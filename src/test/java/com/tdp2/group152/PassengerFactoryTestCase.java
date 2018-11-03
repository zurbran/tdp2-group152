package com.tdp2.group152;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
@Transactional
public class PassengerFactoryTestCase {

//    ApplicationContext context = new FileSystemXmlApplicationContext("src/resources/ApplicationContext.xml");

    @Autowired
    private PassengerDAO passengerFactory;

    @Autowired
    PassengerService passengerService;

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

        this.passengerFactory.saveOrUpdate(passenger);

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
