package com.smartlogix.auth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ADMIN")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminProfile extends Profile {

    @Override
    public String getPermissions() { 
        return "FULL_ACCESS"; 
    }
}