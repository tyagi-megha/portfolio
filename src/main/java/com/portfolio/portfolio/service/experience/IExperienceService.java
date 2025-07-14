package com.portfolio.portfolio.service.experience;

import com.portfolio.portfolio.model.Experience;

import java.util.List;

public interface IExperienceService {
    Experience getExperienceById(Long experienceId);
    List<Experience> getAllExperiences();
    List<Experience> findExperienceByName(String name);
    void deleteExperienceById(Long experienceId);
    Experience updateExperience(Experience experience, Long experienceId);
    List<Experience> saveExperiences(Long organizationId, List<String> experienceNames);
}
