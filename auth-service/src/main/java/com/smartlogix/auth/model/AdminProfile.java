package com.smartlogix.auth.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminProfile extends Profile {
    @Override
    public String getPermissions() { 
        return "FULL_ACCESS"; 
    }
}