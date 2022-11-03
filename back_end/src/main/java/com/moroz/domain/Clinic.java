package com.moroz.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Clinic {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "clinic_phone")
    private String clinicPhone;
    @Basic
    @Column(name = "street_adress")
    private String streetAdress;
    @ManyToOne
    @JoinColumn(name = "city_name", referencedColumnName = "name")
    private City city;
    @ManyToMany
    @JoinTable(name = "clinic_doctor", schema = "new_library", joinColumns = @JoinColumn(name = "clinic_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false))
    private Set<Doctor> doctors;
    @ManyToMany
    @JoinTable(name = "clinic_client", schema = "new_library",
            joinColumns = @JoinColumn(name = "clinic_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false))
    private Set<Client> clients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClinicPhone() {
        return clinicPhone;
    }

    public void setClinicPhone(String clinicPhone) {
        this.clinicPhone = clinicPhone;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clinic clinic = (Clinic) o;
        return Objects.equals(id, clinic.id) && Objects.equals(name, clinic.name) && Objects.equals(clinicPhone, clinic.clinicPhone) && Objects.equals(streetAdress, clinic.streetAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, clinicPhone, streetAdress);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
