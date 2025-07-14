package com.portfolio.portfolio.dto;

import com.portfolio.portfolio.model.Category;
import com.portfolio.portfolio.model.Experience;
import com.portfolio.portfolio.model.Skills;
import lombok.Data;

import java.util.List;

@Data
public class OrganizationDto {
    private Long id;
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private String role;
    private Category category;
    private List<Experience> experiences;
    private List<Skills> skills;

}
