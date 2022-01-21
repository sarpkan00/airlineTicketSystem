package com.sarpkansavaskan.AirlineTicketSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight_route")
public class FlightRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "froms")
    private String from;

    @Column(name = "tos")
    private String to;

    @OneToMany(mappedBy = "flightRoute", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AirlineFlight> airlineFlights;

    public FlightRoute(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
