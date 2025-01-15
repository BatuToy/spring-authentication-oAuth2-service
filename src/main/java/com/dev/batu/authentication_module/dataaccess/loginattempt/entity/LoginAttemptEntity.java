package com.dev.batu.authentication_module.dataaccess.loginattempt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attempt")
public class LoginAttemptEntity {
    @Id
    private UUID id;

    private ZonedDateTime attemptedAt;

    private boolean isSuccess;
}
