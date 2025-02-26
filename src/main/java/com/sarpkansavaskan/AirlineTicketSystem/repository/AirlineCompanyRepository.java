package com.sarpkansavaskan.AirlineTicketSystem.repository;

import com.sarpkansavaskan.AirlineTicketSystem.model.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Integer> {
    Optional<AirlineCompany> findByCompanyName(String companyName);

    Optional<AirlineCompany> findByPhoneNumber(String phoneNumber);

    Optional<AirlineCompany> findById(int id);
}
