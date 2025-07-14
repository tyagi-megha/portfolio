package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.model.Skills;
import com.portfolio.portfolio.response.ApiResponse;
import com.portfolio.portfolio.service.experience.IExperienceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/experiences")
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping("/experience/{experienceId}/experience")
    public ResponseEntity<ApiResponse> getExperienceById(@PathVariable Long experienceId) {

            Experience experience = experienceService.getExperienceById(experienceId);
            return ResponseEntity.ok(new ApiResponse("Found experience", experience));

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllExperiences(){

            List<Experience> experiences = experienceService.getAllExperiences();
            return ResponseEntity.ok(new ApiResponse("Found experiences", experiences));

    }

    @GetMapping("/experience/{name}/experience")
    public ResponseEntity<ApiResponse> findExperienceByName(@PathVariable String name){

            List<Experience> experiences = experienceService.findExperienceByName(name);
            return ResponseEntity.ok(new ApiResponse("Found experiences", experiences));

    }

    @DeleteMapping("/experience/{experienceId}/delete")
    public ResponseEntity<ApiResponse> deleteExperienceById(@PathVariable Long experienceId){

            experienceService.deleteExperienceById(experienceId);
            return ResponseEntity.ok(new ApiResponse("Deleted experience", null));

    }

    @PutMapping("/experience/{experienceId}/update")
    public ResponseEntity<ApiResponse> updateExperience
            (@RequestBody Experience experience, @PathVariable Long experienceId){

            Experience theExperience = experienceService.updateExperience(experience, experienceId);
            return ResponseEntity.ok(new ApiResponse("Updated experience", theExperience));

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveExperiences(@RequestParam Long organizationId, @RequestParam List<String> experienceNames){

            List<Experience> experiences = experienceService.saveExperiences(organizationId, experienceNames);
            return ResponseEntity.ok(new ApiResponse("Saved experiences", experiences));

    }

}
