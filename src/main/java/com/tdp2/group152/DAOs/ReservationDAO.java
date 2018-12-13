package com.tdp2.group152.DAOs;

import com.tdp2.group152.models.Journey;
import com.tdp2.group152.models.Minibus;
import com.tdp2.group152.models.MinibusStop;
import com.tdp2.group152.models.Ticket;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.LocalTime;
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
        return (Ticket) this.sessionFactory.getCurrentSession().createQuery("FROM Ticket t WHERE t.ticketId = :ticketId")
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

    public List<Journey> getJourneysForDate(Date date) {
        return (List<Journey>) this.sessionFactory.getCurrentSession().createQuery("FROM Journey j WHERE year(j.departureTime) = year(:date) AND month(j.departureTime) = month(:date) AND day(j.departureTime) = day(:date)")
                .setParameter("date", date)
                .getResultList();
    }

    public List<Journey> getJourneysForDate(String from, String to, LocalDate date) {
        return (List<Journey>) this.sessionFactory.getCurrentSession().createQuery("FROM Journey j WHERE j.destiny = :destiny AND j.origin = :origin AND year(j.departureTime) = year(:date) AND month(j.departureTime) = month(:date) AND day(j.departureTime) = day(:date)")
                .setParameter("date", date)
                .setParameter("destiny", to)
                .setParameter("origin", from)
                .getResultList();
    }

    public LocalTime getPickUpTime(Journey journey, MinibusStop stop) {
        return (LocalTime) this.sessionFactory.getCurrentSession().createQuery("SELECT pickUpTime FROM ViajeHasParada vhp WHERE journey.journeyId = :journeyId AND stop.stopId = :stopId")
                .setParameter("journeyId", journey.getJourneyId())
                .setParameter("stopId", stop.getStopId())
                .uniqueResult();
    }

    public void saveOrUpdate(Ticket ticket) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(ticket);
    }
}
