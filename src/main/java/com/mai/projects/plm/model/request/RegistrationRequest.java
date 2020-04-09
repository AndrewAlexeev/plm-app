package com.mai.projects.plm.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegistrationRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
