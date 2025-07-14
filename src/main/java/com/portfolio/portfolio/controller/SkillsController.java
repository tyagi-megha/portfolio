package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.model.Skills;
import com.portfolio.portfolio.response.ApiResponse;
import com.portfolio.portfolio.service.skills.ISkillsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/skills")
public class SkillsController {

    private final ISkillsService skillsService;

    @GetMapping("/skills/{skillsId}/skill")
    public ResponseEntity<ApiResponse> getSkillsById(@PathVariable Long skillsId) {

            Skills skills = skillsService.getSkillsById(skillsId);
            return ResponseEntity.ok(new ApiResponse("Found skills", skills));

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllSkills(){

            List<Skills> skills = skillsService.getAllSkills();
            return ResponseEntity.ok(new ApiResponse("Found skills", skills));

    }

    @GetMapping("/skills/{name}/skills")
    public ResponseEntity<ApiResponse> findSkillsByName(@PathVariable String name){

            List<Skills> skills = skillsService.findSkillsByName(name);
            return ResponseEntity.ok(new ApiResponse("Found skills", skills));

    }

    @DeleteMapping("/skills/{skillsId}/delete")
    public ResponseEntity<ApiResponse> deleteSkillsById(@PathVariable Long skillsId){

            skillsService.deleteSkillsById(skillsId);
            return ResponseEntity.ok(new ApiResponse("Deleted skills", null));

    }

    @PutMapping("/skills/{skillsId}/update")
    public ResponseEntity<ApiResponse> updateSkills(@RequestBody Skills  skills,@PathVariable Long skillsId){

            Skills skillsUpdated = skillsService.updateSkills(skills, skillsId);
            return ResponseEntity.ok(new ApiResponse("Updated skills", skillsUpdated));

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveSkills(@RequestParam Long organizationId,@RequestParam List<String> skillsNames){

            List<Skills> skills = skillsService.saveSkills(organizationId, skillsNames);
            return ResponseEntity.ok(new ApiResponse("Saved skills", skills));

    }


}
