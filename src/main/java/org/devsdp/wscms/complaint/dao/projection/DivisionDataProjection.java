package org.devsdp.wscms.complaint.dto.responses;

import lombok.*;


public interface DivisionResponseDto {
         long getId();
         String getDivisionName();
         String getProvince();
         long getDepartmentId();
         String getDepartmentName();
}
