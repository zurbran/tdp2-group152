package com.tdp2.group152;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.transaction.Transactional;

public class PassengerFactory {

    private SessionFactory sessionFactory;

    public PassengerFactory(){

    }

    public PassengerFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void savePassenger(Passenger p) {
        this.sessionFactory.getCurrentSession().save(p);
    }

    @Transactional
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
