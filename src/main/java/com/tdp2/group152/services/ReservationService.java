package com.tdp2.group152.services;

import com.tdp2.group152.DAOs.ReservationDAO;
import com.tdp2.group152.model.Journey;
import com.tdp2.group152.model.Minibus;
import com.tdp2.group152.model.Ticket;

import java.util.Date;
import java.util.List;

public class ReservationService {

    private ReservationDAO reservationDao;

    public ReservationService(ReservationDAO reservationDao) {
        this.reservationDao = reservationDao;
    }

    //    public Ticket reserveTicket() {
//
//    }
//
    public Journey getAvailableJourneys(Journey journey, Date date) {
        List<Minibus> minibuses = this.reservationDao.getMinibusesFromJourneyAndDate(date, journey);
        for(Minibus m : minibuses) {

        }
        return new Journey();
    }

}
