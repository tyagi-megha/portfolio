package com.portfolio.portfolio.service.skills;

import com.portfolio.portfolio.model.Category;
import com.portfolio.portfolio.model.Organization;
import com.portfolio.portfolio.model.Skills;
import com.portfolio.portfolio.repository.SkillsRepository;
import com.portfolio.portfolio.service.organization.IOrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillsService implements ISkillsService{

    private final SkillsRepository skillsRepository;
    private final IOrganizationService organizationService;

    @Override
    public Skills getSkillsById(Long skillsId) {
        return skillsRepository.findById(skillsId)
                .orElseThrow(() ->
                     new EntityNotFoundException("Skills with id " + skillsId + " not found"));
    }

    @Override
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

    @Override
    public List<Skills> findSkillsByName(String name) {
        return skillsRepository.findByName(name);
    }

    @Override
    public void deleteSkillsById(Long skillsId) {
            skillsRepository.findById(skillsId)
                    .ifPresentOrElse(skillsRepository :: delete,
                            () -> {
                        throw new EntityNotFoundException("Skills with id " + skillsId + " not found");
                            });
    }

    @Override
    public Skills updateSkills(Skills  skills, Long skillsId) {
        return Optional.ofNullable(getSkillsById(skillsId))
                .map(oldSkills -> {
                    oldSkills.setName(skills.getName());
                    return skillsRepository.save(oldSkills);
                }).orElseThrow(() -> new EntityNotFoundException("Skills not found"));
    }

    @Override
    public List<Skills> saveSkills(Long organizationId, List<String> skillsNames) {
        Organization organization = organizationService.getOrganizationById(organizationId);
        List<Skills> skillsList = new ArrayList<>();

        for (String skillName : skillsNames) {
            try{
                Skills skills = new Skills();
                skills.setName(skillName);
                skills.setOrganization(organization);
                skillsRepository.save(skills);
                skillsList.add(skills);
            }
            catch(NullPointerException | IllegalArgumentException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return skillsList;
    }
}
