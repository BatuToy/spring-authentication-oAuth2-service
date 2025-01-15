package com.dev.batu.authentication_module.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class Contact {
    @NotNull
    @NotBlank
    @Size(min= 10, max = 10, message = "Phone number format: (XXX) XXX XX XX")
    private final String phoneNumber;
}
