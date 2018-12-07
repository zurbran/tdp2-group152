package com.tdp2.group152.security;

import com.tdp2.group152.models.Passenger;
import com.tdp2.group152.services.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.AuthenticationException;

public abstract class SecuredController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecuredController.class);

    public void authenticateRequest(Long passsengerId, String token, PassengerService passengerService) throws AuthenticationException {
        Passenger passenger = passengerService.getPassengerById(passsengerId);
        if(passenger == null) {
            LOGGER.warn("[AUTHENTICATION] User sent wrong id for authentication");
            throw new AuthenticationException("Wrong User Id");
        }
        if(!passengerService.authenticatePassenger(passenger.getEmail(), token)) {
            LOGGER.warn("[AUTHENTICATION] User sent wrong token for authentication");
            throw new AuthenticationException("Wrong Token");
        }
    }

    public void authenticateRequestForTerminal(Long passsengerId, String token, PassengerService passengerService) throws AuthenticationException {
        Passenger passenger = passengerService.getPassengerById(passsengerId);
        if(passenger == null) {
            LOGGER.warn("[AUTHENTICATION] User sent wrong id for authentication");
            throw new AuthenticationException("Wrong User Id");
        }
        if(!passenger.isTerminal()) {
            LOGGER.warn("[AUTHENTICATION] User trying to authenticate as a terminal");
            throw new AuthenticationException("Not a terminal");
        }
        if(!passengerService.authenticatePassenger(passenger.getEmail(), token)) {
            LOGGER.warn("[AUTHENTICATION] User sent wrong token for authentication");
            throw new AuthenticationException("Wrong Token");
        }
    }
}
