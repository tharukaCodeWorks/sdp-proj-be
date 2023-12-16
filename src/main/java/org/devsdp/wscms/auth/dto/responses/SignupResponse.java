package org.devsdp.wscms.auth.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupResponse {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
}
