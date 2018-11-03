package com.tdp2.group152;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.transaction.Transactional;
import javax.xml.bind.Marshaller;
import javax.xml.bind.helpers.AbstractMarshallerImpl;

public class PassengerService {

    @Autowired
    private PassengerDAO passengerDao;

    public PassengerService(PassengerDAO passengerDao) {
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
    public void updatePassenger(Passenger p) {

    }
}
