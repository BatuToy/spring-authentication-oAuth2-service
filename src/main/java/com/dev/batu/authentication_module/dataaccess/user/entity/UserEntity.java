package com.dev.batu.authentication_module.dataaccess.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth")
public class UserEntity {
    @Id
    @Column(nullable = false, unique = true)
    private UUID id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    @Column(insertable = false)
    private ZonedDateTime updatedAt;

}
