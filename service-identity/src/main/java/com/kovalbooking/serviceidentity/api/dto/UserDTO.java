package com.kovalbooking.serviceidentity.api.dto;

import com.kovalbooking.serviceidentity.repo.model.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private long user_id;
    private String username;
    private String password;
    private UserRole user_role;
}
