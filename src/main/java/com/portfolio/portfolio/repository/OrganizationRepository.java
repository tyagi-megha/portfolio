package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("SELECT o from Organization o WHERE LOWER(o.name) like LOWER(CONCAT('%', :name, '%'))")
    List<Organization> findByName(String name);

    List<Organization> findByCategoryName(String category);

    List<Organization> findByLocation(String location);

    List<Organization> findByCategoryNameAndLocation(String category, String location);

    List<Organization> findByLocationAndName(String location, String name);

    boolean existsByNameAndLocation(String name, String location);
}
