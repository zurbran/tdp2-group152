package com.tdp2.group152;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.transaction.Transactional;

@Transactional
public class PassengerDAO {

    private SessionFactory sessionFactory;

    public PassengerDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Passenger p) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(p);
    }


    public void delete(Passenger p) {
        this.sessionFactory.getCurrentSession().delete(p);
    }

    public Passenger getPassengerById(Long id) {
        return (Passenger) this.sessionFactory.getCurrentSession().createQuery("FROM Passenger p WHERE p.passengerId= :id")
                .setParameter("id",id)
                .uniqueResult();
    }

}
