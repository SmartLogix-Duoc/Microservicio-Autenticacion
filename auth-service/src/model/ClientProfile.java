package com.smartlogix.auth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CLIENT")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientProfile extends Profile {

    @Override
    public String getPermissions() { 
        return "READ_ONLY"; 
    }
}