package com.portfolio.portfolio.request;

import com.portfolio.portfolio.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/*
{
    "name" : "CSC (now DXC)",
    "location" : "Delhi",
    "startDate" : "2014-09-01",
    "endDate" : "2016-05-01",
    "role" : "Associate Product Developer",
    "category" : "Professional Experience"
}
 */
//for getter and setter both
@Data
public class AddOrganizationRequest {

    private Long id;
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private String role;
    private Category category;
}
