package com.sarpkansavaskan.AirlineTicketSystem.model;

import com.sarpkansavaskan.AirlineTicketSystem.model.enums.TicketEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight_ticket")
public class FlightTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "national_id")
    private long passengerNationalId;

    @Column(name = "passenger_first_name")
    private String passengerFirstName;

    @Column(name = "passenger_last_name")
    private String passengerLastName;

    @Column(name = "ticket_number")
    private long ticketNumber;

    @Column(name = "credit_card")
    private String creditCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_flight_id", referencedColumnName = "id")
    private AirlineFlight airlineFlight;

    @Column(name = "is_active")
    private TicketEnum isActive = TicketEnum.ACTIVE;

    public FlightTicket(long passengerNationalId, String passengerFirstName, String passengerLastName, long ticketNumber, String creditCard, AirlineFlight airlineFlight) {
        this.passengerNationalId = passengerNationalId;
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.ticketNumber = ticketNumber;
        this.creditCard = creditCard;
        this.airlineFlight = airlineFlight;
    }


}
