package com.itlize.joolemarketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCreateRequest {
    private Integer projectId;
    private String projectName;
    private String username;
}
