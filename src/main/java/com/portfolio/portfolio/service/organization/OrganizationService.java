package com.portfolio.portfolio.service.organization;

import com.portfolio.portfolio.dto.OrganizationDto;
import com.portfolio.portfolio.model.Category;
import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.model.Organization;
import com.portfolio.portfolio.model.Skills;
import com.portfolio.portfolio.repository.CategoryRepository;
import com.portfolio.portfolio.repository.ExperienceRepository;
import com.portfolio.portfolio.repository.OrganizationRepository;
import com.portfolio.portfolio.repository.SkillsRepository;
import com.portfolio.portfolio.request.AddOrganizationRequest;
import com.portfolio.portfolio.request.UpdateOrganizationRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
    private final OrganizationRepository organizationRepository;
    private final CategoryRepository categoryRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillsRepository skillsRepository;
    private final ModelMapper modelMapper;

    @Override
    public Organization addOrganizations(AddOrganizationRequest request) {
        if(organizationExists(request.getName(), request.getLocation())){
            throw new EntityExistsException("Organization with name " + request.getName() + " already exists");
        }
        //else need to use CategoryRepository to check if category alredy exists, if does then simply add product to it
        //else add category as new category in category table
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);

        return organizationRepository.save(createOrganization(request, category));
    }

    //before adding should check if product already exists by name and location
    private boolean organizationExists(String name, String location) {
        return organizationRepository.existsByNameAndLocation(name, location);
    }

    //help us to save attributes for new organization.AddOrganizationRequest is a DTO to specify attributes we're going to take from
    //Product entity
    private Organization createOrganization(AddOrganizationRequest request, Category category ) {
        return new Organization(request.getName(),
                request.getLocation(),
                request.getStartDate(),
                request.getEndDate(),
                request.getRole(),
                category);
    }


    @Override
    public Organization updateOrganization(UpdateOrganizationRequest request, Long organizationId) {
        return organizationRepository.findById(organizationId)
                .map(existingOrganization -> updateExistingOrganization(existingOrganization, request))
                .map(organizationRepository :: save)
                .orElseThrow(() -> new EntityNotFoundException("Organization with id " + organizationId + " not found"));
    }

    private Organization updateExistingOrganization(Organization existingOrganization, UpdateOrganizationRequest request) {
        existingOrganization.setName(request.getName());
        existingOrganization.setLocation(request.getLocation());
        existingOrganization.setStartDate(request.getStartDate());
        existingOrganization.setEndDate(request.getEndDate());
        existingOrganization.setRole(request.getRole());
        //makes sure category sent for update is already in db
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingOrganization.setCategory(category);
        return existingOrganization;
    }

    @Override
    public void deleteOrganizationById(Long organizationId) {
        organizationRepository.findById(organizationId).
        ifPresentOrElse( organization -> {
            List<Experience> experiences = experienceRepository.findByOrganizationId(organizationId);
            experiences.forEach(experience -> {
                experienceRepository.deleteById(experience.getId());
            });

            List<Skills> skills = skillsRepository.findByOrganizationId(organizationId);
            skills.forEach(skill -> {
                skillsRepository.deleteById(skill.getId());
            });

            Optional.ofNullable(organization.getCategory()).
                    ifPresent(category -> {
                        category.getOrganizations().remove(organization);
                    });
            organization.setCategory(null);

            organizationRepository.deleteById(organization.getId());
            }, () -> {
            throw new EntityNotFoundException("Organization with id " + organizationId + " not found");
            });
    }

    @Override
    public Organization getOrganizationById(Long organizationId) {
        return organizationRepository.findById(organizationId).orElseThrow(
                () -> new EntityNotFoundException("Organization not found")
        );
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public List<Organization> getOrganizationsByName(String name) {
        return organizationRepository.findByName(name);
    }

    @Override
    public List<Organization> getOrganizationsByCategory(String category) {
        return organizationRepository.findByCategoryName(category);
    }

    @Override
    public List<Organization> getOrganizationsByLocation(String location) {
        return organizationRepository.findByLocation(location);
    }

    @Override
    public List<Organization> getOrganizationsByCategoryAndLocation(String category, String location) {
        return organizationRepository.findByCategoryNameAndLocation(category, location);
    }

    @Override
    public List<Organization> getOrganizationsByLocationAndName(String location, String name) {
        return organizationRepository.findByLocationAndName(location, name);
    }

    @Override
    public List<OrganizationDto> convertedOrganizations(List<Organization> organizations) {
        return organizations.stream().map(this::convertToDto).toList();
    }

    @Override
    public OrganizationDto convertToDto(Organization organization) {
        return modelMapper.map(organization, OrganizationDto.class);
    }
}
