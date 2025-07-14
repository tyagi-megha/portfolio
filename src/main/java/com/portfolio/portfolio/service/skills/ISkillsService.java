package com.portfolio.portfolio.service.skills;

import com.portfolio.portfolio.model.Skills;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISkillsService {

    Skills getSkillsById(Long skillsId);
    List<Skills> getAllSkills();
    List<Skills> findSkillsByName(String name);
    void deleteSkillsById(Long skillsId);
    Skills updateSkills(Skills skills, Long skillsId);
    List<Skills> saveSkills(Long organizationId, List<String> skillsNames);
}
