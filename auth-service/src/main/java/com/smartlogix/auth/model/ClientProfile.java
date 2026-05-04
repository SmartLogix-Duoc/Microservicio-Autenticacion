package com.smartlogix.auth.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
public class ClientProfile extends Profile {
    @Override
    public String getPermissions() { 
        return "READ_ONLY"; 
    }
}