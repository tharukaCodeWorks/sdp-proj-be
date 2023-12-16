package org.devsdp.wscms.auth.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private String isEmailVerified;
    private String token;
}
