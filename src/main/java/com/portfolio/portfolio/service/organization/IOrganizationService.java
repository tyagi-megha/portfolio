package com.portfolio.portfolio.service.organization;

import com.portfolio.portfolio.dto.OrganizationDto;
import com.portfolio.portfolio.model.Organization;
import com.portfolio.portfolio.request.AddOrganizationRequest;
import com.portfolio.portfolio.request.UpdateOrganizationRequest;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrganizationService {

    Organization addOrganizations(AddOrganizationRequest request);
    Organization updateOrganization(UpdateOrganizationRequest request, Long organizationId);
    Organization getOrganizationById(Long organizationId);
    void deleteOrganizationById(Long organizationId);

    List<Organization> getAllOrganizations();
    List<Organization> getOrganizationsByName(String name);
    List<Organization> getOrganizationsByCategory(String category);
    List<Organization> getOrganizationsByLocation(String location);
    List<Organization> getOrganizationsByCategoryAndLocation(String category, String location);
    List<Organization> getOrganizationsByLocationAndName(String location, String name);

    List<OrganizationDto> convertedOrganizations(List<Organization> organizations);

    OrganizationDto convertToDto(Organization organization);
}
