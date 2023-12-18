package org.devsdp.wscms.complaint.dto.responses;

import lombok.*;
import org.devsdp.wscms.complaint.enums.ComplaintPriority;
import org.devsdp.wscms.complaint.model.Division;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintCreateResponseDto {
   long complaintId;
   String complaintDesc;
   String complaintTitle;
   String divisionName;
   long divisionId;
   ComplaintPriority priority;
}
