package com.tdp2.group152.DAOs;

import com.tdp2.group152.model.Passenger;
import org.hibernate.SessionFactory;


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