package com.tdp2.group152;

import javax.persistence.*;

@Entity
@Table(name = "pasajero")
public class Passenger {
    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    @Column(name = "id_pasajero")
    private int idPassenger;

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
}
