package com.tdp2.group152.DTOs;

import com.tdp2.group152.models.Passenger;

public class PassengerDTO {
    private String name;
    private String lastName;
    private String dni;

    public PassengerDTO() {}

    public PassengerDTO(Passenger person) {
        this.name = person.getName();
        this.lastName = person.getSurname();
        this.dni = person.getDni();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
