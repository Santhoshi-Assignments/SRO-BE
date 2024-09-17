package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Capability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long capabilityId;
    private String capabilityName;
    private String CapabilityDescription;
    private String capabilityType;

}
