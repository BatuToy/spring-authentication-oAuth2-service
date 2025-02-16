package com.dev.batu.authentication_module.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@Getter
@Builder
public class Contact {
    @NotNull
    @NotBlank
    @Size(min= 10, max = 10, message = "Phone number format: (XXX) XXX XX XX")
    private final String phoneNumber;
}
