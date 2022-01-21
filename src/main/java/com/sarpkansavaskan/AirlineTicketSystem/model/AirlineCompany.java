package com.sarpkansavaskan.AirlineTicketSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "airline_company")
public class AirlineCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "airlineCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AirlineFlight> airlineFlights = new ArrayList<>();

    public AirlineCompany(String companyName, String phoneNumber) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public AirlineCompany(String companyName, String phoneNumber, List<AirlineFlight> airlineFlights) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.airlineFlights = airlineFlights;
    }
}
