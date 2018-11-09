package com.tdp2.group152;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class UserService {

    @Autowired
    private PassengerDAO passengerDao;

    public UserService(PassengerDAO passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Transactional
    public void savePassenger(Passenger p, List<Ticket> tickets, String password) {
        String salt = BCrypt.gensalt();
        p.setSalt(salt);
        String passwordHash = BCrypt.hashpw(password,salt);
        p.setPasswordHash(passwordHash);
        p.setTickets(tickets);
        this.passengerDao.saveOrUpdate(p);
    }


}
