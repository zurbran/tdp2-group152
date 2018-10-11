package com.tdp2.group152;

import javax.persistence.*;

@Entity
@Table(name = "pasajero")
public class Passenger {
    @Id @GeneratedValue
    @Column(name = "id_pasajero")
    private int idPassenger;
    private String dni;
    private String name;
    private String surname;
    private String email;

    public Passenger() {
    }


    public Passenger(int idPassenger, String dni, String name, String surname, String email) {
        this.idPassenger = idPassenger;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
