package com.mk.salesAgent.repository;

import com.mk.salesAgent.entity.SalesRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesRegionRepository extends JpaRepository<SalesRegion, Long> {

    Optional<SalesRegion> findByName(String name);
}
