package com.portfolio.portfolio.service.experience;

import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.model.Organization;
import com.portfolio.portfolio.repository.ExperienceRepository;
import com.portfolio.portfolio.service.organization.IOrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService implements IExperienceService{

    private final ExperienceRepository experienceRepository;
    private final IOrganizationService organizationService;

    @Override
    public Experience getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId)
                .orElseThrow(() -> new EntityNotFoundException("Experience not found!"));
    }

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public List<Experience> findExperienceByName(String name) {
        List<Experience> experience = experienceRepository.findByName(name);
        if(experience == null){
            throw new EntityNotFoundException("Experience with name " + name + " not found");
        }
        return experience;
    }

    @Override
    public void deleteExperienceById(Long experienceId) {
            experienceRepository.findById(experienceId)
                    .ifPresentOrElse(experienceRepository :: delete,
                            () -> {
                        throw new EntityNotFoundException("Experience not found!");
                            });
    }

    @Override
    public Experience updateExperience(Experience experience, Long experienceId) {
        return experienceRepository.findById(experienceId)
                .map(oldExperience -> {
                    oldExperience.setName(experience.getName());
                    return experienceRepository.save(oldExperience);
                }).orElseThrow(() -> new EntityNotFoundException("Experience with id " + experienceId + " not found"));
    }

    @Override
    public List<Experience> saveExperiences(Long organizationId, List<String> experienceNames) {
        Organization organization = organizationService.getOrganizationById(organizationId);
        List<Experience> savedExperiences = new ArrayList<>();
        for (String experienceName : experienceNames) {
            try{
                Experience experience = new Experience();
                experience.setName(experienceName);
                experience.setOrganization(organization);
                experienceRepository.save(experience);
                savedExperiences.add(experience);
            }
            catch(NullPointerException | IllegalArgumentException e){
                throw new RuntimeException(e.getMessage());

            }
        }
        return savedExperiences;
    }
}
