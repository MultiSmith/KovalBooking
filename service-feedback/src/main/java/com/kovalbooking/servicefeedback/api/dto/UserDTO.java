package com.kovalbooking.servicefeedback.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private long user_id;
    private String username;
    private String password;
    private String user_role;
}
