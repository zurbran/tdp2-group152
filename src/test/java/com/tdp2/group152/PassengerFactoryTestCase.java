package com.tdp2.group152;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.*;

public class PassengerFactoryTestCase {

    ApplicationContext context = new FileSystemXmlApplicationContext("src/resources/ApplicationContext.xml");

    PassengerFactory passengerFactory = (PassengerFactory) context.getBean("passengerFactory");

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

        this.passengerFactory.savePassenger(passenger);

    }
}
