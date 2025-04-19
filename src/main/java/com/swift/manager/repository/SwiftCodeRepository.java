package com.swift.manager.repository;

import com.swift.manager.entity.SwiftCode;
import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SwiftCodeRepository extends JpaRepository<SwiftCode, String> {
    @Query("SELECT s FROM SwiftCode s WHERE s.headquarterCode = :headquarterCode AND s.headquarter = false")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    List<SwiftCode> findBranchesByHeadquarter(String headquarterCode);

    @EntityGraph(attributePaths = {"countryISO2"})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    List<SwiftCode> findByCountryISO2(String countryISO2);

    Optional<SwiftCode> findBySwiftCode(String swiftCode);
}