package com.moroz.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Patient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "breed")
    private String breed;
    @Basic
    @Column(name = "health_complains")
    private String healthComplains;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getHealthComplains() {
        return healthComplains;
    }

    public void setHealthComplains(String healthComplains) {
        this.healthComplains = healthComplains;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(breed, patient.breed) && Objects.equals(healthComplains, patient.healthComplains) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, healthComplains);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
