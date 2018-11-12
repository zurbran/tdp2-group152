package com.tdp2.group152;

import org.hibernate.SessionFactory;
import javax.transaction.Transactional;
import java.util.List;


public class ReservationDAO {

    private SessionFactory sessionFactory;

    public ReservationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(MinibusStop ms) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(ms);

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

    public Ticket getTicketById(Long id) {
        return (Ticket) this.sessionFactory.getCurrentSession().createQuery("FROM Ticket t WHERE t.tickerId = :ticketId")
                .setParameter("ticketId", id)
                .uniqueResult();
    }

    public Minibus getMinibusById(Long id) {
        return (Minibus) this.sessionFactory.getCurrentSession().createQuery("FROM Minibus  m WHERE m.minibusId = :minibusId")
                .setParameter("minibusId", id)
                .uniqueResult();
    }

    public MinibusStop getMinibusStopById(Long id) {
        return (MinibusStop) this.sessionFactory.getCurrentSession().createQuery("FROM MinibusStop ms WHERE ms.stopId = :stopId")
                .setParameter("stopId", id)
                .uniqueResult();
    }

    public Journey getJourneyById(Long id) {
        return (Journey) this.sessionFactory.getCurrentSession().createQuery("FROM Journey j WHERE j.journeyId = :journeyId")
                .setParameter("journeyId", id)
                .uniqueResult();
    }

    public List<Ticket> getAllTicketsFromJourney(Long journeyId) {
        return (List<Ticket>) this.sessionFactory.getCurrentSession().createQuery("FROM Ticket t WHERE t.journey.journeyId = :journeyId")
                .setParameter("journeyId", journeyId)
                .getResultList();
    }
}
