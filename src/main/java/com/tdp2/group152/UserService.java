package com.tdp2.group152;

import org.mindrot.jbcrypt.BCrypt;

import javax.transaction.Transactional;
import java.util.List;

public class UserService {

    private PassengerDAO passengerDao;


    public UserService(PassengerDAO passengerDao) {
        this.passengerDao = passengerDao;
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
    public void generateTicket(Passenger p, Ticket t) {

    }


}
