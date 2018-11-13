package com.tdp2.group152.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pasajero")
public class Passenger {
    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    @Column(name = "id_pasajero")
    private Long passengerId;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "salt")
    private String salt;

    @Column(name = "password_hash")
    private String passwordHash;

    @OneToMany(mappedBy = "ticketId", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    List<Ticket> tickets;

    public Passenger() {
        tickets = new ArrayList<>();
    }


    public Passenger(Long passengerId, String dni, String name, String surname, String email) {
        this.passengerId = passengerId;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        tickets = new ArrayList<>();
    }


    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
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
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}