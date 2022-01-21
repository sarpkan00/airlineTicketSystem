package com.sarpkansavaskan.AirlineTicketSystem.dto.request;

import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineCompany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAirlineCompanyRequest {
   private String companyName;
   private String phoneNumber;
}
