package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
public class TechTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int techTeamId;
    private String techTeamName;
    private String techTeamTPM;
    private String techTeamManager;
    private String techTeamDescription;

}
