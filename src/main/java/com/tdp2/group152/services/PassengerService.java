package com.tdp2.group152.services;

import com.tdp2.group152.DAOs.PassengerDAO;
import com.tdp2.group152.models.*;
import javassist.NotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

public class PassengerService {

    private PassengerDAO passengerDao;
    private ReservationService reservationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PassengerService.class);

    public PassengerService(PassengerDAO passengerDao, ReservationService reservationService) {
        this.passengerDao = passengerDao;
        this.reservationService = reservationService;
    }

    @Transactional
    public void savePassenger(Passenger p, String password) {
        String salt = BCrypt.gensalt();
        p.setSalt(salt);
        String passwordHash = BCrypt.hashpw(password, salt);
        p.setPasswordHash(passwordHash);
        this.passengerDao.saveOrUpdate(p);
    }

    @Transactional
    public void generateTicket(Passenger passenger, MinibusStop minibusStop, Journey journey) throws Exception {
        Optional<Ticket> opt = this.reservationService.reserveTicket(journey, minibusStop, passenger);
        if(opt.isPresent()) {
            //TODO Generate QR Code Object based on retrieved ticket to return it back to the controller
        } else {
            throw new NotFoundException("Ticket was not generated");
        }
    }

    @Transactional
    public Passenger getPassengerById(Long passengerId) {
        return this.passengerDao.getPassengerById(passengerId);
    }

    @Transactional
    public boolean authenticatePassenger(String email, String token) {
        return (this.passengerDao.getAuthTokenByTokenAndEmail(email, token) == null) ? false : true;
    }
}
