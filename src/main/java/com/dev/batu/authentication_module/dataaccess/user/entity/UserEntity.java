package com.dev.batu.authentication_module.dataaccess.user.entity;

import com.dev.batu.authentication_module.dataaccess.contact.entity.ContactEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "/user/")
public class UserEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RoleEntity> authorities;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private ContactEntity contact;

    @CreationTimestamp
    @Column(updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private ZonedDateTime updatedAt;
}
