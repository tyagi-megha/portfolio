package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.dto.OrganizationDto;
import com.portfolio.portfolio.model.Organization;
import com.portfolio.portfolio.request.AddOrganizationRequest;
import com.portfolio.portfolio.request.UpdateOrganizationRequest;
import com.portfolio.portfolio.response.ApiResponse;
import com.portfolio.portfolio.service.organization.IOrganizationService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/organizations")
public class OrganizationController {

    private final IOrganizationService organizationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addOrganizations(@RequestBody AddOrganizationRequest request){

            Organization organization = organizationService.addOrganizations(request);
            OrganizationDto organizationDto = organizationService.convertToDto(organization);
            return ResponseEntity.ok(new ApiResponse("Organization added successfully", organizationDto));

    }

    @PutMapping("/organization/{organizationId}/update")
    public ResponseEntity<ApiResponse> updateOrganization(
            @RequestBody UpdateOrganizationRequest request,@PathVariable Long organizationId){

            Organization organization = organizationService.updateOrganization(request, organizationId);
            OrganizationDto organizationDto = organizationService.convertToDto(organization);
            return ResponseEntity.ok(new ApiResponse("Organization updated successfully", organizationDto));

    }

    @DeleteMapping("/organization/{organizationId}/delete")
    public ResponseEntity<ApiResponse> deleteOrganizationById(@PathVariable Long organizationId){

            organizationService.deleteOrganizationById(organizationId);
            return ResponseEntity.ok(new ApiResponse("Organization with id " + organizationId + " deleted successfully", null));

    }

    @GetMapping("/organization/{organizationId}/organization")
    public ResponseEntity<ApiResponse> getOrganizationById(@PathVariable Long organizationId){

            Organization organization = organizationService.getOrganizationById(organizationId);
            OrganizationDto organizationDto = organizationService.convertToDto(organization);
            return ResponseEntity.ok(new ApiResponse("Organization with id " + organizationId + " found", organizationDto));

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrganizations(){

            List<Organization> organizations = organizationService.getAllOrganizations();
            List<OrganizationDto> organizationDtos = organizationService.convertedOrganizations(organizations);
            return ResponseEntity.ok(new ApiResponse("Organizations", organizationDtos));

    }

    @GetMapping("/organization/{name}/organizationByName")
    public ResponseEntity<ApiResponse> getOrganizationsByName(@PathVariable String name){

            List<Organization> organizations = organizationService.getOrganizationsByName(name);
            List<OrganizationDto> organizationDtos = organizationService.convertedOrganizations(organizations);
            return ResponseEntity.ok(new ApiResponse("Organizations", organizationDtos));

    }

    @GetMapping("/organization/{category}/organizationByCategory")
    public ResponseEntity<ApiResponse>  getOrganizationsByCategory(@PathVariable String category){

            List<Organization> organizations = organizationService.getOrganizationsByCategory(category);
            List<OrganizationDto> organizationDtos = organizationService.convertedOrganizations(organizations);
            return ResponseEntity.ok(new ApiResponse("Organizations", organizationDtos));

    }

    @GetMapping("/organization/organizationByCategoryAndLocation")
    public ResponseEntity<ApiResponse> getOrganizationsByCategoryAndLocation(@RequestParam String category,@RequestParam String location) {

            List<Organization> organizations = organizationService.getOrganizationsByCategoryAndLocation(category, location);
            List<OrganizationDto> organizationDtos = organizationService.convertedOrganizations(organizations);
            return ResponseEntity.ok(new ApiResponse("Organizations", organizationDtos));

    }

    @GetMapping("/organization/organizationByLocationAndName")
    public ResponseEntity<ApiResponse> getOrganizationsByLocationAndName(@RequestParam String location, @RequestParam String name) {

            List<Organization> organizations = organizationService.getOrganizationsByLocationAndName( location, name);
            List<OrganizationDto> organizationDtos = organizationService.convertedOrganizations(organizations);
            return ResponseEntity.ok(new ApiResponse("Organizations", organizationDtos));

    }
}
