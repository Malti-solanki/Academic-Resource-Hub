package com.info.academic_resource_hub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDTO {

    private String name;
    private String email;
    private String password;

}
