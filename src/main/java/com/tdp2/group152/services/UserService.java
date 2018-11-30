package com.tdp2.group152.services;

import com.tdp2.group152.DAOs.PassengerDAO;
import com.tdp2.group152.model.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.transaction.Transactional;

public class UserService {

    private PassengerDAO passengerDao;
    private ReservationService reservationService;


    public UserService(PassengerDAO passengerDao, ReservationService reservationService) {
        this.passengerDao = passengerDao;
        this.reservationService = reservationService;
    }

    @Transactional
    public void savePassenger(Passenger p, String password) {
        String salt = BCrypt.gensalt();
        p.setSalt(salt);
        String passwordHash = BCrypt.hashpw(password,salt);
        p.setPasswordHash(passwordHash);
        this.passengerDao.saveOrUpdate(p);
    }

    @Transactional
    public void generateTicket(Passenger passenger, Minibus minibus, MinibusStop minibusStop, Journey journey) {

    }


}
