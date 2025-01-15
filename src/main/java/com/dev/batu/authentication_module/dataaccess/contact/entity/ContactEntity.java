package com.dev.batu.authentication_module.dataaccess.contact.entity;

import com.dev.batu.authentication_module.dataaccess.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
@Entity
@Table(name = "contact")
public class ContactEntity {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false, name = "CELL_PHONE")
    private String phoneNumber;

    @OneToOne(mappedBy = "contact")
    private UserEntity user;
}
