package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByOrganizationId(Long organizationId);

    //because it may be possible user just enter substring of experience name hence wrote this query
    @Query("SELECT e FROM  Experience e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Experience> findByName(String name);
}
