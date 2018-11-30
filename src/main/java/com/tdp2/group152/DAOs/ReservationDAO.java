package com.tdp2.group152.DAOs;

import com.tdp2.group152.model.Ticket;
import com.tdp2.group152.model.Journey;
import com.tdp2.group152.model.Minibus;
import com.tdp2.group152.model.MinibusStop;
import org.hibernate.SessionFactory;

import java.util.Date;
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

    public List<Ticket> getAllTicketsFromJourney(Long journeyId, Date date) {
        return (List<Ticket>) this.sessionFactory.getCurrentSession().createQuery("FROM Ticket t WHERE t.journey.journeyId = :journeyId")
                .setParameter("journeyId", journeyId)
                .getResultList();
    }

    public Minibus getMinibusOfJourney(Long journeyId) {
        return (Minibus) this.sessionFactory.getCurrentSession().createQuery("FROM Combi c WHERE c IN (FROM CombiHasParada chp WHERE chp.journey = :journeyId)")
                .setParameter("journeyId", journeyId)
                .uniqueResult();
    }

    public List<MinibusStop> getMinibusStopsForJourney(Long journeyId, Date date) {
        return (List<MinibusStop>) this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT id.minibusStop FROM CombiHasParada chp WHERE chp.journey.journeyId = :journeyId AND year(chp.pickUpTime) = year(:date) AND day(chp.pickUpTime) = day(:date)")
                .setParameter("journeyId", journeyId)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Journey> getJourneysForDate(Date date) {
        return (List<Journey>) this.sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT journey FROM CombiHasParada chp WHERE year(chp.pickUpTime) = year(:date) AND day(chp.pickUpTime) = day(:date)")
                .setParameter("date", date)
                .getResultList();
    }

    public List<Minibus> getMinibusesFromJourneyAndDate(Date date, Journey journey){
        return   (List<Minibus>) this.sessionFactory.getCurrentSession().createQuery("SELECT  id.minibus FROM CombiHasParada chp  WHERE chp.journey.origin = :origen AND chp.journey.destiny = :destino AND year(chp.pickUpTime) = year(:date) AND day(chp.pickUpTime) = day(:date) AND month(chp.pickUpTime) = month(:date)")
                .setParameter("origen", journey.getOrigin())
                .setParameter("destino", journey.getDestiny())
                .setParameter("date", date)
                .getResultList();
    }

}
