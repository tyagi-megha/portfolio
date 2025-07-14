package com.portfolio.portfolio.request;

import com.portfolio.portfolio.model.Category;
import lombok.Data;

@Data
public class UpdateOrganizationRequest {
    private Long id;
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private String role;
    private Category category;
}
