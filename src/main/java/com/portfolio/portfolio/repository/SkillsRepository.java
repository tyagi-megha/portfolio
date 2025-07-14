package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    List<Skills> findByOrganizationId(Long organizationId);

    @Query("SELECT s from Skills s WHERE LOWER(s.name) like LOWER(CONCAT('%', :name, '%'))")
    List<Skills> findByName(String name);
}
