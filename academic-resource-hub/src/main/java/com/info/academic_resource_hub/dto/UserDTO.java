package com.info.academic_resource_hub.dto;

import com.info.academic_resource_hub.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Role role;

}
