package com.sarpkansavaskan.AirlineTicketSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airline_flight")
public class AirlineFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "airline_flight_number")
    private int airlineFlightNumber;

    @Column(name = "passengers")
    private int passengers;

    @Column(name = "price")
    private double price;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_company_id", referencedColumnName = "id")
    private AirlineCompany airlineCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_route_id", referencedColumnName = "id")
    private FlightRoute flightRoute;

    @OneToMany(mappedBy = "airlineFlight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FlightTicket> flightTicket;

    public AirlineFlight(int airlineFlightNumber, int passengers, double price, Date date) {
        this.airlineFlightNumber = airlineFlightNumber;
        this.passengers = passengers;
        this.price = price;
        this.date = date;
    }

    public AirlineFlight(int airlineFlightNumber, int passengers, double price, Date date, AirlineCompany airlineCompany, FlightRoute flightRoute) {
        this.airlineFlightNumber = airlineFlightNumber;
        this.passengers = passengers;
        this.price = price;
        this.date = date;
        this.airlineCompany = airlineCompany;
        this.flightRoute = flightRoute;
    }
}