package com.info.academic_resource_hub.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateDTO {

    private String name;
    private String email;
    private String password;

}
