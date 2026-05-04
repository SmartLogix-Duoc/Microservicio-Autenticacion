package com.smartlogix.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "profile_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id") // Esta es la columna física (FK) que unirá ambas tablas
    private User user;

    public abstract String getPermissions();
}

@Entity
@DiscriminatorValue("ADMIN")
public class AdminProfile extends Profile {
    @Override
    public String getPermissions() { return "FULL_ACCESS"; }
}

@Entity
@DiscriminatorValue("CLIENT")
public class ClientProfile extends Profile {
    @Override
    public String getPermissions() { return "READ_ONLY"; }
}