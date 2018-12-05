package com.tdp2.group152.DAOs;

import com.tdp2.group152.models.Passenger;
import com.tdp2.group152.security.AuthorizationToken;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

@Transactional
public class PassengerDAO {

    private SessionFactory sessionFactory;

    public PassengerDAO(SessionFactory sessionFactory) {
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
                .setParameter("id", id)
                .uniqueResult();
    }

    public void saveOrUpdate(AuthorizationToken token) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(token);
    }

    public AuthorizationToken getAuthTokenByTokenAndEmail(String email, String token) {
        return (AuthorizationToken) this.sessionFactory.getCurrentSession().createQuery("FROM AuthorizationToken t WHERE t.token = :token AND t.email = :email")
                .setParameter("email", email)
                .setParameter("token", token)
                .uniqueResult();
    }

    public Passenger getPassengerByEmail(String email) {
        return (Passenger) this.sessionFactory.getCurrentSession().createQuery("FROM Passenger p WHERE p.email = :email")
                .setParameter("email", email)
                .uniqueResult();
    }
}
