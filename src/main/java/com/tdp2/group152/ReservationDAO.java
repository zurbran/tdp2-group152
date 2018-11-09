package com.tdp2.group152;

import org.hibernate.SessionFactory;
import javax.transaction.Transactional;

@Transactional
public class ReservationDAO {

    private SessionFactory sessionFactory;

    public ReservationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(MinibusStop ms) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(ms);

    }

    public void saveOrUpdate(Ticket t) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(t);

    }

    public void saveOrUpdate(Minibus m) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(m);

    }

    public void saveOrUpdate(Journey j) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(j);

    }

    public void delete(MinibusStop ms) {
        this.sessionFactory.getCurrentSession().delete(ms);
    }

    public void delete(Ticket t) {
        this.sessionFactory.getCurrentSession().delete(t);
    }

    public void delete(Minibus m) {
        this.sessionFactory.getCurrentSession().delete(m);
    }

    public void delete(Journey j) {
        this.sessionFactory.getCurrentSession().delete(j);
    }
}
