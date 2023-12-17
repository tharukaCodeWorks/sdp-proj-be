package org.devsdp.wscms.complaint.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ComplaintDto {

    private int divisionId;
    private String title;
    private String description;
    private int priority;
    private List<String> imgPathList;


}
