package com.tdp2.group152.services;

import com.tdp2.group152.DAOs.PassengerDAO;
import com.tdp2.group152.models.*;
import com.tdp2.group152.security.AuthorizationToken;
import javassist.NotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.util.Optional;

public class PassengerService {

    private static final String uuid = "e2f3baf9-2e03-4503-89a5-57fe10bcb066";
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

    @Transactional
    public Passenger getPassengerByEmail(String email) {
        return this.passengerDao.getPassengerByEmail(email);
    }

    @Transactional
    public AuthorizationToken signIn(String email, String password) {
        Passenger passenger = this.passengerDao.getPassengerByEmail(email);
        String salt = passenger.getSalt();
        String hashedPwd = passenger.getPasswordHash();

        if(BCrypt.hashpw(password, salt).equals(hashedPwd)) {
            AuthorizationToken token = this.passengerDao.getAuthTokenByEmail(email);
            if(token == null) {
                AuthorizationToken newToken = new AuthorizationToken(email, DigestUtils.md5DigestAsHex((password + this.uuid).getBytes()));
                this.passengerDao.saveOrUpdate(newToken);
                return newToken;
            }
            return token;
        }
        return null;
    }
}
