package com.anurag.backend.repository;

import com.anurag.backend.entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

    List<Surgery> findByCityIgnoreCase(String city);

    List<Surgery> findBySurgeryTypeIgnoreCase(String surgeryType);

}