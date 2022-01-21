package com.sarpkansavaskan.AirlineTicketSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAirlineCompanyRequest {
    private String companyName;
    private String phoneNumber;
}
